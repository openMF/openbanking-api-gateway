package hu.dpc.openbanking.apigateway.entities.accounts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.org.openbanking.v3_1_2.accounts.Consent;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateConsentRequest {
    @JsonProperty("Data")
    private Consent consent;

    public Consent getConsent() {
        return consent;
    }

    public void setConsent(final Consent consent) {
        this.consent = consent;
    }
}
