package com.lwrs.app.service;

import com.lwrs.app.db.entity.EventBookDB;
import com.lwrs.app.db.mapper.EventBookMapper;
import com.lwrs.app.domain.dto.BaseResp;
import com.lwrs.app.domain.dto.req.BookReq;
import com.lwrs.app.enums.Gender;
import com.lwrs.app.enums.RespCode;
import com.lwrs.app.utils.UserLoginContext;
import com.lwrs.app.utils.date.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Slf4j
@Service
public class BookService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private EventBookMapper eventBookMapper;

    public BaseResp bookAccept(BookReq bookReq) throws ParseException {
        Long userId = UserLoginContext.getUserId();
        EventBookDB bookDB = EventBookDB.builder()
            .shopId(bookReq.getShopId())
            .userId(userId)
            .userName(bookReq.getUserName())
            .gender(Gender.valueOf(bookReq.getGender()).name())
            .phone(bookReq.getPhone())
            .birthDate(DateUtils.parseDate(DateUtils.DATE_FORMAT, bookReq.getBirthDate()))
            .bookDate(DateUtils.parseDate(DateUtils.DATE_FORMAT, bookReq.getBookDate()))
            .bookType(bookReq.getBookType())
            .build();
        log.info("bookDB={}", bookDB);
        eventBookMapper.insert(bookDB);
        return BaseResp.of(RespCode.OK);
    }


}
