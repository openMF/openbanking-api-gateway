/*
 * Payment Initiation API
 * Swagger for Payment Initiation API Specification
 *
 * OpenAPI spec version: v3.1.2
 * Contact: ServiceDesk@openbanking.org.uk
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package uk.org.openbanking.v3_1_2.payments;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * OBWriteDomesticResponse3Data
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2019-07-09T18:27:25.589536+02:00[Europe/Budapest]")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OBWriteDomesticResponse3Data {
    @JsonProperty("DomesticPaymentId")
    private String domesticPaymentId = null;
    @JsonProperty("Status")
    private StatusEnum status = null;
    @JsonProperty("StatusUpdateDateTime")
    private String statusUpdateDateTime = null;
    @JsonProperty("CreationDateTime")
    private String creationDateTime = null;
    @JsonProperty("ExpectedExecutionDateTime")
    private String expectedExecutionDateTime = null;
    @JsonProperty("Charges")
    private List<OBWriteDomesticStandingOrderResponse4DataCharges> charges = null;
    @JsonProperty("ConsentId")
    private String consentId = null;
    @JsonProperty("Initiation")
    private OBWriteDomesticResponse3DataInitiation initiation = null;
    @JsonProperty("ExpectedSettlementDateTime")
    private String expectedSettlementDateTime = null;
    @JsonProperty("MultiAuthorisation")
    private OBWriteDomesticStandingOrderResponse4DataMultiAuthorisation multiAuthorisation = null;

    public OBWriteDomesticResponse3Data domesticPaymentId(String domesticPaymentId) {
        this.domesticPaymentId = domesticPaymentId;
        return this;
    }

    /**
     * OB: Unique identification as assigned by the ASPSP to uniquely identify the domestic payment resource.
     *
     * @return domesticPaymentId
     **/
    @JsonProperty("DomesticPaymentId")
    @ApiModelProperty(value = "OB: Unique identification as assigned by the ASPSP to uniquely identify the domestic payment resource.")
    @Size(min = 1, max = 40)
    public String getDomesticPaymentId() {
        return domesticPaymentId;
    }

    public void setDomesticPaymentId(String domesticPaymentId) {
        this.domesticPaymentId = domesticPaymentId;
    }

    public OBWriteDomesticResponse3Data status(StatusEnum status) {
        this.status = status;
        return this;
    }

    /**
     * Specifies the status of the payment information group.
     *
     * @return status
     **/
    @JsonProperty("Status")
    @ApiModelProperty(value = "Specifies the status of the payment information group.")
    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public OBWriteDomesticResponse3Data statusUpdateDateTime(String statusUpdateDateTime) {
        this.statusUpdateDateTime = statusUpdateDateTime;
        return this;
    }

    /**
     * Date and time at which the resource status was updated.All dates in the JSON payloads are represented in ISO 8601 date-time format.  All date-time fields in responses must include the timezone. An example is below: 2017-04-05T10:43:07+00:00
     *
     * @return statusUpdateDateTime
     **/
    @JsonProperty("StatusUpdateDateTime")
    @ApiModelProperty(value = "Date and time at which the resource status was updated.All dates in the JSON payloads are represented in ISO 8601 date-time format.  All date-time fields in responses must include the timezone. An example is below: 2017-04-05T10:43:07+00:00")
    public String getStatusUpdateDateTime() {
        return statusUpdateDateTime;
    }

    public void setStatusUpdateDateTime(String statusUpdateDateTime) {
        this.statusUpdateDateTime = statusUpdateDateTime;
    }

    public OBWriteDomesticResponse3Data creationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
        return this;
    }

    /**
     * Date and time at which the message was created.All dates in the JSON payloads are represented in ISO 8601 date-time format.  All date-time fields in responses must include the timezone. An example is below: 2017-04-05T10:43:07+00:00
     *
     * @return creationDateTime
     **/
    @JsonProperty("CreationDateTime")
    @ApiModelProperty(value = "Date and time at which the message was created.All dates in the JSON payloads are represented in ISO 8601 date-time format.  All date-time fields in responses must include the timezone. An example is below: 2017-04-05T10:43:07+00:00")
    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public OBWriteDomesticResponse3Data expectedExecutionDateTime(String expectedExecutionDateTime) {
        this.expectedExecutionDateTime = expectedExecutionDateTime;
        return this;
    }

    /**
     * Expected execution date and time for the payment resource.All dates in the JSON payloads are represented in ISO 8601 date-time format.  All date-time fields in responses must include the timezone. An example is below: 2017-04-05T10:43:07+00:00
     *
     * @return expectedExecutionDateTime
     **/
    @JsonProperty("ExpectedExecutionDateTime")
    @ApiModelProperty(value = "Expected execution date and time for the payment resource.All dates in the JSON payloads are represented in ISO 8601 date-time format.  All date-time fields in responses must include the timezone. An example is below: 2017-04-05T10:43:07+00:00")
    public String getExpectedExecutionDateTime() {
        return expectedExecutionDateTime;
    }

    public void setExpectedExecutionDateTime(String expectedExecutionDateTime) {
        this.expectedExecutionDateTime = expectedExecutionDateTime;
    }

    public OBWriteDomesticResponse3Data charges(List<OBWriteDomesticStandingOrderResponse4DataCharges> charges) {
        this.charges = charges;
        return this;
    }

    public OBWriteDomesticResponse3Data addChargesItem(OBWriteDomesticStandingOrderResponse4DataCharges chargesItem) {
        if (this.charges == null) {
            this.charges = new ArrayList<>();
        }
        this.charges.add(chargesItem);
        return this;
    }

    /**
     * Get charges
     *
     * @return charges
     **/
    @JsonProperty("Charges")
    @ApiModelProperty(value = "")
    public List<OBWriteDomesticStandingOrderResponse4DataCharges> getCharges() {
        return charges;
    }

    public void setCharges(List<OBWriteDomesticStandingOrderResponse4DataCharges> charges) {
        this.charges = charges;
    }

    public OBWriteDomesticResponse3Data consentId(String consentId) {
        this.consentId = consentId;
        return this;
    }

    /**
     * OB: Unique identification as assigned by the ASPSP to uniquely identify the consent resource.
     *
     * @return consentId
     **/
    @JsonProperty("ConsentId")
    @ApiModelProperty(value = "OB: Unique identification as assigned by the ASPSP to uniquely identify the consent resource.")
    @Size(min = 1, max = 128)
    public String getConsentId() {
        return consentId;
    }

    public void setConsentId(String consentId) {
        this.consentId = consentId;
    }

    public OBWriteDomesticResponse3Data initiation(OBWriteDomesticResponse3DataInitiation initiation) {
        this.initiation = initiation;
        return this;
    }

    /**
     * Get initiation
     *
     * @return initiation
     **/
    @JsonProperty("Initiation")
    @ApiModelProperty(value = "")
    public OBWriteDomesticResponse3DataInitiation getInitiation() {
        return initiation;
    }

    public void setInitiation(OBWriteDomesticResponse3DataInitiation initiation) {
        this.initiation = initiation;
    }

    public OBWriteDomesticResponse3Data expectedSettlementDateTime(String expectedSettlementDateTime) {
        this.expectedSettlementDateTime = expectedSettlementDateTime;
        return this;
    }

    /**
     * Expected settlement date and time for the payment resource.All dates in the JSON payloads are represented in ISO 8601 date-time format.  All date-time fields in responses must include the timezone. An example is below: 2017-04-05T10:43:07+00:00
     *
     * @return expectedSettlementDateTime
     **/
    @JsonProperty("ExpectedSettlementDateTime")
    @ApiModelProperty(value = "Expected settlement date and time for the payment resource.All dates in the JSON payloads are represented in ISO 8601 date-time format.  All date-time fields in responses must include the timezone. An example is below: 2017-04-05T10:43:07+00:00")
    public String getExpectedSettlementDateTime() {
        return expectedSettlementDateTime;
    }

    public void setExpectedSettlementDateTime(String expectedSettlementDateTime) {
        this.expectedSettlementDateTime = expectedSettlementDateTime;
    }

    public OBWriteDomesticResponse3Data multiAuthorisation(OBWriteDomesticStandingOrderResponse4DataMultiAuthorisation multiAuthorisation) {
        this.multiAuthorisation = multiAuthorisation;
        return this;
    }

    /**
     * Get multiAuthorisation
     *
     * @return multiAuthorisation
     **/
    @JsonProperty("MultiAuthorisation")
    @ApiModelProperty(value = "")
    public OBWriteDomesticStandingOrderResponse4DataMultiAuthorisation getMultiAuthorisation() {
        return multiAuthorisation;
    }

    public void setMultiAuthorisation(OBWriteDomesticStandingOrderResponse4DataMultiAuthorisation multiAuthorisation) {
        this.multiAuthorisation = multiAuthorisation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(domesticPaymentId, status, statusUpdateDateTime, creationDateTime, expectedExecutionDateTime, charges, consentId, initiation, expectedSettlementDateTime, multiAuthorisation);
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OBWriteDomesticResponse3Data obWriteDomesticResponse3Data = (OBWriteDomesticResponse3Data) o;
        return Objects.equals(this.domesticPaymentId, obWriteDomesticResponse3Data.domesticPaymentId) &&
                Objects.equals(this.status, obWriteDomesticResponse3Data.status) &&
                Objects.equals(this.statusUpdateDateTime, obWriteDomesticResponse3Data.statusUpdateDateTime) &&
                Objects.equals(this.creationDateTime, obWriteDomesticResponse3Data.creationDateTime) &&
                Objects.equals(this.expectedExecutionDateTime, obWriteDomesticResponse3Data.expectedExecutionDateTime) &&
                Objects.equals(this.charges, obWriteDomesticResponse3Data.charges) &&
                Objects.equals(this.consentId, obWriteDomesticResponse3Data.consentId) &&
                Objects.equals(this.initiation, obWriteDomesticResponse3Data.initiation) &&
                Objects.equals(this.expectedSettlementDateTime, obWriteDomesticResponse3Data.expectedSettlementDateTime) &&
                Objects.equals(this.multiAuthorisation, obWriteDomesticResponse3Data.multiAuthorisation);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OBWriteDomesticResponse3Data {\n");

        sb.append("    domesticPaymentId: ").append(toIndentedString(domesticPaymentId)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    statusUpdateDateTime: ").append(toIndentedString(statusUpdateDateTime)).append("\n");
        sb.append("    creationDateTime: ").append(toIndentedString(creationDateTime)).append("\n");
        sb.append("    expectedExecutionDateTime: ").append(toIndentedString(expectedExecutionDateTime)).append("\n");
        sb.append("    charges: ").append(toIndentedString(charges)).append("\n");
        sb.append("    consentId: ").append(toIndentedString(consentId)).append("\n");
        sb.append("    initiation: ").append(toIndentedString(initiation)).append("\n");
        sb.append("    expectedSettlementDateTime: ").append(toIndentedString(expectedSettlementDateTime)).append("\n");
        sb.append("    multiAuthorisation: ").append(toIndentedString(multiAuthorisation)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    /**
     * Specifies the status of the payment information group.
     */
    public enum StatusEnum {
        ACCEPTEDCREDITSETTLEMENTCOMPLETED("AcceptedCreditSettlementCompleted"),

        ACCEPTEDSETTLEMENTCOMPLETED("AcceptedSettlementCompleted"),

        ACCEPTEDSETTLEMENTINPROCESS("AcceptedSettlementInProcess"),

        ACCEPTEDWITHOUTPOSTING("AcceptedWithoutPosting"),

        PENDING("Pending"),

        REJECTED("Rejected");

        private String value;

        StatusEnum(String value) {
            this.value = value;
        }

        @JsonCreator
        public static StatusEnum fromValue(String text) {
            for (StatusEnum b : StatusEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + text + "'");
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }
    }
}

