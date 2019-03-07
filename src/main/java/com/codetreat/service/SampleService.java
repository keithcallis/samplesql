package com.codetreat.service;

import com.codetreat.entity.SampleEntity;
import com.codetreat.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class SampleService {

    final
    SampleRepository sampleRepo;

    @Autowired()
    public SampleService(SampleRepository sampleRepo) {
        this.sampleRepo = sampleRepo;
    }

    public SampleEntity createSample(SampleEntity sampleEntity) {
        return sampleRepo.save(sampleEntity);
    }

    public SampleEntity updateSample(Long Id, SampleEntity sampleEntity) {
        SampleEntity updatedSample;
        Optional<SampleEntity> searchEntity = sampleRepo.findById(Id);
        if (searchEntity.isPresent()) {
            SampleEntity sample = searchEntity.get();
            sample.setAge(sampleEntity.getAge());
            sample.setName(sampleEntity.getName());
            updatedSample = sampleRepo.save(sample);
        } else {
            throw new EntityNotFoundException();
        }
        return updatedSample;
    }

    public ResponseEntity<Object> deleteSample(Long Id) {
        Optional<SampleEntity> sampleEntity = sampleRepo.findById(Id);
        if (sampleEntity.isPresent()) {
            SampleEntity sample = sampleEntity.get();
            sampleRepo.delete(sample);
        } else {
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok().build();
    }

    public List<SampleEntity> getAll() {
        return sampleRepo.findAll();
    }
}
