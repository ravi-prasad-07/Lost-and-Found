import java.sql.*;

/**
 * DBConnection.java
 * Shriyansh :  DB + Integration
 */
public class DBConnection {

 
    private static final String URL = "jdbc:sqlite:lnf.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initDB() {
        String createUsers = """
            CREATE TABLE IF NOT EXISTS users (
                id       INTEGER PRIMARY KEY AUTOINCREMENT,
                name     TEXT NOT NULL,
                email    TEXT NOT NULL UNIQUE,
                password TEXT NOT NULL,
                role     TEXT DEFAULT 'student'
            );
            """;

        String createItems = """
            CREATE TABLE IF NOT EXISTS items (
                id          INTEGER PRIMARY KEY AUTOINCREMENT,
                title       TEXT NOT NULL,
                description TEXT,
                category    TEXT,
                status      TEXT NOT NULL,
                location    TEXT,
                reported_by INTEGER,
                report_date TEXT,
                is_resolved INTEGER DEFAULT 0
            );
            """;

        String seedAdmin = """
            INSERT OR IGNORE INTO users (name, email, password, role)
            VALUES ('Admin', 'admin@gehu', 'admin123', 'admin');
            """;

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(createUsers);
            stmt.execute(createItems);
            stmt.execute(seedAdmin);
            System.out.println("[DB] SQLite ready.");
        } catch (SQLException e) {
            System.err.println("[DB] Init error: " + e.getMessage());
        }
    }
}
