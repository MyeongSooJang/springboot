package com.bs.boot.boottest.rest.model.service;

import com.bs.boot.boottest.rest.model.dto.DemoDTO;

import java.util.List;

public interface DemoService {

    DemoDTO findByNo(Integer no);
    List<DemoDTO> findByAll();

    boolean insertDemo(DemoDTO demoDTO);

    boolean updateDemo(DemoDTO demoDTO);

    boolean deleteDemo(Integer no);
}
