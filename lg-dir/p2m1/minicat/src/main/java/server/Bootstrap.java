package server;

import container.Context;
import container.Host;
import container.Mapper;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import servlet.ContextLoader;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.*;

/**
 * Minicat的主类
 *
 * @author wanghc
 */
@SuppressWarnings("all")
public class Bootstrap {

    /**
     * 定义socket监听的端口号
     */
    private int port = 8080;

    private final Mapper mapper = new Mapper();

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Minicat 的程序启动入口
     */
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Minicat启动需要初始化展开的一些操作
     */
    public void start() throws Exception {

        // 加载解析server.xml
        load();

        // 定义一个线程池
        int corePoolSize = 10;
        int maximumPoolSize = 50;
        long keepAliveTime = 100L;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(50);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                threadFactory,
                handler
        );

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("=====>>Minicat start on port：" + port);

        /*
         * 完成Minicat 1.0版本
         * 需求：浏览器请求 http://localhost:8080, 返回一个固定的字符串到页面"Hello Minicat!"
         */
        /*while (true) {
            Socket socket = serverSocket.accept();
            // 有了socket，接收到请求，获取输出流
            OutputStream outputStream = socket.getOutputStream();
            String data = "Hello Minicat!";
            String responseText = HttpProtocolUtil.getHttpHeader200(data.getBytes().length) + data;
            outputStream.write(responseText.getBytes());
            socket.close();
        }*/

        /*
         * 完成Minicat 2.0版本
         * 需求：封装Request和Response对象，返回html静态资源文件
         */
        /*while (true) {
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();

            // 封装Request对象和Response对象
            Request request = new Request(inputStream);
            Response response = new Response(socket.getOutputStream());

            response.outputHtml(request.getUrl());
            socket.close();
        }*/

        /*
         * 完成Minicat 3.0版本
         * 需求：可以请求动态资源（Servlet）
         */
        /*while (true) {
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();

            // 封装Request对象和Response对象
            Request request = new Request(inputStream);
            Response response = new Response(socket.getOutputStream());

            // 静态资源处理
            if (servletMap.get(request.getUrl()) == null) {
                response.outputHtml(request.getUrl());
            } else {
                // 动态资源servlet请求
                HttpServlet httpServlet = servletMap.get(request.getUrl());
                httpServlet.service(request, response);
            }
            socket.close();
        }*/

        /*
         * 多线程改造（不使用线程池）
         */
        /*while (true) {
            Socket socket = serverSocket.accept();
            RequestProcessor requestProcessor = new RequestProcessor(socket, servletMap);
            requestProcessor.start();
        }*/

        /*
         * 多线程改造（使用线程池）
         */
        while (true) {
            Socket socket = serverSocket.accept();
            RequestProcessor requestProcessor = new RequestProcessor(socket, mapper);
            //requestProcessor.start();
            threadPoolExecutor.execute(requestProcessor);
        }
    }

    /**
     * 加载解析server.xml
     */
    private void load() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("server.xml");
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(inputStream);
            Element rootElement = document.getRootElement();

            Element connectorElement = (Element) rootElement.selectSingleNode("//Connector");
            String port = connectorElement.attributeValue("port");
            setPort(Integer.parseInt(port));

            List<Element> hostElements = rootElement.selectNodes("//Host");
            for (Element hostElement : hostElements) {
                String hostname = hostElement.attributeValue("name");
                String appBase = hostElement.attributeValue("appBase");

                Host host = new Host(hostname);

                List<Element> contextElements = hostElement.selectNodes("Context");
                if (contextElements != null) {
                    for (Element contextElement : contextElements) {
                        String contextName = contextElement.attributeValue("name");
                        String path = contextElement.attributeValue("path");

                        Context context = new Context(contextName, appBase + "/" + path);
                        ContextLoader contextLoader = new ContextLoader(context);
                        contextLoader.load();

                        host.addContext(context);
                    }
                } else {
                    Context context = new Context("/", appBase);
                    ContextLoader contextLoader = new ContextLoader(context);
                    contextLoader.load();

                    host.addContext(context);
                }
                mapper.addHost(host);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
