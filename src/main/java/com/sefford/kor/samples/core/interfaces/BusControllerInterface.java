package com.sefford.kor.samples.core.interfaces;

/**
 * Interface for Bus Contollers
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public interface BusControllerInterface extends com.sefford.kor.common.interfaces.Postable,
        com.sefford.brender.interfaces.Postable {

    /**
     * Registers the target to the bus to start listening events
     *
     * @param target Target to register
     */
    void register(Object target);

    /**
     * Registers stickly to the bus to start listening events.
     * <p/>
     * A new sticky message will be pushed inmediately to the target if available
     *
     * @param target Target to register
     */
    void registerSticky(Object target);

    /**
     * Desregisters the target from the bus.
     *
     * @param target Target to unregister
     */
    void unregister(Object target);

    /**
     * Posts sticky messages to the bus. Those will be pushed inmediately to the targets if registered
     * stickly.
     *
     * @param message Message to post
     */
    void postSticky(Object message);

}
