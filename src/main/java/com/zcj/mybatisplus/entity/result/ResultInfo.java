package com.zcj.mybatisplus.entity.result;


import lombok.Data;

@Data
public class ResultInfo {
    public boolean success;
    public String message;

    public ResultInfo(boolean success,String message){
        this.success=success;
        this.message=message;
    }
}
