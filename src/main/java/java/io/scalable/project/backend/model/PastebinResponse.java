package java.io.scalable.project.backend.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class PastebinResponse {
    private Integer code = 200;
    private Map<String, String> data = new HashMap<>();

}
