package com.sefford.kor.samples.model.repositories;

import com.sefford.kor.repositories.BaseRepository;
import com.sefford.kor.repositories.interfaces.Repository;
import com.sefford.kor.samples.model.User;

/**
 * Created by sefford on 16/11/14.
 */
public class UserRepository extends BaseRepository<String, User> {

    public UserRepository(Repository<String, User> currentLevel, Repository<String, User> nextLevel) {
        super(currentLevel, nextLevel);
    }
}
