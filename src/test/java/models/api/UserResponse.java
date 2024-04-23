package models.api;

import lombok.Data;

@Data
public class UserResponse {

    private Integer id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;
}
