package com.lwrs.app.domain.dto.resp;

import com.lwrs.app.domain.dto.UserBookInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class UserBookListResp extends BaseResp {

    List<UserBookInfo> userBookInfoList;
}
