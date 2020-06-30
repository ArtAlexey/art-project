package ru.spring.artproject.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CURRENCY")
public class Currency {

    @Id
    private Integer id;
    @Column(name = "numcode")
    private String numcode;
    @Column(name = "charcode")
    private String charCode;
    @Column(name = "nominal")
    private Integer nominal;
    @Column(name = "name")
    private String name;
    @Column(name = "value")
    private Double value;
    @Column(name = "date")
    private Date date;

    public Currency() {
    }

    public Currency(Integer id, String numcode, String charCode, Integer nominal, String name, Double value, Date date) {
        this.id = id;
        this.numcode = numcode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumcode() {
        return numcode;
    }

    public void setNumcode(String numcode) {
        this.numcode = numcode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public Integer getNominal() {
        return nominal;
    }

    public void setNominal(Integer nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", numcode='" + numcode + '\'' +
                ", charCode='" + charCode + '\'' +
                ", nominal=" + nominal +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
