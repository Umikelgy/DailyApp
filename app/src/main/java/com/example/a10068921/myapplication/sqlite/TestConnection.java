package com.example.a10068921.myapplication.sqlite;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 10068921(LgyTT)
 * @description:
 * @date 2019/1/8 16:39
 **/
public class TestConnection  {
    public List<String> TestMsgGetName(){
        Connection conn=null;
        ResultSet resultSet=null;
        Statement statement=null;
        List<String> resultList=new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            conn= DriverManager.getConnection("jdbc:sqlite:test.db");
            statement=conn.createStatement();
            resultSet=statement.executeQuery("SELECT * from COMPANY");
            while(resultSet.next()){
                resultList.add(resultSet.getString("name"));
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }catch (SQLException se){

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
        return resultList;
    }
}
