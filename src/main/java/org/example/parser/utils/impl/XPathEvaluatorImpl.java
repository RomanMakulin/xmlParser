package org.example.parser.utils.impl;

import org.example.parser.utils.XPathEvaluator;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.util.ArrayList;
import java.util.List;

@Component
public class XPathEvaluatorImpl<T> implements XPathEvaluator {

    private final XPath xpath;

    public XPathEvaluatorImpl() {
        XPathFactory xPathFactory = XPathFactory.newInstance();
        this.xpath = xPathFactory.newXPath();
    }

    @Override
    public String evaluateXPath(Document doc, String expression) throws Exception {
        XPathExpression expr = xpath.compile(expression);
        NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent().trim();
        }
        return null;
    }

    @Override
    public Node evaluateXPathAsNode(Document doc, String expression) throws Exception {
        return (Node) xpath.evaluate(expression, doc, XPathConstants.NODE);
    }

    public List<String> evaluateXPathForList(Document doc, String expression) throws Exception {
        XPathExpression expr = xpath.compile(expression);
        NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        List<String> results = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            results.add(nodeList.item(i).getTextContent().trim());
        }
        return results;
    }
}
