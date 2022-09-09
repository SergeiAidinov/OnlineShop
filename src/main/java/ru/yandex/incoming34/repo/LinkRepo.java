package ru.yandex.incoming34.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.incoming34.entities.Link;

@Repository
public interface LinkRepo extends CrudRepository<Link, Long> {

    @Transactional
    void deleteAllByProductId(Long id);
}
