package org.example.parser.utils.impl;

import org.example.parser.utils.XPathMappings;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class XPathMappingsImpl implements XPathMappings {

    private static final Map<String, String> xPathMap;

    static {
        Map<String, String> paths = new HashMap<>();

        paths.put("RepresentativeBag", "//*[local-name()='RepresentativeBag']");

        // organization
        paths.put("OrganizationStandardNameApplicantBag", "//*[local-name()='ApplicantBag']//*[local-name()='OrganizationStandardName']");
        paths.put("OrganizationStandardNameRepresentativeBag", "//*[local-name()='RepresentativeBag']//*[local-name()='OrganizationStandardName']");
        paths.put("EntityIdentifierApplicantBag", "//*[local-name()='ApplicantBag']//*[local-name()='PartyIdentifier'][@*[local-name()='partyIdentifierCategory']='Entity identifier']");
        paths.put("EntityIdentifierRepresentativeBag", "//*[local-name()='RepresentativeBag']//*[local-name()='PartyIdentifier'][@*[local-name()='partyIdentifierCategory']='Entity identifier']");
        paths.put("OrganizationTaxIdentifierApplicantBag", "//*[local-name()='ApplicantBag']//*[local-name()='PartyIdentifier'][@*[local-name()='partyIdentifierCategory']='Tax identifier']");
        paths.put("OrganizationTaxIdentifierRepresentativeBag", "//*[local-name()='RepresentativeBag']//*[local-name()='PartyIdentifier'][@*[local-name()='partyIdentifierCategory']='Tax identifier']");

        // author
        paths.put("AuthorSignatureBag", "//*[local-name()='SignatureBag']//*[local-name()='PersonFullName']");
        paths.put("AuthorRepresentativeBag", "//*[local-name()='RepresentativeBag']//*[local-name()='PersonName']//*[local-name()='PersonFullName']");
        paths.put("AuthorApplicantBag", "//*[local-name()='ApplicantBag']//*[local-name()='PersonName']//*[local-name()='PersonFullName']");
        paths.put("AuthorContact", "//*[local-name()='Contact']//*[local-name()='PersonFullName']");
        paths.put("AuthorPartyIdentifier", "//*[local-name()='ApplicantBag']//*[local-name()='Applicant']//*[local-name()='PartyIdentifier'][@*[local-name()='partyIdentifierCategory']='ФИО']");
        paths.put("PositionSignatureBag", "//*[local-name()='OfficialTitleText']");
        paths.put("PositionPartyIdentifier", "//*[local-name()='ApplicantBag']//*[local-name()='Applicant']//*[local-name()='PartyIdentifier'][@*[local-name()='partyIdentifierCategory']='Должность']");
        paths.put("IndividualTaxIdentifierRepresentativeBag", "//*[local-name()='RepresentativeBag']//*[local-name()='Representative']//*[local-name()='PartyIdentifier'][@*[local-name()='partyIdentifierCategory']='Tax identifier']");
        paths.put("IndividualTaxIdentifierApplicantBag", "//*[local-name()='ApplicantBag']//*[local-name()='Applicant']//*[local-name()='PartyIdentifier'][@*[local-name()='partyIdentifierCategory']='Tax identifier']");
        paths.put("NationalIdNumberRepresentativeBag", "//*[local-name()='RepresentativeBag']//*[local-name()='PartyIdentifier'][@*[local-name()='partyIdentifierCategory']='National identification number']");
        paths.put("NationalIdNumberApplicantBag", "//*[local-name()='ApplicantBag']//*[local-name()='Applicant']//*[local-name()='PartyIdentifier'][@*[local-name()='partyIdentifierCategory']='National identification number']");
        paths.put("PostalAddressTextRepresentativeBag", "//*[local-name()='RepresentativeBag']//*[local-name()='PostalAddressText']");
        paths.put("PostalAddressTextApplicantBag", "//*[local-name()='ApplicantBag']//*[local-name()='PostalAddressText']");

        // other
        paths.put("Type", "//*[local-name()='SignatureKind']");
        paths.put("Date", "//*[local-name()='ElectronicSignatureDate']");

        xPathMap = Collections.unmodifiableMap(paths);
    }

    @Override
    public String getXPath(String key) {
        return xPathMap.get(key);
    }
}
