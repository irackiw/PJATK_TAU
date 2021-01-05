package com.example.app;

import org.junit.*;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

public class ClientTest {

    private Client client;

    @Before
    public void setUp() {
        client = new Client("TEST", 1990);
    }

    @After
    public void tearDown() {
        client = null;
    }

    @Test
    public void testCalculatorClassType() {
        assertTrue(client instanceof Client);
    }

    @Test
    public void testGetAge(){
        Calendar calendar = mock(Calendar.class);
        given(calendar.get(Calendar.YEAR)).willReturn(2020);

        assertEquals(30, client.getAge(calendar));
    }
}
