package com.sefford.kor.samples.ui.rendereing.renderers;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.sefford.brender.interfaces.Renderer;
import com.sefford.kor.samples.R;
import com.sefford.kor.samples.model.User;
import com.squareup.picasso.Picasso;

/**
 * Created by sefford on 16/11/14.
 */
public class UserRenderer implements Renderer<User> {

    final Picasso picasso;
    final Resources resources;
    final Display display;

    @InjectView(R.id.iv_avatar)
    ImageView avatar;
    @InjectView(R.id.tv_name)
    TextView name;

    public UserRenderer(Picasso picasso, Resources resources, Display display) {
        this.picasso = picasso;
        this.resources = resources;
        this.display = display;
    }

    @Override
    public void mapViews(View view) {
        ButterKnife.inject(this, view);
    }

    @Override
    public void hookUpListeners(View view, User renderable) {
        // Do nothing
    }

    @Override
    public void render(Context context, User renderable, int position, boolean first, boolean last) {
        name.setText(renderable.getUsername());
        final Point point = new Point();
        display.getSize(point);
        picasso.load(renderable.getPicture().getLargeUrl())
                .resize(point.x, resources.getDimensionPixelSize(R.dimen.user_picture_height))
                .centerCrop()
                .into(avatar);
    }

    @Override
    public int getId() {
        return R.layout.item_user;
    }
}
