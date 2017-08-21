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


    public static RespBaseDtoBuilder builder() {
        return new RespBaseDtoBuilder();
    }

    public static final class RespBaseDtoBuilder {
        private String code;
        private String msg;

        private RespBaseDtoBuilder() {
        }

        public RespBaseDtoBuilder respCode(RespCode respCode) {
            code = respCode.getCode();
            msg = respCode.getMsg();
            return this;
        }

        public RespBaseDto build() {
            RespBaseDto respBaseDto = new RespBaseDto();
            respBaseDto.msg = this.msg;
            respBaseDto.code = this.code;
            return respBaseDto;
        }
    }
}
