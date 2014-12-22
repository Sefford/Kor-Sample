package com.sefford.kor.samples.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import com.sefford.kor.samples.core.business.InteractorCodes;
import com.sefford.kor.samples.core.business.InteractorFactory;
import com.sefford.kor.samples.core.business.Options;
import com.sefford.kor.samples.core.events.NullEvent;
import com.sefford.kor.samples.core.executors.DataExecutor;
import com.sefford.kor.samples.ui.application.KorSampleApplication;

import javax.inject.Inject;

/**
 * Base facilities for Activities
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public class BaseActivity extends Activity {

    @Inject
    DataExecutor executor;
    @Inject
    InteractorFactory factory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((KorSampleApplication) getApplication()).inject(this);
    }

    /**
     * Execute an interactor into the Provider
     *
     * @param code    Code to identify the interactor to execute
     * @param options Options to configure the interactor
     */
    protected void executeInteractor(InteractorCodes code, Options options) {
        executor.executeOperation(factory.generateInteractor(code, options));
    }

    public void onEvent(NullEvent event) {
        // Equivalent to /dev/null
    }
}
