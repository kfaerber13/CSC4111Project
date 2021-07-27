package com.happy.Reptile_Store.petsgrocery;

import android.app.LauncherActivity;

import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;

import org.junit.Before;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;


public class MainActivityTest {

    ActivityScenario<MainActivity> scenario;

    @Before
    public void setUp(){
        scenario = ActivityScenario.launch(MainActivity.class);
        scenario.moveToState(Lifecycle.State.RESUMED);

    }

    @Test
    public void testDisplayedCart(){
        onView(withId(R.id.btn_save_product)).check(matches(isDisplayed()));
    }

    @Test
    public void testOpenCart(){
        onView(withId(R.id.btn_save_product)).perform(click());
    }

    @Test
    public void testDisplayedrv(){
        onView(withId(R.id.products_recycler)).check(matches(isDisplayed()));
    }
    @Test
    public void testOpenrv1(){
        onView(withId(R.id.products_recycler)).perform(ViewActions.click());
    }
    @Test
    public void testOpenrv2(){
        onView(withId(R.id.products_recycler)).perform(ViewActions.click());
        onView(withId(R.id.rv_images)).check(matches(isDisplayed()));
    }

    @Test
    public void testOpenrv3(){
        onView(withId(R.id.products_recycler)).perform(ViewActions.click());
        onView(withId(R.id.product_desc)).check(matches(isDisplayed()));
    }

    @Test
    public void testOpenrv4(){
        onView(withId(R.id.products_recycler)).perform(ViewActions.click());
        onView(withId(R.id.product_quantity)).check(matches(isDisplayed()));
    }

    @Test
    public void testOpenrv5(){
        onView(withId(R.id.products_recycler)).perform(ViewActions.click());
        onView(withId(R.id.product_price)).check(matches(isDisplayed()));
    }

    @Test
    public void testOpenrv6(){
        onView(withId(R.id.products_recycler)).perform(ViewActions.click());
        onView(withId(R.id.add_cart_product)).perform(ViewActions.click());
    }

    @Test
    public void testOpenrv7(){
        onView(withId(R.id.products_recycler)).perform(ViewActions.click());
        onView(withId(R.id.add_cart_product)).perform(ViewActions.click());
        onView(withId(R.id.btn_save_product)).perform(click());
        onView(withId(R.id.cart_recycler)).check(matches(isDisplayed()));
    }

    @Test
    public void testOpenrv8(){
        onView(withId(R.id.products_recycler)).perform(ViewActions.click());
        onView(withId(R.id.add_cart_product)).perform(ViewActions.click());
        onView(withId(R.id.btn_save_product)).perform(click());
        onView(withId(R.id.cart_recycler)).check(matches(isDisplayed()));
        onView(withId(R.id.cart_recycler)).perform(ViewActions.swipeLeft());
    }

}