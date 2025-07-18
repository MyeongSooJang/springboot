package com.bs.boot.boottest.rest.controller;

import com.bs.boot.boottest.rest.model.dto.DemoDTO;
import com.bs.boot.boottest.rest.model.service.DemoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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

    @Operation(summary = "회원전체 조회", description = "저장된 회원정보 전체를 조회하는 기능")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "전체 회원 조회를 완료"),
            @ApiResponse(responseCode = "404", description = "회원이 없음")
    })
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
    public ResponseEntity save(@Valid @RequestBody DemoDTO demoDTO, BindingResult bindResult) {
        List<Map<String,String>> responseData = bindResult.getFieldErrors().stream()
                .map(err -> Map.of("field", err.getField(), "message", err.getDefaultMessage()))
                .toList();

        if (bindResult.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(responseData);

            // Entity에 접근 할 수 있는 상황이 아님 -> 유효성 검사에서 통과 못함
        }

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
        try {
            demoService.updateDemo(demoDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("명수 실패");
        }
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build(); // 통신이 성공했음
    }

    @DeleteMapping("/{no}")
    public ResponseEntity delete(@PathVariable Integer no) {
        try {
            demoService.deleteDemo(no);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("명수 실패");
        }
        return ResponseEntity.noContent().build();

    }

    //

}
