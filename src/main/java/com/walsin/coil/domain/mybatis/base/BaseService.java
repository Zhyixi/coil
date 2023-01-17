package com.walsin.coil.domain.mybatis.base;

import java.util.List;

/**
 * @author Jane
 * @date 2022/11/22 上午 10:00
 */
public interface BaseService<T> {
      public int insert(T entity) ;

      public int deleteById(Integer id) ;

      public int updateData(T entity) ;

      public List<T> findAll() ;

      public T selectById(Integer id) ;
}
