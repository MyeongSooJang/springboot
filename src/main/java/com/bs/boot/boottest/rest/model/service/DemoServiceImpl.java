package com.bs.boot.boottest.rest.model.service;

import com.bs.boot.boottest.member.model.entity.MemberEntity;
import com.bs.boot.boottest.rest.model.dao.DemoRepository;
import com.bs.boot.boottest.rest.model.dto.DemoDTO;
import com.bs.boot.boottest.rest.model.entity.DemoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@RequiredArgsConstructor
@Service
public class DemoServiceImpl implements DemoService {

    private final DemoRepository repository;
    private final DemoRepository demoRepository;

//    @PersistenceContext
//    private EntityManager em;
    @Transactional
    @Override
    @CacheEvict(value = "demos", allEntries = true)
    public boolean deleteDemo(Integer no) {
//        return repository.deleteAllById(DemoEntity<no>);
        try {
            demoRepository.deleteById(no);
        }catch (Exception e){
            throw new RuntimeException("삭제 실패");
        }
        return true;
    }
    @Transactional
    @Override
    @CacheEvict(value = "demos", allEntries = true)
    public boolean updateDemo(DemoDTO demoDTO) {
        // return insertDemo(Demo)
        DemoEntity e = repository.findById(demoDTO.getDevNo()).orElseThrow();

        e.setDevAge(demoDTO.getDevAge());
        e.setDevName(demoDTO.getDevName());
        return true;
    }
    @Transactional
    @Override
    @CacheEvict(value = "demos", allEntries = true) // 캐쉬를 삭제하는 어노테이션(서버 캐쉬)
    public boolean insertDemo(DemoDTO demoDTO) {
        try{
            repository.save(demoDTO.convert());
            // save
        }catch (Exception e){
            throw new RuntimeException("저장실패");
        }
        // rollback -> 예외가 발생한걸 감지했을때 rollback
        // 단일값일때 가능하다.
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    @Cacheable(value = "demos",key = "'demos'")
    public List<DemoDTO> findByAll() {
        System.out.println("demos 서비스를 실행");
        new Thread(()->{
            try{
                Thread.sleep(5000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
        return repository.findAll().stream().map(DemoEntity::convert).toList();
        // map이라는 것은 새로운 스트림으로 만들어주는것
    }
//    @Transactional(readOnly = true)
    @Override
    public DemoDTO findByNo(Integer no) {
        return repository.findById(no).orElseThrow().convert();
        // 원하는 Exception을 발생시킬수 있다.(orElseThrow) ->  ExceptionHandler
    }
}
