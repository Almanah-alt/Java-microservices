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

    public void setId(Long id) {
        this.id = id;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public void setCenterLocation(String centerLocation) {
        this.centerLocation = centerLocation;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRepairerName(String repairerName) {
        this.repairerName = repairerName;
    }

    public void setIdOfRoom(int idOfRoom) {
        this.idOfRoom = idOfRoom;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRepId(Long repId) {
        this.repId = repId;
    }
}
