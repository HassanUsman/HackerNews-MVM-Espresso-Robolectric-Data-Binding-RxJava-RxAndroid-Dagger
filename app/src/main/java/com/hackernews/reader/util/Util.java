package com.hackernews.reader.util;

import org.ocpsoft.prettytime.PrettyTime;

import java.net.URI;
import java.util.Date;

/**
 * Created by HassanUsman on 13/11/2017.
 */

@SuppressWarnings("ALL")
public class Util {

    public static String getPrettyTime(long unixTimestamp){
        PrettyTime p = new PrettyTime();
        Date newsDate = new Date(unixTimestamp*1000L);
        return p.format(newsDate);
    }

    public static String getHostname(String url){
        //Log.i(AppLog.LOG_TAG,"url: "+url);

        if(url == null){
            return "";
        }

        URI uri;
        try {
            uri = new URI(url);
        } catch (Exception e) {
            return "";
        }

        String hostname = uri.getHost();
        if (hostname != null) {
            return hostname.startsWith("www.") ? hostname.substring(4) : hostname;
        }

        return "";
    }
}
