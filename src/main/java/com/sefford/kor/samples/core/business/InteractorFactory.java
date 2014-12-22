package com.sefford.kor.samples.core.business;

import android.content.Context;
import com.sefford.kor.interactors.CacheInteractor;
import com.sefford.kor.interactors.NetworkInteractor;
import com.sefford.kor.interactors.StandardNetworkInteractor;
import com.sefford.kor.samples.core.business.delegates.DeviceScanDelegate;
import com.sefford.kor.samples.core.business.delegates.GetPhoneContactsDelegate;
import com.sefford.kor.samples.core.business.delegates.GetUsersDelegate;
import com.sefford.kor.samples.core.interfaces.BusControllerInterface;
import com.sefford.kor.samples.core.networking.RandomUserApi;
import com.sefford.kor.samples.model.repositories.UserRepository;
import com.sefford.kor.samples.utils.NetworkUtils;
import com.sefford.kor.samples.utils.SLog;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Interactor Factory for injecting Interactors
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
@Singleton
public class InteractorFactory {

    final BusControllerInterface bus;
    final SLog log;
    final NetworkUtils networkUtils;
    final Context context;
    final RandomUserApi api;
    final UserRepository repo;

    @Inject
    public InteractorFactory(BusControllerInterface bus, SLog log, NetworkUtils networkUtils, Context context,
                             RandomUserApi api, UserRepository repo) {
        this.bus = bus;
        this.log = log;
        this.networkUtils = networkUtils;
        this.context = context;
        this.api = api;
        this.repo = repo;
    }

    /**
     * Generates an interactor
     *
     * @param code    Interactor unique code
     * @param options Expansion element for the interactors
     * @return Interactor
     */
    public CompoundInteractor generateInteractor(InteractorCodes code, Options options) {
        NetworkInteractor networkRequestStrategy = null;
        CacheInteractor cacheExecutionStrategy = null;
        switch (code) {
            case GET_DEVICES:
                networkRequestStrategy = new StandardNetworkInteractor(bus, log, new DeviceScanDelegate(networkUtils));
                break;
            case GET_CONTACTS:
                cacheExecutionStrategy = new CacheInteractor(bus, log, new GetPhoneContactsDelegate(context));
                break;
            case GET_USERS:
                networkRequestStrategy = new StandardNetworkInteractor(bus, log, new GetUsersDelegate(api, repo, options));
                break;
        }
        return new CompoundInteractor(networkRequestStrategy, cacheExecutionStrategy);
    }
}
