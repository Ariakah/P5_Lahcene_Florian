package com.cleanup.todoc.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.AppDao;
import com.cleanup.todoc.database.dao.AppRoomDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskRepository {

    private AppDao mAppDao;

    public TaskRepository(Application application){
        AppRoomDatabase appRoomDatabase = AppRoomDatabase.getDatabase(application);
        mAppDao = appRoomDatabase.mAppDao();
    }

    public void insertTask(Task task){
        new InsertTaskAsyncTask(mAppDao).execute(task);
    }

    private static class InsertTaskAsyncTask extends AsyncTask<Task,Void,Void> {
        private AppDao dao;
        InsertTaskAsyncTask(AppDao dao){
            this.dao=dao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            dao.insert(tasks[0]);
            return null;
        }
    }
    public void deleteTask(Task task){
        new DeleteTaskAsyncTask(mAppDao).execute(task);
    }

    private static class DeleteTaskAsyncTask extends AsyncTask<Task,Void,Void> {
        private AppDao dao;
        DeleteTaskAsyncTask(AppDao dao){
            this.dao=dao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            dao.deleteTask(tasks[0].getId());
            return null;
        }
    }

    public LiveData<List<Task>> getTaskByProject(long projectId){
        return mAppDao.getTaskByProject(projectId);
    }

    public LiveData<List<Task>> getAllTask(){
        return mAppDao.getAllTask();
    }
}