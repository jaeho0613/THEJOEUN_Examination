package database.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import database.dao.BreakingNewsDao;
import database.dao.PopularNewsDao;
import database.dao.TodayNewsDao;
import database.data.BreakingNews;
import database.data.PopularNews;
import database.data.TodayNews;

// 싱글톤으로 구현
// - 멀티 쓰레드 환경에서 하나의 객체만 생성 가능
@Database(entities = {TodayNews.class, BreakingNews.class, PopularNews.class}, version = 1, exportSchema = false)
public abstract class NewsDatabase extends RoomDatabase {

    // getTodayNewsDao 얻기
    public abstract TodayNewsDao getTodayNewsDao();

    // getBreakingNewsDao 얻기
    public abstract BreakingNewsDao getBreakingNewsDao();

    // getPopularNewsDao 얻기
    public abstract PopularNewsDao getPopularNewsDao();

    // 싱글톤 인스턴스 변수
    private static NewsDatabase INSTANCE;

    // 사용할 쓰레드 갯수
    private static final int NUMBER_OF_THREADS = 4;

    // 메인 스레드에서 사용 할 수 있는 쓰레드를 반환
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // 메인 스레드에 접근 하기 위한 ExecutorService 콜백
    private static RoomDatabase.Callback RoomDatabaseCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    // 싱글톤으로 데이터 베이스를 생성
    // - INSTANCE 가 없을 경우 (NULL) 새로 생성
    // - INSTANCE 가 있을 경우 바로 반환
    public static NewsDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (NewsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NewsDatabase.class, "Portfolio3")
                            .addCallback(RoomDatabaseCallBack)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
