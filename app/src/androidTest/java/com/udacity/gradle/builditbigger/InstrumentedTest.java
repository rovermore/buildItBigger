package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Pair;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {

    Context appContext = InstrumentationRegistry.getTargetContext();

    @Test
    public void testVerifyJoke() throws InterruptedException {

        final CountDownLatch latch = new CountDownLatch(1);

        final MainActivityFragment.EndpointsAsyncTask testTask = new MainActivityFragment.EndpointsAsyncTask() {
            @Override
            protected void onPostExecute(String result) {

                if (result != null && result.length() > 0) {
                    latch.countDown();

                }
            }
        };
        testTask.execute(new Pair<Context, String>(appContext, "TEST"));
        latch.await();
    }
}
