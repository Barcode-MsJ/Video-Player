package View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import Model.*;
import Controller.*;

public class UserPanel {
    static Scanner sc = new Scanner(System.in);
    public static void showPanel() throws ParseException {
        System.out.println("Welcome to User Panel!!");
        while (true) {
            System.out.println("1.User Account & Profile Management\n" + "2.Content Interaction (Playback, Feedback, Discovery)\n" +
                    "3.Playlist Management\n" + "4.Channel & Social Interaction\n" + "5.Content Creation & Publishing\n" + "6.Quit UserPanel");
            int command = sc.nextInt();
            if(command == 1){
                System.out.println("1.showUserInfo\n" + "2.editUserInfo ([whichChanges] - [changes])\n" + "3.editUserCategory ([category1] - [category2] - [category3] - [category4])\n" +
                        "4.buyPremium ([type])\n" + "5.increaseCredit ([amount])\n");
                String command1 = sc.nextLine();
                String command2 = sc.nextLine();
                String[] commandInfo = command2.split(" - ");
                if(commandInfo[0].equals("showUserInfo")){
                    System.out.println(UserController.getUserController().showUserInfo());
                }else if(commandInfo[0].equals("editUserInfo")){
                    System.out.println(UserController.getUserController().editUserInfo(Integer.parseInt(commandInfo[1]) , commandInfo[2]));
                }else if(commandInfo[0].equals("editUserCategory")){
                    System.out.println(UserController.getUserController().editUserCategory(Category.valueOf(commandInfo[1]) , Category.valueOf(commandInfo[2]) , Category.valueOf(commandInfo[3]) , Category.valueOf(commandInfo[4])));
                }else if (commandInfo[0].equals("buyPremium")){
                    System.out.println(UserController.getUserController().buyPremium(Premium.valueOf(commandInfo[1])));
                }else if (commandInfo[0].equals("increaseCredit")){
                    System.out.println(UserController.getUserController().increaseCredit(Long.parseLong(commandInfo[1])));
                }
            }else if (command == 2){
                System.out.println("1.playContent ([contentID])\n" + "2.like ([contentID])\n" + "3.searchContent ([contentName])\n" + "4.reportContent ([contentID] - [reportText])\n" +
                        "5.showSuggestions\n" + "6.comment ([commentedContentID] - [commentContent])\n" + "7.sortContentByView\n" +
                        "8.sortContentByLike\n" + "9.sortContentByDate\n" + "10.filterContentByVideo\n" + "11.filterContentByPodcast\n" +
                        "12.filterContentByCategory ([category])\n" + "13.filterContentByDate ([startDate] - [endDate])\n");
                String command1 = sc.nextLine();
                String command2 = sc.nextLine();
                String[] commandInfo = command2.split(" - ");
                if(commandInfo[0].equals("playContent")){
                    //System.out.println(UserController.getUserController().playContent(Long.parseLong(commandInfo[1])));
                }else if (commandInfo[0].equals("like")){
                   // System.out.println(UserController.getUserController().like(Long.parseLong(commandInfo[1])));
                }else if (commandInfo[0].equals("searchContent")){
                    System.out.println(UserController.getUserController().searchContent(commandInfo[1]));
                }else if (commandInfo[0].equals("reportContent")){
                    //System.out.println(UserController.getUserController().reportContent(Long.parseLong(commandInfo[1]) , commandInfo[2]));
                }else if (commandInfo[0].equals("showSuggestions")){
                    System.out.println(UserController.getUserController().showSuggestions());
                }else if (commandInfo[0].equals("comment")){
                    //System.out.println(UserController.getUserController().comment(Long.parseLong(commandInfo[1]) , commandInfo[2]));
                }else if (commandInfo[0].equals("sortContentByView")){
                    ContentController.sortContentByView();
                }else if (commandInfo[0].equals("sortContentByLike")){
                    ContentController.sortContent();
                }else if (commandInfo[0].equals("sortContentByDate")){
                    ContentController.sortContentByDate();
                }else if (commandInfo[0].equals("filterContentByVideo")){
                    System.out.println(ContentController.filterContentByVideo());
                }else if (commandInfo[0].equals("filterContentByPodcast")){
                    System.out.println(ContentController.filterContentByPodcast());
                }else if (commandInfo[0].equals("filterContentByCategory")){
                    System.out.println(ContentController.filterContentByCategory(Category.valueOf(commandInfo[1])));
                }else if (commandInfo[0].equals("filterContentByDate")){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    System.out.println(ContentController.filterContentByDate(sdf.parse(commandInfo[1]) , sdf.parse(commandInfo[2])));
                }
            }else if (command == 3){
                System.out.println("1.makePlaylist ([newPlaylistName])\n" + "2.addContentPlaylist ([playlistName] - [contentID])\n" +
                        "3.showPlaylistInfo\n" + "4.channelPlaylist ([playlistName])\n" + "5.addContentToChannelPlaylist ([contentID] - [playlistName])\n");
                String command1 = sc.nextLine();
                String command2 = sc.nextLine();
                String[] commandInfo = command2.split(" - ");
                if(commandInfo[0].equals("makePlaylist")){
                    System.out.println(UserController.getUserController().makePlaylist(commandInfo[1]));
                }else if(commandInfo[0].equals("addContentPlaylist")){
                    System.out.println(UserController.getUserController().addContentPlaylist(commandInfo[1] , Long.parseLong(commandInfo[2])));
                }else if (commandInfo[0].equals("showPlaylistInfo")){
                    System.out.println(UserController.getUserController().showPlaylistInfo());
                }else if (commandInfo[0].equals("channelPlaylist")){
                    System.out.println(UserController.getUserController().channelPlaylist(commandInfo[1]));
                }else if(commandInfo[0].equals("addContentToChannelPlaylist")){
                    //System.out.println(UserController.getUserController().addContentToChannelPlaylist(Long.parseLong(commandInfo[1]) , Long.parseLong(commandInfo[2])));
                }
            }else if (command == 4){
                System.out.println("1.followChannel ([channelID])\n" + "2.showSubscriptions\n" + "3.showChannels\n" +
                        "4.createNewChannel ([channelName] - [channelDescription] - [channelCover])\n" + "5.loginChannel\n" +
                        "6.addContentToChannel ([contentID])\n" + "7.showChannelInfo\n" + "8.showFollowers\n" + "9.editChannelInfo ([whichChanges] - [changes])\n" +
                        "10.searchChannel([channelName])");
                String command1 = sc.nextLine();
                String command2 = sc.nextLine();
                String[] commandInfo = command2.split(" - ");
                if(commandInfo[0].equals("followChannel")){
                    //System.out.println(UserController.getUserController().followChannel(Long.parseLong(commandInfo[1])));
                }else if(commandInfo[0].equals("showSubscriptions")){
                    System.out.println(UserController.getUserController().showSubscriptions());
                }else if (commandInfo[0].equals("showChannels")){
                    System.out.println(UserController.getUserController().showChannels());
                }else if (commandInfo[0].equals("createNewChannel")){
                    System.out.println(UserController.getUserController().createNewChannel(commandInfo[1] , commandInfo[2] , commandInfo[3]));
                }else if (commandInfo[0].equals("loginChannel")){
                    System.out.println(UserController.getUserController().loginChannel());
                }else if (commandInfo[0].equals("addContentToChannel")){
                    System.out.println(UserController.getUserController().addContentToChannel(Long.parseLong(commandInfo[1])));
                }else if (commandInfo[0].equals("showChannelInfo")){
                    //System.out.println(UserController.getUserController().showChannelInfo());
                }else if (commandInfo[0].equals("showFollowers")){
                    System.out.println(UserController.getUserController().showFollowers());
                }else if (commandInfo[0].equals("editChannelInfo")){
                    System.out.println(UserController.getUserController().editChannelInfo(Integer.parseInt(commandInfo[1]) , commandInfo[2]));
                }else if (commandInfo[0].equals("searchChannel")){
                    System.out.println(UserController.getUserController().searchChannel(commandInfo[1]));
                }
            }else if (command == 5){
                System.out.println("27.createShortVideoContent ([contentTitle] - [isExclusive] - [description] - [duration] - [category] - [contentLink] - [cover] - [videoSubtitles] - [shortsAudio])\n" +
                                "28.createNormalVideoContent ([contentTitle] - [isExclusive] - [description] - [duration] - [category] - [contentLink] - [cover] - [videoSubtitles] - [videoQuality] - [videoFormat])\n" +
                                "29.createLiveStreamContent ([contentTitle] - [isExclusive] - [description] - [duration] - [category] - [contentLink] - [cover] - [videoSubtitles] - [liveSchedule])\n" +
                                "30.createPodcastContent ([contentTitle] - [isExclusive] - [description] - [duration] - [category] - [contentLink] - [cover] - [podcastOwner])\n");
                String command1 = sc.nextLine();
                String command2 = sc.nextLine();
                String[] commandInfo = command2.split(" - ");
                if(commandInfo[0].equals("createShortVideoContent")){
                    System.out.println(UserController.getUserController().createShortVideoContent(commandInfo[1] , Boolean.parseBoolean(commandInfo[2]) , commandInfo[3] , Long.parseLong(commandInfo[4]) , Category.valueOf(commandInfo[5]) , commandInfo[6] , commandInfo[7] , commandInfo[8] , commandInfo[9]));
                }else if (commandInfo[0].equals("createNormalVideoContent")){
                    System.out.println(UserController.getUserController().createNormalVideoContent(commandInfo[1] , Boolean.parseBoolean(commandInfo[2]) , commandInfo[3] , Long.parseLong(commandInfo[4]) , Category.valueOf(commandInfo[5]) , commandInfo[6] , commandInfo[7] , commandInfo[8] , Quality.valueOf(commandInfo[9]) , Format.valueOf(commandInfo[10])));
                }else if (commandInfo[0].equals("createLiveStreamContent")){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    System.out.println(UserController.getUserController().createLiveStreamContent(commandInfo[1] , Boolean.parseBoolean(commandInfo[2]) , commandInfo[3] , Long.parseLong(commandInfo[4]) , Category.valueOf(commandInfo[5]) , commandInfo[6] , commandInfo[7] , commandInfo[8] , sdf.parse(commandInfo[9])));
                }else if (commandInfo[0].equals("createPodcastContent")){
                    System.out.println(UserController.getUserController().createPodcastContent(commandInfo[1] , Boolean.parseBoolean(commandInfo[2]) , commandInfo[3] , Long.parseLong(commandInfo[4]) , Category.valueOf(commandInfo[5]) , commandInfo[6] , commandInfo[7] , commandInfo[8]));
                }
            }else break;
        }
    }
}
