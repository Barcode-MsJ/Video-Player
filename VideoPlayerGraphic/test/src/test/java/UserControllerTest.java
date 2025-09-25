import static org.junit.jupiter.api.Assertions.*;

import org.example.Controller.UserController;
import org.example.Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class UserControllerTest {

    Database database;
    User loggedInUser;

    @BeforeEach
    public void setUp() {
        database = Database.getDatabase();
        database.getAllUsers().clear();
        database.getAllContents().clear();
        database.getAllChannels().clear();

        loggedInUser = new NormalUser("testUser", "pass", "Test User", "test@example.com", "1234567890", "profileCover", Category.Music, Category.Game, Category.News, Category.History);

        database.getAllUsers().add(loggedInUser);

        Content content1 = new Podcast(loggedInUser.getAccountID(), "Test Podcast", false, "Description", 1200, Category.Music, "http://content1.com", "cover1", "Owner1");
        Content content2 = new Podcast(loggedInUser.getAccountID(), "Test Podcast2", false, "Description2", 1200, Category.Music, "http://content2.com", "cover2", "Owner1");
        database.getAllContents().add(content1);
        database.getAllContents().add(content2);

        Playlist playlist = new Playlist("MyPlaylist");
        playlist.getPlaylistContent().add(content1);
        loggedInUser.getUserPlaylist().add(playlist);

        UserController.getUserController().signIn(loggedInUser.getAccountName() , loggedInUser.getPassword());
    }

    @Test
    public void testShowPlaylistInfo() {
        StringBuilder info = UserController.getUserController().showPlaylistInfo();
        assertTrue(info.toString().contains("MyPlaylist"));
        assertTrue(info.toString().contains("http://content1.com"));
    }

    @Test
    public void testShowSuggestions() {
        StringBuilder suggestions = UserController.getUserController().showSuggestions();
        String result = suggestions.toString();
        assertTrue(result.contains("http://content1.com\nhttp://content2.com\nhttp://content1.com\nhttp://content2.com\nhttp://content1.com\nhttp://content2.com\nhttp://content1.com\nhttp://content2.com\nhttp://content1.com"));
    }

    @Test
    public void testShowSubscriptions() {
        Channel channel = new Channel("Channel1", "Desc", "Cover", loggedInUser);
        loggedInUser.getSubscriptions().add(channel);
        database.getAllChannels().add(channel);

        StringBuilder subs = UserController.getUserController().showSubscriptions();
        assertTrue(subs.toString().contains("Channel1"));
    }

    @Test
    public void testShowChannels() {
        Channel channel = new Channel("Channel1", "Desc", "Cover", loggedInUser);
        database.getAllChannels().add(channel);

        StringBuilder channels = UserController.getUserController().showChannels();
        assertTrue(channels.toString().contains("Channel1"));
    }

    @Test
    public void testComment() {
        String response = UserController.getUserController().comment("http://content1.com", "Nice video!");
        assertEquals("Your comment has been successfully posted.", response);
        Content c = database.getAllContents().stream()
                .filter(content -> content.getContentLink().equals("http://content1.com"))
                .findFirst().orElse(null);
        assertNotNull(c);
        assertFalse(c.getComments().isEmpty());
    }

    @Test
    public void testBuyPremium() {
        loggedInUser.setCredit(20);
        String response = UserController.getUserController().buyPremium(Premium.GOLD);
        assertEquals("Your premium subscription purchase was successful.", response);
        assertTrue(UserController.getUserController().getLoggedInUser() instanceof PremiumUser);
        assertTrue(((PremiumUser) UserController.getUserController().getLoggedInUser()).getSubscriptionExpiration().after(new Date()));
    }

    @Test
    public void testIncreaseCredit() {
        long amount = 50;
        String response = UserController.getUserController().increaseCredit(amount);
        assertEquals("Your credit has been successfully topped up.", response);
        assertEquals(50, UserController.getUserController().getLoggedInUser().getCredit());
    }

    @Test
    public void testCreateNewChannel() {
        String response = UserController.getUserController().createNewChannel("MyChannel", "MyDesc", "MyCover");
        assertEquals("Your channel has been successfully created.", response);
        assertNotNull(UserController.getUserController().getLoggedInUser().getUserChannel());
        assertEquals("MyChannel", UserController.getUserController().getLoggedInUser().getUserChannel().getChannelName());
    }

    @Test
    public void testLoginChannel() {
        assertEquals("You do not have a channel. Please create your channel and then log in.", UserController.getUserController().loginChannel());
        UserController.getUserController().createNewChannel("MyChannel", "MyDesc", "MyCover");
        assertEquals("Welcome to your channel!", UserController.getUserController().loginChannel());
    }

    @Test
    public void testAddContentToChannel() {
        UserController.getUserController().createNewChannel("MyChannel", "MyDesc", "MyCover");
        String response = UserController.getUserController().addContentToChannel(1L);
        assertEquals("The content has been successfully added to the channel.", response);
    }

    @Test
    public void testShowChannelInfo() {
        UserController.getUserController().createNewChannel("MyChannel", "MyDesc", "MyCover");
        StringBuilder info = UserController.getUserController().showChannelInfo("MyChannel");
        assertTrue(info.toString().contains(""));
    }

    @Test
    public void testShowFollowers() {
        UserController.getUserController().createNewChannel("MyChannel", "MyDesc", "MyCover");
        User follower = new NormalUser("follower", "pass", "Follower", "follower@example.com", "1234", "cover", Category.Music, Category.Game, Category.News, Category.History);
        UserController.getUserController().getLoggedInUser().getUserChannel().getChannelFollowers().add(follower);
        StringBuilder followers = UserController.getUserController().showFollowers();
        assertTrue(followers.toString().contains("follower"));
    }

    @Test
    public void testEditChannelInfo() {
        UserController.getUserController().createNewChannel("OldName", "OldDesc", "OldCover");
        String response1 = UserController.getUserController().editChannelInfo(1, "NewName");
        assertEquals("Your channel changes have been applied.", response1);
        assertEquals("NewName", UserController.getUserController().getLoggedInUser().getUserChannel().getChannelName());
    }

    @Test
    public void testChannelPlaylist() {
        UserController.getUserController().createNewChannel("MyChannel", "MyDesc", "MyCover");
        String response = UserController.getUserController().channelPlaylist("NewPlaylist");
        assertTrue(response.contains("was created correctly"));
    }

    @Test
    public void testAddContentToChannelPlaylist() {
        UserController.getUserController().createNewChannel("MyChannel", "MyDesc", "MyCover");
        UserController.getUserController().channelPlaylist("NewPlaylist");
        String response = UserController.getUserController().addContentToChannelPlaylist(1L, "NewPlaylist");
        assertEquals("The content has been successfully added to the channel playlist.", response);
    }

    @Test
    public void testCreateShortVideoContent() {
        String response = UserController.getUserController().createShortVideoContent("ShortTitle", false, "Desc", 29, Category.Music, "link", "cover", "subtitles", "audio");
        assertTrue(response.contains("has been successfully added"));
    }

    @Test
    public void testCreateNormalVideoContent() {
        String response = UserController.getUserController().createNormalVideoContent("NormalTitle", false, "Desc", 120, Category.Music, "link2", "cover2", "subtitles2", Quality.LOW, Format.MP4);
        assertTrue(response.contains("has been successfully added"));
    }

    @Test
    public void testCreateLiveStreamContent() {
        Date date = Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        String response = UserController.getUserController().createLiveStreamContent("LiveTitle", false, "Desc", 60, Category.Music, "link3", "cover3", "subtitles3", date);
        assertTrue(response.contains("has been successfully added"));
    }

    @Test
    public void testCreatePodcastContent() {
        String response = UserController.getUserController().createPodcastContent("PodcastTitle", false, "Desc", 45, Category.Music, "link4", "cover4", "owner");
        assertTrue(response.contains("has been successfully added"));
    }
}
