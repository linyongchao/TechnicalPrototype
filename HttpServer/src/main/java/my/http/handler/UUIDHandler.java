package my.http.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.UUID;

/**
 * @author lin
 * @date 2022/1/30 22:54
 */
public class UUIDHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String result = UUID.randomUUID().toString();
        // 设置响应头属性及响应信息的长度
        httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, result.getBytes().length);
        // 获得输出流
        OutputStream out = httpExchange.getResponseBody();
        out.write(result.getBytes());
        out.flush();
        httpExchange.close();
    }
}
