package com.sefford.kor.samples.ui.activities;

import android.os.Bundle;
import com.sefford.kor.samples.R;
import com.sefford.kor.samples.ui.controllers.interfaces.MainActivityControllerInterface;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    MainActivityControllerInterface controller;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_main);
        controller.bind(getWindow().findViewById(android.R.id.content));
    }
}

