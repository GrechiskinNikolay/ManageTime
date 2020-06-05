package com.example.managetime.Model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.example.managetime.Model.dto.Task;

import java.sql.Date;
import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task")
    List<Task> getAllTasks();

    @Query("SELECT * FROM task")
    LiveData<List<Task>> getAllTasksLiveData();

    /*@Query("SELECT * FROM task WHERE date(startTime) = date(:date)")
    List<Task>  getTasksByStartTime(Date date);*/

    @Query("SELECT * FROM task WHERE cast(date(startTime) as integer) = :date")
    List<Task>  getTasksByStartTime(@TypeConverters({DateConverter.class}) Date date);

    @Query("SELECT * FROM task WHERE id = :id LIMIT 1")
    Task getTaskById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTask(Task task);

    @Update
    public void updateTask(Task task);

    @Delete
    public void deleteTask(Task task);
}