package com.tbcmad.todoapp.activity.auth;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.tbcmad.todoapp.data.UserDAO;
import com.tbcmad.todoapp.model.TodoRoomDatabase;
import com.tbcmad.todoapp.model.User;

public class AuthViewModel extends AndroidViewModel {

    private UserDAO mUserDao;
    private Context mContext;

    public AuthViewModel(@NonNull Application application){
        super(application);
        mContext = application.getApplicationContext();
        TodoRoomDatabase database = TodoRoomDatabase.getDatabase(application);
        mUserDao = database.userDao();
    }

    public long registerUser(String username, String password, String email) {
        User user = new User(username, password, email);
        long id = mUserDao.insert(user);
        return id;
    }

    public User validateUser(String username, String password) {
        User user = mUserDao.getUserByUsernameAndPassword(username, password);
        return user;
    }

    public User loginUser(String username, String password) {
        User user = mUserDao.getUserByUsernameAndPassword(username, password);
        return user;
    }

    public void storeUidInSharedPrefs(User user) {
        SharedPreferences preference = mContext.getSharedPreferences("user_pref",  0);
        SharedPreferences.Editor editor = preference.edit();
        editor.putLong("user_id", user.getId());
        editor.apply();
    }

    public static void logout(Context context) {
        SharedPreferences preference = context.getSharedPreferences("user_pref",  0);
        SharedPreferences.Editor editor = preference.edit();
        editor.putLong("user_id", -1);
        editor.apply();
    }

    public void storeUidInSharedPrefs(long id) {
        SharedPreferences preference = mContext.getSharedPreferences("user_pref",  0);
        SharedPreferences.Editor editor = preference.edit();
        editor.putLong("user_id", id);
        editor.apply();
    }

    public static long getUserId(Context context) {
        SharedPreferences preference = context.getSharedPreferences("user_pref",  0);
        long id = preference.getLong("user_id", -1);
        return id;
    }
}
