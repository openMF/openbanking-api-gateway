/*
 * Account and Transaction API Specification
 * Swagger for Account and Transaction API Specification
 *
 * OpenAPI spec version: v3.1.2
 * Contact: ServiceDesk@openbanking.org.uk
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package uk.org.openbanking.v3_1_2.accounts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Details about any caps (maximum charges) that apply to a particular fee/charge. Capping can either be based on an amount (in gbp), an amount (in items) or a rate.
 */
@ApiModel(description = "Details about any caps (maximum charges) that apply to a particular fee/charge. Capping can either be based on an amount (in gbp), an amount (in items) or a rate.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2019-07-10T09:14:46.696896+02:00[Europe/Budapest]")
public class OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeCap {
    @JsonProperty("CappingPeriod")
    private OBPeriod1Code cappingPeriod = null;

    @JsonProperty("OtherFeeType")
    private List<OBReadProduct2DataOtherProductTypeOverdraftOtherFeeType> otherFeeType = null;
    @JsonProperty("FeeType")
    private List<FeeTypeEnum> feeType = null;
    @JsonProperty("FeeCapOccurrence")
    private Integer feeCapOccurrence = null;
    @JsonProperty("MinMaxType")
    private OBMinMaxType1Code minMaxType = null;
    @JsonProperty("Notes")
    private List<String> notes = null;
    @JsonProperty("FeeCapAmount")
    private String feeCapAmount = null;

    public OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeCap cappingPeriod(OBPeriod1Code cappingPeriod) {
        this.cappingPeriod = cappingPeriod;
        return this;
    }

    /**
     * Get cappingPeriod
     *
     * @return cappingPeriod
     **/
    @JsonProperty("CappingPeriod")
    @ApiModelProperty(value = "")
    public OBPeriod1Code getCappingPeriod() {
        return cappingPeriod;
    }

    public void setCappingPeriod(OBPeriod1Code cappingPeriod) {
        this.cappingPeriod = cappingPeriod;
    }

    public OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeCap otherFeeType(List<OBReadProduct2DataOtherProductTypeOverdraftOtherFeeType> otherFeeType) {
        this.otherFeeType = otherFeeType;
        return this;
    }

    public OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeCap addOtherFeeTypeItem(OBReadProduct2DataOtherProductTypeOverdraftOtherFeeType otherFeeTypeItem) {
        if (this.otherFeeType == null) {
            this.otherFeeType = new ArrayList<>();
        }
        this.otherFeeType.add(otherFeeTypeItem);
        return this;
    }

    /**
     * Get otherFeeType
     *
     * @return otherFeeType
     **/
    @JsonProperty("OtherFeeType")
    @ApiModelProperty(value = "")
    public List<OBReadProduct2DataOtherProductTypeOverdraftOtherFeeType> getOtherFeeType() {
        return otherFeeType;
    }

    public void setOtherFeeType(List<OBReadProduct2DataOtherProductTypeOverdraftOtherFeeType> otherFeeType) {
        this.otherFeeType = otherFeeType;
    }

    public OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeCap feeType(List<FeeTypeEnum> feeType) {
        this.feeType = feeType;
        return this;
    }

    public OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeCap addFeeTypeItem(FeeTypeEnum feeTypeItem) {
        if (this.feeType == null) {
            this.feeType = new ArrayList<>();
        }
        this.feeType.add(feeTypeItem);
        return this;
    }

    /**
     * Get feeType
     *
     * @return feeType
     **/
    @JsonProperty("FeeType")
    @ApiModelProperty(value = "")
    @Size(min = 1)
    public List<FeeTypeEnum> getFeeType() {
        return feeType;
    }

    public void setFeeType(List<FeeTypeEnum> feeType) {
        this.feeType = feeType;
    }

    public OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeCap feeCapOccurrence(Integer feeCapOccurrence) {
        this.feeCapOccurrence = feeCapOccurrence;
        return this;
    }

    /**
     * Indicates whether the advertised overdraft rate is guaranteed to be offered to a borrower by the bank e.g. if it�s part of a government scheme, or whether the rate may vary dependent on the applicant�s circumstances.
     *
     * @return feeCapOccurrence
     **/
    @JsonProperty("FeeCapOccurrence")
    @ApiModelProperty(value = "Indicates whether the advertised overdraft rate is guaranteed to be offered to a borrower by the bank e.g. if it�s part of a government scheme, or whether the rate may vary dependent on the applicant�s circumstances.")
    public Integer getFeeCapOccurrence() {
        return feeCapOccurrence;
    }

