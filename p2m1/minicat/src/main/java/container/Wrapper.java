package container;

import server.Servlet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wanghc
 */
@SuppressWarnings("unused")
public class Wrapper extends Container {

    private String urlPattern;

    private Servlet servlet;

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public Servlet getServlet() {
        return servlet;
    }

    public void setServlet(Servlet servlet) {
        this.servlet = servlet;
    }

    public boolean matcher(String uri) {
        Pattern compile = Pattern.compile(urlPattern);
        Matcher matcher = compile.matcher(uri);

        return matcher.find();
    }
}
