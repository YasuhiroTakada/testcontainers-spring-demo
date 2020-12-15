package jp.co.gxp.testcontainerspringdemo.infrastracture.repository;

import java.util.Optional;
import java.util.UUID;
import jp.co.gxp.testcontainerspringdemo.infrastracture.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {

  Optional<Author> findByName(String name);
}
