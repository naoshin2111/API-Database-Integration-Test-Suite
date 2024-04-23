package models.database;

import lombok.Data;

@Data
public class Session {

    private Long id;
    private String sessionKey;
    private Long buildNumber;
}
