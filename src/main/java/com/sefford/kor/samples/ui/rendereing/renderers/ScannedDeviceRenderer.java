package com.sefford.kor.samples.ui.rendereing.renderers;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.sefford.brender.interfaces.Renderer;
import com.sefford.kor.samples.R;
import com.sefford.kor.samples.model.ScannedDevice;

/**
 * Created by sefford on 4/11/14.
 */
public class ScannedDeviceRenderer implements Renderer<ScannedDevice> {

    @InjectView(R.id.iv_active)
    ImageView ivActive;
    @InjectView(R.id.tv_name)
    TextView tvName;

    public ScannedDeviceRenderer() {
    }

    @Override
    public void mapViews(View view) {
        ButterKnife.inject(this, view);
    }

    @Override
    public void hookUpListeners(View view, final ScannedDevice renderable) {
        // Do nothing
    }

    @Override
    public void render(Context context, ScannedDevice renderable, int position, boolean first, boolean last) {
        ivActive.setImageResource(renderable.isActive() ? R.drawable.scanned_device_activated :
                R.drawable.scanned_device_deactivated);
        tvName.setText(renderable.getIp());
    }

    @Override
    public int getId() {
        return R.layout.item_scanned_device;
    }
}
