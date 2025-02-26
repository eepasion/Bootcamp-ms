package com.pragma.bootcamp.infrastructure.adapters.persistenceadapter.repository;
import com.pragma.bootcamp.infrastructure.adapters.persistenceadapter.entity.BootcampEntity;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BootcampRepository extends ReactiveMongoRepository<BootcampEntity, String> {


    @Aggregation(pipeline = {
            "{ $addFields: { numCapacidades: { $size: '$capacidades' } } }",
            "{ $sort: { numCapacidades: ?0 } }",
            "{ $skip: ?1 }",
            "{ $limit: ?2 }"
    })
    Flux<BootcampEntity> findAllSortedByCapabilities(int sortDirection, int skip, int limit);

    @Aggregation(pipeline = {
            "{ $addFields: { lowerName: { $toLower: '$nombre'} } }",
            "{ $sort: { lowerName: ?0 } }",
            "{ $skip: ?1 }",
            "{ $limit: ?2 }"
    })
    Flux<BootcampEntity> findAllSortedByName(int sortDirection, int skip, int limit);

    @Aggregation(pipeline = {
            "{ $skip: ?0 }",
            "{ $limit: ?1 }"
    })
    Flux<BootcampEntity> findAllWithPagination(int skip, int limit);
}
