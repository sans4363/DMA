package com.tbcmad.todoapp.model;

import android.content.Context;
import android.os.AsyncTask;
import android.service.autofill.UserData;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.tbcmad.todoapp.data.TodoDAO;
import com.tbcmad.todoapp.data.UserDAO;

import java.util.Date;

@Database(entities = {
        ETodo.class, User.class
},
        version = 1, exportSchema = false)
public abstract class TodoRoomDatabase extends RoomDatabase {
    public abstract TodoDAO mTodoDao();
    public abstract UserDAO userDao();

    public static TodoRoomDatabase INSTANCE;
    public static TodoRoomDatabase getDatabase(Context context){
        if(INSTANCE == null) {
            synchronized (TodoRoomDatabase.class){
                if(INSTANCE ==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodoRoomDatabase.class,
                            "todo.db")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .addCallback(sCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sCallback=new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

        }
    };

}
