package hu.dpc.openbanking.apigateway.entities.account_access_consents;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import hu.dpc.openbanking.apigateway.entities.RestResponseCommon;
import uk.org.openbanking.v3_1_2.accounts.Consent;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsentResult extends RestResponseCommon {
    @JsonProperty("Data")
    private Consent data;

    public Consent getData() {
        return data;
    }

    public void setData(final Consent data) {
        this.data = data;
    }
}
