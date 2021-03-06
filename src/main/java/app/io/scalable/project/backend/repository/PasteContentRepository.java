package app.io.scalable.project.backend.repository;

import app.io.scalable.project.backend.model.PasteContent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasteContentRepository extends CrudRepository<PasteContent, Long> {

    PasteContent findById(long id);

    PasteContent findByTitle(String title);

    List<PasteContent> findTop100ByOrderByCreatedAtDesc();


}
