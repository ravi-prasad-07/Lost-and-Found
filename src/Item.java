/*
 Sanjay : Java Items
 */
public class Item {

    private int id;
    private String title;
    private String description;
    private String category;
    private String status;     
    private String location;
    private int reportedBy;
    private String reportDate;
    private boolean isResolved;

    public Item(int id, String title, String description, String category,
                String status, String location, int reportedBy,
                String reportDate, boolean isResolved) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.status = status;
        this.location = location;
        this.reportedBy = reportedBy;
        this.reportDate = reportDate;
        this.isResolved = isResolved;
    }

    public int getId()           { return id; }
    public String getTitle()     { return title; }
    public String getDescription() { return description; }
    public String getCategory()  { return category; }
    public String getStatus()    { return status; }
    public String getLocation()  { return location; }
    public int getReportedBy()   { return reportedBy; }
    public String getReportDate() { return reportDate; }
    public boolean isResolved()  { return isResolved; }
}
