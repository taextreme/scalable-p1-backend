package java.io.scalable.project.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.scalable.project.backend.model.PasteContent;

@Repository
public interface PasteContentRepository extends CrudRepository<PasteContent, Long> {

    PasteContent findById(long id);

    PasteContent findByTitle(String title);
}