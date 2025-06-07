package Model;

public class ShortVideo extends Video{
    private String shortsAudio;

    public ShortVideo (long creatorID , String contentTitle , boolean isExclusive , String description , long duration ,
                       Category category , String contentLink , String cover , String videoSubtitles , String shortsAudio) {
        super(creatorID , contentTitle , isExclusive , description , duration , category , contentLink , cover , videoSubtitles);
        this.shortsAudio = shortsAudio ;
    }

    public String getShortsAudio() { return shortsAudio; }
    public void setShortsAudio(String shortsAudio) { this.shortsAudio = shortsAudio; }
}
