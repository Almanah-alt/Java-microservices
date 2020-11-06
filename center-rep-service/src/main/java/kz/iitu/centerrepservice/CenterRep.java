package kz.iitu.centerrepservice;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class CenterRep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String centerName;
    private String centerLocation;
    private String username;
    private String repairerName;
    private int idOfRoom;
    private int price;
    private String phone;
    private Long repId;
}
