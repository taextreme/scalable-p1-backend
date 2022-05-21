package java.io.scalable.project.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.scalable.project.backend.model.PasteContent;
import java.io.scalable.project.backend.model.PastebinRecentsResponse;
import java.io.scalable.project.backend.model.PastebinResponse;
import java.io.scalable.project.backend.service.PastebinSvc;
import java.util.List;
import java.util.Objects;

@RestController
public class PastebinController {


    @Autowired
    private PastebinSvc service;

    @PostMapping("/api/paste")
    public PastebinResponse postApiPaste(String title, String content) {
        PastebinResponse response = new PastebinResponse();
        Long id = service.insertPasteContent(title, content);
        PasteContent pasteContent = service.getPasteContentById(id);
        if (Objects.equals(pasteContent.getTitle(), title) && Objects.equals(pasteContent.getContent(), content)) {
            response.setCode(200);
            response.getData().put("id", String.valueOf(id));
        }
        else {
            response.setCode(400);
            response.getData().put("error", "Pastebin error from POST /api/paste");
        }
        return response;
    }

    @GetMapping("/api/{id}")
    public PastebinResponse getApiId(@PathVariable Long id) {
        PastebinResponse response = new PastebinResponse();
        PasteContent pasteContent = service.getPasteContentById(id);
        if (Objects.nonNull(pasteContent)) {
            response.setCode(200);
            response.getData().put("title", pasteContent.getTitle());
            response.getData().put("content", pasteContent.getContent());
            response.getData().put("createdAt", pasteContent.getCreatedAt());
        }
        else { response.setCode(404); }
        return response;
    }

    @PostMapping("/api/recents")
    public PastebinRecentsResponse postApiRecents() {
        PastebinRecentsResponse response = new PastebinRecentsResponse();
        List<PasteContent> contents = service.get100PasteContentOrderByCreateDate();
        response.setData(contents);
        return response;
    }

}
