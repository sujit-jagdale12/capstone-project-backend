package com.ani.ems.util;

import org.springframework.beans.BeanUtils;

public class DynamicMapper {

    public <T, U> U convertor(T entity, U dto) {
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
