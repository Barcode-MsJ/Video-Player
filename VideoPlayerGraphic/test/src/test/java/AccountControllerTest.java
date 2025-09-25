
import static org.junit.jupiter.api.Assertions.*;

import org.example.Controller.AccountController;
import org.junit.jupiter.api.Test;

public class AccountControllerTest {

    @Test
    public void testIsValidEmail() {
        assertTrue(AccountController.isValidEmail("test@example.com"));
        assertTrue(AccountController.isValidEmail("user.name@mail.co"));
        assertTrue(AccountController.isValidEmail("user-name@mail.org"));

        assertFalse(AccountController.isValidEmail("test@example"));
        assertFalse(AccountController.isValidEmail("test@.com"));
        assertFalse(AccountController.isValidEmail("user@@mail.com"));
        assertFalse(AccountController.isValidEmail("user mail@mail.com"));
        assertFalse(AccountController.isValidEmail("user@mail.c"));
    }

    @Test
    public void testIsValidPhone() {
        assertTrue(AccountController.isValidPhone("09123456789"));
        assertTrue(AccountController.isValidPhone("09987654321"));

        assertFalse(AccountController.isValidPhone("9123456789"));
        assertFalse(AccountController.isValidPhone("08123456789"));
        assertFalse(AccountController.isValidPhone("0912345678"));
        assertFalse(AccountController.isValidPhone("091234567890"));
        assertFalse(AccountController.isValidPhone("09abcdefghij"));
    }
}
