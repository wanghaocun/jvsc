package servlet;

import server.HttpProtocolUtil;
import server.HttpServlet;
import server.Request;
import server.Response;

import java.io.IOException;

/**
 * @author wanghc
 */
public class LagouServlet extends HttpServlet {

    @Override
    public void doGet(Request request, Response response) {
        String content = "<h1>LagouServlet.doGet from demo1</h1>";

        try {
            response.output((HttpProtocolUtil.getHttpHeader200(content.getBytes().length) + content));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(Request request, Response response) {
        String content = "<h1>LagouServlet.doPost from demo1</h1>";

        try {
            response.output((HttpProtocolUtil.getHttpHeader200(content.getBytes().length) + content));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
        System.out.println("LagouServlet.init from demo1");
    }

    @Override
    public void destroy() {
        System.out.println("LagouServlet.destroy from demo1");
    }

}
