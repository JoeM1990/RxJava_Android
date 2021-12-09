package com.creasmit.task.data.repository.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.creasmit.task.data.model.Task;

import java.util.List;


@Dao
public interface TaskDao {

    @Query("SELECT * FROM Task")
    List<Task> getList();

    @Insert
    void insertAll(List<Task> task);

    @Query("DELETE FROM Task")
    void deleteAll();
}
