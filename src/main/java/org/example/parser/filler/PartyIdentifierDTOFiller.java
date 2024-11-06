package org.example.parser.filler;


import org.example.parser.dto.PartyIdentifierDTO;
import org.example.parser.utils.XPathEvaluator;
import org.example.parser.utils.XPathMappings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import java.util.Arrays;
import java.util.Optional;


/**
 * Компонент реализующий интерфейс заполняения PartyIdentifierDTO DTO
 */
@Component
public class PartyIdentifierDTOFiller implements DtoFiller<PartyIdentifierDTO> {

    private static final Logger log = LoggerFactory.getLogger(PartyIdentifierDTOFiller.class);

    private final XPathEvaluator xPathEvaluator;
    private final XPathMappings xPathMappings;

    public PartyIdentifierDTOFiller(XPathEvaluator xPathEvaluator, XPathMappings xPathMappings) {
        this.xPathEvaluator = xPathEvaluator;
        this.xPathMappings = xPathMappings;
    }

    @Override
    public void fill(PartyIdentifierDTO dto, Document document) {
        try {
            boolean isRepresentativeBag = xPathEvaluator.evaluateXPathAsNode(document, xPathMappings.getXPath(XPathKeys.REPRESENTATIVE_BAG)) != null;

            fillOrganization(dto, document, isRepresentativeBag);
            fillAuthor(dto, document, isRepresentativeBag);
            fillOtherFields(dto, document);

            log.debug("Объект DTO заполнен: {}", dto);
        } catch (Exception e) {
            log.error("Ошибка при заполнении PartyIdentifierDTO: ", e);
        }
    }

    private void fillOtherFields(PartyIdentifierDTO dto, Document document) throws Exception {
        dto.setType(xPathEvaluator.evaluateXPath(document, xPathMappings.getXPath(XPathKeys.TYPE)));
        dto.setElectronicSignatureDate(xPathEvaluator.evaluateXPath(document, xPathMappings.getXPath(XPathKeys.DATE)));

        log.debug("Подпись: {}", dto.getType());
        log.debug("Дата: {}", dto.getElectronicSignatureDate());
    }

    private void fillOrganization(PartyIdentifierDTO dto, Document document, boolean isRepresentativeBag) throws Exception {
        String orgNameBagXPath = isRepresentativeBag ? XPathKeys.ORGANIZATION_STANDARD_NAME_REPRESENTATIVE_BAG : XPathKeys.ORGANIZATION_STANDARD_NAME_APPLICANT_BAG;
        String entityIdentifierXPath = isRepresentativeBag ? XPathKeys.ENTITY_IDENTIFIER_REPRESENTATIVE_BAG : XPathKeys.ENTITY_IDENTIFIER_APPLICANT_BAG;
        String orgTaxIdentifierXPath = isRepresentativeBag ? XPathKeys.ORGANIZATION_TAX_IDENTIFIER_REPRESENTATIVE_BAG : XPathKeys.ORGANIZATION_TAX_IDENTIFIER_APPLICANT_BAG;

        dto.setOrganizationStandardName(xPathEvaluator.evaluateXPath(document, xPathMappings.getXPath(orgNameBagXPath)));
        dto.setEntityIdentifier(xPathEvaluator.evaluateXPath(document, xPathMappings.getXPath(entityIdentifierXPath)));
        dto.setOrganizationTaxIdentifier(xPathEvaluator.evaluateXPath(document, xPathMappings.getXPath(orgTaxIdentifierXPath)));

        log.info("Данные об организации:");
        log.info("Название организации: {}", dto.getOrganizationStandardName());
        log.info("Entity identifier: {}", dto.getEntityIdentifier());
        log.info("Организационный налоговый идентификатор: {}", dto.getOrganizationTaxIdentifier());
    }

    private void fillAuthor(PartyIdentifierDTO dto, Document document, boolean isRepresentativeBag) throws Exception {
        String taxIdentifierXPath = isRepresentativeBag ? XPathKeys.INDIVIDUAL_TAX_IDENTIFIER_REPRESENTATIVE_BAG : XPathKeys.INDIVIDUAL_TAX_IDENTIFIER_APPLICANT_BAG;
        String nationalIdXPath = isRepresentativeBag ? XPathKeys.NATIONAL_ID_NUMBER_REPRESENTATIVE_BAG : XPathKeys.NATIONAL_ID_NUMBER_APPLICANT_BAG;
        String postalAddressXPath = isRepresentativeBag ? XPathKeys.POSTAL_ADDRESS_TEXT_REPRESENTATIVE_BAG : XPathKeys.POSTAL_ADDRESS_TEXT_APPLICANT_BAG;
        String positionXPath = getPositionXPath(document);

        Optional<String> author = fillAuthorName(document);

        dto.setAuthor(author.orElse(null));
        dto.setPosition(xPathEvaluator.evaluateXPath(document, xPathMappings.getXPath(positionXPath)));
        dto.setIndividualTaxIdentifier(xPathEvaluator.evaluateXPath(document, xPathMappings.getXPath(taxIdentifierXPath)));
        dto.setNationalIdNumber(xPathEvaluator.evaluateXPath(document, xPathMappings.getXPath(nationalIdXPath)));
        dto.setPostalAddressText(xPathEvaluator.evaluateXPath(document, xPathMappings.getXPath(postalAddressXPath)));

        log.info("Данные о физическом лице:");
        log.info("Автор: {}", dto.getAuthor());
        log.info("Должность: {}", dto.getPosition());
        log.info("Налоговый идентификатор: {}", dto.getIndividualTaxIdentifier());
        log.info("Национальный идентификатор: {}", dto.getNationalIdNumber());
        log.info("Почтовый адрес: {}", dto.getPostalAddressText());
    }

    private String getPositionXPath(Document document) throws Exception {
        return xPathEvaluator.evaluateXPath(document, xPathMappings.getXPath(XPathKeys.POSITION_SIGNATURE_BAG)) != null
                ? XPathKeys.POSITION_SIGNATURE_BAG
                : XPathKeys.POSITION_PARTY_IDENTIFIER;
    }

    private Optional<String> fillAuthorName(Document document){
        return Arrays.stream(XPathKeys.AUTHOR_XPATHS)
                .map(xPathMappings::getXPath)
                .map(xpath -> {
                    try {
                        return xPathEvaluator.evaluateXPath(document, xpath);
                    } catch (Exception e) {
                        log.info("Ошибка при оценке XPath: {}", xpath, e);
                        return null;
                    }
                })
                .filter(value -> value != null && !value.isEmpty())
                .findFirst();
    }

}
