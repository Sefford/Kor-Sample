package com.sefford.kor.samples.core.modules;

import android.content.Context;
import com.sefford.kor.samples.ui.activities.*;
import com.sefford.kor.samples.ui.application.KorSampleApplication;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Dependency injection Module for Dagger
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
@Module(
        // The Module is incomplete, as we require the Application context for the DataProvider
        complete = false,
        includes = {
                ControllerModule.class,
                CoreModule.class,
                UiModule.class
        },
        injects = {
                // Activities
                BaseActivity.class,
                ContactsActivity.class,
                DevicesActivity.class,
                NetworkActivity.class,
                MainActivity.class,
                // Applications
                KorSampleApplication.class,

        })
public class ApplicationModule {

    /**
     * Application context
     */
    final Context context;

    /**
     * Creates new Module with the Application Context
     *
     * @param context Application Context
     */
    public ApplicationModule(Context context) {
        this.context = context.getApplicationContext();
    }

    /**
     * Provides an Application Context
     *
     * @return Application Context
     */
    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }
}
