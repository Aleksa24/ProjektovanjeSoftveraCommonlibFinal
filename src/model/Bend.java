/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.State;

/**
 *
 * @author Aleksa
 */
public class Bend implements DomainObject {

    private Long idIzvodjaca;
    private String nazivBenda;
    private String email;
    private String telefon;
    private String tip;
    private List<Izvodjac> clanoviBenda;
    private State state;
    private String kriterijum;

    public Bend() {
        clanoviBenda = new ArrayList<>();
        state = State.ManyToMany;
        tip = "BEND";
    }
    
    public static Bend bendNoviFactory(String nazivBenda, String email, String telefon) {
        Bend b = new Bend(nazivBenda, email, telefon);
        b.setState(State.ManyToMany);
        return b;
    }

    private Bend(String nazivBenda, String email, String telefon) {
        this.nazivBenda = nazivBenda;
        this.email = email;
        this.telefon = telefon;
        tip = "BEND";
        state = State.ManyToMany;
        clanoviBenda = new ArrayList<>();
    }

    public Bend(String kriterijum) {
        tip = "BEND";
        clanoviBenda = new ArrayList<>();
        state = State.KriterijumskaPretraga;
        this.kriterijum = kriterijum;
    }

    public Long getIdIzvodjaca() {
        return idIzvodjaca;
    }

