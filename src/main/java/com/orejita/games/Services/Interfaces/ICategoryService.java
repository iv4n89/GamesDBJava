package com.orejita.games.Services.Interfaces;

import java.util.List;

import com.orejita.games.Entities.Common.Category;
import com.orejita.games.Entities.Consoles.Console;
import com.orejita.games.Services.IService;

public interface ICategoryService extends IService<Category> {
    
    List<Category> getAllCategories();
    Category getOneCategory(long id);
    Category createCategory(Category category);
    Category updateCategory(long id, Category category);
    Category addConsoleToCategory(long id, long consoleId);
    Category addConsoleToCategory(long id, Console console);
    Category deleteConsoleFromCategory(long id, long consoleId);
    void deleteCategory(long id);

}
