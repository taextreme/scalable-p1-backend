package java.io.scalable.project.backend.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class PasteContent {
        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        Long id;

        String title;

        String content;

        String createdAt;
}
