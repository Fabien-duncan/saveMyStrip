package com.openclassrooms.savemytrip.repository;

import androidx.lifecycle.LiveData;

import com.openclassrooms.savemytrip.database.dao.UserDao;
import com.openclassrooms.savemytrip.models.User;

public class UserDataRepository {

    private final UserDao userDao;

    public UserDataRepository(UserDao userDao) { this.userDao = userDao; }

    // --- GET USER ---

    public LiveData<User> getUser(long userId) { return this.userDao.getUser(userId); }
}
