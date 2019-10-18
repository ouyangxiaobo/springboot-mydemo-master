package com.github.mydemo.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 2895782870082326368L;

    @Excel(name = "ID", orderNum = "0")
    private Integer id;

   // @Excel(name = "性别", replace = {"男_1", "女_2"}, orderNum = "1")
    @Excel(name = "名字",  orderNum = "1")
    private String username;
    @Excel(name = "密码",  orderNum = "2")
    private String password;
    @Excel(name = "生日", exportFormat = "yyyy-MM-dd", orderNum = "3")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 此处注意必须要有空构造函数，否则调用ExcelImportUtil.importExcel(file, User.class, importParams)时会报错“对象创建错误”
     */
    public User() {
    }
}