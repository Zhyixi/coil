package com.walsin.coil.domain.mybatis.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Jane
 * @date 2022/11/22 上午 10:47
 */

public  class BaseServiceImp<T> implements BaseService<T> {
    @Autowired
    protected BaseMapper<T> mapper;

    @Override
    public int insert(T entity) {
        return mapper.insert(entity);
    }

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteById(id);
    }

    @Override
    public int updateData(T entity) {
        return mapper.updateById(entity);
    }

    @Override
    public List<T> findAll() {
        return mapper.selectByMap(null);
    }

    @Override
    public T selectById(Integer id) {
        return mapper.selectById(id);
    }
}
