package com.sefford.kor.samples.core.business;


import com.sefford.kor.interactors.Interactor;

/**
 * Base compound Interactor Class
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public class CompoundInteractor {

    /**
     * Network execution strategy
     */
    final Interactor networkInteractor;

    /**
     * Cache execution strategy
     */
    final Interactor cacheInteractor;

    /**
     * Creates a new Request
     *
     * @param networkInteractor Network execution strategy
     * @param cacheInteractor   Cache execution strategy
     */
    public CompoundInteractor(Interactor networkInteractor, Interactor cacheInteractor) {
        this.networkInteractor = networkInteractor;
        this.cacheInteractor = cacheInteractor;
    }

    public Interactor getNetworkInteractor() {
        return networkInteractor;
    }

    public Interactor getCacheInteractor() {
        return cacheInteractor;
    }

    /**
     * Checks if the request has a Cache Component
     *
     * @return TRUE if has, FALSE otherwise
     */
    public boolean hasCacheComponent() {
        return cacheInteractor != null;
    }

    /**
     * Checks if the request has a Network Component
     *
     * @return TRUE if has, FALSE otherwise
     */
    public boolean hasNetworkComponent() {
        return networkInteractor != null;
    }
}