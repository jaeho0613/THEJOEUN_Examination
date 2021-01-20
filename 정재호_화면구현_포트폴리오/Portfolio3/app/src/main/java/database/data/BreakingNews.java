package database.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

@Entity(tableName = "breaking_news")
public class BreakingNews {

    @PrimaryKey
    @NonNull
    public Long guid;

    @Embedded
    public BaseNews baseNews;

    @Ignore
    public BreakingNews(BaseNews baseNews) {
        this.baseNews = baseNews;
    }

    public BreakingNews(Long guid, BaseNews baseNews) {
        this.guid = guid;
        this.baseNews = baseNews;
    }
}
