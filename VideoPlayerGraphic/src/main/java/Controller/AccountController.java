package Controller;

import Model.Database;
import java.util.regex.*;


public class AccountController {
    private static AccountController accountController;

    private AccountController(){};
    public static AccountController getAccountController(){
        if(accountController == null){
            accountController = new AccountController();
        }
        return accountController;
    }

    public static long createAccountID (){
        return Database.getDatabase().getAllUsers().size()+1 ;
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
        return Pattern.matches(emailRegex, email);
    }

    public static boolean isValidPhone(String phone) {
        String phoneRegex = "^09\\d{9}$";
        return Pattern.matches(phoneRegex, phone);
    }
}
