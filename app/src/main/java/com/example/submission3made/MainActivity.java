package com.example.submission3made;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.submission3made.film.MainViewModelFilm;
import com.example.submission3made.film.model.RespFilm;
import com.example.submission3made.service.ApiClient;
import com.example.submission3made.service.ApiService;
import com.google.android.material.tabs.TabLayout;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {


//    private RecyclerView recyclerView;


    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;

    String language = "id";
    private MainViewModelFilm mainViewModelFilm;

//    private ArrayList<FilmBean> filmBeans = new ArrayList<>();

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String listFilmString =     getResources().getString(R.string.listFilm);
        String listTvString = getResources().getString(R.string.listTv);



        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText(listFilmString));
        tabLayout.addTab(tabLayout.newTab().setText(listTvString));


//
//        ApiService service = ApiClient.getRetrofitInstance().create(ApiService.class);
//
//        Call<RespFilm> callLanguage = service.getDataLanguage(language);
//        callLanguage.enqueue(new Callback<RespFilm>() {
//            @Override
//            public void onResponse(Call<RespFilm> call, Response<RespFilm> response) {
//
//
//            }
//
//            @Override
//            public void onFailure(Call<RespFilm> call, Throwable t) {
//
//                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
//            }
//        });

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

//    public static void getLanguage(Context c, String language) {
//
//        Locale locale = new Locale(language);
//        Locale.setDefault(locale);
//
//        Configuration config = c.getResources().getConfiguration();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            config.setLocale(locale);
//        }
//
//        c.getResources().updateConfiguration(config, c.getResources().getDisplayMetrics());
//
//
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings){
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
