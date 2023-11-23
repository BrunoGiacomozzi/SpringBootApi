package com.application.rest.controller.dto;

import com.application.rest.entities.Product;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Builder
@RequiredArgsConstructor
@Data
public class MakerDTO {

    private Long id;

    private String name;

    private List<Product> productsList;

}
