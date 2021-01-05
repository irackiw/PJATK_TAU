package com.example.app;

import org.joda.money.Money;

public class InvoiceTaxCalculator {

    private static final double ipBoxTaxPercentage = 0.05;
    private static final int splitPaymentRequiredLimitPln = 15000;

    public double getVatFromGross(double grossValue, double vatRate) {
        this.validatePositiveValue(grossValue);
        return Math.round(grossValue * vatRate / (1.00 + vatRate));
    }

    public double getVatFromNet(double grossValue, double vatRate) {
        this.validatePositiveValue(grossValue);
        return Math.round(grossValue * vatRate);
    }

    public double getIncomeTaxFromGross(double grossValue, double vatRate, double incomeTaxRate) {
        this.validatePositiveValue(grossValue);
        return Math.round((grossValue - this.getVatFromGross(grossValue, vatRate)) * incomeTaxRate);
    }

    public double getIncomeTaxFromNet(double netValue, double incomeTaxRate) {
        this.validatePositiveValue(netValue);
        return netValue * incomeTaxRate;
    }

    public double getIncomeTaxFromCurrencyUnit(Money money) {
        this.validatePositiveValue(money.getAmount().doubleValue());
        return this.getIncomeTaxFromNet(money.getAmount().doubleValue(), ipBoxTaxPercentage);
    }

    public boolean isSplitPaymentRequired(double value) {
        this.validatePositiveValue(value);
        return value > splitPaymentRequiredLimitPln;
    }

    public boolean isSplitPaymentRequiredForMoney(Money money, String currency) {
        if (!"PLN".equals(currency)) {
            throw new IllegalArgumentException("Currency not supported.");
        }
        double value = money.getAmount().doubleValue();

        return this.isSplitPaymentRequired(value);
    }

    public float getOrderTotalPrice(Order order) {
        return (float) order.getQty() * order.getPrice();
    }

    public boolean isSplitPaymentRequiredForOrder(Order order)
    {
        float value = this.getOrderTotalPrice(order);
        return this.isSplitPaymentRequired(value);
    }

    protected void validatePositiveValue(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value must be positive.");
        }
    }

}
