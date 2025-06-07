package Model;

import java.util.ArrayList;

public class Database {
    private static Database database;
    private ArrayList<User> allUsers;
    private ArrayList<Content> allContents;
    private ArrayList<Report> allReports;
    private ArrayList<Channel> allChannels;
    private Admin admin;

    private Database (){
        allUsers = new ArrayList<>();
        allContents = new ArrayList<>();
        allReports = new ArrayList<>();
        allChannels = new ArrayList<>();
    }

    public static Database getDatabase() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    public ArrayList<User> getAllUsers() { return allUsers; }
    public void setAllUsers(ArrayList<User> allUsers) { this.allUsers = allUsers; }

    public ArrayList<Content> getAllContents() { return allContents; }
    public void setAllContents(ArrayList<Content> allContents) { this.allContents = allContents; }

    public ArrayList<Report> getAllReports() { return allReports; }
    public void setAllReports(ArrayList<Report> allReports) { this.allReports = allReports; }

    public ArrayList<Channel> getAllChannels() { return allChannels; }
    public void setAllChannels(ArrayList<Channel> allChannels) { this.allChannels = allChannels; }

    public Admin getAdmin() { return admin; }
    public void setAdmin(Admin admin) { this.admin = admin; }
}
