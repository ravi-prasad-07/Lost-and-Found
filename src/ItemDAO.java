import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ItemDAO.java
 * Sanjay : Java Items
 */
public class ItemDAO {

    /** Add a new lost/found report */
    public boolean addItem(Item item) {
        String sql = "INSERT INTO items (title, description, category, status, location, reported_by, report_date) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, item.getTitle());
            ps.setString(2, item.getDescription());
            ps.setString(3, item.getCategory());
            ps.setString(4, item.getStatus());
            ps.setString(5, item.getLocation());
            ps.setInt(6, item.getReportedBy());
            ps.setString(7, item.getReportDate());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Add item error: " + e.getMessage());
        }
        return false;
    }

    /** Fetch all unresolved items */
    public List<Item> getAllItems() {
        List<Item> list = new ArrayList<>();
        String sql = "SELECT * FROM items WHERE is_resolved = 0 ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Item(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("category"),
                    rs.getString("status"),
                    rs.getString("location"),
                    rs.getInt("reported_by"),
                    rs.getString("report_date"),
                    rs.getInt("is_resolved") == 1
                ));
            }
        } catch (SQLException e) {
            System.err.println("Fetch items error: " + e.getMessage());
        }
        return list;
    }

    /** Mark item as resolved (Admin) */
    public boolean resolveItem(int id) {
        String sql = "UPDATE items SET is_resolved = 1 WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Resolve error: " + e.getMessage());
        }
        return false;
    }

    /** Delete item (Admin) */
    public boolean deleteItem(int id) {
        String sql = "DELETE FROM items WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Delete error: " + e.getMessage());
        }
        return false;
    }

}
