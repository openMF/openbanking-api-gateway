package uk.org.openbanking.v3_1_2.payments.interop;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InteropData {
    @JsonProperty("amountType")
    private String amountType;
    @JsonProperty("transactionType")
    private TransactionType transactionType;

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(final String amountType) {
        this.amountType = amountType;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(final TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
