package com.sefford.kor.samples.ui.controllers.interfaces;

import android.view.View;

/**
 * Created by sefford on 16/11/14.
 */
public interface DevicesActivityControllerInterface {

    void bind(View view);

    void performRequest();

    void startListening();

    void stopListening();
}
