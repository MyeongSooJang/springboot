package com.bs.boot.boottest.rest.controller;

import com.bs.boot.boottest.rest.model.dto.DemoDTO;
import com.bs.boot.boottest.rest.model.service.DemoService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/*
    RestAPI 만들기

*/
@RequiredArgsConstructor
@RestController
// @ResponseBody + @Controller(Mvc모듈 안에서 요청이 들어오면 mapping method를 가지고 있는것)
@RequestMapping("/demos")
public class DemoController {

    private final DemoService demoService;

    @GetMapping
    public ResponseEntity<List<DemoDTO>> findByAll() {
        return ResponseEntity.ok().body(demoService.findByAll());
//                .cacheControl(CacheControl.maxAge(2, TimeUnit.MINUTES).cachePrivate())
//                .body(demoService.findByAll());
    }

    @GetMapping("/{no}")
    // 여기 다시 공부하기
    public DemoDTO findByNo(@PathVariable Integer no) {
        return demoService.findByNo(no);
    }
    // 저장 수정 삭제

    @PostMapping
    public ResponseEntity save(@RequestBody DemoDTO demoDTO) {
        try {
            demoService.insertDemo(demoDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("명수 실패");
        }
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build(); //-->????
        // return ResponseEntity.ok();
    }

    @PutMapping
    public ResponseEntity update(@RequestBody DemoDTO demoDTO) {
        try{
            demoService.updateDemo(demoDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("명수 실패");
        }
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build(); // 통신이 성공했음
    }

    @DeleteMapping("/{no}")
    public ResponseEntity delete(@PathVariable Integer no) {
        try{
            demoService.deleteDemo(no);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("명수 실패");
        }
        return ResponseEntity.noContent().build();

    }



}
