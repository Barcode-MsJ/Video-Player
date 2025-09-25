package org.example.Controller;


import org.example.Model.Channel;
import org.example.Model.Content;
import org.example.Model.Database;

public class ChannelController {
    private static ChannelController channelController;

    private ChannelController(){};
    public static ChannelController getChannelController(){
        if(channelController == null){
            channelController = new ChannelController();
        }
        return channelController;
    }

    public static long createChannelID (){
        return Database.getDatabase().getAllChannels().size()+1 ;
    }

    public static void sortChannel() {
        for (int i = 0 ; i < Database.getDatabase().getAllChannels().size() ; i++) {
            for (int j = 0 ; j < Database.getDatabase().getAllChannels().size() - 1 - i ; j++) {
                if (Database.getDatabase().getAllChannels().get(j).getChannelFollowers().size() <
                        Database.getDatabase().getAllChannels().get(j + 1).getChannelFollowers().size()) {
                    Channel temp = Database.getDatabase().getAllChannels().get(j);
                    Database.getDatabase().getAllChannels().set(j , Database.getDatabase().getAllChannels().get(j + 1));
                    Database.getDatabase().getAllChannels().set(j + 1, temp);
                }
            }
        }
    }
}
