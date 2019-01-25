package com.example.a10068921.myapplication.sqlite;

import java.time.LocalDateTime;

/**
 * @author 10068921(LgyTT)
 * @description:
 * @date 2019/1/21 15:02
 **/
public class NormalModel {
    private String name;
    private LocalDateTime createTime;
    private String description;
    private String descriptionPath;

    public NormalModel(Builder builder) {
        this.name=builder.name;
        this.createTime=builder.createTime;
        this.description=builder.description;
        this.descriptionPath=builder.descriptionPath;
    }

    public static Builder newBuilder(){
        return new Builder();
    }
  public static  class Builder {
        private String name;
        private LocalDateTime createTime;
        private String description;
        private String descriptionPath;

        public Builder name(String name){
            this.name=name;
            return this;
        }
        public Builder createTime(LocalDateTime createTime){
            this.createTime=createTime;
            return this;
        }
        public Builder description(String description){
            this.description=description;
            return this;
        }
        public Builder descriptionPath(String descriptionPath){
            this.descriptionPath=descriptionPath;
            return this;
        }

        public NormalModel builder(){
           return new NormalModel(this);
        }
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public String getDescription() {
        return description;
    }

    public String getDescriptionPath() {
        return descriptionPath;
    }
}
