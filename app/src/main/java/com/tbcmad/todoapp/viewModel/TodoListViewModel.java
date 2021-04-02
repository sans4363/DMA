package com.tbcmad.todoapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tbcmad.todoapp.data.TodoRepository;
import com.tbcmad.todoapp.model.ETodo;

import java.util.List;

public class TodoListViewModel extends AndroidViewModel {

    private TodoRepository mTodoRepository;

    public TodoListViewModel(@NonNull Application application){
        super(application);
        mTodoRepository=new TodoRepository(application);
    }

    public void deleteAllTodos(long userId) {
        mTodoRepository.deleteAll(userId);
    }

    public void deleteAllCompletedTodos(long userId) {
        mTodoRepository.deleteAllCompleted(userId);
    }

    public List<ETodo> getAllUsersTodos() {
        return mTodoRepository.getAllTodoList();
    }

    public List<ETodo> getUsersTodos(long id) {
        return mTodoRepository.mTodoDAO.getUsersTodos(id);
    }

   public void insert(ETodo todo) {
        mTodoRepository.insert(todo);
    }

    public  void deleteById(ETodo todo){
        mTodoRepository.delete(todo);
    }

    public ETodo getTodoById(int id) {
        return mTodoRepository.getTodoById(id);
    }

    public void update(ETodo eTodo){
        mTodoRepository.update(eTodo);
    }
}
