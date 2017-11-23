package cz.muni.fi.pa165.dndtroops.dto;

import cz.muni.fi.pa165.dndtroops.entities.Hero;
import cz.muni.fi.pa165.dndtroops.entities.Troop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TroopDto {

    private Long id;
    private String name;
    private String mission;
    private long goldenMoney;
    private List<Hero> heroes = new ArrayList<>();

    public TroopDto(){

    }

    public TroopDto(String name, String mission, long goldenMoney)
    {
        this.name=name;
        this.mission=mission;
        this.goldenMoney=goldenMoney;
    }

    public TroopDto(String name, String mission, long goldenMoney, Hero hero)
    {
        this.name=name;
        this.mission=mission;
        this.goldenMoney=goldenMoney;
        this.heroes.add(hero);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof Troop)) return false;

        Troop troop = (Troop) o;

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

    public List<Hero> getHeroes() {return heroes;}

    public void addHero(Hero hero) {heroes.add(hero);}

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
