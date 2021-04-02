package com.tbcmad.todoapp.data;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.tbcmad.todoapp.model.ETodo;

import java.util.List;


@Dao
public interface  TodoDAO {
    @Insert
    void insert(ETodo todo);

    @Query("DELETE FROM todo_table WHERE userId=:userId")
    void deleteAll(long userId);

    @Query("DELETE FROM todo_table WHERE userId=:userId AND is_completed=1")
    void deleteAllCompleted(long userId);

    @Delete
    void deleteById(ETodo todo);

    @Query("SELECT * FROM todo_table WHERE id=:id")
    ETodo getTodoById(int id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(ETodo... todo);

    @Query("SELECT * FROM todo_table ORDER BY todo_date, priority desc")
    List<ETodo> getAllTodos();

    @Query("SELECT * FROM todo_table WHERE userId=:userId ORDER BY todo_date, priority desc")
    List<ETodo> getUsersTodos(long userId);


}
