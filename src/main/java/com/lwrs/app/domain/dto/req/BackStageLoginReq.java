package com.lwrs.app.domain.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class BackStageLoginReq {
    private String alias;
    private String code;
}
