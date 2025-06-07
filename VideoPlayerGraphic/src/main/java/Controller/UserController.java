package Controller;

import Model.*;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class UserController {
    private static UserController userController;
    private User loggedInUser ;

    public User getLoggedInUser() { return loggedInUser; }

    private UserController(){};
    public static UserController getUserController(){
        if(userController == null){
            userController = new UserController();
        }
        return userController;
    }

    public String signUp (String accountName , String password , String firstNameAndLastName , String email ,
                          String phoneNumber , String profileCover ,
                          Category category1 , Category category2 , Category category3 , Category category4){
        NormalUser newUser = new NormalUser(accountName , password , firstNameAndLastName , email , phoneNumber , profileCover , category1 , category2 , category3 , category4);
        boolean isAccountExist = false;
        for(User check : Database.getDatabase().getAllUsers()){
            if(check.getAccountName().equals(newUser.getAccountName()) || check.getEmail().equals(newUser.getEmail())){
                isAccountExist = true;
                break;
            }
        }
        if(isAccountExist)return "Your account or email already exists. Please log in.";
        Database.getDatabase().getAllUsers().add(newUser);
        return "Your account has been created successfully.";
    }

    public String signIn (String accountName , String password){
        boolean isLogINCorrect = false;
        boolean isUserBlock = false;
        for(User check : Database.getDatabase().getAllUsers()){
            if(check.getAccountName().equals(accountName) && check.getPassword().equals(password)){
                isUserBlock = check.getIsBlocked();
                if(!check.getIsBlocked()) {
                    isLogINCorrect = true;
                    loggedInUser = check;
                    break;
                }
            }
        }
        if(isUserBlock) return "Your account has been blocked by the admin.";
        if(!isLogINCorrect)return "Login failed. Please try again.";
        return "Your login was successful.";
    }

    public StringBuilder showUserInfo(){
        StringBuilder showUserInfoString = new StringBuilder();
        showUserInfoString.append("Your Account Name : ").append(loggedInUser.getAccountName()).append("\tYour FullName : ").append(loggedInUser.getFirstNameAndLastName());
        showUserInfoString.append("\nYour Email : ").append(loggedInUser.getEmail()).append("\tYour Phone Number : ").append(loggedInUser.getPhoneNumber());
        showUserInfoString.append("\nYour Profile Cover : ").append(loggedInUser.getProfileCover());
        return showUserInfoString;
    }

    public String editUserInfo (int whichChanges , String changes){
        for(int i = 0 ; i < Database.getDatabase().getAllUsers().size() ; i++){
            if(loggedInUser.getAccountID() == Database.getDatabase().getAllUsers().get(i).getAccountID()){
                if(whichChanges == 1){
                    Database.getDatabase().getAllUsers().get(i).setAccountName(changes);
                }else if(whichChanges == 2){
                    Database.getDatabase().getAllUsers().get(i).setPassword(changes);
                }else if(whichChanges == 3){
                    Database.getDatabase().getAllUsers().get(i).setFirstNameAndLastName(changes);
                }else if(whichChanges == 4){
                    Database.getDatabase().getAllUsers().get(i).setEmail(changes);
                }else if(whichChanges == 5){
                    Database.getDatabase().getAllUsers().get(i).setPhoneNumber(changes);
                }else if(whichChanges == 6){
                    Database.getDatabase().getAllUsers().get(i).setProfileCover(changes);
                }
                loggedInUser = Database.getDatabase().getAllUsers().get(i);
                break;
            }
        }
        return "Your changes have been applied.";
    }

    public String editUserCategory(Category category1 , Category category2 , Category category3 , Category category4){
        for(int i = 0 ; i < Database.getDatabase().getAllUsers().size() ; i++){
            if(loggedInUser.getAccountID() == Database.getDatabase().getAllUsers().get(i).getAccountID()){
                ArrayList<Category> newCategory = new ArrayList<>();
                newCategory.add(category1);
                newCategory.add(category2);
                newCategory.add(category3);
                newCategory.add(category4);
                Database.getDatabase().getAllUsers().get(i).setFavoriteCategories(newCategory);
                loggedInUser = Database.getDatabase().getAllUsers().get(i);
                break;
            }
        }
        return "Your changes have been applied.";
    }

    public String makePlaylist (String newPlaylistName){
        if(loggedInUser.getIsPremium()){
            for(int i = 0 ; i < Database.getDatabase().getAllUsers().size() ; i++){
                if(loggedInUser.getAccountID() == Database.getDatabase().getAllUsers().get(i).getAccountID()){
                    Database.getDatabase().getAllUsers().get(i).addPlaylist(newPlaylistName);
                    loggedInUser = Database.getDatabase().getAllUsers().get(i);
                }
            }
            return "Your playlist was created correctly.";
        }else {
            if(loggedInUser.getUserPlaylist().size() < 5){
                for(int i = 0 ; i < Database.getDatabase().getAllUsers().size() ; i++){
                    if(loggedInUser.getAccountID() == Database.getDatabase().getAllUsers().get(i).getAccountID()){
                        Database.getDatabase().getAllUsers().get(i).addPlaylist(newPlaylistName);
                        loggedInUser = Database.getDatabase().getAllUsers().get(i);
                    }
                }
                return "Your playlist was created correctly.";
            }else {
                return "You have reached your playlist creation limit and are not allowed to create any more playlists.";
            }
        }
    }

    public String addContentPlaylist(String playlistName , long contentID){
        if(loggedInUser.getIsPremium()) {
            for(Content content : Database.getDatabase().getAllContents()) {
                if(contentID == content.getContentID()) {
                    for (int i = 0; i < loggedInUser.getUserPlaylist().size(); i++) {
                        if (loggedInUser.getUserPlaylist().get(i).getPlaylistName().equals(playlistName)) {
                            for (int j = 0; j < Database.getDatabase().getAllUsers().size(); j++) {
                                if (loggedInUser.getAccountID() == Database.getDatabase().getAllUsers().get(j).getAccountID()) {
                                    Database.getDatabase().getAllUsers().get(j).getUserPlaylist().get(i).getPlaylistContent().add(content);
                                    loggedInUser = Database.getDatabase().getAllUsers().get(j);
                                }
                            }
                            return "Your content has been successfully added to the playlist.";
                        }
                    }
                    return "The requested playlist was not found.";
                }
            }
            return "The requested content was not found.";

        }else {
            for(Content content : Database.getDatabase().getAllContents()) {
                if(content.getContentID() == contentID) {
                    for (int i = 0; i < loggedInUser.getUserPlaylist().size(); i++) {
                        if (loggedInUser.getUserPlaylist().get(i).getPlaylistName().equals(playlistName)) {
                            if (loggedInUser.getUserPlaylist().get(i).getPlaylistContent().size() < 10) {
                                for (int j = 0; j < Database.getDatabase().getAllUsers().size(); j++) {
                                    if (loggedInUser.getAccountID() == Database.getDatabase().getAllUsers().get(j).getAccountID()) {
                                        Database.getDatabase().getAllUsers().get(j).getUserPlaylist().get(i).getPlaylistContent().add(content);
                                        loggedInUser = Database.getDatabase().getAllUsers().get(j);
                                    }
                                }
                                return "Your content has been successfully added to the playlist.";
                            } else {
                                return "You have reached your limit for adding content to the playlist and are not allowed to add more.";
                            }
                        }
                    }
                    return "The requested playlist was not found.";
                }
            }
            return "The requested content was not found.";
        }
    }

    public String playContent (String contentLink){
        for(int i = 0 ; i < Database.getDatabase().getAllContents().size() ; i++){
            if(Database.getDatabase().getAllContents().get(i).getContentLink().equals(contentLink)){
                Database.getDatabase().getAllContents().get(i).setViews(Database.getDatabase().getAllContents().get(i).getViews()+1);
                for(int j = 0 ; j < Database.getDatabase().getAllUsers().size() ; j++){
                    if(loggedInUser.getAccountID() == Database.getDatabase().getAllUsers().get(j).getAccountID()){
                        Database.getDatabase().getAllUsers().get(j).getUserPlaylist().get(1).getPlaylistContent().add(Database.getDatabase().getAllContents().get(i));
                    }
                }
            }
        }
        return "The requested content was not found.";
    }

    public String like(String contentLink){
        for(int i = 0 ; i < Database.getDatabase().getAllContents().size() ; i++){
            if(Database.getDatabase().getAllContents().get(i).getContentLink().equals(contentLink)){
                Database.getDatabase().getAllContents().get(i).setLikes(Database.getDatabase().getAllContents().get(i).getLikes()+1);
                for(int j = 0 ; j < Database.getDatabase().getAllUsers().size() ; j++){
                    if(loggedInUser.getAccountID() == Database.getDatabase().getAllUsers().get(j).getAccountID()){
                        Database.getDatabase().getAllUsers().get(j).getUserPlaylist().get(0).getPlaylistContent().add(Database.getDatabase().getAllContents().get(i));
                    }
                }
                return "Your like has been successfully processed.";
            }
        }
        return "The requested content was not found.";
    }

    public StringBuilder searchContent (String contentName){
        StringBuilder contentsString = new StringBuilder();
        for(Content content : Database.getDatabase().getAllContents()){
            if(content.getContentTitle().equals(contentName)){
                contentsString.append(content.getContentLink()).append("\n");
            }
        }
        return contentsString;
    }

    public StringBuilder searchChannel (String channelName){
        StringBuilder channelsString = new StringBuilder();
        for(Channel channel : Database.getDatabase().getAllChannels()){
            if(channel.getChannelName().equals(channelName)){
                channelsString.append(channel.getChannelName()).append(" ").append(channel.getChannelCover()).append(" ").append(channel.getChannelFollowers().size()).append("\n");
            }
        }
        return channelsString;
    }

    public String reportContent (String contentLink , String reportText){
        for(Content content : Database.getDatabase().getAllContents()){
            if(content.getContentLink().equals(contentLink)){
                Report report = new Report(loggedInUser , content.getContentID() , content.getCreatorID() , reportText);
                Database.getDatabase().getAllReports().add(report);
                return "Your report has been successfully submitted.";
            }
        }
        return "The requested content was not found.";
    }

    public String followChannel (String channelName){
        for(int i = 0 ; i < Database.getDatabase().getAllChannels().size() ; i++){
            if(Database.getDatabase().getAllChannels().get(i).getChannelName().equals(channelName)){
                for(int j = 0 ; j < Database.getDatabase().getAllUsers().size() ; j++){
                    if(Database.getDatabase().getAllUsers().get(j).getAccountID() == loggedInUser.getAccountID()){
                        Database.getDatabase().getAllChannels().get(i).getChannelFollowers().add(loggedInUser);
                        Database.getDatabase().getAllUsers().get(j).getSubscriptions().add(Database.getDatabase().getAllChannels().get(i));
                        loggedInUser = Database.getDatabase().getAllUsers().get(j);
                        return  "You have successfully followed.";
                    }
                }
            }
        }
        return "The requested channel was not found.";
    }

    public StringBuilder showPlaylistInfo (){
        StringBuilder playlistInfoString = new StringBuilder();
        for(Playlist playlist : loggedInUser.getUserPlaylist()){
            playlistInfoString.append(playlist.getPlaylistName()).append(" ");
            for (Content content : playlist.getPlaylistContent()){
                playlistInfoString.append(content.getContentLink()).append(" ");
            }
            playlistInfoString.append("\n");
        }
        return playlistInfoString;
    }

    public StringBuilder showSuggestions (){
        StringBuilder suggestionsString = new StringBuilder();
        int counter = 0 ;
        for(Content content : Database.getDatabase().getAllContents()){
            if(content.getCategory() == loggedInUser.getFavoriteCategories().get(0) ||
                    content.getCategory() == loggedInUser.getFavoriteCategories().get(1) ||
                    content.getCategory() == loggedInUser.getFavoriteCategories().get(2) ||
                    content.getCategory() == loggedInUser.getFavoriteCategories().get(3)){
                suggestionsString.append(content.getContentLink()).append("\n");
                counter++;
            }
            if(counter == 9)break;
        }
        while (counter < 9){
            for(Content content : Database.getDatabase().getAllContents()){
                suggestionsString.append(content.getContentLink()).append("\n");
                counter++;
            }
        }
        return suggestionsString;
    }

    public StringBuilder showSubscriptions (){
        StringBuilder subscriptionsString = new StringBuilder();
        for(Channel channel : loggedInUser.getSubscriptions()){
            subscriptionsString.append(channel.getChannelName()).append(" ").append(channel.getChannelCover()).append(" ").append(channel.getChannelFollowers().size()).append("\n");
        }
        return subscriptionsString;
    }

    public StringBuilder showChannels(){
        StringBuilder channelsString = new StringBuilder();
        for(Channel channel : Database.getDatabase().getAllChannels()){
            channelsString.append(channel.getChannelName()).append("\t").append(channel.getChannelCover()).append("\t");
            channelsString.append(channel.getChannelDescription()).append("\n");
        }
        return channelsString ;
    }

    public String comment (String contentLink , String commentContent){
        for(Content content : Database.getDatabase().getAllContents()){
            if(content.getContentLink().equals(contentLink)){
                Comment comment = new Comment(loggedInUser , content.getContentID() , commentContent);
                content.getComments().add(comment);
                return "Your comment has been successfully posted.";
            }
        }
        return "The requested content was not found.";
    }

    public String buyPremium (Premium type){
        PremiumUser premiumUser = new PremiumUser(loggedInUser.getAccountName() , loggedInUser.getPassword() , loggedInUser.getFirstNameAndLastName() , loggedInUser.getEmail() , loggedInUser.getPhoneNumber() , loggedInUser.getProfileCover() , loggedInUser.getFavoriteCategories().get(0) , loggedInUser.getFavoriteCategories().get(1) , loggedInUser.getFavoriteCategories().get(2) , loggedInUser.getFavoriteCategories().get(3));
        premiumUser.setAccountID(loggedInUser.getAccountID());
        premiumUser.setCredit(loggedInUser.getCredit());
        premiumUser.setUserPlaylist(loggedInUser.getUserPlaylist());
        premiumUser.setUserChannel(loggedInUser.getUserChannel());
        premiumUser.setSubscriptions(loggedInUser.getSubscriptions());
        if(type.equals(Premium.GOLD)){
            if(loggedInUser.getCredit() >= 14) {
                LocalDate futureLocalDate = LocalDate.now().plusMonths(6);
                Date futureDate = Date.from(futureLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                premiumUser.setSubscriptionExpiration(futureDate);
                premiumUser.setCredit(premiumUser.getCredit()-14);
            }else return "Your account balance is insufficient.";
        }else if(type.equals(Premium.SILVER)){
            if (loggedInUser.getCredit() >= 9) {
                LocalDate futureLocalDate = LocalDate.now().plusMonths(2);
                Date futureDate = Date.from(futureLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                premiumUser.setSubscriptionExpiration(futureDate);
                premiumUser.setCredit(premiumUser.getCredit()-9);
            }else return "Your account balance is insufficient.";
        }else {
            if(loggedInUser.getCredit() >= 5) {
                LocalDate futureLocalDate = LocalDate.now().plusMonths(1);
                Date futureDate = Date.from(futureLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                premiumUser.setSubscriptionExpiration(futureDate);
                premiumUser.setCredit(premiumUser.getCredit()-5);
            }else return "Your account balance is insufficient.";
        }
        for (int i = 0 ; i < Database.getDatabase().getAllUsers().size() ; i++){
            if(Database.getDatabase().getAllUsers().get(i).getAccountID() == loggedInUser.getAccountID()){
                Database.getDatabase().getAllUsers().remove(i);
                Database.getDatabase().getAllUsers().add(i , premiumUser);
                loggedInUser = Database.getDatabase().getAllUsers().get(i);
                break;
            }
        }
        return "Your premium subscription purchase was successful.";
    }

    public String increaseCredit (long amount){
        for(int i = 0 ; i < Database.getDatabase().getAllUsers().size() ; i++){
            if(loggedInUser.getAccountID() == Database.getDatabase().getAllUsers().get(i).getAccountID()){
                Database.getDatabase().getAllUsers().get(i).setCredit(Database.getDatabase().getAllUsers().get(i).getCredit() + amount);
                loggedInUser = Database.getDatabase().getAllUsers().get(i) ;
                break;
            }
        }
        return  "Your credit has been successfully topped up.";
    }

    public String createNewChannel (String channelName , String channelDescription , String channelCover){
        if(loggedInUser.getUserChannel() != null) return "You have already created your exclusive channel. Please log in.";
        Channel channel = new Channel(channelName , channelDescription , channelCover , loggedInUser);
        Database.getDatabase().getAllChannels().add(channel);
        for (int i = 0 ; i < Database.getDatabase().getAllUsers().size() ; i++){
            if(Database.getDatabase().getAllUsers().get(i).getAccountID() == loggedInUser.getAccountID()){
                Database.getDatabase().getAllUsers().get(i).setUserChannel(channel);
                loggedInUser = Database.getDatabase().getAllUsers().get(i);
            }
        }
        return "Your channel has been successfully created.";
    }

    public String loginChannel(){
        if(loggedInUser.getUserChannel() == null) return "You do not have a channel. Please create your channel and then log in.";
        return "Welcome to your channel!";
    }

    public String addContentToChannel (long contentID){
        for(Content content : Database.getDatabase().getAllContents()){
            if(content.getContentID() == contentID){
                for(int i = 0 ; i < Database.getDatabase().getAllUsers().size() ; i++){
                    if(Database.getDatabase().getAllUsers().get(i).getAccountID() == loggedInUser.getAccountID()){
                        Database.getDatabase().getAllUsers().get(i).getUserChannel().getChannelPlaylists().get(0).getPlaylistContent().add(content);
                        loggedInUser = Database.getDatabase().getAllUsers().get(i);
                        return "The content has been successfully added to the channel.";
                    }
                }
            }
        }
        return "The requested content was not found.";
    }

    public StringBuilder showChannelInfo(String channelName){
        StringBuilder channelInfoString = new StringBuilder();
        for(Channel channel : Database.getDatabase().getAllChannels()){
            if(channel.getChannelName().equals(channelName)){
                for(Playlist playlist : channel.getChannelPlaylists()){
                    channelInfoString.append(playlist.getPlaylistName()).append(" ");
                    for(Content content : playlist.getPlaylistContent()){
                        channelInfoString.append(content.getContentLink()).append(" ");
                    }
                    channelInfoString.append("\n");
                }
            }
        }
        return channelInfoString;
    }

    public StringBuilder showFollowers (){
        StringBuilder followersString = new StringBuilder();
        for(User user : loggedInUser.getUserChannel().getChannelFollowers()){
            followersString.append(user.getAccountName()).append(" ").append(user.getProfileCover()).append("\n");
        }
        return followersString;
    }

    public String editChannelInfo (int whichChanges , String changes){
        for(int i = 0 ; i < Database.getDatabase().getAllUsers().size() ; i++){
            if(loggedInUser.getAccountID() == Database.getDatabase().getAllUsers().get(i).getAccountID()) {
                if(whichChanges == 1){
                    Database.getDatabase().getAllUsers().get(i).getUserChannel().setChannelName(changes);
                }else if (whichChanges == 2){
                    Database.getDatabase().getAllUsers().get(i).getUserChannel().setChannelDescription(changes);
                }else if (whichChanges == 3){
                    Database.getDatabase().getAllUsers().get(i).getUserChannel().setChannelCover(changes);
                }
                loggedInUser = Database.getDatabase().getAllUsers().get(i);
                break;
            }
        }
        return "Your channel changes have been applied.";
    }

    public String channelPlaylist(String playlistName){
        for(int i = 0 ; i < Database.getDatabase().getAllUsers().size() ; i++){
            if(loggedInUser.getAccountID() == Database.getDatabase().getAllUsers().get(i).getAccountID()){
                Database.getDatabase().getAllUsers().get(i).getUserChannel().addChannelPlaylist(playlistName);
                loggedInUser = Database.getDatabase().getAllUsers().get(i);
                return "Your channel playlist whit ID : " + Database.getDatabase().getAllUsers().get(i).getUserChannel().getChannelPlaylists().get(Database.getDatabase().getAllUsers().get(i).getUserChannel().getChannelPlaylists().size()-1).getPlaylistID() + " was created correctly.";
            }
        }
        return "Your channel playlist was created correctly.";
    }

    public String addContentToChannelPlaylist (long contentID , String playlistName){
        for(Content content : Database.getDatabase().getAllContents()) {
            if (content.getContentID() == contentID) {
                for (int i = 0; i < Database.getDatabase().getAllUsers().size(); i++) {
                    if (Database.getDatabase().getAllUsers().get(i).getAccountID() == loggedInUser.getAccountID()) {
                        for(int j = 0 ; j < Database.getDatabase().getAllUsers().get(i).getUserChannel().getChannelPlaylists().size() ; j++){
                            if(Database.getDatabase().getAllUsers().get(i).getUserChannel().getChannelPlaylists().get(j).getPlaylistName().equals(playlistName)){
                                Database.getDatabase().getAllUsers().get(i).getUserChannel().getChannelPlaylists().get(j).getPlaylistContent().add(content);
                                Database.getDatabase().getAllUsers().get(i).getUserChannel().getChannelPlaylists().get(0).getPlaylistContent().add(content);
                                loggedInUser = Database.getDatabase().getAllUsers().get(i);
                                return "The content has been successfully added to the channel playlist.";
                            }
                        }
                        return "The requested playlist was not found.";
                    }
                }
            }
        }
        return "The requested content was not found.";
    }

    public String createShortVideoContent(String contentTitle , boolean isExclusive , String description , long duration ,
                                          Category category , String contentLink , String cover , String videoSubtitles , String shortsAudio){
        if (duration > 30) return "Your short video must be less than 30 seconds.";
        ShortVideo shortVideo = new ShortVideo(loggedInUser.getAccountID() , contentTitle , isExclusive , description , duration , category , contentLink , cover , videoSubtitles , shortsAudio);
        Database.getDatabase().getAllContents().add(shortVideo);
        return "Your short video with ID : " + shortVideo.getContentID() + " has been successfully added.";
    }

    public String createNormalVideoContent(String contentTitle , boolean isExclusive , String description , long duration ,
                                           Category category , String contentLink , String cover , String videoSubtitles ,
                                           Quality videoQuality , Format videoFormat){
        NormalVideo normalVideo = new NormalVideo(loggedInUser.getAccountID() , contentTitle , isExclusive , description , duration , category , contentLink , cover , videoSubtitles , videoQuality , videoFormat);
        Database.getDatabase().getAllContents().add(normalVideo);
        return "Your normal video with ID : " + normalVideo.getContentID() + " has been successfully added.";
    }

    public String createLiveStreamContent(String contentTitle , boolean isExclusive , String description , long duration ,
                                          Category category , String contentLink , String cover , String videoSubtitles ,
                                          Date liveSchedule){
        LiveStream liveStream = new LiveStream(loggedInUser.getAccountID() , contentTitle , isExclusive , description , duration , category , contentLink , cover , videoSubtitles , liveSchedule);
        Database.getDatabase().getAllContents().add(liveStream);
        return "Your live stream with ID : " + liveStream.getContentID() + " has been successfully added.";
    }

    public String createPodcastContent(String contentTitle , boolean isExclusive , String description , long duration ,
                                       Category category , String contentLink , String cover , String podcastOwner){
        Podcast podcast = new Podcast(loggedInUser.getAccountID() , contentTitle , isExclusive , description , duration , category , contentLink , cover , podcastOwner);
        Database.getDatabase().getAllContents().add(podcast);
        return "Your podcast with ID : " + podcast.getContentID() + " has been successfully added.";
    }
}