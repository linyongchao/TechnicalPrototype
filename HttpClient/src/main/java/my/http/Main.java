package my.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author lin
 * @date 2022/1/30 22:59
 */
public class Main {

    public static void main(String[] args) throws IOException {
        String time = post("http://localhost:11111/time", "yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(time);
        String uuid = post("http://localhost:22222/uuid", null);
        System.out.println(uuid);
    }

    public static String post(String url, String param) throws IOException {
        URL httpUrl = new URL(url);
        HttpURLConnection urlConn = (HttpURLConnection) httpUrl.openConnection();
        urlConn.setDoOutput(true);
        urlConn.setDoInput(true);
        urlConn.setRequestMethod("POST");
        if (param != null) {
            OutputStream out = urlConn.getOutputStream();
            out.write(param.getBytes());
            out.flush();
        }
        StringBuilder sb = new StringBuilder();
        if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            InputStream in = urlConn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            in.close();
            urlConn.disconnect();
        }
        return sb.toString();
    }
}
