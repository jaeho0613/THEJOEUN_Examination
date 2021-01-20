package database.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "today_news")
public class TodayNews {

    @PrimaryKey
    @NonNull
    public Long guid;

    @Embedded
    public BaseNews baseNews;

    @Ignore
    public TodayNews(BaseNews baseNews) {
        this.baseNews = baseNews;
    }

    public TodayNews(Long guid, BaseNews baseNews) {
        this.guid = guid;
        this.baseNews = baseNews;
    }
}
