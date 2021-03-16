package container;

import server.Request;
import server.Servlet;
import servlet.WebappClassLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghc
 */
@SuppressWarnings("unused")
public class Context extends Container {

    private String name;

    private String contextPath;

    private List<Wrapper> wrappers;

    public Context(String name, String contextPath) {
        this.name = name;
        this.contextPath = contextPath;
        setClassLoader(new WebappClassLoader(contextPath));
    }

    public void addWrapper(Wrapper wrapper) {
        if (this.wrappers == null) {
            this.wrappers = new ArrayList<>();
        }
        this.wrappers.add(wrapper);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public List<Wrapper> getWrappers() {
        return wrappers;
    }

    public void setWrappers(List<Wrapper> wrappers) {
        this.wrappers = wrappers;
    }

    public Servlet getServlet(Request request, String contextPath) {
        if (wrappers == null) {
            return null;
        }

        String slash = "/";
        String uri = request.getUrl();
        if (!slash.equals(contextPath) && !"".equals(contextPath)) {
            uri = uri.replace(contextPath, "");
        }

        for (Wrapper wrapper : wrappers) {
            if (wrapper.matcher(uri)) {
                return wrapper.getServlet();
            }
        }

        return null;
    }
    
}
