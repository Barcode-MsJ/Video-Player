package org.example.Controller;

import org.example.Model.*;
import java.util.Date;

public class ContentController {
    private static ContentController contentController;

    private ContentController(){};
    public static ContentController getContentController(){
        if(contentController == null){
            contentController = new ContentController();
        }
        return contentController;
    }

    public static long createContentID (){
        return Database.getDatabase().getAllContents().size()+1 ;
    }

    public static void sortContent() {
        for (int i = 0 ; i < Database.getDatabase().getAllContents().size() ; i++) {
            for (int j = 0 ; j < Database.getDatabase().getAllContents().size() - 1 - i ; j++) {
                if (Database.getDatabase().getAllContents().get(j).getLikes() <
                        Database.getDatabase().getAllContents().get(j + 1).getLikes()) {
                    Content temp = Database.getDatabase().getAllContents().get(j);
                    Database.getDatabase().getAllContents().set(j , Database.getDatabase().getAllContents().get(j + 1));
                    Database.getDatabase().getAllContents().set(j + 1, temp);
                }
            }
        }
    }

    public static void sortContentByView() {
        for (int i = 0 ; i < Database.getDatabase().getAllContents().size() ; i++) {
            for (int j = 0 ; j < Database.getDatabase().getAllContents().size() - 1 - i ; j++) {
                if (Database.getDatabase().getAllContents().get(j).getViews() <
                        Database.getDatabase().getAllContents().get(j + 1).getViews()) {
                    Content temp = Database.getDatabase().getAllContents().get(j);
                    Database.getDatabase().getAllContents().set(j , Database.getDatabase().getAllContents().get(j + 1));
                    Database.getDatabase().getAllContents().set(j + 1, temp);
                }
            }
        }
    }

    public static void sortContentByDate() {
        for (int i = 0 ; i < Database.getDatabase().getAllContents().size() ; i++) {
            for (int j = 0 ; j < Database.getDatabase().getAllContents().size() - 1 - i ; j++) {
                if (Database.getDatabase().getAllContents().get(j).getReleaseDate().before(Database.getDatabase().getAllContents().get(j + 1).getReleaseDate())) {
                    Content temp = Database.getDatabase().getAllContents().get(j);
                    Database.getDatabase().getAllContents().set(j , Database.getDatabase().getAllContents().get(j + 1));
                    Database.getDatabase().getAllContents().set(j + 1, temp);
                }
            }
        }
    }

    public static StringBuilder filterContentByVideo(){
        StringBuilder filterContentByVideoString = new StringBuilder();
        for(Content content : Database.getDatabase().getAllContents()){
            if(content instanceof NormalVideo || content instanceof ShortVideo || content instanceof LiveStream){
                filterContentByVideoString.append(content.getContentTitle()).append(" : ").append(content.getContentLink()).append("\n");
            }
        }
        return filterContentByVideoString;
    }

    public static StringBuilder filterContentByPodcast(){
        StringBuilder filterContentByPodcastString = new StringBuilder();
        for(Content content : Database.getDatabase().getAllContents()){
            if(content instanceof Podcast){
                filterContentByPodcastString.append(content.getContentTitle()).append(" : ").append(content.getContentLink()).append("\n");
            }
        }
        return filterContentByPodcastString;
    }

    public static StringBuilder filterContentByCategory(Category category){
        StringBuilder filterContentByCategoryString = new StringBuilder();
        for(Content content : Database.getDatabase().getAllContents()){
            if(content.getCategory().equals(category)){
                filterContentByCategoryString.append(content.getContentTitle()).append(" : ").append(content.getContentLink()).append("\n");
            }
        }
        return filterContentByCategoryString;
    }

    public static StringBuilder filterContentByDate(Date startDate , Date endDate){
        StringBuilder filterContentByDateString = new StringBuilder();
        for(Content content : Database.getDatabase().getAllContents()){
            if(content.getReleaseDate().before(endDate) && content.getReleaseDate().after(startDate)){
                filterContentByDateString.append(content.getContentTitle()).append(" : ").append(content.getContentLink()).append("\n");
            }
        }
        return filterContentByDateString;
    }
}
