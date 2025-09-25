package org.example.Model;

import java.util.Date;

public class LiveStream extends Video{
    private long liveViewers = 0;
    private Date liveSchedule;

    public LiveStream (long creatorID , String contentTitle , boolean isExclusive , String description , long duration ,
                       Category category , String contentLink , String cover , String videoSubtitles ,
                       Date liveSchedule){
        super(creatorID , contentTitle , isExclusive , description , duration , category , contentLink , cover , videoSubtitles);
        this.liveSchedule = liveSchedule ;
    }

    public long getLiveViewers() { return liveViewers; }
    public void setLiveViewers(long liveViewers) { this.liveViewers = liveViewers; }

    public Date getLiveSchedule() { return liveSchedule; }
    public void setLiveSchedule(Date liveSchedule) { this.liveSchedule = liveSchedule; }
}
