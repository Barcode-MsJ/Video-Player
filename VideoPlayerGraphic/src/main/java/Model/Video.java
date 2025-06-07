package Model;

public abstract class Video extends Content {
    private String videoSubtitles;

    public Video(long creatorID , String contentTitle , boolean isExclusive , String description , long duration ,
                   Category category , String contentLink , String cover , String videoSubtitles) {
        super(creatorID , contentTitle , isExclusive , description , duration , category , contentLink , cover);
        this.videoSubtitles = videoSubtitles;
    }

    public String getVideoSubtitles() { return videoSubtitles; }
    public void setVideoSubtitles(String videoSubtitles) { this.videoSubtitles = videoSubtitles; }
}
