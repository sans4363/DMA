package com.tbcmad.todoapp.data;

import android.app.Application;
import android.os.AsyncTask;
import com.tbcmad.todoapp.model.ETodo;
import com.tbcmad.todoapp.model.TodoRoomDatabase;
import java.util.List;

public class TodoRepository {
    public TodoDAO mTodoDAO;
    public UserDAO mUserDao;

    public TodoRepository(Application application){
        TodoRoomDatabase database = TodoRoomDatabase.getDatabase(application);
        mTodoDAO = database.mTodoDao();
        mUserDao = database.userDao();
    }

    public TodoDAO getmTodoDAO() {
        return mTodoDAO;
    }

    public void setmTodoDAO(TodoDAO mTodoDAO) {
        this.mTodoDAO = mTodoDAO;
    }

    public List<ETodo> getAllTodoList() {
        return mTodoDAO.getAllTodos();
    }

    public void insert(ETodo eTodo){
        new insertTodoAysncTask().execute(mTodoDAO, eTodo);
    }

    public void delete(ETodo eTodo){
        new deleteTodoAysncTask().execute(mTodoDAO, eTodo);
    }

    public void deleteAll(long userId){
        new DeleteAllTodoAysncTask().execute(mTodoDAO, userId);
    }

    public void deleteAllCompleted(long userId){
        new DeleteAllCompletedTodoAysncTask().execute(mTodoDAO, userId);
    }

    public ETodo getTodoById(int id){
        return  mTodoDAO.getTodoById(id);
    }

    public void update(ETodo eTodo){
        new updateTodoAysncTask().execute(mTodoDAO, eTodo);
    }

    private static class insertTodoAysncTask extends AsyncTask<Object, Void, Void>{

        public insertTodoAysncTask() {
            super();
        }

        @Override
        protected Void doInBackground(Object... params) {
            TodoDAO todoDao = (TodoDAO) params[0];
            ETodo todo = (ETodo) params[1];
            todoDao.insert(todo);
            return null;
        }
    }

    private static class deleteTodoAysncTask extends AsyncTask<Object, Void, Void>{

        public deleteTodoAysncTask() {
            super();
        }

        @Override
        protected Void doInBackground(Object... params) {
            TodoDAO todoDao = (TodoDAO) params[0];
            ETodo todo = (ETodo) params[1];
            todoDao.deleteById(todo);
            return null;
        }
    }

    private static class DeleteAllTodoAysncTask extends AsyncTask<Object, Void, Void>{

        public DeleteAllTodoAysncTask() {
            super();
        }

        @Override
        protected Void doInBackground(Object... params) {
            TodoDAO todoDao = (TodoDAO) params[0];
            long userId = (long) params[1];
            todoDao.deleteAll(userId);
            return null;
        }
    }

    private static class DeleteAllCompletedTodoAysncTask extends AsyncTask<Object, Void, Void>{

        public DeleteAllCompletedTodoAysncTask() {
            super();
        }

        @Override
        protected Void doInBackground(Object... params) {
            TodoDAO todoDao = (TodoDAO) params[0];
            long userId = (long) params[1];
            todoDao.deleteAllCompleted(userId);
            return null;
        }
    }


    private static class updateTodoAysncTask extends AsyncTask<Object, Void, Void>{

        public updateTodoAysncTask() {
            super();
        }

        @Override
        protected Void doInBackground(Object... params) {
            TodoDAO todoDao = (TodoDAO) params[0];
            ETodo todo = (ETodo) params[1];
            todoDao.update(todo);
            return null;
        }
    }
}
