package com.github.mydemo.controller;

import com.github.mydemo.common.utils.ExcelUtil;
import com.github.mydemo.common.utils.ExpAndImpUtil;
import com.github.mydemo.model.User;
import com.github.mydemo.service.ExcelService;
import com.github.mydemo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/*
 * @ClassName
 * @Decription TOO
 * @Author HanniOvO
 * @Date 2019/10/17 12:21
 */
@RestController
@RequestMapping("/excel")
@Slf4j
public class ExcelController {

    @Autowired
    private UserService userService;


    @Autowired
    private ExcelService excelService;

    @ApiOperation(value = "excel文件导出",notes = "excel文件导出")
    @RequestMapping("/exportExcelUsers")
    public String exportExcelUsers(HttpServletRequest request, HttpServletResponse response){
        try {
           String fileName= excelService.exportExcel(request,response);
            log.info("导出成功.....");
            return fileName;

        }catch (Exception e){
            e.printStackTrace();
            log.error("导出失败，e="+e);
        }
          return null;
    }

    @ApiOperation(value = "excel文件导出",notes = "excel文件导出")
    @RequestMapping("/export")
    public void export(HttpServletResponse response){
        try {
            List<User> userList=userService.queryAllUsers();
            ExpAndImpUtil.exportExcel(userList,"用户信息","",User.class,"海贼王"+new Date().toString()+".xls",response);
            log.info("导出成功.....");

        }catch (Exception e){
            e.printStackTrace();
            log.error("导出失败，e="+e);
        }
        return ;
    }


}
