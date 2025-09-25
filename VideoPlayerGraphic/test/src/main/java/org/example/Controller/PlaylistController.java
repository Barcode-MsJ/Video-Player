package org.example.Controller;

import org.example.Model.Database;

public class PlaylistController {
    private static PlaylistController playlistController;

    private PlaylistController(){};
    public static PlaylistController getPlaylistController(){
        if(playlistController == null){
            playlistController = new PlaylistController();
        }
        return playlistController;
    }

    public static long createPlaylistID (int number){
        return number ;
    }
}
