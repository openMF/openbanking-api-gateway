package hu.dpc.poc.openbanking.accounts.hu.dpc.poc.openbanking.entities.hu.dpc.poc.openbanking.accounts.account_access_consents;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"Risk", "Links", "Meta"})
public class AccountAccessConsentsResult {
    @JsonProperty("Data")
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
