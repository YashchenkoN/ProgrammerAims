package com.programmer.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;

/**
 * Created by kolyan on 8/7/15.
 */
public class LinkGenerationUtil {
    public static String getActivationLink(HttpServletRequest req, String activationKey) {
        String result = "";
        try {
            URL url = new URL(req.getRequestURL().toString());
            result += "http://";
            result += url.getHost() + ":";
            result += url.getPort();
        } catch (Exception e) {
            e.printStackTrace();
        }
        result += "/activate/" + activationKey;
        return result;
    }
}
