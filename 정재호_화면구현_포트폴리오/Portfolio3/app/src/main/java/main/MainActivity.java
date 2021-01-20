package main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import org.jaeho.main.databinding.ActivityMainBinding;

import background.AlarmReceiver;
import database.data.BreakingNews;
import database.data.TodayNews;
import database.database.NewsDatabase;
import database.viewmodel.TodayNewViewModel;
import fragment.FragmentBreakingNews;
import fragment.FragmentPopularNews;
import fragment.FragmentTodayNews;
import rss.RssParsing;
import utility.SharedKey;
import utility.UtilSharedPreference;

public class MainActivity extends AppCompatActivity {

    // 바인딩 필드
    ActivityMainBinding binding;

    // 프레그먼트 필드
    FragmentTodayNews fragmentTodayNews;
    FragmentBreakingNews fragmentBreakingNews;
    FragmentPopularNews fragmentPopularNews;

    AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 바인딩 객체 초기화
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 필드 초기화
        fragmentTodayNews = new FragmentTodayNews();
        fragmentBreakingNews = new FragmentBreakingNews();
        fragmentPopularNews = new FragmentPopularNews();

        // 알람 매니저 초기화
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        // 초기 화면
        getSupportFragmentManager().beginTransaction().replace(binding.container.getId(), fragmentTodayNews).commit();

        // 탭 클릭 리스너
        binding.bottomTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();

                switch (pos) {
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(binding.container.getId(), fragmentTodayNews).commit();
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(binding.container.getId(), fragmentBreakingNews).commit();
                        break;
                    case 2:
                        getSupportFragmentManager().beginTransaction().replace(binding.container.getId(), fragmentPopularNews).commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // 플로팅 버튼 리스너
        binding.fabMain.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    UtilSharedPreference.setInteger(getApplicationContext(), SharedKey.TIME_HOUR_KEY, hourOfDay);
                    UtilSharedPreference.setInteger(getApplicationContext(), SharedKey.TIME_MINUTE_KEY, minute);

                    Log.e("Debug", "저장 된 시간 : " + UtilSharedPreference.getInteger(getApplicationContext(), SharedKey.TIME_HOUR_KEY));
                    Log.e("Debug", "저장 된 분 : " + UtilSharedPreference.getInteger(getApplicationContext(), SharedKey.TIME_MINUTE_KEY));

                    Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 100, pendingIntent);
                    } else {
                        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 100, pendingIntent);
                    }
                }
            }, 0, 0, false);

            timePickerDialog.setMessage("뉴스 데이터 통신 시간 설정");
            timePickerDialog.show();
        });

        // 최초 1회 알람 시작
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 100, pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 100, pendingIntent);
        }
    }
}