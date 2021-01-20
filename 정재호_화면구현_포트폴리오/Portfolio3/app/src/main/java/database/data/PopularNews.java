package database.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "popular_news")
public class PopularNews {

    @PrimaryKey
    @NonNull
    public Long guid;

    @Embedded
    public BaseNews baseNews;

    @Ignore
    public PopularNews(BaseNews baseNews) {
        this.baseNews = baseNews;
    }

    public PopularNews(Long guid, BaseNews baseNews) {
        this.guid = guid;
        this.baseNews = baseNews;
    }
}
