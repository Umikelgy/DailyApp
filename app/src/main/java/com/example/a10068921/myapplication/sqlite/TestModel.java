package com.example.a10068921.myapplication.sqlite;

/**
 * @author 10068921(LgyTT)
 * @description:
 * @date 2019/1/24 13:33
 **/

public class TestModel {
    private Integer id;
    private String name;
    private Integer number;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }

    public TestModel(Builder builder) {
        this.id=builder.id;
        this.name=builder.name;
        this.number=builder.number;
    }
    public static Builder newBuilder(){
        return new Builder();
    }
  public static class Builder{
        private Integer id;
        private String name;
        private Integer number;
        public Builder id(Integer id){
            this.id=id;
            return this;
        }
        public Builder name(String name){
            this.name=name;
            return this;
        }
        public Builder number(Integer number){
            this.number=number;
            return this;
        }
        public TestModel builder(){
         return    new TestModel(this);
        }
    }
}
