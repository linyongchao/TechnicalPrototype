package my.http.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lin
 * @date 2022/1/30 22:42
 */
public class TimeHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        // 获得输入流
        InputStream inputStream = httpExchange.getRequestBody();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        System.out.println("输入内容：" + sb.toString());
        SimpleDateFormat sdf = new SimpleDateFormat(sb.toString());
        String result = sdf.format(new Date());
        System.out.println("输出内容：" + result);
        // 设置响应头属性及响应信息的长度
        httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, result.getBytes().length);
        // 获得输出流
        OutputStream out = httpExchange.getResponseBody();
        out.write(result.getBytes());
        out.flush();
        httpExchange.close();
    }
}
