package Model;

import java.util.Date;

public class PremiumUser extends User {
    private Date subscriptionExpiration;

    public PremiumUser (String accountName , String password , String firstNameAndLastName , String email ,
                       String phoneNumber , String profileCover ,
                       Category category1 , Category category2 , Category category3 , Category category4){
        super(accountName , password , firstNameAndLastName , email , phoneNumber , profileCover , category1 , category2 , category3 , category4);
        setTsPremium(true);
    }

    public Date getSubscriptionExpiration() { return subscriptionExpiration; }
    public void setSubscriptionExpiration(Date subscriptionExpiration) { this.subscriptionExpiration = subscriptionExpiration; }
}
