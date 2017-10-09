package com.lwrs.app.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TempUserLinkDB {
    private Long id;
    private Long userId;
    private String tempUid;
    private Date createAt;
    private Date updateAt;
}
