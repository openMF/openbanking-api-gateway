package hu.dpc.poc.openbanking.accounts.hu.dpc.poc.openbanking.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"Links", "Meta"})
public class AccountsResult {
    @JsonProperty("Data")
    private AccountsData accountsData;

    public AccountsData getAccountsData() {
        return accountsData;
    }

    public void setAccountsData(AccountsData accountsData) {
        this.accountsData = accountsData;
    }
}
