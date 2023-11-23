package com.application.rest.controller;

import com.application.rest.controller.dto.ProductDTO;
import com.application.rest.entities.Product;
import com.application.rest.service.IProductService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/product")// http://localhost:8080/product
public class ProductController {

    private final IProductService ps;

    @GetMapping("/find/{id}") // http://localhost:8080/maker/find/id
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Product> productOptional = ps.findByid(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            ProductDTO productDto = ProductDTO.builder()
                    .id(product.getId())
                    .title(product.getTitle())
                    .price(product.getPrice())
                    .description(product.getDescription())
                    .maker(product.getMaker())
                    .stock(product.getStock())
                    .image(product.getImage())
                    .build();

            return ResponseEntity.ok(productDto);
        }

        return ResponseEntity.notFound().build();

    }

    @GetMapping("/findall")// http://localhost:8080/findall
    public ResponseEntity<?> findAll() {

        List<ProductDTO> productList = ps.findAll()
                .stream()
                .map(product -> ProductDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .description(product.getDescription())
                .stock(product.getStock())
                .image(product.getImage())
                .maker(product.getMaker())
                .build())
                .toList();

        if (!productList.isEmpty()) {

            return ResponseEntity.ok(productList);
        }

        return ResponseEntity.notFound().build();

    }

    @PostMapping("/save") // http://localhost:8080/product/save
    public ResponseEntity<?> save(@RequestBody ProductDTO productrDto) throws URISyntaxException {

        if (productrDto.getTitle() == null || productrDto.getTitle().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        if (productrDto.getDescription() == null || productrDto.getDescription().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        ps.saved(Product.builder()
                .title(productrDto.getTitle())
                .price(productrDto.getPrice())
                .description(productrDto.getDescription())
                .image(productrDto.getImage())
                .maker(productrDto.getMaker())
                .stock(productrDto.getStock())
                .build());

        return (ResponseEntity<?>) ResponseEntity.created(new URI("/product/save")).build();

    }

    @PostMapping("/savewmaker") // http://localhost:8080/product/savewmaker
    public ResponseEntity<?> saveWithoutMaker(@RequestBody ProductDTO productrDto) throws URISyntaxException {

        if (productrDto.getTitle() == null || productrDto.getTitle().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        if (productrDto.getDescription() == null || productrDto.getDescription().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        ps.saved(Product.builder()
                .title(productrDto.getTitle())
                .price(productrDto.getPrice())
                .description(productrDto.getDescription())
                .image(productrDto.getImage())
                .stock(productrDto.getStock())
                .build());

        return (ResponseEntity<?>) ResponseEntity.created(new URI("/product/savewmaker")).build();

    }

    @DeleteMapping("/delete/{id}") //http://localhost:8080/product/delete/id
    public ResponseEntity<?> deleteById(@PathVariable Long id) {

        if (id != null) {
            ps.deleteById(id);
            return ResponseEntity.ok("The register has been deleted");
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/find/{title}") //http://localhost:8080/product/find/title
    public ResponseEntity<?> findByTile(@PathVariable String title) {

        String lowerCaseTitle = title.toLowerCase();

        if (lowerCaseTitle != null && !lowerCaseTitle.isBlank()) {

            List<ProductDTO> products = ps.findByTitle(lowerCaseTitle)
                    .stream()
                    .map(product -> ProductDTO.builder()
                    .id(product.getId())
                    .title(product.getTitle())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .image(product.getImage())
                    .stock(product.getStock())
                    .build())
                    .toList();

            return ResponseEntity.ok(products);
        }

        return ResponseEntity.badRequest().build();

    }

    @GetMapping("/findbyprice/{min}and{max}")//http://localhost:8080/product/findbyprice/ min and max
    public ResponseEntity<?> findByPriceInRange(@PathVariable Double min, @PathVariable Double max) {

        if (min == null || max == null) {
            return ResponseEntity.badRequest().body("min and max prices cannot be null");
        }

        if (max > min) {
            List<ProductDTO> products = ps.findByPriceInRange(min, max)
                    .stream()
                    .map(product -> ProductDTO.builder()
                    .id(product.getId())
                    .title(product.getTitle())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .image(product.getImage())
                    .stock(product.getStock())
                    .build())
                    .toList();

            return ResponseEntity.ok(products);

        }
        return ResponseEntity.badRequest().body("The maximum price value cannot be less than the min");
    }

    @GetMapping("/findavailable")//http://localhost:8080/product/findavailable
    public ResponseEntity<?> findByStockAvailable() {

        List<ProductDTO> productWithStock = ps.finByStock()
                .stream()
                .map(product -> ProductDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .image(product.getImage())
                .stock(product.getStock())
                .build())
                .toList();

        return ResponseEntity.ok(productWithStock);

    }

}
