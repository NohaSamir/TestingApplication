package com.example.testingapplication;
// Put this in the com.example.testingapplication.servicelocator package so that we can use
// the setServiceLocator to a custom provider.

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.android.dx.command.Main;
import com.example.testingapplication.servicelocator.ServiceLocatorSetter;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.model.Statement;
import org.mockito.Mockito;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    /**
     * A test rule, which encapsulate the {@link ActivityTestRule}, and "delegates" the
     * {@link TestRule#apply(Statement, Description)} to it. And set the
     * {@link com.example.testingapplication.servicelocator.ServiceLocator} to get a
     * {@link Multiplier} instead of the real object.
     */
    @Rule
    public ActivityTestRule<MainActivity> delegate =
            new ActivityTestRule<MainActivity>(MainActivity.class, true, true) {
        @Override
        public Statement apply(final Statement base, final Description description) {
            multiplier = Mockito.mock(Multiplier.class);
            final ActivityTestRule<MainActivity> delegate =
                    new ActivityTestRule<>(MainActivity.class, true, true);
            ServiceLocatorSetter.mockTheServiceLocator(multiplier);
            return delegate.apply(base, description);
        }

    };

    @After
    public void reset(){
        ServiceLocatorSetter.reset();
    }

    private Multiplier multiplier = Mockito.mock(Multiplier.class);

    private MutableLiveData<Integer> data = new MutableLiveData<>();

    /**
     * Test using Mockito
     * <p>
     * I need to test how to replace activity view model with my fake view model using mockito
     */
    @Test
    public void onMultiplyClicked_TextViewChange() {

        Mockito.when(multiplier.multiply("", "", null)).thenReturn(6);

        onView(withId(R.id.button)).perform(closeSoftKeyboard(), click());

        onView(withId(R.id.textView)).check(ViewAssertions.matches(withText("6")));

    }

    /**
     * Test using flavor
     * <p>
     * by this way we can observe our live data in onCreate()
     * after run change build variant to mock
     */
    @Test
    public void onMultiplyClicked_TextViewChangeUsingTheSingleton() {
        Mockito.when(multiplier.multiply("", "", null)).thenReturn(6);

        onView(withId(R.id.button)).perform(closeSoftKeyboard(), click());

        onView(withId(R.id.textView)).check(ViewAssertions.matches(withText("6")));

    }

}
