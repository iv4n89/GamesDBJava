package com.orejita.games.Services.Common;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orejita.games.DAO.Common.ICategoryDao;
import com.orejita.games.DAO.Consoles.IConsoleDao;
import com.orejita.games.Entities.Common.Category;
import com.orejita.games.Entities.Consoles.Console;
import com.orejita.games.Exceptions.Console.ConsoleNotFoundException;
import com.orejita.games.Services.Interfaces.ICategoryService;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryDao dao;

    @Autowired
    private IConsoleDao consoleDao;

    @Override
    public List<Category> getAllCategories() {
        return dao.findAll();
    }

    @Override
    public Category getOneCategory(long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public Category createCategory(Category category) {
        return dao.save(category);
    }

    @Override
    public Category updateCategory(long id, Category category) {
        Category _category = this.getOneCategory(id);

        if (_category == null) {
            return null;
        }

        if (category.getName() != null) {
            _category.setName(category.getName());
        }
        if (category.getConsoles() != null) {
            _category.setConsoles(category.getConsoles());
        }

        return dao.save(_category);
    }

    @Override
    public Category addConsoleToCategory(long id, long consoleId) {
        Category category = this.getOneCategory(id);
        Console console = consoleDao.findById(consoleId).orElseThrow(() -> new ConsoleNotFoundException("Console not found, id " + consoleId));
        Set<Console> consoles = category.getConsoles();
        consoles.add(console);
        category.setConsoles(consoles);
        return dao.save(category);
    }

    @Override
    public Category addConsoleToCategory(long id, Console console) {
        Category category = this.getOneCategory(id);
        Set<Console> consoles = category.getConsoles();
        consoles.add(console);
        category.setConsoles(consoles);
        return dao.save(category);
    }

    @Override
    public Category deleteConsoleFromCategory(long id, long consoleId) {
        Category category = this.getOneCategory(id);
        Set<Console> consoles = category.getConsoles().stream().filter(console -> console.getId() != consoleId).collect(Collectors.toSet());
        category.setConsoles(consoles);
        return this.dao.save(category);
    }

    @Override
    public void deleteCategory(long id) {
        dao.deleteById(id);
    }


    
}
