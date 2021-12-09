package com.creasmit.task.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.creasmit.task.data.model.Task;
import com.creasmit.task.data.repository.TaskRepository;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository taskRepository;
    private MutableLiveData<List<Task>> listTaskLiveData;
    private MutableLiveData<String> error;
    private MutableLiveData<String> success;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        this.taskRepository = new TaskRepository(application);
        this.listTaskLiveData = new MutableLiveData<>();
        this.error = new MediatorLiveData<>();
        this.success = new MediatorLiveData<>();
    }

    public MutableLiveData<List<Task>> getAll() {
        this.taskRepository.getDatas().subscribe(listTask -> {
            this.listTaskLiveData.postValue(listTask);
        });
        return this.listTaskLiveData;
    }

    public void add(Task task) {
        this.taskRepository.add(task).subscribe(taskHttpResponse -> {
            if (taskHttpResponse.getCode().equals("200"))
                this.success.postValue(taskHttpResponse.getMessage());
            else
                this.error.postValue(taskHttpResponse.getMessage());
        });
    }

    /*
    public void delete(long taskId) {
        this.taskRepository.delete(taskId).subscribe(taskHttpResponse -> {
            if (taskHttpResponse.getCode().equals("200"))
                this.success.postValue(taskHttpResponse.getMessage());
            else
                this.error.postValue(taskHttpResponse.getMessage());
        });
    }*/

    public MutableLiveData<String> getError() {
        return error;
    }

    public MutableLiveData<String> getSuccess() {
        return success;
    }
}
