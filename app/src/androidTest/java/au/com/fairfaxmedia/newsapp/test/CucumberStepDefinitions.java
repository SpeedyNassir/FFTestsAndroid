package au.com.fairfaxmedia.newsapp.test;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Rule;
import org.junit.runner.RunWith;

import au.com.fairfaxmedia.newsapp.R;
import au.com.fairfaxmedia.newsapp.test.util.ActivityFinisher;
import au.com.fairfaxmedia.newsapp.test.util.WebViewLocators;
import au.com.fairfaxmedia.newsapp.view.activity.MainActivity;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class CucumberStepDefinitions extends ActivityInstrumentationTestCase2 {

    private Activity mActivity;

    public CucumberStepDefinitions() {
        super(LauncherActivity.class);
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mActivity = mActivityTestRule.launchActivity(new Intent()); // Start Activity before each test scenario
        assertNotNull(mActivity);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        ActivityFinisher.finishOpenActivities();
    }

    @Given("^I launch the News App$")
    public void launchApp() throws Throwable {
        Thread.sleep(2000);
        HelperMethods.verifyElementWithIdDispalyed(R.id.news_list_fragment);
    }

    @Then("^I Verify ([0-9]*) Articles are present in Index Screen$")
    public void exactNumberOfElelemtsInRecyclerView(int articlesCount) throws Throwable {
        onView(withId(R.id.recycler_view)).check(new RecyclerViewItemCountAssertion(articlesCount));
    }

    @Then("^I Verify No More Than ([0-9]*) Articles are present in Index Screen$")
    public void noMoreThanNumberOfElementsInRecyclerView(int articlesCount) throws Throwable {
        onView(withId(R.id.recycler_view)).check(new RecyclerViewItemCountLessOrEqualAssertion(articlesCount));
    }

    @Then("^I should see ([a-zA-Z]*) for each article in Index Screen$")
    public void verifyImageForEachArticle(String elementType) throws Throwable {
        switch (elementType) {
            case "Image":
                HelperMethods.verifyElementInEachArticle(R.id.recycler_view, R.id.thumbnail);
            case "Heading":
                HelperMethods.verifyElementInEachArticle(R.id.recycler_view, R.id.headline);
            case "Abstract":
                HelperMethods.verifyElementInEachArticle(R.id.recycler_view, R.id.theAbstract);
            case "ByLine":
                HelperMethods.verifyElementInEachArticle(R.id.recycler_view, R.id.byLine);
        }
    }

    @When("^I Tap on ([0-9]*) (.*) news$")
    public void tapOnNewsArticle(int articlePosition, String _) throws Throwable {
        HelperMethods.tapOnPositionRecyclerView(articlePosition);
    }

    @Then("^I should see full screen page$")
    public void verifyFullScreenNews() throws Throwable {
        HelperMethods.verifyWebViewId(WebViewLocators.SAVE_ARTICLE);
        HelperMethods.verifyTextDispalyed(R.string.home);
    }

    @And("^I should see screen with News heading$")
    public void verifyNewsHeading() throws Throwable {
        HelperMethods.verifyTextDispalyed(R.string.app_name);
    }

    @When("^I tap on ([A-z]*) button$")
    public void tapOnNewsButton(String _) throws Throwable {
        HelperMethods.tapOnContentWithText("Navigate up");
    }
}
