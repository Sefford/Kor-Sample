package com.sefford.kor.samples.ui.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by sefford on 23/10/14.
 */
@Singleton
public class IntentController {

    final Context context;

    @Inject
    public IntentController(Context context) {
        this.context = context;
    }

    public void startActivity(Class clazz, Bundle extras, int flags) {
        Intent intent = new Intent(context, clazz);
        if (flags != 0) {
            intent.setFlags(flags);
        }
        if (Bundle.EMPTY != extras) {
            intent.putExtras(extras);
        }
        context.startActivity(intent);
    }
}
