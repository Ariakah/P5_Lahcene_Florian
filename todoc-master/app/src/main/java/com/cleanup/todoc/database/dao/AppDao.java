package com.cleanup.todoc.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface AppDao {

    @Insert
    void insert(Project project);

    @Insert
    void insert(Task task);

    @Query("select * from tableProject")
    LiveData<List<Project>> getAllProject();

    @Query("select * from tableTask")
    LiveData<List<Task>> getAllTask();

    @Query("select * from tableTask")
    List<Task> getAllTaskList();

    @Query("select * from tableTask where _projectId =:projectId")
    LiveData<List<Task>> getTaskByProject(long projectId);

    @Query("delete from tableTask where _projectId =:projectId")
    void deleteProjectTasks(long projectId);

    @Query("delete from tableTask where _Id =:taskId")
    void deleteTask(long taskId);

    @Query("delete from tableProject where _id =:projectId")
    void deleteProject(long projectId);
}
