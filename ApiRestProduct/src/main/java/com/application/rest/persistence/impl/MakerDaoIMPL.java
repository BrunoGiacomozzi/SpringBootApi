package com.application.rest.persistence.impl;

import com.application.rest.entities.Maker;
import com.application.rest.persistence.IMakerDAO;
import com.application.rest.repository.MakerRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MakerDaoIMPL implements IMakerDAO {
    
    private final MakerRepository mr;

    @Override
    public Optional<Maker> findById(Long id) {
        return mr.findById(id);
    }

    @Override
    public List<Maker> findAll() {
        return (List<Maker>) mr.findAll();
    }

    @Override
    public void save(Maker maker) {
        mr.save(maker);
    }

    @Override
    public void deleteById(Long id) {
        mr.deleteById(id);
    }

}
