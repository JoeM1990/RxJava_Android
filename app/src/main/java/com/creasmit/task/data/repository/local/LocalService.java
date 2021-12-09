package com.creasmit.task.data.repository.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.creasmit.task.data.model.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {
        Task.class
}, version = 1, exportSchema = false)
public abstract class LocalService extends RoomDatabase {

    private static volatile LocalService localService;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static LocalService newInstance(Context context) {
        synchronized (LocalService.class) {
            if (localService == null) {
                localService = Room.databaseBuilder(context, LocalService.class, "task").build();
            }
        }
        return localService;
    }

    public abstract TaskDao task();
}