package zad1;

public class InvoiceTaxCalculator {

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

    protected void validatePositiveValue(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value must be positive.");
        }
    }

}
