package com.orejita.games.Util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class ModelMapperUtil<T, U> {

    private Class<T> modelClass;
    private Class<U> dtoClass;

    public ModelMapperUtil(Class<T> modelClass, Class<U> dtoClass) {
        this.modelClass = modelClass;
        this.dtoClass = dtoClass;
    }

    public Class<T> getModelClass() {
        return this.modelClass;
    }

    public Class<U> getDtoClass() {
        return this.dtoClass;
    }

    @Autowired
    private ModelMapper modelMapper;

    public U convertToDto(T model) {
        U dto = this.modelMapper.map(model, dtoClass);
        return dto;
    }

    public T convertToEntity(U dto) {
        T entity = this.modelMapper.map(dto, modelClass);
        return entity;
    }
    
}
