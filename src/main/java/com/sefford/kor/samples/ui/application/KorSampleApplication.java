package com.sefford.kor.samples.ui.application;

import android.app.Application;
import com.sefford.kor.samples.core.modules.ApplicationModule;
import dagger.ObjectGraph;

/**
 * Application with injection capabilities
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public class KorSampleApplication extends Application {
    /**
     * Object Injection Graph for Development.
     */
    ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Dagger's Dependency injection object Graph
        objectGraph = initializeGraph();
        objectGraph.inject(this);
    }

    /**
     * Initializes Object graph
     *
     * @return New object graph
     */
    ObjectGraph initializeGraph() {
        return ObjectGraph.create(new ApplicationModule(this));
    }

    /**
     * Injection facility for the elements.
     *
     * @param instance Instance of the object to inject dependencies
     * @param <T>      Class that will be injected
     */
    public <T> void inject(T instance) {
        objectGraph.inject(instance);
    }

    /**
     * Provider facility for the elements.
     *
     * @param type Type of the instance to get
     * @param <T>  Class that will be injected
     */
    public <T> T get(Class<T> type) {
        return objectGraph.get(type);
    }

    public ObjectGraph getGraph() {
        return objectGraph;
    }
}
