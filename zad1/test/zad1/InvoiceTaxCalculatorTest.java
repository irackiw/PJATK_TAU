package zad1;

import org.junit.*;

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
        assertEquals(result, 230.00);
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
        assertEquals(result, 230.00);
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
        assertEquals(result, 190.00);
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
        assertEquals(result, 190.00);
    }

    @Test
    public void testGetIncomeTaxFromNetException() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.getIncomeTaxFromNet(-1.00, 0.19);
        });
    }
}
