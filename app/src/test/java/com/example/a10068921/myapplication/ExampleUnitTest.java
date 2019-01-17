package com.example.a10068921.myapplication;

import android.database.DatabaseUtils;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void SqliteTest(){
        Connection conn=null;
        ResultSet resultSet=null;
        Statement statement=null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn= DriverManager.getConnection("jdbc:sqlite:test.db");

         statement=conn.createStatement();
         String sql ="select * from COMPANY";
         resultSet=statement.executeQuery(sql);
         while(resultSet.next()){
            System.out.println( resultSet.getString("name"));
         }


        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }catch (SQLException se){
            System.out.println(se.getMessage());
            se.printStackTrace();
        }finally {
            try {
                resultSet.close();
                statement.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    @Test
    public void testWords(){
        String massage="ajfla,jfalag,j,jsfs~jklff,22,1,~j";
      int t;
        Set<Integer> indexs=new TreeSet<>();
        String allKeys="~·！@#￥%……&*（）—+《》？，。、；：’‘“”【】{}`~!$%^&*()_+-={}[];:''\",./<>?";
        for(char c:allKeys.toCharArray()){
                 t=massage.indexOf(c);
          while(t!=-1&&t!=massage.length()){
              indexs.add(t);
             t=massage.indexOf(c,t+1);

          }
        }

        List<String> list=new ArrayList<>();
        Set<Integer> startIndexs=indexs;
        startIndexs.add(-1);
        indexs.stream().forEach(System.out::println);
        List<Integer> endIndexs=indexs.stream().sorted().collect(Collectors.toList());

      List<Integer> start=  startIndexs.stream().sorted().collect(Collectors.toList());
      int i;
      for( i=0;i<endIndexs.size();i++){
          list.add(massage.substring(start.get(i)+1,endIndexs.get(i)));
      }
      list.add(massage.substring(start.get(i)+1));
list.stream().forEach(System.out::println);
    }
    @Test
    public void TestIndexOf(){
      int[] as={12,11,4,1,21,1,2,23};


    }
}