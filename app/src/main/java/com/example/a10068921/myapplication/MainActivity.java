package com.example.a10068921.myapplication;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import com.example.a10068921.myapplication.adapter.NormalAdapter;
import com.example.a10068921.myapplication.sqlite.DatabaseHelper;
import com.example.a10068921.myapplication.sqlite.SqliteUtils;
import com.example.a10068921.myapplication.sqlite.TestConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 10068921
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
     * 创建数据库
     * */
        String createSql="example (id integer primary key autoincrement ,name varchar(20) ,number INTEGER)";
        String insertSql="insert into example (name,number) values(?,?) ";
       SqliteUtils sqliteUtils= new SqliteUtils(this);
       sqliteUtils.modifyDB(createSql);
       sqliteUtils.insertTableMassage(insertSql,new Object[]{"name1","1234"});
        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**
         * RecyclerView 设置
         * */
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<String> data=initData();
        recyclerView.setAdapter(new NormalAdapter(data));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private List<String> initData() {
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
