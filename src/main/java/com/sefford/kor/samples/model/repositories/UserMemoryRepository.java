package com.sefford.kor.samples.model.repositories;

import com.sefford.kor.repositories.MemoryRepository;
import com.sefford.kor.samples.model.User;

import java.util.Map;

/**
 * Created by sefford on 16/11/14.
 */
public class UserMemoryRepository extends MemoryRepository<String, User> {

    public UserMemoryRepository(Map<String, User> cache) {
        super(cache);
    }
}
