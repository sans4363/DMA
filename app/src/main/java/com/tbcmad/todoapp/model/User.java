package com.tbcmad.todoapp.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.tbcmad.todoapp.util.DateConverter;

import java.util.Date;

@Entity(tableName = "user_table")
public class User {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "email")
    private String email;

    @TypeConverters({DateConverter.class})
    @ColumnInfo(name = "createdDate")
    private Date createdDate;

    @Ignore
    public User() {

    }

    public User(@NonNull String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdDate = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
