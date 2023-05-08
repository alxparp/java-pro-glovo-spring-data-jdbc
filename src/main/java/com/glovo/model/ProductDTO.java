package com.glovo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProductDTO {

    private Integer id;
    private String name;
    private Double cost;

}
