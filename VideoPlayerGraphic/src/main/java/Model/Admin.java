package Model;

public class Admin extends Account{
    private static Admin admin;

    private Admin(String accountName , String password , String firstNameAndLastName , String email ,
                  String phoneNumber , String profileCover){
        super(accountName , password , firstNameAndLastName , email , phoneNumber , profileCover);
        Database.getDatabase().setAdmin(admin);
    }

    public static Admin getAdmin(String accountName , String password , String firstNameAndLastName , String email ,
                                 String phoneNumber , String profileCover) {
        if (admin == null) {
            admin = new Admin(accountName , password , firstNameAndLastName , email , phoneNumber , profileCover);
            Database.getDatabase().setAdmin(admin);
        }
        return admin;
    }
    public static Admin getAdmin() {
        if (admin == null) {
            throw new IllegalStateException("Admin instance has not been initialized yet.\n");
        }
        return admin;
    }
}
