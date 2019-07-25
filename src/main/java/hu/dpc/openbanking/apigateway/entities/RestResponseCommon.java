package hu.dpc.openbanking.apigateway.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
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
