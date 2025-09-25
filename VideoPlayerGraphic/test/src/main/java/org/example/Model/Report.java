package org.example.Model;

public class Report {
    private User reporter;
    private long reportedContentID;
    private long reportedUserID;
    private String description;

    public Report (User reporter , long reportedContentID , long reportedUserID , String description){
        this.reporter = reporter;
        this.reportedContentID = reportedContentID;
        this.reportedUserID = reportedUserID;
        this.description = description;
    }

    public User getReporter() { return reporter; }
    public void setReporter(User reporter) { this.reporter = reporter; }

    public long getReportedContentID() { return reportedContentID; }
    public void setReportedContentID(long reportedContentID) { this.reportedContentID = reportedContentID; }

    public long getReportedUserID() { return reportedUserID; }
    public void setReportedUserID(long reportedUserID) { this.reportedUserID = reportedUserID; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
