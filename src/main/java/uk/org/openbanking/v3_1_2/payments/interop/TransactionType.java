package uk.org.openbanking.v3_1_2.payments.interop;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionType {
    @JsonProperty("scenario")
    private String scenario;
    @JsonProperty("initiator")
    private String initiator;
    @JsonProperty("initiatorType")
    private String initiatorType;

    public String getScenario() {
        return scenario;
    }

    public void setScenario(final String scenario) {
        this.scenario = scenario;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(final String initiator) {
        this.initiator = initiator;
    }

    public String getInitiatorType() {
        return initiatorType;
    }

    public void setInitiatorType(final String initiatorType) {
        this.initiatorType = initiatorType;
    }
}
