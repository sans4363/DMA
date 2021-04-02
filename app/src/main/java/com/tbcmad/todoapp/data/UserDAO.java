package com.tbcmad.todoapp.data;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.tbcmad.todoapp.model.User;

@Dao
public interface UserDAO {
    @Insert
    long insert(User user);

    @Query("DELETE FROM user_table")
    void deleteAll();

    @Delete
    void deleteById(User user);

    @Query("SELECT * FROM user_table WHERE id=:id")
    User getUserById(int id);

    @Query("SELECT * FROM user_table WHERE username=:username AND password=:password")
    User getUserByUsernameAndPassword(String username, String password);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(User... users);
}
