package be.ordina.workshop.spring4.java8.model;

import java.math.BigDecimal;

/**
 * Created by stevedezitter on 19/02/15.
 */
public class Beer {
    private Long id;
    private String name;
    private String description;
    private BigDecimal alcoholPercentage;

    //private BeerType beerType;

    public Beer() {

    }

    public Beer(Long id, String name, String description, BigDecimal alcoholPercentage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.alcoholPercentage = alcoholPercentage;
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

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAlcoholPercentage() {
        return alcoholPercentage;
    }
    public void setAlcoholPercentage(BigDecimal alcoholPercentage) {
        this.alcoholPercentage = alcoholPercentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Beer beer = (Beer) o;

        if (id != beer.getId()) return false;
        if (!alcoholPercentage.equals(beer.alcoholPercentage)) return false;
        if (!description.equals(beer.description)) return false;
        if (!name.equals(beer.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + alcoholPercentage.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", alcoholPercentage=" + alcoholPercentage +
                '}';
    }
}