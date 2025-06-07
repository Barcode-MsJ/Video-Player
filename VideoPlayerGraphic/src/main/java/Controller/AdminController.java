package Controller;

import Model.*;

public class AdminController {
    private static AdminController adminController;
    private boolean isAdminLogin = false ;

    private AdminController(){};
    public static AdminController getAdminController(){
        if(adminController == null){
            adminController = new AdminController();
        }
        return adminController;
    }

    public String adminLogin (String accountName , String password){
        if(Database.getDatabase().getAdmin().getAccountName().equals(accountName) && Database.getDatabase().getAdmin().getPassword().equals(password)){
            isAdminLogin = true;
            return "Your login was successful.";
        }
        return "Login failed. Please try again.";
    }

    public StringBuilder showBestChannels(){
        StringBuilder bestChannelString = new StringBuilder();
        if(!isAdminLogin){
            bestChannelString.append("Unauthorized access.");
            return bestChannelString;
        }
        ChannelController.sortChannel();
        for(Channel channel : Database.getDatabase().getAllChannels()){
            bestChannelString.append(channel.getChannelName()).append(" ").append(channel.getChannelCover()).append(" ").append(channel.getChannelFollowers().size());
        }
        return bestChannelString;
    }

    public StringBuilder showBestContent(){
        StringBuilder bestContentString = new StringBuilder();
        if(!isAdminLogin){
            bestContentString.append("Unauthorized access.");
            return bestContentString;
        }
        ContentController.sortContent();
        for(Content content : Database.getDatabase().getAllContents()){
            bestContentString.append(content.getContentLink()).append("\n");
        }
        return bestContentString;
    }

    public StringBuilder showUserInfo(){
        StringBuilder userInfoString = new StringBuilder();
        if(!isAdminLogin){
            userInfoString.append("Unauthorized access.");
            return userInfoString;
        }
        for(User user : Database.getDatabase().getAllUsers()){
            userInfoString.append(user.getAccountName()).append(" ").append(user.getProfileCover()).append("\n");
        }
        return userInfoString;
    }

    public StringBuilder showContentInfo(){
        StringBuilder contentInfoString = new StringBuilder();
        if(!isAdminLogin){
            contentInfoString.append("Unauthorized access.");
            return contentInfoString;
        }
        for(Content content : Database.getDatabase().getAllContents()){
            contentInfoString.append(content.getContentLink()).append("\n");
        }
        return contentInfoString;
    }

    public StringBuilder showReports(){
        StringBuilder reportsString = new StringBuilder();
        if(!isAdminLogin){
            reportsString.append("Unauthorized access.");
            return reportsString;
        }
        for (Report report : Database.getDatabase().getAllReports()){
            reportsString.append(report.getDescription()).append("\tContentID : ").append(report.getReportedContentID()).append("\tUserID : ").append(report.getReportedUserID()).append("\n");
        }
        return reportsString;
    }

    public String deleteContent (long contentID){
        if(!isAdminLogin) return "Unauthorized access.";
        for(int i = 0 ; i < Database.getDatabase().getAllContents().size() ; i++){
            if(Database.getDatabase().getAllContents().get(i).getContentID() == contentID){
                Database.getDatabase().getAllContents().remove(i);
                return "The selected content has been successfully deleted.";
            }
        }
        return "The requested content was not found.";
    }

    public String blockUser(long userID){
        if(!isAdminLogin) return "Unauthorized access.";
        for(int i = 0 ; i < Database.getDatabase().getAllUsers().size() ; i++){
            if(Database.getDatabase().getAllUsers().get(i).getAccountID() == userID){
                Database.getDatabase().getAllUsers().get(i).setIsBlocked(true);
                return "The selected user has been successfully blocked.";
            }
        }
        return "The requested user was not found.";
    }

    public String unblockUser(long userID){
        if(!isAdminLogin) return "Unauthorized access.";
        for(int i = 0 ; i < Database.getDatabase().getAllUsers().size() ; i++){
            if(Database.getDatabase().getAllUsers().get(i).getAccountID() == userID){
                Database.getDatabase().getAllUsers().get(i).setIsBlocked(false);
                return "The selected user has been successfully unblocked.";
            }
        }
        return "The requested user was not found.";
    }
    public String adminLogout(){
        isAdminLogin = false;
        return "The admin has successfully logged out.";
    }
}
