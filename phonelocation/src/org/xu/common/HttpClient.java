package org.xu.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 构建一个http请求
 */
public class HttpClient {

    public static String doGet(String httpUrl, String charset){
        StringBuffer buffer = new StringBuffer();
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;

        try {
            URL url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.connect();

            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(is, charset));

                String temp;
                while ( (temp = br.readLine()) != null) {
                    buffer.append(temp).append("\r\n");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                connection.disconnect();
            }
        }


        return buffer.toString();
    }

}
