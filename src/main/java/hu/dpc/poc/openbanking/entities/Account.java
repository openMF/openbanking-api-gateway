package hu.dpc.poc.openbanking.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account {
    /**

     {
     "Consent": {
     "Account": [
     {
     "AccountId": "22289",
     "Status": "Enabled",
     "StatusUpdateDateTime": "2019-01-01T06:06:06+00:00",
     "Currency": "GBP",
     "AccountType": "Personal",
     "AccountSubType": "CurrentAccount",
     "Nickname": "Bills"
     },
     {
     "AccountId": "31820",
     "Status": "Enabled",
     "StatusUpdateDateTime": "2018-01-01T06:06:06+00:00",
     "Currency": "GBP",
     "AccountType": "Personal",
     "AccountSubType": "CurrentAccount",
     "Nickname": "Household"
     }
     ]
     },
     "Links": {
     "Self": "https://api.alphabank.com/open-banking/v3.1/aisp/accounts/"
     },
     "Meta": {
     "TotalPages": 1
     }
     }
     */

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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusUpdateDateTime() {
        return statusUpdateDateTime;
    }

    public void setStatusUpdateDateTime(String statusUpdateDateTime) {
        this.statusUpdateDateTime = statusUpdateDateTime;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountSubType() {
        return accountSubType;
    }

    public void setAccountSubType(String accountSubType) {
        this.accountSubType = accountSubType;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


}
