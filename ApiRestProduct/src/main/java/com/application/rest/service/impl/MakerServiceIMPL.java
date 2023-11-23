package com.application.rest.service.impl;

import com.application.rest.entities.Maker;
import com.application.rest.persistence.IMakerDAO;
import com.application.rest.service.IMakerService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MakerServiceIMPL implements IMakerService {
    
    private final IMakerDAO md;
    
    @Override
    public Optional<Maker> findById(Long id) {
        return md.findById(id);
    }
    
    @Override
    public List<Maker> findAll() {
        return md.findAll();
    }
    
    @Override
    public void save(Maker maker) {
        md.save(maker);
    }
    
    @Override
    public void deleteById(Long id) {
        md.deleteById(id);
    }
    
}