    public String getNazivBenda() {
        return nazivBenda;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefon() {
        return telefon;
    }
    
    public List<Izvodjac> getClanoviBenda() {
        return clanoviBenda;
    }

    public void setIdIzvodjaca(Long idIzvodjaca) {
        this.idIzvodjaca = idIzvodjaca;
    }

    public void setNazivBenda(String nazivBenda) {
        this.nazivBenda = nazivBenda;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public void setClanoviBenda(List<Izvodjac> clanoviBenda) {
        this.clanoviBenda = clanoviBenda;
    }

    public Bend appendIdIzvodjaca(Long idIzvodjaca) {
        this.idIzvodjaca = idIzvodjaca;
        return this;
    }

    public Bend appendNazivBenda(String nazivBenda) {
        this.nazivBenda = nazivBenda;
        return this;
    }
    public Bend appendState(State state){
        this.state =state;
        return this;
    }

    public Bend appendEmail(String email) {
        this.email = email;
        return this;
    }

    public Bend appendTelefon(String telefon) {
        this.telefon = telefon;
        return this;
    }

    public Bend appendTip(String tip) {
        this.tip = tip;
        return this;
    }

    @Override
    public DomainObject createObjectFromResaultSet(ResultSet rs) throws SQLException {
        return new Bend()
                .appendIdIzvodjaca(rs.getLong("idIzvodjaca"))
                .appendEmail(rs.getString("email"))
                .appendTelefon(rs.getString("brojTelefona"))
                .appendNazivBenda(rs.getString("nazivBenda"))
                .appendTip(rs.getString("tip"));
    }

    @Override
    public String getObjectAtributes() {
        if (state.equals(State.ManyToMany)) {
            return "nazivBenda,email,brojTelefona,tip";
        }
        if (state.equals(State.KriterijumskaPretraga)) {
            return "idIzvodjaca,nazivBenda,email,brojTelefona,tip";
        }
        if (state.equals(State.GetAll)) {
            return "idIzvodjaca,nazivBenda,email,brojTelefona,tip";
        }
        if (state.equals(State.FindManyToMany)) {
            return "idIzvodjaca,nazivBenda,email,brojTelefona,tip";
        }
        if (state.equals(State.Default)) {
            return "idIzvodjaca,nazivBenda,email,brojTelefona,tip";
        }
        return "idIzvodjaca,nazivBenda,email,brojTelefona,tip";
    }

    @Override
    public String getTabbleName() {
        return "izvodjac";
    }

    @Override
    public String getWhereCondition() {
        if (state.equals(State.KriterijumskaPretraga)) {
            return kriterijum + " AND tip = '" + tip + "'";
        }
        if (state.equals(State.GetAll)) {
            return " tip = '" + tip + "'";
        }
        if (state.equals(State.FindManyToMany)) {
            return " idIzvodjaca = " + idIzvodjaca + " AND tip = '" + tip + "'";
        }
        if (state.equals(State.Update)) {
            state = State.ManyToMany;
            return " idIzvodjaca = " + idIzvodjaca + " AND tip = '" + tip + "'";
        }
        if (state.equals(State.Default)) {
            state = State.ManyToMany;
            return " idIzvodjaca = " + idIzvodjaca + " AND tip = '" + tip + "'";
        }
        if (state.equals(State.ManyToMany)) {
            return " tip = '" + tip + "'";
        }
        return "idIzvodjaca = " + idIzvodjaca + " AND tip = '" + tip + "'";
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }

    //"idIzvodjaca,nazivBenda,email,brojTelefona,tip"
    @Override
    public String getAtributeValues() {
        if (state.equals(State.ManyToMany)) {
            StringBuilder sb = new StringBuilder();
            sb.append("'").append(nazivBenda).append("',")
                    .append("'").append(email).append("',")
                    .append("'").append(telefon).append("',")
                    .append("'").append(tip).append("'");
            return sb.toString();
        }
        if (state.equals(State.GetAll)) {
            StringBuilder sb = new StringBuilder();
            sb.append(idIzvodjaca)
                    .append("'").append(nazivBenda).append("',")
                    .append("'").append(email).append("',")
                    .append("'").append(telefon).append("',")
                    .append("'").append(tip).append("'");
            return sb.toString();
        }
        return "getAtributes mora da se ispise za bend";
    }

    @Override
    public void setId(Long id) {
        this.idIzvodjaca = id;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public List<DomainObject> getList() {
        List<DomainObject> listaDomenskihObjekata = new ArrayList<>();
        for (Izvodjac clan : clanoviBenda) {
            listaDomenskihObjekata.add(clan);
        }
        return listaDomenskihObjekata;
    }

    @Override
    public String getAgregateTableName() {
        return "clanbend";
    }

    @Override
    public String getIdColumnName() {
        return "idIzvodjaca";
    }

    @Override
    public Long getID() {
        return idIzvodjaca;
    }

    @Override
    public String toString() {
        return  idIzvodjaca + " " + nazivBenda;
    }

    @Override
    public String getConectedTableName() {
        return "izvodjac";
    }

    @Override
    public String getConectedTebleID() {
        return "idIzvodjaca";
    }

    @Override
    public DomainObject createConnectedDomainObjectFromResultSet(ResultSet rs) throws SQLException {
        return new Izvodjac()
                .appendIdIzvodjac(rs.getLong("o2.idIzvodjaca"))
                .appendEmail(rs.getString("o2.email"))
                .appendIme(rs.getString("o2.ime"))
                .appendPrezime(rs.getString("o2.prezime"))
                .appendTelefon(rs.getString("o2.brojTelefona"))
                .appendPol(Pol.valueOf(rs.getString("o2.pol")))
                .appendTip(rs.getString("o2.tip"));
    }

    @Override
    public void addToList(DomainObject connectedDomainObject) {
        this.clanoviBenda.add((Izvodjac) connectedDomainObject);
    }

//    private Long idIzvodjaca;
//    private String nazivBenda;
//    private String email;
//    private String telefon;
//    private String tip;

    @Override
    public String getUpdateAtributes() {
        return "nazivBenda='" + nazivBenda
                + "',email='" + email
                + "',brojTelefona='" + telefon + "'";
    }

    @Override
    public String getDomainObjectAgregateIdColumnName() {
        return "idBenda";
    }

    @Override
    public String getAgregateConectedTableID() {
        return "idClana";
    }

    public String getTip() {
        return tip;
    }
    
    
}
