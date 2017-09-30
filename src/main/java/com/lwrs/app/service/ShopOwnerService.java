package com.lwrs.app.service;

import com.lwrs.app.constant.Constants;
import com.lwrs.app.db.entity.EventBookDB;
import com.lwrs.app.db.entity.ShopDB;
import com.lwrs.app.db.entity.ShopOwnerDB;
import com.lwrs.app.db.mapper.EventBookMapper;
import com.lwrs.app.db.mapper.ShopMapper;
import com.lwrs.app.db.mapper.ShopOwnerMapper;
import com.lwrs.app.domain.dto.resp.BaseResp;
import com.lwrs.app.domain.dto.resp.ShopViewBookResp;
import com.lwrs.app.enums.RespCode;
import com.lwrs.app.utils.ServletHelper;
import com.lwrs.app.utils.UserIdMask;
import com.lwrs.app.utils.UserLoginContext;
import com.lwrs.app.utils.date.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ShopOwnerService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private ShopOwnerMapper shopOwnerMapper;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private EventBookMapper eventBookMapper;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private ShopMapper shopMapper;


    public BaseResp accessCheck(String alias, String code){
        ShopOwnerDB shopOwnerDB = shopOwnerMapper.selectByAlias(alias);
        if(null == shopOwnerDB){
            return BaseResp.of(RespCode.NOT_EXIST, "用户名不存在");
        }

        if(!shopOwnerDB.getCode().equals(MD5Encoder.encode(code.getBytes()))){
           return BaseResp.of(RespCode.CHECK_FAILED, "密码不正确");
        }

        ServletHelper.addCookie(Constants.SHOP_OWNER_COOKIE_NAME, UserIdMask.maskUserId(shopOwnerDB.getId()));
        return BaseResp.of(RespCode.OK);
    }


    public ShopViewBookResp getBookList(){
        Long shopOwnerId = UserLoginContext.getShopOwner();
        ShopOwnerDB shopOwnerDB = shopOwnerMapper.selectById(shopOwnerId);
        if(null == shopOwnerDB || StringUtils.isEmpty(shopOwnerDB.getShopIds())){
            log.info("shopOwnerMapper select empty by Id={} or no shop bind, db={}", shopOwnerId, shopOwnerDB);
            BaseResp.of(RespCode.NOT_EXIST);
        }
        String[] shopIds = shopOwnerDB.getShopIds().trim().split(",");

        List<ShopViewBookResp.OneShopBook> shopsBookList = new ArrayList<>();
        for(String shopIdStr : shopIds){
            if(StringUtils.isEmpty(shopIdStr)){
                continue;
            }
            Long shopId =Long.valueOf(shopIdStr);
            ShopDB shopDB = shopMapper.selectById(shopId);
            if (null == shopDB){
                log.info("get empty shop by shopId={}", shopIdStr);
                continue;
            }
            //get each shop userBook detail
            List<EventBookDB> eventBookDBList = eventBookMapper.selectByShopId(shopId);
            //parse to UserBookInfo
            List<ShopViewBookResp.UserBookInfo> userBookInfoList = eventBookDBList.stream()
                .map(bookDB ->
                    ShopViewBookResp.UserBookInfo.builder()
                        .userName(bookDB.getUserName())
                        .gender(bookDB.getGender())
                        .phone(bookDB.getPhone())
                        .birthDate(DateUtils.format(DateUtils.DATE_FORMAT, bookDB.getBirthDate()))
                        .bookDate(DateUtils.format(DateUtils.DATE_FORMAT, bookDB.getBookDate()))
                        .bookType(bookDB.getBookType())
                        .build()
                    )
                .collect(Collectors.toList());

            ShopViewBookResp.OneShopBook oneShopBook = ShopViewBookResp.OneShopBook.builder()
                .shopName(shopDB.getShopName())
                .userBookInfoList(userBookInfoList)
                .build();
            shopsBookList.add(oneShopBook);
        }
        ShopViewBookResp bookResp = ShopViewBookResp.builder().shopsBookList(shopsBookList).build();
        bookResp.setRespCode(RespCode.OK);
        return bookResp;
    }
}
