package java.io.scalable.project.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.scalable.project.backend.model.PasteContent;
import java.io.scalable.project.backend.model.PastebinResponse;
import java.io.scalable.project.backend.repository.PasteContentRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class PastebinSvc {

    @Autowired
    PasteContentRepository repository;

    public Long insertPasteContent(String title, String content) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        PasteContent pasteContent = new PasteContent();
        pasteContent.setTitle(title);
        pasteContent.setContent(content);
        pasteContent.setCreatedAt(dtf.format(now));
        return repository.save(pasteContent).getId();
    }

    public PasteContent getPasteContent(Long id) {
        return repository.findById(id.longValue());
    }
}
