package models.database;

import lombok.Data;

@Data
public class Author {

    private Long id;
    private String name;
    private String login;
    private String email;
}
