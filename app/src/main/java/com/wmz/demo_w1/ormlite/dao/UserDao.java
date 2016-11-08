package com.wmz.demo_w1.ormlite.dao;

import android.content.Context;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.Where;
import com.wmz.demo_w1.ormlite.helper.DBHelper;
import com.wmz.demo_w1.ormlite.bean.User;
import com.wmz.mylibrary.ormlite.AbsDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmz on 2016/10/28.
 */

public class UserDao extends AbsDao<User,Integer>{

    public UserDao(Context context) {
        super(context);
    }

    @Override
    public Dao<User, Integer> getDao() {
        return DBHelper.getHelper(context).getDao(User.class);
    }

}
