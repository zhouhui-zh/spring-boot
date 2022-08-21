package com.example.datasources.dbs;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhouhui
 * @Description desc
 * @date 2022-07-31 11:00
 */

@Aspect
@Order(-1)//保证在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {//改变数据源

    public void changeDataSource(String targetDataSource) {
        if (StringUtils.isNotEmpty(targetDataSource) && DynamicDataSourceContextHolder.isContainsDataSource(targetDataSource)) {
            DynamicDataSourceContextHolder.setDataSourceType(targetDataSource);
        }else {

        }
    }

    public void clearDataSource(String targetDataSource) {
        DynamicDataSourceContextHolder.clearDataSourceType();

    }

    public List<String> getDataSourceIds() {
        return DynamicDataSourceContextHolder.getDataSourceIds();
    }

}