package database.dao;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

public interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(T newsInfo);
}
