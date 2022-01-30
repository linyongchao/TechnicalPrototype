package my.http;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;
import java.io.IOException;
import java.net.InetSocketAddress;
import my.http.handler.TimeHandler;
import my.http.handler.UUIDHandler;

/**
 * @author lin
 * @date 2022/1/30 22:40
 */
public class Main {

    public static void main(String[] args) throws IOException {
        start(11111, 100, "/time", new TimeHandler());
        start(22222, 100, "/uuid", new UUIDHandler());
        System.out.println("服务已启动");
    }

    public static <T extends HttpHandler> void start(int port, int maxClient, String path, T t) throws IOException {
        HttpServerProvider provider = HttpServerProvider.provider();
        // 设置监听端口和可接受的最大客户端链接数
        HttpServer httpserver = provider.createHttpServer(new InetSocketAddress(port), maxClient);
        httpserver.createContext(path, t);
        httpserver.setExecutor(null);
        httpserver.start();
    }

}
