package com.denghanbo.resume.repository;


import com.denghanbo.resume.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository  extends JpaRepository<Page, Integer> {

    List<Page> findByType(Integer type);

}
