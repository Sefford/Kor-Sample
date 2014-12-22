package com.sefford.kor.samples.core.executors;

import com.sefford.kor.interactors.interfaces.CacheDelegate;
import com.sefford.kor.providers.interfaces.Executor;
import com.sefford.kor.samples.core.business.CompoundInteractor;

import java.util.List;

/**
 * Data provider that manages API Requests orders
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public class DataExecutor implements Executor<CompoundInteractor> {

    /**
     * Data Provider Manager ID
     */
    public static final String NETWORK_PROVIDER_ID = "DataProvider";
    /**
     * Data Provider Manager ID
     */
    public static final String CACHE_PROVIDER_ID = "CacheProvider";
    /**
     * Queue of Network Requests
     */
    final java.util.concurrent.Executor networkQueue;
    /**
     * Queue of Cache Requests
     */
    final java.util.concurrent.Executor cacheQueue;

    /**
     * Creates a new instance of of DataManager
     *
     * @param networkQueue JobManager to handle different queries
     * @param cacheQueue   Jobmanager to handle Cache Queues
     */
    public DataExecutor(java.util.concurrent.Executor networkQueue, java.util.concurrent.Executor cacheQueue) {
        this.networkQueue = networkQueue;
        this.cacheQueue = cacheQueue;
    }


    @Override
    public void executeOperation(CompoundInteractor interactor) {
        if (interactor.hasNetworkComponent()) {
            networkQueue.execute(interactor.getNetworkInteractor());
        }
        if (hasToRequestToCache(interactor)) {
            cacheQueue.execute(interactor.getCacheInteractor());
        }
    }

    boolean hasToRequestToCache(CompoundInteractor request) {
        return request.hasCacheComponent() && ((CacheDelegate) request.getCacheInteractor().getDelegate()).isCacheValid();
    }

    @Override
    public void executeOperations(List<CompoundInteractor> interactors) {
        for (final CompoundInteractor interactor : interactors) {
            executeOperation(interactor);
        }
    }

    /**
     * Clears the queue
     */
    public void clear() {
        // Nothing for now
    }
}