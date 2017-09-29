package com.lwrs.app.controller.conf;

import com.lwrs.app.domain.BaseDto;
import com.lwrs.app.enums.RespCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * http status为4xx,5xx时 返回的错误信息对象
 * http status为2xx时 只需要返回状态码
 */
@Getter
@Setter
@ToString
public class ErrorResponse extends BaseDto {

    private LocalDateTime timestamp;

    private RespCode errorCode;

    private String errorMessage;

    private String path;

    public  ErrorResponse() {
    }

    public  ErrorResponse(RespCode errorCode, String errorMessage, String path) {
      this.errorCode = errorCode;
      this.errorMessage = errorMessage;
      this.path = path;
      this.timestamp = LocalDateTime.now();
    }
}
