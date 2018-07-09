package au.com.fairfaxmedia.newsapp.test;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.web.webdriver.Locator;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import au.com.fairfaxmedia.newsapp.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.core.internal.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.web.sugar.Web.onWebView;
import static android.support.test.espresso.web.webdriver.DriverAtoms.findElement;
import static org.hamcrest.Matchers.allOf;

public class HelperMethods {
    private static int getCountFromRecyclerView(@IdRes int RecyclerViewId) {
        final int[] COUNT = {0};
        Matcher matcher = new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                COUNT[0] = ((RecyclerView) item).getAdapter().getItemCount();
                return true;
            }

            @Override
            public void describeTo(Description description) {
            }
        };
        onView(allOf(withId(RecyclerViewId), isDisplayed())).check(matches(matcher));
        int result = COUNT[0];
        COUNT[0] = 0;
        return result;
    }

    private static Matcher<View> atPosition(final int position, @NonNull final Matcher<View> itemMatcher) {
        checkNotNull(itemMatcher);
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(final RecyclerView view) {
                RecyclerView.ViewHolder viewHolder = view.findViewHolderForAdapterPosition(position);
                if (viewHolder == null) {
                    // has no item on such position
                    return false;
                }
                return itemMatcher.matches(viewHolder.itemView);
            }
        };
    }

    private static int countNumberOfArticles() {
        return getCountFromRecyclerView(R.id.recycler_view);
    }

    public static void verifyElementInEachArticle(int parentElement, int childElement) {
        int size = countNumberOfArticles();
        for (int i = 0; i < size; i++) {
            onView(withId(parentElement))
                    .perform(scrollToPosition(i))
                    .check(matches(atPosition(i, hasDescendant(withId(childElement))))).check(matches(isDisplayed()));
        }
    }

    public static void tapOnPositionRecyclerView(int newsPosition) {
        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(newsPosition, click()));
    }

    public static void verifyTextDispalyed(int textToVerify) {
        onView(withText(textToVerify)).check(matches(isDisplayed()));
    }

    public static void verifyElementWithIdDispalyed(int idOfElement) {
        onView(withId(idOfElement)).check(matches(isDisplayed()));
    }

    public static void tapOnContentWithText(String contentText) {
        onView(withContentDescription(contentText)).check(matches(isDisplayed())).perform(click());
    }

    public static void verifyWebViewId(String contentText) {
        onWebView().withElement(findElement(Locator.ID, contentText));
    }
}
