package Model;

import java.util.Date;

public class Comment {
    private User commenter;
    private long commentedContentID;
    private String commentContent;
    private Date commentDate;

    public Comment (User commenter , long commentedContentID , String commentContent){
        this.commenter = commenter;
        this.commentedContentID = commentedContentID;
        this.commentContent = commentContent;
        this.commentDate = new Date();
    }

    public User getCommenter() { return commenter; }
    public void setCommenter(User commenter) { this.commenter = commenter; }

    public long getCommentedContentID() { return commentedContentID; }
    public void setCommentedContentID(long commentedContentID) { this.commentedContentID = commentedContentID; }

    public String getCommentContent() { return commentContent; }
    public void setCommentContent(String commentContent) { this.commentContent = commentContent; }

    public Date getCommentDate() { return commentDate; }
}
