package org.example.Model;

public class NormalVideo extends Video{
    private Quality videoQuality;
    private Format videoFormat;

    public NormalVideo (long creatorID , String contentTitle , boolean isExclusive , String description , long duration ,
                        Category category , String contentLink , String cover , String videoSubtitles ,
                        Quality videoQuality , Format videoFormat) {
        super(creatorID , contentTitle , isExclusive , description , duration , category , contentLink , cover , videoSubtitles);
        this.videoQuality = videoQuality ;
        this.videoFormat = videoFormat ;
    }

    public Quality getVideoQuality() { return videoQuality; }
    public void setVideoQuality(Quality videoQuality) { this.videoQuality = videoQuality; }

    public Format getVideoFormat() { return videoFormat; }
    public void setVideoFormat(Format videoFormat) { this.videoFormat = videoFormat; }
}
