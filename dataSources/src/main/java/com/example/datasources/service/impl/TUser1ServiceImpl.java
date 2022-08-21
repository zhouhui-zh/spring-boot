package com.example.datasources.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.datasources.dao.TUser1Mapper;
import com.example.datasources.entity.TUser1;
import com.example.datasources.service.ITUser1Service;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hui
 * @since 2022-07-31
 */
@Service
public class TUser1ServiceImpl extends ServiceImpl<TUser1Mapper, TUser1> implements ITUser1Service {

}
