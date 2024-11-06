package org.example.parser.utils;

/**
 * Интерфейс управления hashMap для получения значений по ключу из XML документа
 */
public interface XPathMappings {
    /**
     * Получить значение по ключу
     *
     * @param key ключ
     * @return значение
     */
    String getXPath(String key);
}
