package com.lwrs.app.domain.dto.resp;

import com.lwrs.app.domain.BaseDto;
import com.lwrs.app.enums.RespCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResp extends BaseDto{

    protected String code;

    protected String msg;


    public BaseResp(){
        code = RespCode.OK.getCode();
        msg = RespCode.OK.getMsg();
    }

    public BaseResp(RespCode respCode){
        code = respCode.getCode();
        msg = respCode.getMsg();
    }

    public BaseResp(RespCode respCode,  String msg){
        code = respCode.getCode();
        this.msg = msg;
    }

    public static BaseResp of(RespCode respCode){
        return new BaseResp(respCode);
    }

    public static BaseResp of(RespCode respCode, String msg){
        return new BaseResp(respCode, msg);
    }


    public void setRespCode(RespCode respCode){
        code = respCode.getCode();
        msg = respCode.getMsg();
    }
}
