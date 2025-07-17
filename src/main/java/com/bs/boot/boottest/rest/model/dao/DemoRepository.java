package com.bs.boot.boottest.rest.model.dao;

import com.bs.boot.boottest.rest.model.entity.DemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository extends JpaRepository<DemoEntity,Integer> {


}
