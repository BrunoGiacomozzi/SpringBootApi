package com.application.rest.controller;

import com.application.rest.controller.dto.MakerDTO;
import com.application.rest.entities.Maker;
import com.application.rest.service.IMakerService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/maker")// http://localhost:8080/maker
public class MakerController {

    private final IMakerService ms;

    @GetMapping("/find/{id}") // http://localhost:8080/maker/find/id
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Maker> makerOptional = ms.findById(id);

        if (makerOptional.isPresent()) {
            Maker maker = makerOptional.get();

            MakerDTO makerDto = MakerDTO.builder()
                    .id(maker.getId())
                    .name(maker.getName())
                    .productsList(maker.getProductsList())
                    .build();

            return ResponseEntity.ok(makerDto);
        }

        return ResponseEntity.notFound().build();

    }

    @GetMapping("/findall")// http://localhost:8080/findall
    public ResponseEntity<?> findAll() {

        List<MakerDTO> makerList = ms.findAll()
                .stream()
                .map(maker -> MakerDTO.builder()
                .id(maker.getId())
                .name(maker.getName())
                .productsList(maker.getProductsList())
                .build())
                .toList();

        if (!makerList.isEmpty()) {

            return ResponseEntity.ok(makerList);
        }

        return ResponseEntity.notFound().build();

    }

    @PostMapping("/save") // http://localhost:8080/maker/save
    public ResponseEntity<?> save(MakerDTO makerDto) throws URISyntaxException {

        if (makerDto.getName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        if (makerDto.getProductsList().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        ms.save(Maker.builder().name(makerDto.getName()).build());

        return (ResponseEntity<?>) ResponseEntity.created(new URI("/maker/save")).build();

    }

    @PostMapping("/savewithproducts") // http://localhost:8080/maker/save
    public ResponseEntity<?> savewithProduct(MakerDTO makerDto) throws URISyntaxException {

        if (makerDto.getName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        if (makerDto.getProductsList().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        ms.save(Maker.builder().name(makerDto.getName())
                .productsList(makerDto.getProductsList())
                .build());

        return (ResponseEntity<?>) ResponseEntity.created(new URI("/maker/savewithproducts")).build();

    }

    @PutMapping("/update/{id}") // http://localhost:8080/maker/update/id
    public ResponseEntity<?> updateMaker(@PathVariable Long id, @RequestBody MakerDTO makerDto) {

        Optional<Maker> makerId = ms.findById(id);

        if (makerId.isPresent()) {
            Maker maker = makerId.get();

            maker.setName(makerDto.getName());
            maker.setProductsList(makerDto.getProductsList());

            ms.save(maker);

            return ResponseEntity.ok("Register has been updtaed!");
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}") // http://localhost:8080/maker/delete/id
    public ResponseEntity<?> deleteById(@PathVariable Long id) {

        if (id != null) {

            ms.deleteById(id);

            return ResponseEntity.ok("The register has been erased!");

        }

        return ResponseEntity.notFound().build();

    }

}
