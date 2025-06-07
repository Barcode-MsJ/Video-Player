package Model;

public class Podcast extends Content {
    private String podcastOwner;

    public Podcast(long creatorID , String contentTitle , boolean isExclusive , String description , long duration ,
                   Category category , String contentLink , String cover , String podcastOwner) {
        super(creatorID , contentTitle , isExclusive , description , duration , category , contentLink , cover);
        this.podcastOwner = podcastOwner;
    }

    public String getPodcastOwner() { return podcastOwner; }
    public void setPodcastOwner (String podcastOwner) { this.podcastOwner = podcastOwner; }
}
