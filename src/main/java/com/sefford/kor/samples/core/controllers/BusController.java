package com.sefford.kor.samples.core.controllers;


import com.sefford.kor.samples.core.interfaces.BusControllerInterface;
import de.greenrobot.event.EventBus;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Bus Controller to handle IPC on the application
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
@Singleton
public class BusController implements BusControllerInterface {

    final EventBus bus;

    @Inject
    public BusController(EventBus bus) {
        this.bus = bus;
    }

    @Override
    public void register(Object target) {
        if (!bus.isRegistered(target)) {
            bus.register(target);
        }
    }

    @Override
    public void registerSticky(Object target) {
        if (!bus.isRegistered(target)) {
            bus.registerSticky(target);
        }
    }

    @Override
    public void unregister(Object target) {
        if (bus.isRegistered(target)) {
            bus.unregister(target);
        }
    }

    @Override
    public void postSticky(Object message) {
        bus.postSticky(message);
    }

    @Override
    public void post(Object message) {
        bus.post(message);
    }
}
