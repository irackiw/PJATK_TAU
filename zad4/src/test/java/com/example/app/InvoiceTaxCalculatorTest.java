package com.example.app;

import org.joda.money.Money;
import org.junit.*;
import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class InvoiceTaxCalculatorTest {

    private InvoiceTaxCalculator calculator;

    @Before
    public void setUp() {
        calculator = new InvoiceTaxCalculator();
    }

    @After
    public void tearDown() {
        calculator = null;
    }

    @Test
    public void testCalculatorClassType() {
        assertTrue(calculator instanceof InvoiceTaxCalculator);
    }

    @Test
    public void testGetVatFromGross() {
        double result = calculator.getVatFromGross(1230.00, 0.23);
        assertEquals(230.00, result);
    }

    @Test
    public void testGetVatFromGrossException() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.getVatFromGross(-5, 0.23);
        });
    }

    @Test
    public void testGetVatFromNet() {
        double result = calculator.getVatFromNet(1000.00, 0.23);
        assertEquals(230.00, result);
    }

    @Test
    public void testGetVatFromNetException() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.getVatFromNet(-5, 0.23);
        });
    }

    @Test
    public void testGetIncomeTaxFromGross() {
        double result = calculator.getIncomeTaxFromGross(1230.00, 0.23, 0.19);
        assertEquals(190.00, result);
    }

    @Test
    public void testGetIncomeTaxFromGrossException() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.getIncomeTaxFromGross(-5, 0.23, 0.19);
        });
    }

    @Test
    public void testGetIncomeTaxFromNet() {
        double result = calculator.getIncomeTaxFromNet(1000.00, 0.19);
        assertEquals(190.00, result);
    }

    @Test
    public void testGetIncomeTaxFromNetException() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.getIncomeTaxFromNet(-1.00, 0.19);
        });
    }

    @Test
    public void testGetIncomeTaxFromCurrencyUnitException() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.getIncomeTaxFromCurrencyUnit(Money.parse("PLN -5.00"));
        });
    }

    @Test
    public void testGetIncomeTaxFromCurrencyUnit() {
        double result = calculator.getIncomeTaxFromCurrencyUnit(Money.parse("PLN 100.00"));
        assertEquals(5.00, result);
    }

    @Test
    public void testIsSplitPaymentRequiredException() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.isSplitPaymentRequired(-5000.00);
        });
    }

    @Test
    public void testIsSplitPaymentRequiredPositive() {
        boolean result = calculator.isSplitPaymentRequired(9000.00);
        assertEquals(false, result);
    }

    @Test
    public void testIsSplitPaymentRequiredNegative() {
        boolean result = calculator.isSplitPaymentRequired(9000.00);
        assertEquals(false, result);
    }

    @Test
    public void testIsSplitPaymentRequiredForMoneyCurrencyException() {
        Money money = Money.parse("PLN 100.00");
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.isSplitPaymentRequiredForMoney(money, "GBP");
        });
    }

    @Test
    public void testIsSplitPaymentRequiredForMoneyNegativeException() {
        Money money = Money.parse("PLN -100.00");
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.isSplitPaymentRequiredForMoney(money, "PLN");
        });
    }

    @Test
    public void testIsSplitPaymentRequiredForMoneyNegative() {
        Money money = Money.parse("PLN 100.00");
        boolean result = calculator.isSplitPaymentRequiredForMoney(money, "PLN");

        assertEquals(false, result);
    }

    @Test
    public void testIsSplitPaymentRequiredForMoneyPositive() {
        Money money = Money.parse("PLN 10005.00");
        boolean result = calculator.isSplitPaymentRequiredForMoney(money, "PLN");

        assertEquals(false, result);
    }

    @Test
    public void testGetOrderTotalPrice() {
        Order order = mock(Order.class);
        when(order.getQty()).thenReturn(5);
        when(order.getPrice()).thenReturn((float) 200.00);

        assertEquals(1000.00, this.calculator.getOrderTotalPrice(order));
    }

    @Test
    public void testIsSplitPaymentRequiredForOrder() {
        Order order = mock(Order.class);
        when(order.getQty()).thenReturn(5);
        when(order.getPrice()).thenReturn((float) 200.00);

        assertFalse(this.calculator.isSplitPaymentRequiredForOrder(order));
    }

}