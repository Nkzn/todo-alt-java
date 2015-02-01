package jp.water_cell.android.template;

import android.app.Application;

import jp.water_cell.android.template.prngfix.PRNGFixes;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        PRNGFixes.apply();
    }
}
