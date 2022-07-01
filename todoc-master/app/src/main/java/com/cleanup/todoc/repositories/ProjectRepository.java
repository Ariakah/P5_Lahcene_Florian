package com.cleanup.todoc.repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.AppDao;
import com.cleanup.todoc.database.dao.AppRoomDatabase;
import com.cleanup.todoc.model.Project;

import java.util.List;

public class ProjectRepository {

    private AppDao mAppDao;

    public ProjectRepository(Application application){
        AppRoomDatabase appRoomDatabase = AppRoomDatabase.getDatabase(application);
        mAppDao = appRoomDatabase.mAppDao();
    }

    public void insertProject(Project project){
        new InsertProjectAsyncTask(mAppDao).execute(project);
    }

    private static class InsertProjectAsyncTask extends AsyncTask<Project,Void,Void> {
        private AppDao dao;
    InsertProjectAsyncTask(AppDao dao){
        this.dao=dao;
    }

        @Override
        protected Void doInBackground(Project... projects) {
            dao.insert(projects[0]);
            return null;
        }
    }
    public void deleteProject(Project project){
        new DeleteProjectAsyncTask(mAppDao).execute(project);
    }

    private static class DeleteProjectAsyncTask extends AsyncTask<Project,Void,Void> {
        private AppDao dao;
        DeleteProjectAsyncTask(AppDao dao){
            this.dao=dao;
        }

        @Override
        protected Void doInBackground(Project... projects) {
            dao.deleteProject(projects[0].getId());
            return null;
        }
    }

    public LiveData<List<Project>> getAllProject(){
        return mAppDao.getAllProject();
    }
}