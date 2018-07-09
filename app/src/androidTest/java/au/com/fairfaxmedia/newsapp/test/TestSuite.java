package au.com.fairfaxmedia.newsapp.test;

import android.support.test.runner.AndroidJUnit4;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;

//TODO Make tagging & plugins configurable and remove hard codded tags
@RunWith(AndroidJUnit4.class)
//@CucumberOptions(features = "features", tags = "@Sanity_01", plugin = {"pretty"})
@CucumberOptions(features = "features", plugin = {"pretty"})

public class TestSuite {
}