package com.sefford.kor.samples.model.repositories;

import android.support.v4.util.LruCache;
import com.sefford.kor.repositories.LruMemoryRepository;
import com.sefford.kor.samples.model.User;

/**
 * Created by sefford on 13/12/14.
 */
public class UserLRURepository extends LruMemoryRepository<String, User> {

    public static final int MAX_ELEMENTS = 10;

    public UserLRURepository(LruCache<String, User> cache) {
        super(cache);
    }
}
