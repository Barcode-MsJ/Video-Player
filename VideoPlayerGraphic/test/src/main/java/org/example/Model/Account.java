package org.example.Model;


import org.example.Controller.AccountController;

public abstract class Account {
    private long accountID;
    private String accountName;
    private String password;
    private String firstNameAndLastName;
    private String email;
    private String phoneNumber;
    private String profileCover;

    public Account (String accountName , String password , String firstNameAndLastName , String email ,
                    String phoneNumber , String profileCover){
        this.accountID = AccountController.createAccountID();
        this.accountName = accountName;
        this.password = password;
        this.firstNameAndLastName = firstNameAndLastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profileCover = profileCover;
    }

    public long getAccountID() { return accountID; }
    public void setAccountID(long accountID) { this.accountID = accountID; }

    public String getAccountName() { return accountName; }
    public void setAccountName(String accountName) { this.accountName = accountName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFirstNameAndLastName() { return firstNameAndLastName; }
    public void setFirstNameAndLastName(String firstNameAndLastName) { this.firstNameAndLastName = firstNameAndLastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getProfileCover() { return profileCover; }
    public void setProfileCover(String profileCover) { this.profileCover = profileCover; }
}
