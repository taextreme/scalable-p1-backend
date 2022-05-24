package app.io.scalable.project.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasteContentNoID {
    String title;
    String content;
    String createdAt;
}