
import org.example.Controller.AdminController;
import org.example.Model.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AdminControllerTest {
    private AdminController adminController;
    private Database db;

    @BeforeEach
    public void setup() {
        db = Database.getDatabase();

        db.setAllUsers(new ArrayList<>());
        db.setAllContents(new ArrayList<>());
        db.setAllReports(new ArrayList<>());
        db.setAllChannels(new ArrayList<>());

        Admin.getAdmin("MsJ" ,"666666" ,"MohammadSadeghJeihani",
                "jeihanim@gmail.com","09051419917","src/main/resources/image/youtube.png");

        User user = new NormalUser("user1", "pass1", "User One", "user1@example.com", "09112223344", "", Category.Music, Category.Game, Category.News, Category.History);
        db.getAllUsers().add(user);

        Content content = new Podcast(user.getAccountID(), "Test Podcast", false, "Description", 1200, Category.Music, "link1", "cover1", "Owner1");
        db.getAllContents().add(content);

        adminController = AdminController.getAdminController();
    }

    @Test
    public void testAdminLoginSuccess() {
        String result = adminController.adminLogin("MsJ", "666666");
        assertEquals("Your login was successful.", result);
    }

    @Test
    public void testAdminLoginFailure() {
        String result = adminController.adminLogin("MsJ", "333");
        assertEquals("Login failed. Please try again.", result);
    }

    @Test
    public void testBlockAndUnblockUser() {
        adminController.adminLogin("MsJ", "666666");
        User user = db.getAllUsers().get(0);

        String blockResult = adminController.blockUser(user.getAccountID());
        assertEquals("The selected user has been successfully blocked.", blockResult);
        assertTrue(user.getIsBlocked());

        String unblockResult = adminController.unblockUser(user.getAccountID());
        assertEquals("The selected user has been successfully unblocked.", unblockResult);
        assertFalse(user.getIsBlocked());
    }

    @Test
    public void testDeleteContent() {
        adminController.adminLogin("MsJ", "666666");
        Content content = db.getAllContents().get(0);

        String deleteResult = adminController.deleteContent(content.getContentID());
        assertEquals("The selected content has been successfully deleted.", deleteResult);
        assertTrue(db.getAllContents().isEmpty());
    }

    @Test
    public void testAdminLogout() {
        adminController.adminLogin("MsJ", "666666");
        String logoutResult = adminController.adminLogout();
        assertEquals("The admin has successfully logged out.", logoutResult);
    }
}
