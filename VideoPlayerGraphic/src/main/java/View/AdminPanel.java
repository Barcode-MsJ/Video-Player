package View;

import Controller.AdminController;

import java.util.Scanner;

public class AdminPanel {
    static Scanner sc = new Scanner(System.in);
    public static void showPanel() {
        System.out.println("Welcome to Admin Panel!!");
        while (true){
            System.out.println("1.showBestChannels\n" + "2.showBestContent\n" + "3.showUserInfo\n" + "4.showContentInfo\n" +
                    "5.showReports\n" + "6.deleteContent ([contentID])\n" + "7.blockUser ([userID])\n" + "8.unblockUser ([userID])\n" + "9.adminLogout\n");
            String command2 = sc.nextLine();
            String[] commandInfo = command2.split(" - ");
            if(commandInfo[0].equals("showBestChannels")){
                System.out.println(AdminController.getAdminController().showBestChannels());
            }else if (commandInfo[0].equals("showBestContent")){
                System.out.println(AdminController.getAdminController().showBestContent());
            }else if (commandInfo[0].equals("showUserInfo")){
                System.out.println(AdminController.getAdminController().showUserInfo());
            }else if (commandInfo[0].equals("showContentInfo")){
                System.out.println(AdminController.getAdminController().showContentInfo());
            }else if (commandInfo[0].equals("showReports")){
                System.out.println(AdminController.getAdminController().showReports());
            }else if (commandInfo[0].equals("deleteContent")){
                System.out.println(AdminController.getAdminController().deleteContent(Long.parseLong(commandInfo[1])));
            }else if (commandInfo[0].equals("blockUser")){
                System.out.println(AdminController.getAdminController().blockUser(Long.parseLong(commandInfo[1])));
            }else if (commandInfo[0].equals("unblockUser")){
                System.out.println(AdminController.getAdminController().unblockUser(Long.parseLong(commandInfo[1])));
            }else {
                System.out.println(AdminController.getAdminController().adminLogout());
            }
        }
    }
}
