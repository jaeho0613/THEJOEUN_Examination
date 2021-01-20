package org.jaeho.portfolio2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.TextView;

import MenuFragment.FragmentSlideShow;

public class MenuActivity extends AppCompatActivity {

    TextView textViewName;
    TextView textViewAge;
    Button buttonBack;

    String name = "";
    String age = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        textViewAge = findViewById(R.id.textViewAge);
        textViewName = findViewById(R.id.textViewName);
        buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("name", name);
            intent.putExtra("age", age);
            setResult(RESULT_OK, intent);
            finish();
        });

        Intent intent = getIntent();
        getData(intent);
    }

    public void getData(Intent intent) {
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            name = bundle.getString("name");
            age = bundle.getString("age");
            textViewName.setText("이름 : " + name);
            textViewAge.setText("나이 : " + age);
        }
    }
}