package br.com.battista.arcadiacaller;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

@SmallTest
@RunWith(AndroidJUnit4.class)
public class ApplicationTest {

    @Test
    public void shouldValidAppPackageName() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("br.com.battista.arcadiacaller", appContext.getPackageName());
    }
}
