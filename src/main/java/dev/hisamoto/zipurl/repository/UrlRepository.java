package dev.hisamoto.zipurl.repository;

import dev.hisamoto.zipurl.entitites.UrlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends MongoRepository <UrlEntity, String> {
}
