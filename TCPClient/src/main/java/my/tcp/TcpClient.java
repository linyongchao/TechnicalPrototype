package my.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @author lin
 * @date 2022/3/14 10:23
 */
public class TcpClient {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 6060);
            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            // 客户端输入
            writer.write("客户端输入信息");
            writer.flush();
            //只关闭输出流而不关闭连接
            socket.shutdownOutput();

            // 服务端返回
            String info;
            while ((info = bufferedReader.readLine()) != null) {
                System.out.println("客户端接收：" + info);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
