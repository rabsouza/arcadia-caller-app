package br.com.battista.arcadiacaller.util;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(android.util.Log.class)
public class CurrencyUtilsTest {

    @Before
    public void setup() {
        mockStatic(Log.class);
    }

    @Test
    public void shouldFormatValueAndTrucatDecimalPlacesWhenValueHaveFiveDecimalPlaces() {
        String valueFormatted = CurrencyUtils.format(new BigDecimal(12.98795));
        assertThat(valueFormatted, equalTo("R$ 12,99"));
    }

    @Test
    public void shouldFormatValueWhenValueWithDecimalPlaces() {
        String valueFormatted = CurrencyUtils.format(new BigDecimal(12.98));
        assertThat(valueFormatted, equalTo("R$ 12,98"));
    }

    @Test
    public void shouldFormatValueWhenValueNoDecimalPlaces() {
        String valueFormatted = CurrencyUtils.format(new BigDecimal(12));
        assertThat(valueFormatted, equalTo("R$ 12,00"));
    }

    @Test
    public void shouldReturnEmptyStringWhenValueNull() {
        String valueFormatted = CurrencyUtils.format(null);
        assertThat(valueFormatted, equalTo(""));
    }

}