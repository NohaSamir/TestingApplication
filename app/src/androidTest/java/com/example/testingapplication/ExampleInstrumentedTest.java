package com.example.testingapplication;
// Put this in the com.example.testingapplication.servicelocator package so that we can use
// the setServiceLocator to a custom provider.

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.testingapplication.servicelocator.ServiceLocatorSetter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.model.Statement;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

class MockViewModel extends CalculatorViewModel {
    private MutableLiveData<Integer> result = new MutableLiveData<>();

    MutableLiveData<Integer> getResult() {
        return result;
    }

    void multiply(String x, String y) {
        result.setValue(6);
    }
}

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
     * {@link MockViewModel} instead of the real object.
     */
    @Rule
    public TestRule activityTestRule = new TestRule() {
        @Override
        public Statement apply(Statement base, Description description) {
            ActivityTestRule<MainActivity> delegate = new ActivityTestRule<>(MainActivity.class, true, true);
            calculatorViewModel = new MockViewModel();
            ServiceLocatorSetter.mockTheServiceLocator(calculatorViewModel);
            return delegate.apply(base, description);
        }

    };

    private CalculatorViewModel calculatorViewModel;

    private MutableLiveData<Integer> data = new MutableLiveData<>();

    /**
     * Test using Mockito
     * <p>
     * I need to test how to replace activity view model with my fake view model using mockito
     */
    @Test
    public void onMultiplyClicked_TextViewChange() {

        onView(withId(R.id.button)).perform(closeSoftKeyboard(), click());

        calculatorViewModel.getResult().postValue(6);

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

        onView(withId(R.id.button)).perform(closeSoftKeyboard(), click());

        onView(withId(R.id.textView)).check(ViewAssertions.matches(withText("6")));

    }

}
