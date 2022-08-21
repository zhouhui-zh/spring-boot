package com.example.datasources.interceptor;

/**
 * 拦截器
 *
 * @author zhouhui
 * @Description desc
 * @date 2022-07-31 22:51
 */


import com.example.datasources.dbs.DynamicDataSourceAspect;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
public class Interceptor implements HandlerInterceptor {

    private DynamicDataSourceAspect dynamicDataSourceAspect;

    public Interceptor(DynamicDataSourceAspect dynamicDataSourceAspect) {
        this.dynamicDataSourceAspect = dynamicDataSourceAspect;
    }

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String dbs = request.getParameter("dbs");
        log.info("dbs=[{}]", dbs);
        if (StringUtils.isBlank(dbs)) {
            dynamicDataSourceAspect.changeDataSource(dbs);
        } else {
            dynamicDataSourceAspect.changeDataSource(dbs);
        }
        return true;
    }


}
