package database.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import database.dao.BreakingNewsDao;
import database.data.BaseNews;
import database.data.BreakingNews;
import database.database.NewsDatabase;

public class BreakingNewsViewModel extends AndroidViewModel {

    private BreakingNewsDao breakingNewsDao;
    private final LiveData<List<BreakingNews>> breakingNewsList;

    public BreakingNewsViewModel(@NonNull Application application) {
        super(application);

        NewsDatabase db = NewsDatabase.getDatabase(application);

        breakingNewsDao = db.getBreakingNewsDao();
        breakingNewsList = breakingNewsDao.getAll();
    }

    public LiveData<List<BreakingNews>> getTodayNewsList() {
        return breakingNewsList;
    }

    public void insert(BreakingNews breakingNews) {
        NewsDatabase.databaseWriteExecutor.execute(() -> breakingNewsDao.insert(breakingNews));
    }
}
