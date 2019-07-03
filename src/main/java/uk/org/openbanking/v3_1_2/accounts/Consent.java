package uk.org.openbanking.v3_1_2.accounts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.org.openbanking.v3_1_2.commons.Account;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Consent {
    /*
"ConsentId": "urn-alphabank-intent-88379",
        "Status": "AwaitingAuthorisation",
        "StatusUpdateDateTime": "2017-05-02T00:00:00+00:00",
        "CreationDateTime": "2017-05-02T00:00:00+00:00",
        "Permissions": [
        "ReadAccountsDetail",
        "ReadBalances",
        "ReadBeneficiariesDetail",
        "ReadDirectDebits",
        "ReadProducts",
        "ReadStandingOrdersDetail",
        "ReadTransactionsCredits",
        "ReadTransactionsDebits",
        "ReadTransactionsDetail",
        "ReadOffers",
        "ReadPAN",
        "ReadParty",
        "ReadPartyPSU",
        "ReadScheduledPaymentsDetail",
        "ReadStatementsDetail"
        ],
        "ExpirationDateTime": "2017-08-02T00:00:00+00:00",
        "TransactionFromDateTime": "2017-05-03T00:00:00+00:00",
        "TransactionToDateTime": "2017-12-03T00:00:00+00:00"
},
*/

    @JsonProperty("StatusUpdateDateTime")
    private String statusUpdateDateTime;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("CreationDateTime")
    private String creationDateTime;
    @JsonProperty("Permissions")
    private List<String> permissions;
    @JsonProperty("ExpirationDateTime")
    private String expirationDateTime;
    @JsonProperty("TransactionFromDateTime")
    private String transactionFromDateTime;
    @JsonProperty("TransactionToDateTime")
    private String transactionToDateTime;
    @JsonProperty("ConsentId")
    private String consentId;
    @JsonProperty("Action")
    private String action;
    @JsonProperty("Account")
    private List<Account> accounts;

    public String getStatusUpdateDateTime() {
        return statusUpdateDateTime;
    }

    public void setStatusUpdateDateTime(final String statusUpdateDateTime) {
        this.statusUpdateDateTime = statusUpdateDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(final String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(final List<String> permissions) {
        this.permissions = permissions;
    }

    public String getExpirationDateTime() {
        return expirationDateTime;
    }

    public void setExpirationDateTime(final String expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    public String getTransactionFromDateTime() {
        return transactionFromDateTime;
    }

    public void setTransactionFromDateTime(final String transactionFromDateTime) {
        this.transactionFromDateTime = transactionFromDateTime;
    }

    public String getTransactionToDateTime() {
        return transactionToDateTime;
    }

    public void setTransactionToDateTime(final String transactionToDateTime) {
        this.transactionToDateTime = transactionToDateTime;
    }

    public String getConsentId() {
        return consentId;
    }

    public void setConsentId(final String consentId) {
        this.consentId = consentId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(final String action) {
        this.action = action;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(final List<Account> accounts) {
        this.accounts = accounts;
    }
}
