package com.tbcmad.todoapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.tbcmad.todoapp.util.DateConverter;

import java.util.Date;

@Entity(tableName = "todo_table")
public class ETodo {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "userId")
    private long userId;

    @TypeConverters({DateConverter.class})
    @ColumnInfo(name = "todo_date")
    private Date todoDate;

    @ColumnInfo(name = "priority")
    private int priority;

    @ColumnInfo(name = "is_completed")
    private boolean isCompleted;
    @Ignore
    public ETodo() {
    }

    public ETodo(@NonNull String title, String description, Date todoDate, int priority, boolean isCompleted, long userId) {
        this.title = title;
        this.description = description;
        this.todoDate = todoDate;
        this.priority = priority;
        this.isCompleted = isCompleted;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTodoDate() {
        return todoDate;
    }

    public void setTodoDate(Date todoDate) {
        this.todoDate = todoDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
