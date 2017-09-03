package com.lwrs.app.db.entity;

import com.lwrs.app.enums.FileType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@Builder
@NoArgsConstructor
public class FileLocationDB {

    private Long id;
    private Long userId;
    /**
     * @see FileType#name
     */
    private String type;
    /**
     * 相对位置
     */
    private String location;
    private Date createAt;
    private Date updateAt;


}
