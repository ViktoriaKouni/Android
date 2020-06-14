package com.example.corona.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.corona.Database.TipsDAO;
import com.example.corona.Database.TipsDatabase;
import com.example.corona.model.Tips;

import java.util.List;


public class TipsRepository {

    private TipsDAO tipsDAO;
    private static TipsRepository instance;
    private LiveData<List<Tips>> allTips;

    private TipsRepository(Application application) {
        TipsDatabase database = TipsDatabase.getInstance(application);
        tipsDAO = database.guidanceDao();
        allTips = tipsDAO.getAllTips();
    }

    public static synchronized TipsRepository getInstance(Application application) {
        if (instance == null)
            instance = new TipsRepository(application);

        return instance;
    }

    public LiveData<List<Tips>> getAllTips() {
        return allTips;
    }


    public void insert(Tips tips) {
        new InsertTipsAsync(tipsDAO).execute(tips);
    }


    private static class InsertTipsAsync extends AsyncTask<Tips, Void, Void> {
        private TipsDAO tipsDAO;

        private InsertTipsAsync(TipsDAO tipsDAO) {
            this.tipsDAO = tipsDAO;
        }

        @Override
        protected Void doInBackground(Tips... tips) {
            tipsDAO.insert(tips[0]);
            return null;
        }
    }

    public void delete(Tips tips) {
        new DeleteGuidanceAsyncTask(tipsDAO).execute(tips);
    }

    private static class DeleteGuidanceAsyncTask extends AsyncTask<Tips, Void, Void> {
        private TipsDAO tipsDAO;

        private DeleteGuidanceAsyncTask(TipsDAO tipsDAO) {
            this.tipsDAO = tipsDAO;
        }

        @Override
        protected Void doInBackground(Tips... tips) {
            tipsDAO.delete(tips[0]);
            return null;
        }

    }
}
