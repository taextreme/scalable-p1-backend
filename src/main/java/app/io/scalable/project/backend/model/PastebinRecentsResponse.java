package app.io.scalable.project.backend.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PastebinRecentsResponse {
    private List<PasteContentNoID> data = new ArrayList<>();
}

