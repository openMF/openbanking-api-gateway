package hu.dpc.poc.openbanking.entities.account_access_consents;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsentResult {
    @JsonProperty("Data")
    private Consent data;

    public Consent getData() {
        return data;
    }

    public void setData(Consent data) {
        this.data = data;
    }
}
