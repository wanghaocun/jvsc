package servlet;

import container.Context;
import container.Wrapper;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import server.Servlet;

import java.io.FileInputStream;
import java.util.List;

/**
 * @author wanghc
 */
public class ContextLoader {

    private static final String WEB = "/WEB-INF/web.xml";

    private final Context context;
    private final String contextPath;
    private final ClassLoader classLoader;

    public ContextLoader(Context context) {
        this.context = context;
        this.contextPath = context.getContextPath();
        this.classLoader = context.getClassLoader();
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    public void load() {
        try {
            FileInputStream inputStream = new FileInputStream(contextPath + WEB);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);
            Element rootElement = document.getRootElement();

            List<Element> selectNodes = rootElement.selectNodes("//servlet");
            for (Element element : selectNodes) {
                Element servletNameElement = (Element) element.selectSingleNode("servlet-name");
                String servletName = servletNameElement.getStringValue();

                Element servletClassElement = (Element) element.selectSingleNode("servlet-class");
                String servletClass = servletClassElement.getStringValue();

                Element servletMapping = (Element) rootElement
                        .selectSingleNode("/web-app/servlet-mapping[servlet-name='" + servletName + "']");
                String urlPattern = servletMapping.selectSingleNode("url-pattern").getStringValue();

                Wrapper wrapper = new Wrapper();
                wrapper.setUrlPattern(urlPattern);

                wrapper.setServlet((Servlet) classLoader.loadClass(servletClass).newInstance());
                wrapper.setClassLoader(classLoader);
                context.addWrapper(wrapper);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
