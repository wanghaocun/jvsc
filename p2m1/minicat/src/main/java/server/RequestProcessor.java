package server;

import container.Mapper;

import java.io.InputStream;
import java.net.Socket;

/**
 * @author wanghc
 */
public class RequestProcessor extends Thread {

    private final Socket socket;
    private final Mapper mapper;

    public RequestProcessor(Socket socket, Mapper mapper) {
        this.socket = socket;
        this.mapper = mapper;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();

            // 封装Request对象和Response对象
            Request request = new Request(inputStream);
            Response response = new Response(socket.getOutputStream());

            Servlet servlet = mapper.getServlet(request);
            // 静态资源处理
            if (servlet == null) {
                response.outputHtml(request.getUrl());
            } else {
                // 动态资源servlet请求
                servlet.service(request, response);
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
