package kz.iitu.repairermicroservice;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
public class Repairer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String name;
    private int idOfRoom;
    private int price;
    @Column(unique = true)
    private String phone;
    private String password;
}
