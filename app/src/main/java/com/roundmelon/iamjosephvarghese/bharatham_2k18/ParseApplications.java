package com.roundmelon.iamjosephvarghese.bharatham_2k18;



import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

public class ParseApplications {
    private String xmlData;
    private ArrayList<Application> applications;

    public ParseApplications(String xmlData) {
        this.xmlData = xmlData;
        applications = new ArrayList<Application>();
    }

    public ArrayList<Application> getApplications() {
        return applications;
    }

    public boolean process(){
        boolean status = true;
        Application currentRecord = null;
        boolean inEntry = false;
        String textValue = "";

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(this.xmlData));
            int eventType = xpp.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT){
                String tagName = xpp.getName();
                switch(eventType){
                    case XmlPullParser.START_TAG:
                        // Log.d("ParseApplications","starting tag for " + tagName);
                        if(tagName.equalsIgnoreCase("entry")){
                            inEntry = true;
                            currentRecord = new Application();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        // Log.d("ParseApplications","Ending tag for " + tagName);
                        if(inEntry){
                            if(tagName.equalsIgnoreCase("entry")){
                                applications.add(currentRecord);
                                inEntry = false;
                            }
                            else if(tagName.equalsIgnoreCase("title")){
                                currentRecord.setTitle(textValue);
                            }
                            else if(tagName.equalsIgnoreCase("content")){
                                currentRecord.setContent(textValue.substring(textValue.indexOf(">")+1,textValue.lastIndexOf("<")).replace("<br />","\n"));
                            }

                        }
                        break;

                    default:
                        //nothing else to do


                }

                eventType = xpp.next();
            }



        }catch(Exception e) {
            status = false;
            e.printStackTrace();
        }

        for(Application app : applications){
            Log.d("ParseApplications","******");
            Log.d("ParseApplications","Name" + app.getTitle());
            Log.d("ParseApplications","Content" + app.getContent());
//            Log.d("ParseApplications","Artist" + app.getLink());
//            Log.d("ParseApplications","Release Date" + app.getPubDate());
        }
        return true;
    }

}
