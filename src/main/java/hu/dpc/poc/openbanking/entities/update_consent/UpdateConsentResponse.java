package hu.dpc.poc.openbanking.entities.update_consent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import hu.dpc.poc.openbanking.entities.RestResponseCommon;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateConsentResponse extends RestResponseCommon {
}
