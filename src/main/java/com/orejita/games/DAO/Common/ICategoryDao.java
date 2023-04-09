package com.orejita.games.DAO.Common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orejita.games.Entities.Common.Category;

@Repository
public interface ICategoryDao extends JpaRepository<Category, Long> {
    
}
