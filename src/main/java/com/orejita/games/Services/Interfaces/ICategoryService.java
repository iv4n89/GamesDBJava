package com.orejita.games.Services.Interfaces;

import java.util.List;

import com.orejita.games.Entities.Common.Category;
import com.orejita.games.Entities.Consoles.Console;

public interface ICategoryService {
    
    List<Category> getAllCategories();
    Category getOneCategory(long id);
    Category createCategory(Category category);
    Category updateCategory(long id, Category category);
    Category addConsoleToCategory(long id, long consoleId);
    Category addConsoleToCategory(long id, Console console);
    void deleteCategory(long id);

}
