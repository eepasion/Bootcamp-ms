package com.pragma.bootcamp.infrastructure.adapters.persistenceadapter.repository;
import com.pragma.bootcamp.infrastructure.adapters.persistenceadapter.entity.BootcampEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BootcampRepository extends ReactiveMongoRepository<BootcampEntity, String> {
}
