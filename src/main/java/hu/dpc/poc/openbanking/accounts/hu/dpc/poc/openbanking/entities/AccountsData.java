package hu.dpc.poc.openbanking.accounts.hu.dpc.poc.openbanking.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AccountsData {
    @JsonProperty("Account")
    private List<Account> accounts;

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
