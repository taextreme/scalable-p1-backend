package app.io.scalable.project.backend.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class PastebinResponse {
    private Map<String, String> data = new HashMap<>();

}
