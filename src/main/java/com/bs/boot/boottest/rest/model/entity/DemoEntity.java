package com.bs.boot.boottest.rest.model.entity;

import com.bs.boot.boottest.rest.model.dto.DemoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "DEMO")
@SequenceGenerator(sequenceName = "SEQ_DEMO_NO",name = "seq_demo_no",initialValue = 1,allocationSize = 1)// 대소문자 구분안함 SEQUENCE NAME
public class DemoEntity {
    @Id
    @GeneratedValue(generator = "seq_demo_no", strategy = GenerationType.SEQUENCE)
    @Column(name = "DEVNO")
    private Integer devNo;
    @Column(name = "DEVNAME")
    private String devName;
    @Column(name = "DEVAGE")
    private String devAge;
    @Column(name = "DEVEMAIL")
    private String devEmail;
    @Column(name = "DEVGENDER")
    private String devGender;
    @Column(name = "DEVLANG")
    private String devLang;

    public DemoDTO convert(){
        DemoDTO dto = DemoDTO.builder()
                .devNo(devNo)
                .devName(devName)
                .devAge(devAge)
                .devEmail(devEmail)
                .devGender(devGender)
                .devLang(devLang!=null?devLang.split(","):null)
                .build();
        return dto;
    }

}
