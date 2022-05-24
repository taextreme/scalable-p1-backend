package app.io.scalable.project.backend.controller;

import app.io.scalable.project.backend.model.*;
import app.io.scalable.project.backend.service.PastebinSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@ResponseBody
public class PastebinController {


    @Autowired
    private PastebinSvc service;

    @PostMapping("/api/paste")
    public PastebinResponse postApiPaste(@RequestBody PostApiPasteRequest request) {
        Long id = service.insertPasteContent(request.getTitle(), request.getContent());
        PasteContent pasteContent = service.getPasteContentById(id);
        if (Objects.equals(pasteContent.getTitle(), request.getTitle()) && Objects.equals(pasteContent.getContent(), request.getContent())) {
            return successPostApiPasteRsponse(pasteContent);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pastebin error from POST /api/paste");
        }
    }

    @ResponseStatus(code = HttpStatus.OK)
    private PastebinResponse successPostApiPasteRsponse(PasteContent pasteContent) {
        PastebinResponse response = new PastebinResponse();
        response.getData().put("id", String.valueOf(pasteContent.getId()));
        return response;
    }

    @GetMapping("/api/{id}")
    public PastebinResponse getApiId(@PathVariable Long id) {
        PasteContent pasteContent = service.getPasteContentById(id);
        if (Objects.nonNull(pasteContent)) {
            return successGetApiId(pasteContent);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(code = HttpStatus.OK)
    private PastebinResponse successGetApiId(PasteContent pasteContent) {
        PastebinResponse response = new PastebinResponse();
        response.getData().put("title", pasteContent.getTitle());
        response.getData().put("content", pasteContent.getContent());
        response.getData().put("createdAt", pasteContent.getCreatedAt());
        return response;
    }

    @PostMapping("/api/recents")
    public PastebinRecentsResponse postApiRecents() {
        List<PasteContent> contents = service.get100PasteContentOrderByCreateDate();
        if (contents.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return successPostApiRecents(contents);
        }
    }

    private PastebinRecentsResponse successPostApiRecents(List<PasteContent> contents) {
        PastebinRecentsResponse response = new PastebinRecentsResponse();
        contents.forEach(pasteContent -> response.getData().add(new PasteContentNoID(pasteContent.getTitle(), pasteContent.getContent(), pasteContent.getCreatedAt())));
        return response;
    }

}
