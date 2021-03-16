package servlet;

import server.HttpProtocolUtil;
import server.HttpServlet;
import server.Request;
import server.Response;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author wanghc
 */
public class LagouServlet extends HttpServlet {

    @Override
    public void doGet(Request request, Response response) {
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String content = String.format("<h1>LagouServlet.doGet from demo2. [%s]</h1>", currentTime);

        try {
            response.output((HttpProtocolUtil.getHttpHeader200(content.getBytes().length) + content));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(Request request, Response response) {
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String content = String.format("<h1>LagouServlet.doPost from demo2. [%s]</h1>", currentTime);

        try {
            response.output((HttpProtocolUtil.getHttpHeader200(content.getBytes().length) + content));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
        System.out.println("LagouServlet.init from demo2");
    }

    @Override
    public void destroy() {
        System.out.println("LagouServlet.destroy from demo2");
    }

}
