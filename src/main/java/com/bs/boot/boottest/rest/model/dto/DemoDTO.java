package com.bs.boot.boottest.rest.model.dto;

import com.bs.boot.boottest.rest.model.entity.DemoEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
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
    @Size(min=2, message="이름은 두글자 이상")
    private String devName;
    @Min(value=8 ,message="개발자는 8세 이상")
    private String devAge;
    @Email(message = "이메일 형식이 맞지 않습니다.")
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
