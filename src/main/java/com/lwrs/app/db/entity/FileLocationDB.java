package com.lwrs.app.db.entity;

import com.lwrs.app.domain.BaseDto;
import com.lwrs.app.enums.FileType;
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
public class FileLocationDB extends BaseDto{

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
