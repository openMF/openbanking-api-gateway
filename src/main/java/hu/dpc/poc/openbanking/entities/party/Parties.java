package hu.dpc.poc.openbanking.entities.party;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Parties {
    @JsonProperty("Party")
    private Party party;

    public Party getParty() {
        return party;
    }

    public void setParty(final Party party) {
        this.party = party;
    }
}
