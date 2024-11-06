package org.example.parser.impl;


import org.example.parser.XMLParsingService;
import org.example.parser.dto.PartyIdentifierDTO;
import org.example.parser.filler.PartyIdentifierDTOFiller;
import org.example.parser.utils.XmlDocumentLoader;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;


/**
 * Реализация интерфейса парсинга XML файла в DTO из массива байт
 *
 * @param <T> необходимый объект DTO
 */
@Service
public class XMLParsingServiceImpl<T> implements XMLParsingService<T> {

    private final XmlDocumentLoader xmlDocumentLoader;
    private final PartyIdentifierDTOFiller partyIdentifierDTOFiller;

    public XMLParsingServiceImpl(XmlDocumentLoader xmlDocumentLoader,
                                 PartyIdentifierDTOFiller partyIdentifierDTOFiller) {
        this.xmlDocumentLoader = xmlDocumentLoader;
        this.partyIdentifierDTOFiller = partyIdentifierDTOFiller;
    }

    @Override
    public T extractData(byte[] xmlBytes, Class<T> dtoClass) throws Exception {
        Document document = xmlDocumentLoader.loadDocument(xmlBytes);
        return fillDataTransferObject(document, dtoClass);
    }

    /**
     * Заполнение DTO на основе переданного класса
     *
     * @param document документ с XML
     * @param dtoClass класс DTO
     * @return заполненный объект DTO
     * @throws Exception возможные ошибки
     */
    private T fillDataTransferObject(Document document, Class<T> dtoClass) throws Exception {
        // Новый экземпляр DTO через рефлексию
        T dtoInstance = dtoClass.getDeclaredConstructor().newInstance();

        // В зависимости от класса DTO, выбираем соответствующий DtoFiller
        if (dtoInstance instanceof PartyIdentifierDTO) {
            partyIdentifierDTOFiller.fill((PartyIdentifierDTO) dtoInstance, document);
        }
        // Здесь можно добавить дополнительные проверки для других типов DTO

        return dtoInstance;
    }

}
