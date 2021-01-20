package database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import database.data.BreakingNews;

@Dao
public interface BreakingNewsDao extends BaseDao<BreakingNews> {

    @Query("SELECT * FROM breaking_news ORDER BY guid ASC")
    LiveData<List<BreakingNews>> getAll();
}
