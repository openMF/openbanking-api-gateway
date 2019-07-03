package uk.org.openbanking.v3_1_2.commons;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountAccount {
    @JsonProperty("SchemeName")
    private String schemeName;
    @JsonProperty("Identification")
    private String identification;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("SecondaryIdentification")
    private String secondaryIdentification;

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(final String schemeName) {
        this.schemeName = schemeName;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(final String identification) {
        this.identification = identification;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSecondaryIdentification() {
        return secondaryIdentification;
    }

    public void setSecondaryIdentification(final String secondaryIdentification) {
        this.secondaryIdentification = secondaryIdentification;
    }
}
