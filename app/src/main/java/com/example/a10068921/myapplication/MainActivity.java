package com.example.a10068921.myapplication;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;

import com.example.a10068921.myapplication.adapter.EventAdapter;
import com.example.a10068921.myapplication.common.Permission;
import com.example.a10068921.myapplication.common.Utils;
import com.example.a10068921.myapplication.flesh.EndlessRecyclerOnScrollListener;
import com.example.a10068921.myapplication.flesh.LoadMoreWrapper;
import com.example.a10068921.myapplication.sqlite.NormalModel;
import com.example.a10068921.myapplication.sqlite.SqliteService;
import com.example.a10068921.myapplication.sqlite.impl.SqliteServiceImpl;
import com.example.a10068921.myapplication.sqlite.sqlthread.DeleteThread;
import com.scalified.fab.ActionButton;

import java.util.*;


/**
 * @author 10068921
 */
public class MainActivity extends AppCompatActivity {

    private static int tableIndex=0;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ActionButton addEvent;
    private LoadMoreWrapper loadMoreWrapper;
    private List<NormalModel> data=new ArrayList<>();

   @RequiresApi(api = Build.VERSION_CODES.M)
   @Override
   protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**权限赋予*/
        Permission.permission(this,this);
        /**创建数据源文件路径*/
       Utils.createPath();

        swipeRefreshLayout=findViewById(R.id.swipe_refresh_layout);
        recyclerView=findViewById(R.id.recycler_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       addEvent=findViewById(R.id.action_button);
       addEvent.setButtonColor(getColor(R.color.fab_material_lime_500));
       addEvent.setImageResource(R.drawable.fab_plus_icon);
       addEvent.show();
       addEvent.removeShadow();
       addEvent.setOnClickListener(view -> {

       });

        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4DB6AC"));
        swipeRefreshLayout.setOnRefreshListener(()->{
           data.clear();
           tableIndex=0;
           initData();
           loadMoreWrapper.notifyDataSetChanged();

//            延迟1秒关闭下拉刷新
           swipeRefreshLayout.postDelayed(()->{
               if(swipeRefreshLayout!=null&&swipeRefreshLayout.isRefreshing()){
                   swipeRefreshLayout.setRefreshing(false);
               }
           },1000);
       });

       initData();
       /**RecyclerView 设置*/
       loadMoreWrapper =new LoadMoreWrapper( new EventAdapter(data));
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
         /**添加滑动删除*/
       ItemTouchHelper helper=new ItemTouchHelper(new MyItemTouchHelperCallback());
       //  数据过少，没有一页时出现加载动画bug
       recyclerView.setAdapter(loadMoreWrapper);
//       上拉加载更多
       recyclerView.addOnScrollListener(new MyEndlessRecyclerOnScrollListener());
       helper.attachToRecyclerView(recyclerView);
    }

    private int initData() {
        SqliteService sqliteService=new SqliteServiceImpl();
        List<NormalModel> tData=sqliteService.selectNormalAllEvent(this,tableIndex);
        data.addAll(tData);
        if(tData.size()==10){
            tableIndex++;
        }
        return tData.size();
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
        initData();
        recyclerView.setAdapter(loadMoreWrapper);
        return super.onOptionsItemSelected(item);
    }
    class  MyItemTouchHelperCallback extends ItemTouchHelper.Callback {

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            //拖拽
            int dragFlags = ItemTouchHelper.UP|ItemTouchHelper.DOWN;
            //侧滑删除
            int swipeFlags = ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
            return makeMovementFlags(dragFlags,swipeFlags);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            Collections.swap(data,viewHolder.getAdapterPosition(),target.getAdapterPosition());
            loadMoreWrapper.notifyItemMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            NormalModel normalModel=data.remove(viewHolder.getAdapterPosition());
            new Thread(new DeleteThread(normalModel,MainActivity.this)).start();
            loadMoreWrapper.notifyItemRemoved(viewHolder.getAdapterPosition());
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }
    }

    class MyEndlessRecyclerOnScrollListener extends EndlessRecyclerOnScrollListener {
        @Override
        public void onLoadMore() {
            loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING);
            if(initData()>0){
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(()->{
//                             initData();
                            loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);
                        });
                    }
                }, 1000);
            }else {
//                 到底加载的提示
                loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_END);
            }
        }
    }
}
