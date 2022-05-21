package app.io.scalable.project.backend.model;

import lombok.Data;

import java.util.List;

@Data
public class PastebinRecentsResponse {
    private Integer code = 200;
    private List<PasteContent> data;
}
