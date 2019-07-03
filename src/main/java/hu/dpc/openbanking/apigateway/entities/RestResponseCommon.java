package hu.dpc.openbanking.apigateway.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RestResponseCommon {
    @JsonIgnore
    private int responseCode;
    @JsonIgnore
    private String rawResponse;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(final int responseCode) {
        this.responseCode = responseCode;
    }

    public String getRawResponse() {
        return rawResponse;
    }

    public void setRawResponse(final String rawResponse) {
        this.rawResponse = rawResponse;
    }
}
