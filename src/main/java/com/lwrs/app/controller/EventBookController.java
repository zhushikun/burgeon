package com.lwrs.app.controller;

import com.lwrs.app.domain.dto.UserBookInfo;
import com.lwrs.app.domain.dto.resp.BaseResp;
import com.lwrs.app.domain.dto.req.BookReq;
import com.lwrs.app.domain.dto.resp.UserBookListResp;
import com.lwrs.app.enums.RespCode;
import com.lwrs.app.exception.InvalidParamException;
import com.lwrs.app.service.BookService;
import com.lwrs.app.utils.UserLoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/event")
public class EventBookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/ajax/book")
    @ResponseBody
    public BaseResp book(@Valid @RequestBody BookReq bookReq, BindingResult bindingResult) throws ParseException {
        handleValidResult(bindingResult);
        return bookService.bookAccept(bookReq);
    }

    @GetMapping("/ajax/book-list")
    @ResponseBody
    public UserBookListResp myBookList(){
        Long userId = UserLoginContext.getUserId();
        List<UserBookInfo> myBookList = bookService.getBookList(userId);
        UserBookListResp resp = UserBookListResp.builder()
            .userBookInfoList(myBookList)
            .build();
        resp.setRespCode(RespCode.OK);
        return resp;
    }


    private void handleValidResult(BindingResult bindingResult){
        List<FieldError> errors = bindingResult.getFieldErrors();
        if(CollectionUtils.isEmpty(errors)){
            return;
        }
        String errMsg = errors.stream().map(fieldError -> fieldError.getDefaultMessage()).findFirst().get();
        throw new InvalidParamException(errMsg);
    }

}
