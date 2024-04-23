package models.api;

import lombok.Data;

@Data
public class PostResponse {

    private String title;
    private String body;
    private int userId;
    private int id;
}
