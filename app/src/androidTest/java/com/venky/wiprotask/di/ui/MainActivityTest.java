package com.venky.wiprotask.di.ui;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.venky.wiprotask.R;
import com.venky.wiprotask.di.TestComponentRule;
import com.venky.wiprotask.ui.itemdetails.ItemDetailsFragment;
import com.venky.wiprotask.ui.main.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    public final TestComponentRule component =
            new TestComponentRule(InstrumentationRegistry.getTargetContext());

    public final IntentsTestRule<MainActivity> main =
            new IntentsTestRule<>(MainActivity.class,false,false);

    @Rule
    public TestRule chain = RuleChain.outerRule(component).around(main);

    @Before
    public void setup() {

    }

    @Test
    public void checkViewsDisplay() {
        main.launchActivity(MainActivity.getStartIntent(component.getContext()));

        onView(withId(R.id.drawer_layout))
                .check(matches(isDisplayed()));

        onView(withId(R.id.nav_view))
                .check(matches(isDisplayed()));
    }
}
