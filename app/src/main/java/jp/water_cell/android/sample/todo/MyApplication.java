package jp.water_cell.android.sample.todo;

import android.app.Application;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (!BuildConfig.DEBUG) {
            PRNGFixes.apply();
        }
    }
}
