package com.lwrs.app.domain.dto;

import com.lwrs.app.domain.BaseDto;
import com.lwrs.app.enums.RespCode;

public class RespBaseDto extends BaseDto{

    private String code;

    private String msg;


    public RespBaseDto(){
        code = RespCode.OK.getCode();
        msg = RespCode.OK.getMsg();
    }

    public RespBaseDto(RespCode respCode){
        code = respCode.getCode();
        msg = respCode.getMsg();
    }

    public static RespBaseDto of(RespCode respCode){
        return new RespBaseDto(respCode);
    }


    public void setRespCode(RespCode respCode){
        code = respCode.getCode();
        msg = respCode.getMsg();
    }
}
