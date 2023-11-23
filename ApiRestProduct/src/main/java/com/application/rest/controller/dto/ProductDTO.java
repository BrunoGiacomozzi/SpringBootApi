package com.application.rest.controller.dto;

import com.application.rest.entities.Maker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Builder
@RequiredArgsConstructor
@Data
public class ProductDTO {

    private Long id;

    private String title;

    private String description;

    private Double price;

    private String image;

    private Integer stock;

    private Maker maker;

}
