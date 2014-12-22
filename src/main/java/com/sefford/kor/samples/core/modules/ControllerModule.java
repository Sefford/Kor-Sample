package com.sefford.kor.samples.core.modules;

import com.sefford.kor.samples.ui.controllers.ContactsActivityController;
import com.sefford.kor.samples.ui.controllers.DevicesActivityController;
import com.sefford.kor.samples.ui.controllers.MainActivityController;
import com.sefford.kor.samples.ui.controllers.NetworkActivityController;
import com.sefford.kor.samples.ui.controllers.interfaces.ContactsActivityControllerInterface;
import com.sefford.kor.samples.ui.controllers.interfaces.DevicesActivityControllerInterface;
import com.sefford.kor.samples.ui.controllers.interfaces.MainActivityControllerInterface;
import com.sefford.kor.samples.ui.controllers.interfaces.NetworkActivityControllerInterface;
import dagger.Module;
import dagger.Provides;

/**
 * Dependency injection Module for UI controllers
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
@Module(complete = false,
        library = true)
public class ControllerModule {

    @Provides
    public MainActivityControllerInterface provideMainActivityController(MainActivityController controller) {
        return controller;
    }

    @Provides
    public DevicesActivityControllerInterface provideDevicesActivityController(DevicesActivityController controller) {
        return controller;
    }

    @Provides
    public ContactsActivityControllerInterface provideContactsActivityController(ContactsActivityController controller) {
        return controller;
    }

    @Provides
    public NetworkActivityControllerInterface provideNetworkActivityController(NetworkActivityController controller) {
        return controller;
    }
}
