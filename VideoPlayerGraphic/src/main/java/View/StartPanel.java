package View;

import java.text.ParseException;
import java.util.*;
import Model.*;
import Controller.*;

public class StartPanel {
    static Scanner sc = new Scanner(System.in);
    public static void showPanel() throws ParseException {
        System.out.println("Welcome to Video Player!!");
        while (true) {
            System.out.println("1. Signup ([signup] - [accountName] - [password] - [firstNameAndLastName] - [email] - [phoneNumber] - [profileCover] - [Favorite category1] - [Favorite category2] - [Favorite category3] - [Favorite category4])" +
                    "\n 2. Login ([login] - [accountName] - [password])" + "\n 3. AdminLogin ([adminLogin] - [accountName] - [password])" +
                    "\n  4. Quit application (Quit)");
            String command = sc.nextLine();
            String[] commandInfo = command.split(" - ");

            if(commandInfo[0].equals("signup")){
                if (AccountController.isValidEmail(commandInfo[4]) && AccountController.isValidPhone(commandInfo[5])) {
                    String returnText = UserController.getUserController().signUp(commandInfo[1], commandInfo[2], commandInfo[3], commandInfo[4], commandInfo[5], commandInfo[6], Category.valueOf(commandInfo[7]), Category.valueOf(commandInfo[8]), Category.valueOf(commandInfo[9]), Category.valueOf(commandInfo[10]));
                    System.out.println(returnText);
                }else {
                    System.out.println("Your Email of phoneNumber is not Valid");
                }
            }else if(commandInfo[0].equals("login")){
                String returnText = UserController.getUserController().signIn(commandInfo[1] , commandInfo[2]);
                System.out.println(returnText);
                if(returnText.equals("Your login was successful.")){
                    UserPanel.showPanel();
                }
            }else if(commandInfo[0].equals("adminLogin")){
                String returnText = AdminController.getAdminController().adminLogin(commandInfo[1] , commandInfo[2]);
                System.out.println(returnText);
                if(returnText.equals("Your login was successful.")){
                    AdminPanel.showPanel();
                }
            }else return;
        }
    }
}
