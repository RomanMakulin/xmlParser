package org.example.parser.dto;


import java.util.List;

/**
 * Объект DTO для парсинга данных из XML различных типов заявок
 */
public class PartyIdentifierDTO {

    private String applicationId;
    private String type;
    private String electronicSignatureDate;
    private String organizationStandardName;
    private String entityIdentifier;
    private String organizationTaxIdentifier;
    private String author;
    private String position;
    private String nationalIdNumber;
    private String individualTaxIdentifier;
    private String postalAddressText;
    private List<String> links;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getElectronicSignatureDate() {
        return electronicSignatureDate;
    }

    public void setElectronicSignatureDate(String electronicSignatureDate) {
        this.electronicSignatureDate = electronicSignatureDate;
    }

    public String getOrganizationStandardName() {
        return organizationStandardName;
    }

    public void setOrganizationStandardName(String organizationStandardName) {
        this.organizationStandardName = organizationStandardName;
    }

    public String getEntityIdentifier() {
        return entityIdentifier;
    }

    public void setEntityIdentifier(String entityIdentifier) {
        this.entityIdentifier = entityIdentifier;
    }

    public String getOrganizationTaxIdentifier() {
        return organizationTaxIdentifier;
    }

    public void setOrganizationTaxIdentifier(String organizationTaxIdentifier) {
        this.organizationTaxIdentifier = organizationTaxIdentifier;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNationalIdNumber() {
        return nationalIdNumber;
    }

    public void setNationalIdNumber(String nationalIdNumber) {
        this.nationalIdNumber = nationalIdNumber;
    }

    public String getIndividualTaxIdentifier() {
        return individualTaxIdentifier;
    }

    public void setIndividualTaxIdentifier(String individualTaxIdentifier) {
        this.individualTaxIdentifier = individualTaxIdentifier;
    }

    public String getPostalAddressText() {
        return postalAddressText;
    }

    public void setPostalAddressText(String postalAddressText) {
        this.postalAddressText = postalAddressText;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "PartyIdentifierDTO{" +
                "applicationId='" + applicationId + '\'' +
                ", type='" + type + '\'' +
                ", electronicSignatureDate='" + electronicSignatureDate + '\'' +
                ", organizationStandardName='" + organizationStandardName + '\'' +
                ", entityIdentifier='" + entityIdentifier + '\'' +
                ", organizationTaxIdentifier='" + organizationTaxIdentifier + '\'' +
                ", author='" + author + '\'' +
                ", position='" + position + '\'' +
                ", nationalIdNumber='" + nationalIdNumber + '\'' +
                ", individualTaxIdentifier='" + individualTaxIdentifier + '\'' +
                ", postalAddressText='" + postalAddressText + '\'' +
                ", links=" + links +
                '}';
    }
}

