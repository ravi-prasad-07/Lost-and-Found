import java.io.*;
import java.util.List;

/**
 * JsonExporter.java
 * Shriyansh :  DB + Integration
 */
public class JsonExporter {

    private static final String OUTPUT_PATH = "../frontend/data.json";

    public static void export() {
        ItemDAO dao = new ItemDAO();
        List<Item> items = dao.getAllItems();

        StringBuilder json = new StringBuilder();
        json.append("[\n");

        for (int i = 0; i < items.size(); i++) {
            Item it = items.get(i);
            json.append("  {\n");
            json.append("    \"id\": ").append(it.getId()).append(",\n");
            json.append("    \"title\": \"").append(escape(it.getTitle())).append("\",\n");
            json.append("    \"description\": \"").append(escape(it.getDescription())).append("\",\n");
            json.append("    \"category\": \"").append(escape(it.getCategory())).append("\",\n");
            json.append("    \"status\": \"").append(escape(it.getStatus())).append("\",\n");
            json.append("    \"location\": \"").append(escape(it.getLocation())).append("\",\n");
            json.append("    \"report_date\": \"").append(escape(it.getReportDate())).append("\"\n");
            json.append("  }");
            if (i < items.size() - 1) json.append(",");
            json.append("\n");
        }

        json.append("]");

        try (PrintWriter pw = new PrintWriter(new FileWriter(OUTPUT_PATH))) {
            pw.print(json.toString());
            System.out.println("[Export] data.json updated (" + items.size() + " items).");
        } catch (IOException e) {
            System.err.println("[Export] Failed to write data.json: " + e.getMessage());
        }
    }

    private static String escape(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
