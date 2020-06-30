package ru.spring.artproject.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "HISTORY")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "charcodein")
    private String charCodeIn;
    @Column(name = "namein")
    private String nameIn;
    @Column(name = "valuein")
    private Double valueIn;
    @Column(name = "charcodeout")
    private String charCodeOut;
    @Column(name = "nameout")
    private String nameOut;
    @Column(name = "valueout")
    private Double valueOut;
    @Column(name = "datein")
    private Date dateIn;

    public History() {
    }

    public History(Long id, String charCodeIn, String nameIn, Double valueIn, String charCodeOut, String nameOut, Double valueOut, Date dateIn) {
        this.id = id;
        this.charCodeIn = charCodeIn;
        this.nameIn = nameIn;
        this.valueIn = valueIn;
        this.charCodeOut = charCodeOut;
        this.nameOut = nameOut;
        this.valueOut = valueOut;
        this.dateIn = dateIn;
    }

    public History(String charCodeIn, String nameIn, Double valueIn, String charCodeOut, String nameOut, Double valueOut, Date dateIn) {
        this.charCodeIn = charCodeIn;
        this.nameIn = nameIn;
        this.valueIn = valueIn;
        this.charCodeOut = charCodeOut;
        this.nameOut = nameOut;
        this.valueOut = valueOut;
        this.dateIn = dateIn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCharCodeIn() {
        return charCodeIn;
    }

    public void setCharCodeIn(String charCodeIn) {
        this.charCodeIn = charCodeIn;
    }

    public String getNameIn() {
        return nameIn;
    }

    public void setNameIn(String nameIn) {
        this.nameIn = nameIn;
    }

    public Double getValueIn() {
        return valueIn;
    }

    public void setValueIn(Double valueIn) {
        this.valueIn = valueIn;
    }

    public String getCharCodeOut() {
        return charCodeOut;
    }

    public void setCharCodeOut(String charCodeOut) {
        this.charCodeOut = charCodeOut;
    }

    public String getNameOut() {
        return nameOut;
    }

    public void setNameOut(String nameOut) {
        this.nameOut = nameOut;
    }

    public Double getValueOut() {
        return valueOut;
    }

    public void setValueOut(Double valueOut) {
        this.valueOut = valueOut;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", charCodeIn='" + charCodeIn + '\'' +
                ", nameIn='" + nameIn + '\'' +
                ", valueIn=" + valueIn +
                ", charCodeOut='" + charCodeOut + '\'' +
                ", nameOut='" + nameOut + '\'' +
                ", valueOut=" + valueOut +
                ", dateIn=" + dateIn +
                '}';
    }
}
