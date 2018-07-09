package au.com.fairfaxmedia.newsapp;

import android.support.test.espresso.IdlingRegistry;

import com.jakewharton.espresso.OkHttp3IdlingResource;

import okhttp3.OkHttpClient;

public abstract class IdlingResources {
    public static void registerOkHtpp(OkHttpClient client) {
        IdlingRegistry.getInstance().register(OkHttp3IdlingResource.create("OkHtpp", client));

    }
}
