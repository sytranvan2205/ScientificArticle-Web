package com.iuh.sientifirticle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iuh.sientifirticle.entity.ScientificArticle;

@Repository
public interface ScientificArticleRepository extends JpaRepository<ScientificArticle, Long>{

}
