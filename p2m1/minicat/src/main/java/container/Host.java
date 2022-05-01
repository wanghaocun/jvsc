package container;

import server.Request;
import server.Servlet;

import java.util.*;

/**
 * @author wanghc
 */
@SuppressWarnings("unused")
public class Host {

    private String name;

    private List<Context> contexts;

    public Host(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addContext(Context context) {
        if (this.contexts == null) {
            this.contexts = new ArrayList<>();
        }
        this.contexts.add(context);
    }

    public List<Context> getContexts() {
        return contexts;
    }

    public void setContexts(List<Context> contexts) {
        this.contexts = contexts;
    }

    public Servlet getServlet(Request request) {
        if (contexts == null) {
            return null;
        }

        for (Context context : contexts) {
            String contextName = context.getName();
            if (!contextName.startsWith("/")) {
                contextName = "/" + contextName;
            }
            if (request.getUrl().startsWith(contextName)) {
                return context.getServlet(request, contextName);
            }
        }

        return null;
    }

}
