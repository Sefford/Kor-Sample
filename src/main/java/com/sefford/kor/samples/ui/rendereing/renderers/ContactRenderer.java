package com.sefford.kor.samples.ui.rendereing.renderers;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.sefford.brender.interfaces.Renderer;
import com.sefford.kor.samples.R;
import com.sefford.kor.samples.model.Contact;
import com.sefford.kor.samples.ui.components.LetterTileDrawable;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;


/**
 * Followed Renderer is a Decorator of the UserRenderer to display user
 * view elements together with a follow button.
 * <p/>
 *
 * @author Saul Diaz <saul@feverup.com>
 */
public class ContactRenderer implements Renderer<Contact> {


    /**
     * Rounded corners transformation for the Avatar View
     */
    final Transformation transformation;
    final Picasso picasso;
    final Resources resources;
    /**
     * Avatar of the View
     */
    @InjectView(R.id.iv_avatar)
    ImageView avatar;
    @InjectView(R.id.tv_name)
    TextView name;

    /**
     * Creates a new instance of ContactRenderer
     *
     * @param bus
     * @param picasso
     * @param resources
     */
    public ContactRenderer(Transformation transformation, Picasso picasso, Resources resources) {
        this.transformation = transformation;
        this.picasso = picasso;
        this.resources = resources;
    }

    @Override
    public void mapViews(View view) {
        ButterKnife.inject(this, view);
    }

    @Override
    public void hookUpListeners(View view, final Contact renderable) {
        // Empty
    }

    @Override
    public void render(Context context, Contact renderable, int position, boolean first, boolean last) {
        name.setText(renderable.getName());
        if (renderable.getImage().startsWith("http")) {
            picasso.load(renderable.getImage())
                    .placeholder(R.drawable.avatar_placeholder)
                    .transform(transformation)
                    .into(avatar);
        } else {
            LetterTileDrawable letterTileDrawable = new LetterTileDrawable(resources);
            letterTileDrawable.setContactDetails(renderable.getName(), Long.toString(renderable.getId()));
            picasso.load(Uri.parse(renderable.getImage()))
                    .placeholder(R.drawable.avatar_placeholder)
                    .transform(transformation)
                    .error(letterTileDrawable)
                    .into(avatar);
        }
    }

    @Override
    public int getId() {
        return 0;
    }
}
