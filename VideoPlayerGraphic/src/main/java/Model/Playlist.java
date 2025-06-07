package Model;

import java.util.ArrayList;
import Controller.*;

public class Playlist {
    private long playlistID;
    private String playlistName;
    private ArrayList<Content> playlistContent;
    private static int count = 0 ;

    public Playlist(String playlistName){
        this.playlistID = PlaylistController.createPlaylistID(count);
        this.playlistName = playlistName;
        playlistContent = new ArrayList<>();
        count++;
    }

    public long getPlaylistID() { return playlistID; }

    public String getPlaylistName() { return playlistName; }
    public void setPlaylistName(String playlistName) { this.playlistName = playlistName; }

    public ArrayList<Content> getPlaylistContent() { return playlistContent; }
    public void setPlaylistContent(ArrayList<Content> playlistContent) { this.playlistContent = playlistContent; }
}
