package database.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import database.dao.TodayNewsDao;
import database.data.TodayNews;
import database.database.NewsDatabase;

public class TodayNewViewModel extends AndroidViewModel {

    private TodayNewsDao todayNewsDao;
    private final LiveData<List<TodayNews>> todayNewsList;

    public TodayNewViewModel(@NonNull Application application) {
        super(application);

        NewsDatabase db = NewsDatabase.getDatabase(application);

        todayNewsDao = db.getTodayNewsDao();
        todayNewsList = todayNewsDao.getAll();
    }

    public LiveData<List<TodayNews>> getTodayNewsList() {
        return todayNewsList;
    }

    public void insert(TodayNews todayNews) {
        NewsDatabase.databaseWriteExecutor.execute(() -> todayNewsDao.insert(todayNews));
    }
}
