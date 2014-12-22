package com.sefford.kor.samples.core.business.responses;

import com.sefford.kor.responses.ResponseInterface;

/**
 * Base Interactor response.
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public class InteractorResponse implements ResponseInterface {

    boolean success;
    boolean fromNetwork;

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public boolean isFromNetwork() {
        return fromNetwork;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setFromNetwork(boolean fromNetwork) {
        this.fromNetwork = fromNetwork;
    }
}
