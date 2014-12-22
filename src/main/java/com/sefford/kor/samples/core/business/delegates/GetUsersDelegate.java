package com.sefford.kor.samples.core.business.delegates;

import com.sefford.kor.interactors.interfaces.CacheDelegate;
import com.sefford.kor.interactors.interfaces.NetworkDelegate;
import com.sefford.kor.samples.core.business.Options;
import com.sefford.kor.samples.core.business.errors.GetUserError;
import com.sefford.kor.samples.core.business.responses.GetUserResponse;
import com.sefford.kor.samples.core.networking.RandomUserApi;
import com.sefford.kor.samples.model.User;
import com.sefford.kor.samples.model.repositories.UserRepository;

import java.util.ArrayList;

/**
 * Created by sefford on 16/11/14.
 */
public class GetUsersDelegate implements NetworkDelegate<GetUserResponse, GetUserError>, CacheDelegate<GetUserResponse, GetUserError> {

    public static final int NUM_USERS = 40;
    final RandomUserApi api;
    final UserRepository repo;
    final int numUsers;

    public GetUsersDelegate(RandomUserApi api, UserRepository repo, Options options) {
        this.api = api;
        this.repo = repo;
        this.numUsers = options.getParams().length == 0 ? NUM_USERS : Integer.valueOf(options.getParams()[0]);
    }

    @Override
    public GetUserResponse retrieveFromCache() {
        GetUserResponse response = new GetUserResponse();
        response.setUserList(new ArrayList<User>(repo.getAll()));
        response.setFromNetwork(Boolean.FALSE);
        response.setSuccess(!response.getUserList().isEmpty());
        return response;
    }

    @Override
    public boolean isCacheValid() {
        return true;
    }

    @Override
    public GetUserResponse retrieveNetworkResponse() throws Exception {
        return api.getUsers(NUM_USERS);
    }

    @Override
    public GetUserResponse postProcess(GetUserResponse response) {
        response.setSuccess(!response.getUserList().isEmpty());
        response.setFromNetwork(Boolean.TRUE);
        return response;
    }

    @Override
    public void saveToCache(GetUserResponse response) {
        repo.saveAll(response.getUserList());
    }

    @Override
    public GetUserError composeErrorResponse(Exception e) {
        return new GetUserError();
    }

    @Override
    public String getInteractorName() {
        return "GetUsersDelegate";
    }
}
