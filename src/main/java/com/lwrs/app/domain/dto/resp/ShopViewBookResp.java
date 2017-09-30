package com.lwrs.app.domain.dto.resp;

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
public class ShopViewBookResp extends BaseResp {

    List<OneShopBook> shopsBookList;

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter@Setter
    //一个shop 被book
    public static class OneShopBook {
        private String shopName;
        List<UserBookInfo> userBookInfoList;
    }


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter@Setter
    public static class UserBookInfo{
        private String userName;
        private String gender;
        private String phone;
        private String birthDate;

        private String bookType;
        private String bookDate;
    }
}
