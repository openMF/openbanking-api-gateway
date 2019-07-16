package hu.dpc.openbanking.apigateway;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.org.openbanking.v3_1_2.payments.OBActiveOrHistoricCurrencyAndAmount;
import uk.org.openbanking.v3_1_2.payments.OBWriteDomesticStandingOrderResponse4DataCharges;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PaymentsHelper {
    /**
     * Format Domestic Charges
     *
     * @param charge
     * @param withBearer
     * @return
     */
    @NotNull
    public static String formatPaymentCharge(@NotNull final OBWriteDomesticStandingOrderResponse4DataCharges charge, final boolean withBearer) {
        final StringBuilder sbuf = new StringBuilder(256);
        if (withBearer) {
            sbuf.append(charge.getChargeBearer().toString()).append(": ");
        }

        final OBExternalPaymentChargeType1Code paymentChargeType = OBExternalPaymentChargeType1Code.find(charge.getType());
        if (null == paymentChargeType) {
            sbuf.append(charge.getType());
        } else {
            sbuf.append(paymentChargeType.getDescr());
        }
        sbuf.append(": ");

        final OBActiveOrHistoricCurrencyAndAmount chargeAmount = charge.getAmount();
        sbuf.append(chargeAmount.getAmount()).append(' ').append(chargeAmount.getCurrency());

        return sbuf.toString();
    }

    /**
     * Sort charges on charge bearer; type; amount currency; amount
     *
     * @param items
     */
    public static void sortDomesticStandingOrderDataCharges(@NotNull final List<OBWriteDomesticStandingOrderResponse4DataCharges> items) {
        Collections.sort(items, new Comparator<OBWriteDomesticStandingOrderResponse4DataCharges>() {
            @Override
            public int compare(@NotNull final OBWriteDomesticStandingOrderResponse4DataCharges item1, @NotNull final OBWriteDomesticStandingOrderResponse4DataCharges item2) {
                int cmp = item1.getChargeBearer().compareTo(item2.getChargeBearer());
                if (0 == cmp) {
                    cmp = item1.getType().compareTo(item2.getType());
                    if (0 == cmp) {
                        final OBActiveOrHistoricCurrencyAndAmount item1Amount = item1.getAmount();
                        final OBActiveOrHistoricCurrencyAndAmount item2Amount = item2.getAmount();
                        cmp = item1Amount.getCurrency().compareTo(item2Amount.getCurrency());
                        if (0 == cmp) {
                            cmp = item1Amount.getAmount().compareTo(item2Amount.getAmount());
                        }
                    }
                }
                return cmp;
            }
        });
    }

    public enum OBExternalPaymentChargeType1Code {
        UK_OBIE_CHAPSOut("UK.OBIE.CHAPSOut", "CHAPS Payment Service fee"),
        UK_OBIE_BalanceTransferOut("UK.OBIE.BalanceTransferOut", "Balance Transfer Service fee"),
        UK_OBIE_MoneyTransferOut("UK.OBIE.MoneyTransferOut", "Money Transfer Service fee");

        private final String shortCode;
        private final String descr;

        OBExternalPaymentChargeType1Code(final String shortCode, final String descr) {
            this.shortCode = shortCode;
            this.descr = descr;
        }

        @Nullable
        public static OBExternalPaymentChargeType1Code find(@NotNull final String shortCode) {
            for (final OBExternalPaymentChargeType1Code item : OBExternalPaymentChargeType1Code.values()) {
                if (shortCode.equalsIgnoreCase(item.shortCode)) {
                    return item;
                }
            }
            return null;
        }

        public String getShortCode() {
            return shortCode;
        }

        public String getDescr() {
            return descr;
        }
    }
}
