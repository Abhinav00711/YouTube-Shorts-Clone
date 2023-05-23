package com.example.shortsclone;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private List<Post> postList = new ArrayList<>();
    private int currentPage = 0;
    private ViewPager2 viewPager2;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        viewPager2 = findViewById(R.id.viewPager);
        pagerAdapter = new PagerAdapter(postList,MainActivity.this);
        viewPager2.setAdapter(pagerAdapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == pagerAdapter.getItemCount() - 1) {
                    currentPage++;
                    fetchData();
                }
            }
        });
        fetchData();
    }

    private void fetchData(){
        Request.Builder builder = new Request.Builder();
        Request request = builder.url("https://internship-service.onrender.com/videos?page=" + currentPage).build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Gson gson = new GsonBuilder().create();
                PostModel postModel = gson.fromJson(response.body().string(), PostModel.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        postList = postModel.getData().getPosts();
                        pagerAdapter.setData(postList);
                    }
                });

            }
        });
    }
}