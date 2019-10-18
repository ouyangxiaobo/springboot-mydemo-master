package com.github.mydemo.model.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/*
 * @ClassName
 * @Decription TOO
 * @Author HanniOvO
 * @Date 2019/10/16 14:53
 */
@Data
public class UserExt {

    private String username;

    private String password;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    private String startTime;
    private String  endTime;
}
