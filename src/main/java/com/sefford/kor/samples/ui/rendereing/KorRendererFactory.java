package com.sefford.kor.samples.ui.rendereing;

import android.content.res.Resources;
import android.view.Display;
import com.sefford.brender.interfaces.Postable;
import com.sefford.brender.interfaces.Renderer;
import com.sefford.brender.interfaces.RendererFactory;
import com.sefford.kor.samples.R;
import com.sefford.kor.samples.ui.rendereing.renderers.ContactRenderer;
import com.sefford.kor.samples.ui.rendereing.renderers.NullRenderer;
import com.sefford.kor.samples.ui.rendereing.renderers.ScannedDeviceRenderer;
import com.sefford.kor.samples.ui.rendereing.renderers.UserRenderer;
import com.sefford.kor.samples.ui.transformations.RoundedBorderTransformation;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

/**
 * Renderer Factory
 *
 * @author Saul Diaz <sefford@gmail.com>
 */

public class KorRendererFactory implements RendererFactory<Void> {

    final Picasso picasso;
    final Resources resources;
    final Display display;

    @Inject
    public KorRendererFactory(Picasso picasso, Resources resources, Display display) {
        this.picasso = picasso;
        this.resources = resources;
        this.display = display;
    }

    @Override
    public Renderer getRenderer(int renderableId, Postable postable, Void extras) {
        switch (renderableId) {
            case R.layout.item_scanned_device:
                return new ScannedDeviceRenderer();
            case R.layout.item_contact:
                return new ContactRenderer(new RoundedBorderTransformation(resources.getDimensionPixelSize(R.dimen.avatar_rounded_corners)),
                        picasso, resources);
            case R.layout.item_user:
                return new UserRenderer(picasso, resources, display);
            default:
                return new NullRenderer();
        }
    }
}
