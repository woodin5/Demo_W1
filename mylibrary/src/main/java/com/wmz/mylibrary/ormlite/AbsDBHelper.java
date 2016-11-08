package com.wmz.mylibrary.ormlite;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;


public abstract class AbsDBHelper extends OrmLiteSqliteOpenHelper {

    public AbsDBHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        onCreating(sqLiteDatabase, connectionSource);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        onUpgrading(sqLiteDatabase, connectionSource, i, i1);
    }

    // TableUtils.createTable   建表
    // TableUtils.dropTable     删表

    public abstract void onCreating(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource);

    public abstract void onUpgrading(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1);

}
