package com.library.controller;

import com.Sip.samples.Sample;
import com.library.entity.Reader;
import com.library.service.ReaderService;
import com.library.util.Constant;
import com.library.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by 王彦鑫 on 2018/10/16
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private ReaderService readerService;

    /**
     * 登录
     * @param username
     * @param password
     * @param map
     * @return
     */
    @RequestMapping("/login")
    public String dologin(String username, String password, Map<String, Object> map) {
        String flag = readerService.loginFlag(username,password);
        Reader reader = readerService.queryByUsername(username);
        SessionUtils.setSessionAttribute(Constant.CURRENT_USER,reader);
        map.put("flag",flag);
        if("".equals(flag)){
            return "projectPages/BookDisplay";
        }else{
            return "vue";
        }
    }

}
