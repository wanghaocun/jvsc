package server;

/**
 * @author wanghc
 */
@SuppressWarnings("unused")
public interface Servlet {

    /**
     * 初始化
     * @throws Exception e
     */
    void init() throws Exception;

    /**
     * 销毁
     * @throws Exception e
     */
    void destroy() throws Exception;

    /**
     * 处理请求
     * @param request Request
     * @param response Response
     * @throws Exception e
     */
    void service(Request request, Response response) throws Exception;

}
