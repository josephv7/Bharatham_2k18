package com.roundmelon.iamjosephvarghese.bharatham_2k18;


public class Application {

    private String title;

    public Application(String title) {
        this.title = title;
    }
    public Application() {

    }
    private String content;




//    public void setPubDate(String pubDate) {
//        this.pubDate = pubDate;
//    }

    @Override
    public String toString() {

        return getTitle() + "\n" + getContent();


//        return "Title: " + getTitle() + "\n" ;
                //+
//                "Link: " + getLink() + "\n" +
//                "Published Date: " + getPubDate() + "\n";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
