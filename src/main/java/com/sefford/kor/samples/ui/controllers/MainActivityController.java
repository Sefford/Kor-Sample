package com.sefford.kor.samples.ui.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.sefford.kor.samples.R;
import com.sefford.kor.samples.ui.activities.ContactsActivity;
import com.sefford.kor.samples.ui.activities.DevicesActivity;
import com.sefford.kor.samples.ui.activities.NetworkActivity;
import com.sefford.kor.samples.ui.controllers.interfaces.MainActivityControllerInterface;

import javax.inject.Inject;

/**
 * Created by sefford on 16/11/14.
 */
public class MainActivityController implements MainActivityControllerInterface {

    final IntentController intentController;

    @InjectView(R.id.bt_contacts)
    Button btContacts;
    @InjectView(R.id.bt_ips)
    Button btIps;
    @InjectView(R.id.bt_network)
    Button btNetwork;
    @InjectView(R.id.bt_cache)
    Button btCache;

    @Inject
    public MainActivityController(IntentController intentController) {
        this.intentController = intentController;
    }

    @Override
    public void bind(View view) {
        ButterKnife.inject(this, view);
    }

    @OnClick(R.id.bt_contacts)
    public void onContactsClicked(View view) {
        intentController.startActivity(ContactsActivity.class, Bundle.EMPTY, Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    @OnClick(R.id.bt_ips)
    public void onIpsClicked(View view) {
        intentController.startActivity(DevicesActivity.class, Bundle.EMPTY, Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    @OnClick(R.id.bt_network)
    public void onNetworkClicked(View view) {
        intentController.startActivity(NetworkActivity.class, Bundle.EMPTY, Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    @OnClick(R.id.bt_cache)
    public void onCacheClicked(View view) {

    }
}
