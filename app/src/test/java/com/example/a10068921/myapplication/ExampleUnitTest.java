package com.example.a10068921.myapplication;

import android.database.DatabaseUtils;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;

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
}