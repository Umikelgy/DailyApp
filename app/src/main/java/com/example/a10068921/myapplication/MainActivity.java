package com.example.a10068921.myapplication;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.a10068921.myapplication.adapter.NormalAdapter;
import com.example.a10068921.myapplication.common.Permission;
import com.example.a10068921.myapplication.flesh.EndlessRecyclerOnScrollListener;
import com.example.a10068921.myapplication.flesh.LoadMoreWrapper;
import com.example.a10068921.myapplication.sqlite.NormalModel;
import com.example.a10068921.myapplication.sqlite.SqliteService;
import com.example.a10068921.myapplication.sqlite.impl.SqliteServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * @author 10068921
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LoadMoreWrapper loadMoreWrapper;
    private List<String> data=new ArrayList<>();
   @Override
   protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**权限赋予*/
       Permission.permission(this,this);

       swipeRefreshLayout=findViewById(R.id.swipe_refresh_layout);
       recyclerView=findViewById(R.id.recycler_view);
       toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4DB6AC"));


       initTData();

       /**RecyclerView 设置*/
       NormalAdapter normalAdapter= new NormalAdapter(data);
       loadMoreWrapper =new LoadMoreWrapper(normalAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(loadMoreWrapper);

        swipeRefreshLayout.setOnRefreshListener(()->{
            data.clear();
            initTData();
            loadMoreWrapper.notifyDataSetChanged();

//            延迟1秒关闭下拉刷新
            swipeRefreshLayout.postDelayed(()->{
                if(swipeRefreshLayout!=null&&swipeRefreshLayout.isRefreshing()){
                    swipeRefreshLayout.setRefreshing(false);
                }
            },1000);
        });

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
             loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING);
             if(data.size()<20){
                 new Timer().schedule(new TimerTask() {
                     @Override
                     public void run() {
                         runOnUiThread(()->{
                             initTData();
                             loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);
                         });
                     }
                 }, 1000);
             }else {
//                 到底加载的提示
                 loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_END);
             }
            }
        });
    }

    private List<NormalModel> initData() {
        SqliteService sqliteService=new SqliteServiceImpl();
        return sqliteService.selectNormalAllEvent(this);
    }


    private void initTData() {
        for(int i=0;i<10;i++){
                data.add("item"+i);
            }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        data.clear();
        initTData();
        recyclerView.setAdapter(loadMoreWrapper);
        return super.onOptionsItemSelected(item);
    }
}
