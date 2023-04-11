package com.orejita.games.Controllers;

import java.lang.reflect.ParameterizedType;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.orejita.games.Services.IService;

public abstract class BaseController<T, D> {

    @Autowired
    protected ModelMapper modelMapper;

    @Autowired
    protected IService<T> service;

    protected D convertToDto(T entity) {
        D dto = modelMapper.map(entity, getDtoClass());
        return dto;
    }

    protected T convertToEntity(D Dto) {
        T entity = modelMapper.map(Dto, getEntityClass());
        return entity;
    }

    @SuppressWarnings("unchecked")
    protected Class<T> getEntityClass() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<T>) type.getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    protected Class<D> getDtoClass() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<D>) type.getActualTypeArguments()[1];
    }
    
}