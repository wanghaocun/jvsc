package container;

import server.Request;
import server.Servlet;

import java.util.*;

/**
 * @author wanghc
 */
@SuppressWarnings("unused")
public class Mapper {

    private List<Host> hosts;

    public void addHost(Host host) {
        if (this.hosts == null) {
            this.hosts = new ArrayList<>();
        }
        this.hosts.add(host);
    }

    public List<Host> getHosts() {
        return hosts;
    }

    public void setHosts(List<Host> hosts) {
        this.hosts = hosts;
    }

    public Servlet getServlet(Request request) {
        return Optional.ofNullable(hosts)
                .orElse(Collections.emptyList())
                .stream()
                .filter(o -> Objects.equals(o.getName(), request.getHost()))
                .findFirst()
                .map(o -> o.getServlet(request))
                .orElse(null);
    }

}
