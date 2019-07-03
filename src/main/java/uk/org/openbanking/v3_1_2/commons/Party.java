package uk.org.openbanking.v3_1_2.commons;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Party {
    @JsonProperty("PartyId")
    private String partyId;
    @JsonProperty("PartyType")
    private String partyType;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("FullLegalName")
    private String fullLegalName;
    @JsonProperty("LegalStructure")
    private String legalStructure;
    @JsonProperty("BeneficialOwnership")
    private boolean BeneficialOwnership;
    @JsonProperty("AccountRole")
    private String accountRole;
    @JsonProperty("EmailAddress")
    private String emailAddress;

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(final String partyId) {
        this.partyId = partyId;
    }

    public String getPartyType() {
        return partyType;
    }

    public void setPartyType(final String partyType) {
        this.partyType = partyType;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getFullLegalName() {
        return fullLegalName;
    }

    public void setFullLegalName(final String fullLegalName) {
        this.fullLegalName = fullLegalName;
    }

    public String getLegalStructure() {
        return legalStructure;
    }

    public void setLegalStructure(final String legalStructure) {
        this.legalStructure = legalStructure;
    }

    public boolean isBeneficialOwnership() {
        return BeneficialOwnership;
    }

    public void setBeneficialOwnership(final boolean beneficialOwnership) {
        BeneficialOwnership = beneficialOwnership;
    }

    public String getAccountRole() {
        return accountRole;
    }

    public void setAccountRole(final String accountRole) {
        this.accountRole = accountRole;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
