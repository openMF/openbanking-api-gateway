package hu.dpc.poc.openbanking.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountsResult {
    @JsonProperty("Consent")
    private AccountsData accountsData;

    public AccountsData getAccountsData() {
        return accountsData;
    }

    public void setAccountsData(AccountsData accountsData) {
        this.accountsData = accountsData;
    }
}
