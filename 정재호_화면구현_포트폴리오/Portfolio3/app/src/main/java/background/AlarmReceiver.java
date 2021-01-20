package background;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Debug;
import android.util.Log;

import java.util.Calendar;

import utility.SharedKey;
import utility.UtilSharedPreference;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("Debug", "Background 알림!!");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, UtilSharedPreference.getInteger(context, SharedKey.TIME_HOUR_KEY));
        calendar.set(Calendar.MINUTE, UtilSharedPreference.getInteger(context, SharedKey.TIME_MINUTE_KEY));

        long currentTime = System.currentTimeMillis();
        long myTime = calendar.getTimeInMillis();

        if (currentTime >= myTime) {
            Log.e("Debug", "하루뒤로 설정, 서비스 실행!");

            calendar.add(Calendar.DATE, 1);
            Intent intentService = new Intent(context, DBService.class);
            context.startService(intentService);
        } else {
            Log.e("Debug", "지정된 시간에 실행");
        }

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intentAlarm = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
    }
}