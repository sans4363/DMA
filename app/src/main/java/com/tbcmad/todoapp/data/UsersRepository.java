package com.tbcmad.todoapp.data;

import android.app.Application;
import android.os.AsyncTask;

import com.tbcmad.todoapp.model.TodoRoomDatabase;
import com.tbcmad.todoapp.model.User;

public class UsersRepository {
    private UserDAO mUsersDAO;

    public UsersRepository(Application application){
        TodoRoomDatabase database = TodoRoomDatabase.getDatabase(application);
        mUsersDAO = database.userDao();
    }

    public User getUser(String username, String password) {
        return mUsersDAO.getUserByUsernameAndPassword(username, password);
    }

    public void setUsersDAO(UserDAO userDAO) {
        this.mUsersDAO = userDAO;
    }

    public void insert(User user){
        new InsertUserAsyncTask().execute(mUsersDAO, user);
    }

    public void delete(User user){
        new DeleteUserAsyncTask().execute(mUsersDAO, user);
    }

    public void update(User user){
        new updateUserAsyncTask().execute(mUsersDAO, user);
    }

    private static class InsertUserAsyncTask extends AsyncTask<Object, Void, Void> {

        public InsertUserAsyncTask() {
            super();
        }

        @Override
        protected Void doInBackground(Object... params) {
            UserDAO usersDAO = (UserDAO) params[0];
            User user = (User) params[1];
            usersDAO.insert(user);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<Object, Void, Void>{

        public DeleteUserAsyncTask() {
            super();
        }

        @Override
        protected Void doInBackground(Object... params) {
            UserDAO usersDAO = (UserDAO) params[0];
            User user = (User) params[1];
            usersDAO.deleteById(user);
            return null;
        }
    }

    private static class updateUserAsyncTask extends AsyncTask<Object, Void, Void>{

        public updateUserAsyncTask() {
            super();
        }

        @Override
        protected Void doInBackground(Object... params) {
            UserDAO usersDAO = (UserDAO) params[0];
            User user = (User) params[1];
            usersDAO.update(user);
            return null;
        }
    }
}

