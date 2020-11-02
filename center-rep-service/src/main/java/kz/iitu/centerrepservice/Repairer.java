package kz.iitu.centerrepservice;

import lombok.Data;

@Data
public class Repairer {
    private Long id;
    private String username;
    private String name;
    private int idOfRoom;
    private int price;
    private String phone;
    private String password;


}
