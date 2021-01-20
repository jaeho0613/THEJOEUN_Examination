package rss;

import android.content.Context;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import database.data.BaseNews;

public class RssParsing {

    public static HashMap<Long, BaseNews> parsing(String parsingUrl) {
        HashMap<Long, BaseNews> baseNewsList = new HashMap<>();

        try {
            URL url = new URL(parsingUrl);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            parser.setInput(bis, null);

            String title = null;
            String content = null;
            String author = null;
            String link = null;
            Long guid = null;

            int parserEvent = parser.getEventType();
            boolean isItemTag = false;
            String tag = "";

            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                switch (parserEvent) {
                    case XmlPullParser.START_TAG:
                        tag = parser.getName();
                        if (tag.equals("item")) isItemTag = true;
                        break;

                    case XmlPullParser.TEXT:
                        if (isItemTag) {
                            if (tag.equals("guid")) {
                                guid = Long.parseLong(parser.getText());
                                //Log.d("Debug", "Guid : " + guid);

                            } else if (tag.equals("title")) {
                                title = parser.getText();
                                //Log.d("Debug", "Title : " + title);

                            } else if (tag.equals("description")) {
                                content = parser.getText();
                                //Log.d("Debug", "description : " + content);

                            } else if (tag.equals("author")) {
                                author = parser.getText();
                                //Log.d("Debug", "author : " + author);

                            } else if (tag.equals("link")) {
                                link = parser.getText();
                                //Log.d("Debug", "link : " + link);
                            }
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {

                            BaseNews baseNews = new BaseNews();
                            baseNews.title = title;
                            baseNews.author = author;
                            baseNews.content = content;
                            baseNews.link = link;
                            baseNewsList.put(guid, baseNews);

                            title = null;
                            content = null;
                            author = null;
                            link = null;
                            guid = null;
                            isItemTag = false;
                        }
                        tag = "";
                        break;
                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Debug", "Error : " + e);
        }

        return baseNewsList;
    }
}