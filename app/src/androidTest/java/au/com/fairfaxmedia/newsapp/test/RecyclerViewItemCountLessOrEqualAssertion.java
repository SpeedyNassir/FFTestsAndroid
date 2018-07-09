package au.com.fairfaxmedia.newsapp.test;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class RecyclerViewItemCountLessOrEqualAssertion implements ViewAssertion {
    private final int expectedCount;

    RecyclerViewItemCountLessOrEqualAssertion(int expectedCount) {
        this.expectedCount = expectedCount;
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) {
            throw noViewFoundException;
        }
        RecyclerView recyclerView = (RecyclerView) view;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        assertThat("Count Greater Than Expected",
                adapter.getItemCount(),
                lessThanOrEqualTo(expectedCount));
    }
}
