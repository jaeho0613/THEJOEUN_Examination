package database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import database.data.PopularNews;

@Dao
public interface PopularNewsDao extends BaseDao<PopularNews> {

    @Query("SELECT * FROM popular_news ORDER BY guid ASC")
    LiveData<List<PopularNews>> getAll();
}
