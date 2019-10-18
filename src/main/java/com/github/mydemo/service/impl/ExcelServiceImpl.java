package com.github.mydemo.service.impl;

import ch.qos.logback.core.util.FileUtil;
import com.github.mydemo.common.utils.DateUtil;
import com.github.mydemo.common.utils.ExcelUtil;
import com.github.mydemo.dao.UserMapper;
import com.github.mydemo.model.User;
import com.github.mydemo.service.ExcelService;
import com.github.mydemo.service.UserService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

/*
 * @ClassName
 * @Decription TOO
 * @Author HanniOvO
 * @Date 2019/10/17 12:18
 */

@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private UserService userService;

    @Override
    public String exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception{

        List<User> userList = userService.queryAllUsers();
        // 创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建表
        HSSFSheet sheet = workbook.createSheet("用户信息");
        // 创建行
        HSSFRow row = sheet.createRow(0);
        // 创建单元格样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        // 表头
        String[] head = {"ID","姓名", "生日"};
        HSSFCell cell;
        // 设置表头
        for(int iHead=0; iHead<head.length; iHead++) {
            cell = row.createCell(iHead);
            cell.setCellValue(head[iHead]);
            cell.setCellStyle(cellStyle);
        }
        // 设置表格内容
        for(int iBody=0; iBody<userList.size(); iBody++) {
            row = sheet.createRow(iBody+1);
            User u = userList.get(iBody);
            String[] userArray = new String[3];
            userArray[0]=u.getId()+"";
            userArray[1]=u.getUsername();
            userArray[2]=u.getBirthday() + "";
            for(int iArray=0; iArray<userArray.length; iArray++) {
                row.createCell(iArray).setCellValue(userArray[iArray]);
            }
        }

        ExcelUtil.createFile(response, workbook);

        return workbook.toString();
       }




}
