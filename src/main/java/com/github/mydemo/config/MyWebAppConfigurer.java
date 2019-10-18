package com.github.mydemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * @ClassName
 * @Decription TOO
 * @Author HanniOvO
 * @Date 2019/10/17 9:47
 */
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {

    /**上传地址*/
   // @Value("${file.upload.path}")
    private String filePath="E:\\images\\";
    /**显示相对地址*/
    //@Value("${file.upload.path.relative}")
    private String fileRelativePath="/images/**";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(fileRelativePath).
                addResourceLocations("file:/" + filePath);
    }
}
