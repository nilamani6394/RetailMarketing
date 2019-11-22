package com.cms.app.main.Repositatory;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.app.main.model.Page;


public interface PageRepository extends JpaRepository<Page, Integer>{

	Page findBySlug(String slug);
	Page findBySlugAndIdNot(String slug,int id);
	List<Page> findAllByOrderSortingAsc();
}
