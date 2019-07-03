package uk.org.openbanking.v3_1_2.commons;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account {
    @JsonProperty("AccountId")
    private String accountId;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("StatusUpdateDateTime")
    private String statusUpdateDateTime;
    @JsonProperty("Currency")
    private String currency;
    @JsonProperty("AccountType")
    private String accountType;
    @JsonProperty("AccountSubType")
    private String accountSubType;
    @JsonProperty("Nickname")
    private String nickname;
    @JsonProperty("Account")
    private List<AccountAccount> accountAccountList;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(final String accountId) {
        this.accountId = accountId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getStatusUpdateDateTime() {
        return statusUpdateDateTime;
    }

    public void setStatusUpdateDateTime(final String statusUpdateDateTime) {
        this.statusUpdateDateTime = statusUpdateDateTime;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    public String getAccountSubType() {
        return accountSubType;
    }

    public void setAccountSubType(final String accountSubType) {
        this.accountSubType = accountSubType;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(final String nickname) {
        this.nickname = nickname;
    }

    public List<AccountAccount> getAccountAccountList() {
        return accountAccountList;
    }

    public void setAccountAccountList(final List<AccountAccount> accountAccountList) {
        this.accountAccountList = accountAccountList;
    }
}
