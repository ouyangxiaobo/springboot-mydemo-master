package com.github.mydemo.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ExcelService {

    String exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
