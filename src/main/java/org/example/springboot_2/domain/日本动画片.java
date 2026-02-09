package org.example.springboot_2.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class 日本动画片 {
    private Long id;
//    @JsonProperty('name')
    private String name;


}
