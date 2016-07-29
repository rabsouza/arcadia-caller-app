package br.com.battista.arcadiacaller.util;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Calendar;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(android.util.Log.class)
public class DateUltisTest {

    private String expDate = "^\\d{2}/\\d{2}/\\d{4}$";

    @Before
    public void setup() {
        mockStatic(Log.class);
    }

    @Test
    public void shouldFormatDateWhenCalenderValid() {
        String dateFormatted = DateUtils.format(Calendar.getInstance());
        assertTrue(dateFormatted.matches(expDate));
    }

    @Test
    public void shouldReturnEmptyStringWhenCalenderNull() {
        String dateFormatted = DateUtils.format(null);
        assertThat(dateFormatted, equalTo(""));
    }
}
