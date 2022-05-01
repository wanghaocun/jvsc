package server;

import java.io.IOException;
import java.io.InputStream;

/**
 * 把请求信息封装为Request对象（根据InputSteam输入流封装）
 *
 * @author wanghc
 */
@SuppressWarnings("all")
public class Request {

    /**
     * 请求方式，比如GET/POST
     */
    private String method;

    /**
     * 例如 /,/index.html
     */
    private String url;

    /**
     * 主机
     */
    private String host;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 输入流，其他属性从输入流中解析出来
     */
    private InputStream inputStream;

    public Request() {
    }

    /**
     * 构造器，输入流传入
     *
     * @param inputStream InputStream
     * @throws IOException e
     */
    public Request(InputStream inputStream) throws IOException {
        this.inputStream = inputStream;

        // 从输入流中获取请求信息
        int count = 0;
        while (count == 0) {
            count = inputStream.available();
        }

        byte[] bytes = new byte[count];
        inputStream.read(bytes);

        String inputStr = new String(bytes);
        // 获取第一行请求头信息 // GET / HTTP/1.1
        String firstLineStr = inputStr.split("\\r\\n")[0];

        String[] strings = firstLineStr.split(" ");

        this.method = strings[0];
        this.url = strings[1];

        System.out.println("=====>>method:" + method);
        System.out.println("=====>>url:" + url);

        String hostPort = inputStr.split("\\r\\n")[1].split(" ")[1];
        this.host = hostPort.split(":")[0];
        String port = hostPort.split(":")[1];
        if (port == null || port.isBlank()) {
            this.port = 80;
        } else {
            this.port = Integer.parseInt(port);
        }
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

}
