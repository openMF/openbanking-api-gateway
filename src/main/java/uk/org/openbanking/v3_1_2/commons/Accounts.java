package uk.org.openbanking.v3_1_2.commons;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Accounts {
    @JsonProperty("Account")
    private List<Account> account;

    public List<Account> getAccount() {
        return account;
    }

    public void setAccount(final List<Account> account) {
        this.account = account;
    }
}
