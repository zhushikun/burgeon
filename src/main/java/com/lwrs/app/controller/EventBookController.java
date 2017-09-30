package com.lwrs.app.controller;

import com.lwrs.app.domain.dto.resp.BaseResp;
import com.lwrs.app.domain.dto.req.BookReq;
import com.lwrs.app.exception.InvalidParamException;
import com.lwrs.app.service.BookService;
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



    private void handleValidResult(BindingResult bindingResult){
        List<FieldError> errors = bindingResult.getFieldErrors();
        if(CollectionUtils.isEmpty(errors)){
            return;
        }
        String errMsg = errors.stream().map(fieldError -> fieldError.getDefaultMessage()).findFirst().get();
        throw new InvalidParamException(errMsg);
    }

}
