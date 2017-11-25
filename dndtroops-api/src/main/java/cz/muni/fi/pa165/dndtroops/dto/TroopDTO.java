package cz.muni.fi.pa165.dndtroops.dto;

import java.util.Objects;

public class TroopDTO {

    private Long id;
    private String name;
    private String mission;
    private long goldenMoney;

    public TroopDTO() {

    }

    public TroopDTO(String name, String mission, long goldenMoney) {
        this.name = name;
        this.mission = mission;
        this.goldenMoney = goldenMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof TroopDTO)) return false;

        TroopDTO troop = (TroopDTO) o;

        return Objects.equals(name, troop.getName());
    }

    @Override
    public int hashCode() {
        int hash = 17 + getName().hashCode();
        return hash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public long getGoldenMoney() {
        return goldenMoney;
    }

    public void setGoldenMoney(Long goldenMoney) {
        this.goldenMoney = goldenMoney;
    }
}

