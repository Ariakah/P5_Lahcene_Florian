package com.cleanup.todoc.database.dao;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.TaskRepository;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private TaskRepository mTaskRepository;

    private final LiveData<List<Task>> mData;

    public ViewModel(@NonNull Application application) {
        super(application);
        mTaskRepository = new TaskRepository(application);
        mData = mTaskRepository.getAllTask();
    }

    public LiveData<List<Task>> getData() {return mData;}

    public void insert(Task task) {mTaskRepository.insertTask(task);}

    public void delete(Task task){mTaskRepository.deleteTask(task);}
}
