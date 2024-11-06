package org.example.parser.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * Интерфейс работы с XPath, включая настройку пространства имен
 */
public interface XPathEvaluator {

    /**
     * Получение значения по ключу XML документа
     *
     * @param doc        документ DOM XML
     * @param expression ключ
     * @return значение
     * @throws Exception возможные ошибки
     */
    String evaluateXPath(Document doc, String expression) throws Exception;

    /**
     * Получение узла по XPath в XML документе
     *
     * @param doc        документ DOM XML
     * @param expression XPath выражение
     * @return узел или null, если узел не найден
     * @throws Exception возможные ошибки
     */
    Node evaluateXPathAsNode(Document doc, String expression) throws Exception;
}
