package com.lagou.config;

import com.lagou.pojo.Configuration;
import com.lagou.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class XMLMapperBuilder {

    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(InputStream inputStream) throws DocumentException {

        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();

        String namespace = rootElement.attributeValue("namespace");

        List<Element> list = rootElement.selectNodes("//select");
        for (Element element : list) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String paramterType = element.attributeValue("paramterType");
            String sqlText = element.getTextTrim();
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParamterType(paramterType);
            mappedStatement.setSql(sqlText);
            String key = namespace + "." + id;
            configuration.getMappedStatementMap().put(key, mappedStatement);

        }

        // 增删改
        constructSqlStatement(rootElement, "//insert");

        constructSqlStatement(rootElement, "//update");

        constructSqlStatement(rootElement, "//delete");

    }

    /**
     * 增删改查操作
     * @param rootElement Element
     * @param label String
     */
    @SuppressWarnings("unchecked")
    private void constructSqlStatement(Element rootElement, String label) {
        String namespace = rootElement.attributeValue("namespace");
        List<Element> insertList = rootElement.selectNodes(label);
        for (Element element : insertList) {
            String id = element.attributeValue("id");
            String parameterType = element.attributeValue("parameterType");
            String insertSql = element.getTextTrim();

            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setParamterType(parameterType);
            mappedStatement.setSql(insertSql);

            String key = namespace + "." + id;
            configuration.getMappedStatementMap().put(key, mappedStatement);
        }
    }


}
