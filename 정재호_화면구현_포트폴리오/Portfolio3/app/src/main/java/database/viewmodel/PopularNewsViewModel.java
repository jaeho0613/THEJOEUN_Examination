package database.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import database.dao.PopularNewsDao;
import database.data.PopularNews;
import database.database.NewsDatabase;

public class PopularNewsViewModel extends AndroidViewModel {

    private PopularNewsDao popularNewsDao;
    private final LiveData<List<PopularNews>> popularNewsList;

    public PopularNewsViewModel(@NonNull Application application) {
        super(application);

        NewsDatabase db = NewsDatabase.getDatabase(application);

        popularNewsDao = db.getPopularNewsDao();
        popularNewsList = popularNewsDao.getAll();
    }

    public LiveData<List<PopularNews>> getTodayNewsList() {
        return popularNewsList;
    }

    public void insert(PopularNews popularNews) {
        NewsDatabase.databaseWriteExecutor.execute(() -> popularNewsDao.insert(popularNews));
    }
}
