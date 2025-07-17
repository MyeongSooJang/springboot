package com.bs.boot.boottest.rest.model.dto;

import com.bs.boot.boottest.rest.model.entity.DemoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DemoDTO {

    private Integer devNo;
    private String devName;
    private String devAge;
    private String devEmail;
    private String devGender;
    private String[] devLang;

    public DemoEntity convert(){
        return DemoEntity.builder()
                                .devName(devName)
                                .devAge(devAge)
                                .devEmail(devEmail)
                                .devGender(devGender)
                                .devLang(devLang!=null?String.join(",",devLang):null)
                                .build();
    }
}
