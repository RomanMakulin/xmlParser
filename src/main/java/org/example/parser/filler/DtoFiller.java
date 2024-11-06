package org.example.parser.filler;


import org.w3c.dom.Document;

/**
 * Интерфейс заполнения DTO из XML
 */
public interface DtoFiller<T> {

    /**
     * Заполнить необходимый объект DTO данными из документа XML
     *
     * @param dto      нужный объект DTO
     * @param document документ XML
     * @throws Exception возможные ошибки
     */
    void fill(T dto, Document document) throws Exception;

}
