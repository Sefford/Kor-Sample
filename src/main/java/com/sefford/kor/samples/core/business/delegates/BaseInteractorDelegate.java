package com.sefford.kor.samples.core.business.delegates;

import com.sefford.kor.repositories.interfaces.Repository;

/**
 * Base Delegate that is used to actually perform the correct process when doing a request
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public abstract class BaseInteractorDelegate<R extends Repository> {

    /**
     * Repository to save to the cache
     */
    protected final R repository;

    /**
     * Creates a new instance of API request
     *
     * @param repository Repository to save the result
     */
    protected BaseInteractorDelegate(R repository) {
        this.repository = repository;
    }
}