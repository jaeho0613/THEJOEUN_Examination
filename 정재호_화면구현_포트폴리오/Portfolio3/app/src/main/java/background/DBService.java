package background;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.HashMap;

import database.data.BaseNews;
import database.data.BreakingNews;
import database.data.PopularNews;
import database.data.TodayNews;
import database.viewmodel.BreakingNewsViewModel;
import database.viewmodel.PopularNewsViewModel;
import database.viewmodel.TodayNewViewModel;
import rss.RssParsing;
import utility.SharedKey;
import utility.UtilSharedPreference;

public class DBService extends Service {
    String popularNewsUrl = "http://rss.etnews.co.kr/Section903.xml";
    String todayNewsUrl = "http://rss.etnews.co.kr/Section901.xml";
    String breakingNewsUrl = "http://rss.etnews.co.kr/Section902.xml";

    public DBService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Runnable todayRun = () -> {
            HashMap<Long, BaseNews> baseNewsList = RssParsing.parsing(todayNewsUrl);

            for (Long key : baseNewsList.keySet()) {
                TodayNewViewModel model = new TodayNewViewModel(getApplication());
                model.insert(new TodayNews(key, baseNewsList.get(key)));
            }
        };

        new Thread(todayRun).start();

        Runnable breakingRun = () -> {
            HashMap<Long, BaseNews> baseNewsList = RssParsing.parsing(breakingNewsUrl);

            for (Long key : baseNewsList.keySet()) {
                BreakingNewsViewModel model = new BreakingNewsViewModel(getApplication());
                model.insert(new BreakingNews(key, baseNewsList.get(key)));
            }
        };

        new Thread(breakingRun).start();

        Runnable popularRun = () -> {
            HashMap<Long, BaseNews> baseNewsList = RssParsing.parsing(popularNewsUrl);

            for (Long key : baseNewsList.keySet()) {
                PopularNewsViewModel model = new PopularNewsViewModel(getApplication());
                model.insert(new PopularNews(key, baseNewsList.get(key)));
            }
        };

        new Thread(popularRun).start();
        // }

        return super.onStartCommand(intent, flags, startId);
    }
}