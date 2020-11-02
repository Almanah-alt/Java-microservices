package kz.iitu.centerrepservice;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

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
