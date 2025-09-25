package org.example.Model;

public class NormalUser extends User {
    private final static int playlistContentCount = 10 ;
    private final static int playlistCount = 3 ;

    public NormalUser (String accountName , String password , String firstNameAndLastName , String email ,
                       String phoneNumber , String profileCover ,
                       Category category1 , Category category2 , Category category3 , Category category4){
        super(accountName , password , firstNameAndLastName , email , phoneNumber , profileCover , category1 , category2 , category3 , category4);
    }
}
