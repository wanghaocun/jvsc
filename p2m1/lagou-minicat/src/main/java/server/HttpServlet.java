package server;

/**
 * @author wanghc
 */
public abstract class HttpServlet implements Servlet {

    /**
     * get请求标志
     */
    public static final String GET = "GET";

    /**
     * 处理get请求
     *
     * @param request  Request
     * @param response Response
     */
    public abstract void doGet(Request request, Response response);

    /**
     * 处理post请求
     *
     * @param request  Request
     * @param response Response
     */
    public abstract void doPost(Request request, Response response);

    @Override
    public void service(Request request, Response response) {

        if (GET.equalsIgnoreCase(request.getMethod())) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

}
