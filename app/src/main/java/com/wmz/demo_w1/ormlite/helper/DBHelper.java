package com.wmz.demo_w1.ormlite.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.wmz.demo_w1.ormlite.bean.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/28.
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {
    private static final String DB_NAME = "ormlite_test.db";
    private Map<String ,Dao> daos = new HashMap<String, Dao>();
    private static DBHelper helper;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public static DBHelper getHelper(Context context){
        if(helper==null){
            synchronized (DBHelper.class){
                if(helper==null){
                    helper = new DBHelper(context);
                }
            }
        }
        return helper;
    }

    public synchronized Dao getDao(Class clazz){
        Dao dao = null;
        String className = clazz.getName();
        if(daos.containsKey(className)){
           dao =  daos.get(className);
        }
        if(dao==null){
            try {
                dao = super.getDao(clazz);
                daos.put(className,dao);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dao;
    }

    @Override
    public void close() {
        super.close();
        for(String key:daos.keySet()){
            Dao dao = daos.get(key);
            dao = null;
        }
    }
}
