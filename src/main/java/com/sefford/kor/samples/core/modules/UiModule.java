package com.sefford.kor.samples.core.modules;

import android.content.Context;
import android.content.res.Resources;
import android.view.Display;
import android.view.WindowManager;
import com.sefford.brender.builder.Builder;
import com.sefford.kor.samples.ui.rendereing.KorRendererFactory;
import com.squareup.picasso.Picasso;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Dependency injection Module for Core elements
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
@Module(complete = false,
        library = true)
public class UiModule {

    @Provides
    public Builder provideRendererBuilder(KorRendererFactory factory) {
        return new Builder(factory);
    }

    @Provides
    @Singleton
    public Resources provideResources(Context context) {
        return context.getResources();
    }

    @Provides
    @Singleton
    public Picasso providePicasso(Context context) {
        return Picasso.with(context);
    }

    @Provides
    @Singleton
    public Display provideDisplay(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay();
    }
}
