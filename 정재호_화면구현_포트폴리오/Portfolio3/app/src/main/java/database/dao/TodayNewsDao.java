package database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import database.data.TodayNews;

@Dao
public interface TodayNewsDao extends BaseDao<TodayNews> {

    @Query("SELECT * FROM today_news ORDER BY guid ASC")
    LiveData<List<TodayNews>> getAll();
}
