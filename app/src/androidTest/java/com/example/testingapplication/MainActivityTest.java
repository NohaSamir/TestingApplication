package com.example.testingapplication;

import androidx.lifecycle.MutableLiveData;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class, true, true);

    @Mock
    public CalculatorViewModel calculatorViewModel;

    /* @Rule
     public MockitoRule mockitoRule = MockitoJUnit.rule();
     */

    private MutableLiveData<Integer> data = new MutableLiveData<>();


    @Before
    public void init() {

        //calculatorViewModel = spy(CalculatorViewModel.class);
        calculatorViewModel = mock(CalculatorViewModel.class);

    }

    @Test
    public void onMultiplyClicked_TextViewChangeCorrectly() {


        onView(withId(R.id.editText1)).perform(typeText("2"));

        onView(withId(R.id.editText2)).perform(typeText("2"));

        onView(withId(R.id.button)).perform(closeSoftKeyboard(), click());

        onView(withId(R.id.textView)).check(ViewAssertions.matches(withText("4")));


    }

    /**
     * I need to test how to replace activity view model with my fake view model
     */
    @Test
    public void onMultiplyClicked_TextViewChange() {

        activityTestRule.getActivity().setViewModel(calculatorViewModel);

        doNothing().when(calculatorViewModel).multiply(Mockito.anyString(), Mockito.anyString());

        when(calculatorViewModel.getResult()).thenReturn(data);

        onView(withId(R.id.button)).perform(closeSoftKeyboard(), click());

        data.postValue(6);

        onView(withId(R.id.textView)).check(ViewAssertions.matches(withText("6")));


    }
}