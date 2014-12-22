package com.sefford.kor.samples.ui.activities;

import android.os.Bundle;
import com.sefford.kor.samples.R;
import com.sefford.kor.samples.ui.controllers.interfaces.DevicesActivityControllerInterface;

import javax.inject.Inject;

/**
 * Created by sefford on 16/11/14.
 */
public class DevicesActivity extends BaseActivity {

    @Inject
    DevicesActivityControllerInterface controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_list);
        controller.bind(getWindow().findViewById(android.R.id.content));
        controller.startListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        controller.performRequest();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        controller.stopListening();
    }
}
