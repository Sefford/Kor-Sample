package com.sefford.kor.samples.ui.rendereing.renderers;

import android.content.Context;
import android.view.View;
import com.sefford.brender.interfaces.Renderer;

/**
 * Null Object pattern for Renderering
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public class NullRenderer implements Renderer<Object> {
    @Override
    public void mapViews(View view) {
        // Empty
    }

    @Override
    public void hookUpListeners(View view, Object renderable) {
        // Empty
    }

    @Override
    public void render(Context context, Object renderable, int position, boolean first, boolean last) {
        // Empty
    }

    @Override
    public int getId() {
        return 0;
    }
}