    public void setFeeCapOccurrence(Integer feeCapOccurrence) {
        this.feeCapOccurrence = feeCapOccurrence;
    }

    public OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeCap minMaxType(OBMinMaxType1Code minMaxType) {
        this.minMaxType = minMaxType;
        return this;
    }

    /**
     * Get minMaxType
     *
     * @return minMaxType
     **/
    @JsonProperty("MinMaxType")
    @ApiModelProperty(value = "")
    public OBMinMaxType1Code getMinMaxType() {
        return minMaxType;
    }

    public void setMinMaxType(OBMinMaxType1Code minMaxType) {
        this.minMaxType = minMaxType;
    }

    public OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeCap notes(List<String> notes) {
        this.notes = notes;
        return this;
    }

    public OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeCap addNotesItem(String notesItem) {
        if (this.notes == null) {
            this.notes = new ArrayList<>();
        }
        this.notes.add(notesItem);
        return this;
    }

    /**
     * Get notes
     *
     * @return notes
     **/
    @JsonProperty("Notes")
    @ApiModelProperty(value = "")
    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeCap feeCapAmount(String feeCapAmount) {
        this.feeCapAmount = feeCapAmount;
        return this;
    }

    /**
     * Cap amount charged for a fee/charge
     *
     * @return feeCapAmount
     **/
    @JsonProperty("FeeCapAmount")
    @ApiModelProperty(value = "Cap amount charged for a fee/charge")
    @Pattern(regexp = "^(-?\\\\d{1,14}){1}(\\\\.\\\\d{1,4}){0,1}$")
    public String getFeeCapAmount() {
        return feeCapAmount;
    }

    public void setFeeCapAmount(String feeCapAmount) {
        this.feeCapAmount = feeCapAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cappingPeriod, otherFeeType, feeType, feeCapOccurrence, minMaxType, notes, feeCapAmount);
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeCap obReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeCap = (OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeCap) o;
        return Objects.equals(this.cappingPeriod, obReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeCap.cappingPeriod) &&
                Objects.equals(this.otherFeeType, obReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeCap.otherFeeType) &&
                Objects.equals(this.feeType, obReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeCap.feeType) &&
                Objects.equals(this.feeCapOccurrence, obReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeCap.feeCapOccurrence) &&
                Objects.equals(this.minMaxType, obReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeCap.minMaxType) &&
                Objects.equals(this.notes, obReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeCap.notes) &&
                Objects.equals(this.feeCapAmount, obReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeCap.feeCapAmount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeCap {\n");

        sb.append("    cappingPeriod: ").append(toIndentedString(cappingPeriod)).append("\n");
        sb.append("    otherFeeType: ").append(toIndentedString(otherFeeType)).append("\n");
        sb.append("    feeType: ").append(toIndentedString(feeType)).append("\n");
        sb.append("    feeCapOccurrence: ").append(toIndentedString(feeCapOccurrence)).append("\n");
        sb.append("    minMaxType: ").append(toIndentedString(minMaxType)).append("\n");
        sb.append("    notes: ").append(toIndentedString(notes)).append("\n");
        sb.append("    feeCapAmount: ").append(toIndentedString(feeCapAmount)).append("\n");
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
     * Fee/charge type which is being capped
     */
    public enum FeeTypeEnum {
        FBAO("FBAO"),

        FBAR("FBAR"),

        FBEB("FBEB"),

        FBIT("FBIT"),

        FBOR("FBOR"),

        FBOS("FBOS"),

        FBSC("FBSC"),

        FBTO("FBTO"),

        FBUB("FBUB"),

        FBUT("FBUT"),

        FTOT("FTOT"),

        FTUT("FTUT");

        private String value;

        FeeTypeEnum(String value) {
            this.value = value;
        }

        @JsonCreator
        public static FeeTypeEnum fromValue(String text) {
            for (FeeTypeEnum b : FeeTypeEnum.values()) {
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
