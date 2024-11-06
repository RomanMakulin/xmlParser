package org.example.parser;

/**
 * Интерфейс парсинга XML массива байт в нужный DTO
 *
 * @param <T> объект DTO
 */
public interface XMLParsingService<T> {
    /**
     * Извлечь данные в DTO из массива байт XML
     *
     * @param xmlBytes массив байт XML
     * @param dtoClass класс DTO
     * @return собранный объект DTO
     * @throws Exception возможные ошибки
     */
    T extractData(byte[] xmlBytes, Class<T> dtoClass) throws Exception;
}
