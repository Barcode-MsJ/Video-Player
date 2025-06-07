package Model;

import Controller.ChannelController;

import java.util.ArrayList;

public class Channel {
    private long channelID;
    private  String channelName;
    private String channelDescription;
    private String channelCover;
    private User channelOwner;
    private ArrayList<Playlist> channelPlaylists;
    private ArrayList<User> channelFollowers;

    public Channel (String channelName , String channelDescription , String channelCover , User channelOwner){
        this.channelID = ChannelController.createChannelID();
        this.channelName = channelName;
        this.channelDescription = channelDescription;
        this.channelCover = channelCover;
        this.channelOwner = channelOwner;
        channelPlaylists = new ArrayList<>();
        channelPlaylists.add(new Playlist("allContents"));
        channelFollowers = new ArrayList<>();
    }

    public long getChannelID() { return channelID; }

    public String getChannelName() { return channelName; }
    public void setChannelName(String channelName) { this.channelName = channelName; }

    public String getChannelDescription() { return channelDescription; }
    public void setChannelDescription(String channelDescription) { this.channelDescription = channelDescription; }

    public String getChannelCover() { return channelCover; }
    public void setChannelCover(String channelCover) { this.channelCover = channelCover; }

    public User getChannelOwner() { return channelOwner; }
    public void setChannelOwner(User channelOwner) { this.channelOwner = channelOwner; }

    public ArrayList<Playlist> getChannelPlaylists() { return channelPlaylists; }
    public void setChannelPlaylists(ArrayList<Playlist> channelPlaylists) {this.channelPlaylists = channelPlaylists; }

    public void addChannelPlaylist(String newPlaylistName){
        channelPlaylists.add(new Playlist(newPlaylistName));
    }

    public ArrayList<User> getChannelFollowers() { return channelFollowers; }
    public void setChannelFollowers(ArrayList<User> channelFollowers) { this.channelFollowers = channelFollowers; }
}
