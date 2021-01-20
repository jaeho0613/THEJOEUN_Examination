package org.jaeho.portfolio2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import HomeFragment.FragmentMain;
import MenuFragment.FragmentEmail;
import MenuFragment.FragmentGallery;
import MenuFragment.FragmentInfo;
import MenuFragment.FragmentMap;
import MenuFragment.FragmentSlideShow;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // 메뉴 필드
    FragmentMain fragmentMain;
    FragmentEmail fragmentEmail;
    FragmentInfo fragmentInfo;
    FragmentMap fragmentMap;
    FragmentGallery fragmentGallery;
    FragmentSlideShow fragmentSlideShow;

    // 아이템 필드
    DrawerLayout drawer;
    Toolbar toolbar;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    FloatingActionButton floating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 플레그먼트 생성
        fragmentMain = new FragmentMain();
        fragmentEmail = new FragmentEmail();
        fragmentInfo = new FragmentInfo();
        fragmentMap = new FragmentMap();
        fragmentGallery = new FragmentGallery();
        fragmentSlideShow = new FragmentSlideShow();

        // 아이템 생성
        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        bottomNavigationView = findViewById(R.id.nav_bottom);
        floating = findViewById(R.id.floating);

        // 액션바 설정
        setSupportActionBar(toolbar);

        // 네비게이션 뷰 설정
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.nav_open, R.string.nav_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // 플로팅 버튼 클릭 이벤트
        floating.setOnClickListener(v -> {
            Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
        });

        // 클릭 이벤트 처리
        // - 네비게이션 클릭
        navigationView.setNavigationItemSelectedListener(this);

        // - 바텀 네비게이션 클릭
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_email:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentEmail).commit();
                    Toast.makeText(this, "첫 번째 하단탭 선택", Toast.LENGTH_SHORT).show();
                    toolbar.setTitle("하단메뉴 이메일 화면");
                    return true;
                case R.id.menu_Info:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentInfo).commit();
                    Toast.makeText(this, "두 번째 하단탭 선택", Toast.LENGTH_SHORT).show();
                    toolbar.setTitle("하단메뉴 정보 화면");
                    return true;
                case R.id.menu_map:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentMap).commit();
                    Toast.makeText(this, "세 번째 하단탭 선택", Toast.LENGTH_SHORT).show();
                    toolbar.setTitle("하단메뉴 맵 화면");
                    return true;
            }
            return true;
        });

        // 메인 뷰 초기화
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentMain).commit();

        // 메인 퉅바 이름 초기화
        getSupportActionBar().setTitle("Home Fragment");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.menu_edit:
                Toast.makeText(this, "메뉴 Edit 선택됨.", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_setting:
                Toast.makeText(this, "설정 메뉴가  선택됨.", Toast.LENGTH_SHORT).show();
                return true;
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    // 네비게이션 뷰 클릭 이벤트
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_home:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragmentMain).commit();
                drawer.closeDrawer(GravityCompat.START);
                toolbar.setTitle("Home Fragment");
                return true;
            case R.id.menu_gallery:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragmentGallery).commit();
                drawer.closeDrawer(GravityCompat.START);
                toolbar.setTitle("GalleryFragment");
                return true;
            case R.id.menu_sildeshow:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragmentSlideShow).commit();
                drawer.closeDrawer(GravityCompat.START);
                toolbar.setTitle("SlideShowFragment");
                return true;
            case R.id.menu_email:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragmentEmail).commit();
                drawer.closeDrawer(GravityCompat.START);
                toolbar.setTitle("EmailFragment");
                return true;
            case R.id.menu_Info:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragmentInfo).commit();
                drawer.closeDrawer(GravityCompat.START);
                toolbar.setTitle("InfoFragment");
                return true;
            case R.id.menu_map:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragmentMap).commit();
                drawer.closeDrawer(GravityCompat.START);
                toolbar.setTitle("MapFragment");
                return true;
        }

        return true;
    }

    // 뒤로가기 키를 눌렀을 때에 대한 상황을 오버라이딩한다.
    @Override
    public void onBackPressed() {
        // AlertDialog 빌더를 이용해 종료시 발생시킬 창을 띄운다
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        alBuilder.setMessage("정말로 종료하시겠습니까?");

        // "예" 버튼을 누르면 실행되는 리스너
        alBuilder.setPositiveButton("예", (dialog, which) -> {
            finish(); // 현재 액티비티를 종료한다. (MainActivity에서 작동하기 때문에 애플리케이션을 종료한다.)
        });
        // "아니오" 버튼을 누르면 실행되는 리스너
        alBuilder.setNegativeButton("아니오", (dialog, which) -> {
            return; // 아무런 작업도 하지 않고 돌아간다
        });
        alBuilder.setTitle("안내");
        alBuilder.show(); // AlertDialog.Bulider로 만든 AlertDialog를 보여준다.
    }
}