package com.cleanup.todoc.database.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

@Database(entities = {Project.class, Task.class}, version = 1, exportSchema = false)
public abstract class AppRoomDatabase extends RoomDatabase {

    public abstract AppDao mAppDao();

    private static AppRoomDatabase instance;

    public static AppRoomDatabase getDatabase(Context context){

        if (instance != null)
            return instance;
        synchronized (AppRoomDatabase.class){
        instance = Room.databaseBuilder(context, AppRoomDatabase.class, "AppDB").build();}
        return instance;
    }
}
