package com.wmz.mylibrary.ormlite;

import android.content.Context;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmz on 2016/10/28.
 */

public abstract class AbsDao<T, V> { // T表DAO操作的类, V表示操作类的主键类型

    protected Context context;

    public AbsDao(Context context) {
        this.context = context;
    }

    /**
     * 增加一条记录
     */
    public int add(T t) {
        if (getDao() != null) {
            try {
                return getDao().create(t);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    /**
     * 增加List集合
     */
    public void addAll(List<T> list) {
        if (getDao() != null) {
            try {
                getDao().create(list);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除一条记录
     */
    public void delete(T t){
        if (getDao() != null) {
            try {
                getDao().delete(t);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 修改一条记录
     */
    public void update(T t) {
        if (getDao() != null) {
            try {
                getDao().update(t);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 查询所有
     *
     */
    public List<T> queryForAll() {
        if (getDao() != null) {
            try {
                return getDao().queryForAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 根据列名查询
     *
     */
    public List<T> queryForColumnName(String columnName, String value){
        ArrayList<T> list = new ArrayList<>();
        try {
            Where<T,V> where = getDao().queryBuilder().where().eq(columnName,value);
            CloseableIterator<T> it = where.iterator();
            while (it.hasNext()){
                list.add(it.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 删除所有
     */
    public void clear() {
        try {
            if (getDao() != null) {
                getDao().deleteBuilder().delete();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public abstract Dao<T, V> getDao();

}

