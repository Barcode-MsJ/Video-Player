package Model;

import java.util.ArrayList;

public abstract class User extends Account{
    private long credit = 0;
    private ArrayList<Playlist> userPlaylist;
    private Channel userChannel;
    private ArrayList<Channel> subscriptions;
    private ArrayList<Category> favoriteCategories = new ArrayList<>();
    private boolean isPremium = false;
    private boolean isBlocked = false;

    public User (String accountName , String password , String firstNameAndLastName , String email ,
                 String phoneNumber , String profileCover ,
                 Category category1 , Category category2 , Category category3 , Category category4){
        super(accountName , password , firstNameAndLastName , email , phoneNumber , profileCover);
        userPlaylist = new ArrayList<>();
        subscriptions = new ArrayList<>();
        favoriteCategories.add(category1);
        favoriteCategories.add(category2);
        favoriteCategories.add(category3);
        favoriteCategories.add(category4);
        userPlaylist.add(new Playlist("Liked"));
        userPlaylist.add(new Playlist("WatchLater"));
    }

    public long getCredit() { return credit; }
    public void setCredit(long credit) { this.credit = credit; }

    public ArrayList<Playlist> getUserPlaylist() { return userPlaylist; }
    public void setUserPlaylist(ArrayList<Playlist> userPlaylist) { this.userPlaylist = userPlaylist; }

    public void addPlaylist(String newPlaylistName){
        userPlaylist.add(new Playlist(newPlaylistName));
    }

    public Channel getUserChannel() { return userChannel; }
    public void setUserChannel(Channel userChannel) { this.userChannel = userChannel; }

    public ArrayList<Channel> getSubscriptions() { return subscriptions; }
    public void setSubscriptions(ArrayList<Channel> subscriptions) { this.subscriptions = subscriptions; }

    public ArrayList<Category> getFavoriteCategories() { return favoriteCategories; }
    public void setFavoriteCategories(ArrayList<Category> favoriteCategories) { this.favoriteCategories = favoriteCategories; }

    public boolean getIsPremium() { return isPremium; }
    public void setTsPremium(boolean isPremium) { this.isPremium = isPremium; }

    public boolean getIsBlocked() { return isBlocked; }
    public void setIsBlocked(boolean isBlocked) { this.isBlocked = isBlocked; }
}
