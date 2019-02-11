package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;
import android.util.Pair;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {

    String asyncTaskResponse = null;
    Context appContext = InstrumentationRegistry.getTargetContext();
    Exception mError = null;

    @Test
    public void testVerifyJoke() throws InterruptedException {

        final CountDownLatch latch = new CountDownLatch(1);

        final MainActivityFragment.EndpointsAsyncTask testTask = new MainActivityFragment.EndpointsAsyncTask() {};
        testTask.setListener(new MainActivityFragment.EndpointsAsyncTask.Listener() {
            @Override
            public void onComplete(String jsonString, Exception e) {
                asyncTaskResponse = jsonString;
                mError = e;
               latch.countDown();
            }
        });
        testTask.execute(new Pair<Context, String>(appContext, "TEST"));
        latch.await();

        assertNull(mError);
        assertFalse(TextUtils.isEmpty(asyncTaskResponse));
        assertTrue(asyncTaskResponse.startsWith("Hi"));
        assertTrue(asyncTaskResponse.endsWith("TEST"));
    }
}
