package kz.iitu.centerrepservice;

import lombok.Data;

@Data
public class Center {
    private Long id;
    private String name;
    private String location;

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
