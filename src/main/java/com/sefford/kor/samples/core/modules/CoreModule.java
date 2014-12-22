package com.sefford.kor.samples.core.modules;

import android.support.v4.util.LruCache;
import com.google.gson.GsonBuilder;
import com.sefford.kor.samples.BuildConfig;
import com.sefford.kor.samples.core.controllers.BusController;
import com.sefford.kor.samples.core.executors.DataExecutor;
import com.sefford.kor.samples.core.interfaces.BusControllerInterface;
import com.sefford.kor.samples.core.networking.ApiErrorHandler;
import com.sefford.kor.samples.core.networking.RandomUserApi;
import com.sefford.kor.samples.core.networking.UserConverter;
import com.sefford.kor.samples.model.User;
import com.sefford.kor.samples.model.repositories.UserLRURepository;
import com.sefford.kor.samples.model.repositories.UserMemoryRepository;
import com.sefford.kor.samples.model.repositories.UserRepository;
import com.sefford.kor.samples.utils.SLog;
import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Dependency injection Module for Core elements
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
@Module(library = true)
public class CoreModule {

    @Provides
    public EventBus provideEventBus() {
        return new EventBus();
    }

    @Provides
    public BusControllerInterface provideBus(BusController controller) {
        return controller;
    }

    @Provides
    public GsonBuilder provideGsonBuilder() {
        return new GsonBuilder();
    }

    @Provides
    public DataExecutor provideDataExecutor() {
        return new DataExecutor(new ThreadPoolExecutor(3, 4, 120, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>()),
                new ThreadPoolExecutor(4, 6, 120, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>()));
    }

    @Provides
    @Singleton
    public UserMemoryRepository provideUserMemoryRepository() {
        return new UserMemoryRepository(new HashMap<String, User>());
    }

    @Provides
    @Singleton
    public UserLRURepository provideUserLRURepository() {
        return new UserLRURepository(new LruCache<String, User>(UserLRURepository.MAX_ELEMENTS));
    }

    @Provides
    @Singleton
    public UserRepository provideUserRepository(UserLRURepository lru, UserMemoryRepository memory) {
        return new UserRepository(lru, memory);// Memory simulates Disk persistence
    }

    @Provides
    @Singleton
    public RandomUserApi provideRandomUserApi(SLog log, ApiErrorHandler errorHandler, UserConverter converter) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.randomuser.me/")
                .setLog(log)
                .setLogLevel(!BuildConfig.DEBUG ? RestAdapter.LogLevel.NONE : RestAdapter.LogLevel.FULL)
                .setClient(new OkClient())
                .setConverter(converter)
                .setExecutors(Executors.newCachedThreadPool(), Executors.newCachedThreadPool())
                .setErrorHandler(errorHandler)
                .build();
        return restAdapter.create(RandomUserApi.class);
    }
}
