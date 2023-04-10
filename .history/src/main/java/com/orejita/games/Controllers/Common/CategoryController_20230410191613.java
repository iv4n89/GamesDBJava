package com.orejita.games.Controllers.Common;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.orejita.games.DTO.Common.CategoryDto;
import com.orejita.games.DTO.Requests.OnCreate;
import com.orejita.games.Entities.Common.Category;
import com.orejita.games.Services.Interfaces.ICategoryService;

@Controller
@Validated
@RequestMapping("/category")
public class CategoryController {
    
    @Autowired
    private ICategoryService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseBody
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = this.service.getAllCategories();
        return categories.stream()
                .map(this::convertToDto)
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CategoryDto getOneCategory(@PathVariable("id") long id) {
        Category category = this.service.getOneCategory(id);
        return convertToDto(category);
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto createCategory(@Validated(OnCreate.class) @RequestBody CategoryDto category) {
        Category entity = convertToEntity(category);
        Category _category = this.service.createCategory(entity);
        return convertToDto(_category);
    }

    @PutMapping("/{id}")
    @


    private CategoryDto convertToDto(Category category) {
        CategoryDto dto = this.modelMapper.map(category, CategoryDto.class);
        return dto;
    }

    private Category convertToEntity(CategoryDto dto) {
        Category category = this.modelMapper.map(dto, Category.class);
        return category;
    }

}
