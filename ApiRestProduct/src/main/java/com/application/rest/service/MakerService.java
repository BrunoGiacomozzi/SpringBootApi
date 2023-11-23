package com.application.rest.service;

import com.application.rest.entities.Maker;
import com.application.rest.repository.MakerRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MakerService {

    private final MakerRepository mr;

    public Optional<Maker> findById(Long id) {
        return mr.findById(id);
    }

    public List<Maker> findAll() {
        return (List<Maker>) mr.findAll();
    }

}
