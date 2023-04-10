package com.orejita.games.Controllers.Common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.orejita.games.Services.Interfaces.ICategoryService;

@Controller
@Validated
@RequestMapping("/category")
public class CategoryController {
    
    @Autowired
    private ICategoryService service;

    @Autowire

}
