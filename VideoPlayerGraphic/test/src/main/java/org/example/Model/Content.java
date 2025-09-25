package org.example.Model;

import org.example.Controller.ContentController;

import java.util.ArrayList;
import java.util.Date;

public abstract class Content {
    private long contentID;
    private long creatorID;
    private String contentTitle;
    private boolean isExclusive;
    private String description;
    private long duration;
    private long views = 0;
    private long likes = 0;
    private Date releaseDate;
    private Category category;
    private String contentLink;
    private String cover;
    private ArrayList<Comment> comments;

    public Content(long creatorID , String contentTitle , boolean isExclusive , String description , long duration ,
                   Category category , String contentLink , String cover) {
        this.contentID = ContentController.createContentID();
        this.creatorID = creatorID;
        this.contentTitle = contentTitle;
        this.isExclusive = isExclusive;
        this.description = description;
        this.duration = duration;
        this.releaseDate = new Date();
        this.category = category;
        this.contentLink = contentLink;
        this.cover = cover;
        this.comments = new ArrayList<>();
    }

    public long getContentID() { return contentID; }

    public long getCreatorID() { return creatorID; }
    public void setCreatorID(long creatorID) { this.creatorID = creatorID; }

    public String getContentTitle() { return contentTitle; }
    public void setContentTitle(String contentTitle) { this.contentTitle = contentTitle; }

    public boolean isExclusive() { return isExclusive; }
    public void setExclusive(boolean exclusive) { isExclusive = exclusive; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public long getDuration() { return duration; }
    public void setDuration(long duration) { this.duration = duration; }

    public long getViews() { return views; }
    public void setViews(long views) { this.views = views; }

    public long getLikes() { return likes; }
    public void setLikes(long likes) { this.likes = likes; }

    public Date getReleaseDate() { return releaseDate; }
    public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public String getContentLink() { return contentLink; }
    public void setContentLink(String contentLink) { this.contentLink = contentLink; }

    public String getCover() { return cover; }
    public void setCover(String cover) { this.cover = cover; }

    public ArrayList<Comment> getComments() { return comments; }
    public void setComments(ArrayList<Comment> comments) { this.comments = comments; }
}
