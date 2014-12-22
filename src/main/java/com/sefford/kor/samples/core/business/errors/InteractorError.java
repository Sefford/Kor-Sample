package com.sefford.kor.samples.core.business.errors;

import com.sefford.kor.errors.ErrorInterface;

/**
 * Created by sefford on 15/10/14.
 */
public class InteractorError implements ErrorInterface {

    int status;
    String userMessage;
    String internalMessage;

    @Override
    public int getStatusCode() {
        return status;
    }

    @Override
    public String getUserError() {
        return userMessage;
    }

    @Override
    public String getMessage() {
        return internalMessage;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public void setInternalMessage(String internalMessage) {
        this.internalMessage = internalMessage;
    }
}
