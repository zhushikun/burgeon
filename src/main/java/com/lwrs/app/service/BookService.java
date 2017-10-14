package com.lwrs.app.service;

import com.lwrs.app.db.entity.EventBookDB;
import com.lwrs.app.db.entity.ShopDB;
import com.lwrs.app.db.mapper.EventBookMapper;
import com.lwrs.app.db.mapper.ShopMapper;
import com.lwrs.app.domain.dto.UserBookInfo;
import com.lwrs.app.domain.dto.resp.BaseResp;
import com.lwrs.app.domain.dto.req.BookReq;
import com.lwrs.app.enums.Gender;
import com.lwrs.app.enums.RespCode;
import com.lwrs.app.utils.UserLoginContext;
import com.lwrs.app.utils.date.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private EventBookMapper eventBookMapper;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private ShopMapper shopMapper;

    public BaseResp bookAccept(BookReq bookReq) throws ParseException {
        Long userId = UserLoginContext.getUserId();
        EventBookDB bookDB = EventBookDB.builder()
            .shopId(bookReq.getShopId())
            .userId(userId)
            .userName(bookReq.getUserName())
            .gender(bookReq.getGender())
            .phone(bookReq.getPhone())
            .birthDate(DateUtils.parseDate(DateUtils.DATE_FORMAT, bookReq.getBirthDate()))
            .bookDate(DateUtils.parseDate(DateUtils.DATE_FORMAT, bookReq.getBookDate()))
            .bookType(bookReq.getBookType())
            .build();
        log.info("bookDB={}", bookDB);
        eventBookMapper.insert(bookDB);
        return BaseResp.of(RespCode.OK);
    }

    public List<UserBookInfo> getBookList(Long userId){
        List<EventBookDB> eventBookDBList = eventBookMapper.selectByUserId(userId);
        if(CollectionUtils.isEmpty(eventBookDBList)){
            return null;
        }
        List<Long> shopIds = eventBookDBList.stream()
            .map(eventBookDB -> eventBookDB.getShopId())
            .collect(Collectors.toList());
        Map<Long, ShopDB> shopMap = getShopByShopIds(shopIds);
        List<UserBookInfo> bookInfoS = eventBookDBList.stream()
            .map(eventBookDB -> UserBookInfo.builder()
                .shopName(null == shopMap.get(eventBookDB.getShopId()) ? "" : shopMap.get(eventBookDB.getShopId()).getShopName())
                .userName(eventBookDB.getUserName())
                .gender(eventBookDB.getGender())
                .phone(eventBookDB.getPhone())
                .birthDate(DateUtils.format(DateUtils.DATE_FORMAT, eventBookDB.getBirthDate()))
                .bookType(eventBookDB.getBookType())
                .bookDate(DateUtils.format(DateUtils.DATE_FORMAT, eventBookDB.getBookDate()))
                .build())
            .collect(Collectors.toList());
        return bookInfoS;
    }

    private Map<Long, ShopDB> getShopByShopIds(List<Long> shopIds){
        if(CollectionUtils.isEmpty(shopIds)){
            return new HashMap<>();
        }
        List<ShopDB> shopDBS = shopMapper.selectByIds(shopIds);
        return shopDBS.stream().collect(Collectors.toMap(ShopDB::getId, Function.identity()));
    }

}
