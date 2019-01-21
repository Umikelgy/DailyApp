package com.example.a10068921.myapplication;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.a10068921.myapplication.adapter.NormalAdapter;
import com.example.a10068921.myapplication.adapter.quick.QuickAdapter;
import com.example.a10068921.myapplication.common.Permission;
import com.example.a10068921.myapplication.sqlite.NormalModel;
import com.example.a10068921.myapplication.sqlite.SqliteService;
import com.example.a10068921.myapplication.sqlite.impl.SqliteServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 10068921
 */
public class MainActivity extends AppCompatActivity {



   @Override
   protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**权限赋予*/
       Permission.permission(this,this);

        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /**RecyclerView 设置*/
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

       // TODO: 2019/1/18 数据来源
        List<String> testData=initTData();
        List<NormalModel> data=initData();

        recyclerView.setAdapter(new NormalAdapter(testData));


    }

    private List<NormalModel> initData() {
        SqliteService sqliteService=new SqliteServiceImpl();
        return sqliteService.selectNormalAllEvent();
    }


    private List<String> initTData() {
        List<String> mData=new ArrayList<>();
        for(int i=0;i<100;i++){
                mData.add("item"+i);
            }
        return mData;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
