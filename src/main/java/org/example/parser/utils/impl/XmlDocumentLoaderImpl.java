package org.example.parser.utils.impl;

import org.example.parser.utils.XmlDocumentLoader;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public class XmlDocumentLoaderImpl implements XmlDocumentLoader {

    @Override
    public Document loadDocument(byte[] xmlBytes) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);  // Включаем поддержку пространств имен
        DocumentBuilder builder = factory.newDocumentBuilder();
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(xmlBytes)) {
            return builder.parse(inputStream);  // Парсинг XML из массива байт
        }
    }
}
