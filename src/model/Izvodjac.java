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
public class Izvodjac implements DomainObject{

    private Long idIzvodjac;
    private String ime;
    private String prezime;
    private String email;
    private String telefon;
    private Pol pol;
    private String tip;
    List<VrstaIzvodjaca> listaVrsta;
    private State state = State.ManyToMany;
    private String kriterijum;

    public Izvodjac() {
        listaVrsta = new ArrayList<>();
        state = State.ManyToMany;
        tip = "IZVODJAC";
    }
    /**
     * za kriterijumsku pretragu
     * @param kriterijum 
     */
    public Izvodjac(String kriterijum) {
        listaVrsta = new ArrayList<>();
        state = State.KriterijumskaPretraga;
        this.kriterijum = kriterijum;
        tip = "IZVODJAC";
    }

    private Izvodjac(String ime, String prezime, String email, String telefon, Pol pol) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.telefon = telefon;
        this.pol = pol;
        listaVrsta = new ArrayList<>();
        state = State.ManyToMany;
        tip = "IZVODJAC";
    }
    
    /**
     * 
     * @param ime
     * @param prezime
     * @param email
     * @param telefon
     * @param pol
     * @return 
     */
    public static Izvodjac izvodjacNoviFactory(String ime, String prezime, String email, String telefon, Pol pol){
        Izvodjac i =  new Izvodjac(ime, prezime, email, telefon, pol);
        i.setState(State.ManyToMany);
        i.setTip("IZVODJAC");
        return i;
    }

    public List<VrstaIzvodjaca> getListaVrsta() {
        return listaVrsta;
    }
    
    public Long getIdIzvodjac() {
        return idIzvodjac;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefon() {
        return telefon;
    }

    public Pol getPol() {
        return pol;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
    
    public void setIdIzvodjac(Long idIzvodjac) {
        this.idIzvodjac = idIzvodjac;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }

    public void setListaVrsta(List<VrstaIzvodjaca> listaVrsta) {
        this.listaVrsta = listaVrsta;
    }
    
    public Izvodjac appendTip(String tip) {
        this.tip = tip;
        return this;
    }
    public Izvodjac appendListaVrsta(List<VrstaIzvodjaca> listaVrsta) {
        this.listaVrsta = listaVrsta;
        return this;
    }
    
    public Izvodjac appendIdIzvodjac(Long idIzvodjac) {
        this.idIzvodjac = idIzvodjac;
        return this;
    }

    public Izvodjac appendIme(String ime) {
        this.ime = ime;
        return this;
    }

    public Izvodjac appendPrezime(String prezime) {
        this.prezime = prezime;
        return this;
    }

    public Izvodjac appendEmail(String email) {
        this.email = email;
        return this;
    }

    public Izvodjac appendTelefon(String telefon) {
        this.telefon = telefon;
        return this;
    }

    public Izvodjac appendPol(Pol pol) {
        this.pol = pol;
        return this;
    }

    @Override
    public DomainObject createObjectFromResaultSet(ResultSet rs) throws SQLException {
        return new Izvodjac()
            .appendIdIzvodjac(rs.getLong("idIzvodjaca"))
            .appendEmail(rs.getString("email"))
            .appendIme(rs.getString("ime"))
            .appendPrezime(rs.getString("prezime"))
            .appendTelefon(rs.getString("brojTelefona"))
            .appendPol(Pol.valueOf(rs.getString("pol")))
            .appendTip(rs.getString("tip"));
    }

    @Override
    public String getObjectAtributes() {
        if (state.equals(State.ManyToMany)) {
            return "ime,prezime,email,brojTelefona,pol,tip";
        }
        if (state.equals(State.KriterijumskaPretraga)) {
            return "idIzvodjaca,ime,prezime,email,brojTelefona,pol,tip";
        }
        if (state.equals(State.GetAll)) {
            return "idIzvodjaca,ime,prezime,email,brojTelefona,pol,tip";
        }
        if (state.equals(State.FindManyToMany)) {
            return "idIzvodjaca,ime,prezime,email,brojTelefona,pol,tip";
        }
        if (state.equals(State.Default)) {
            return "idIzvodjaca,ime,prezime,email,brojTelefona,pol,tip";
        }
        return "idIzvodjaca,ime,prezime,email,brojTelefona,pol,tip";
    }

    @Override
    public String getTabbleName() {
        return "izvodjac";
    }

    @Override
    public String getWhereCondition() {
        if (state.equals(State.KriterijumskaPretraga)) {
            return kriterijum + " AND tip = '" + tip +"'";
        }
        if (state.equals(State.GetAll)) {
            return " tip = '" + tip +"'";
        }
        if (state.equals(State.FindManyToMany)) {
            return " idIzvodjaca = " + idIzvodjac + " AND tip = '" + tip +"'";
        }
        if (state.equals(State.Update)) {
            state = State.ManyToMany;
            return " idIzvodjaca = " + idIzvodjac + " AND tip = '" + tip +"'";
        }
        if (state.equals(State.Default)) {
            state = State.ManyToMany;
            return " idIzvodjaca = " + idIzvodjac + " AND tip = '" + tip +"'";
        }
        if (state.equals(State.ManyToMany)) {
            return " tip = '" + tip +"'";
        }
        return "idIzvodjaca = " + idIzvodjac + " AND tip = '" + tip +"'";
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String getAtributeValues() {
        if (state.equals(State.ManyToMany)) {
            StringBuilder sb = new StringBuilder();
            sb.append("'").append(ime).append("',")
                    .append("'").append(prezime).append("',")
                    .append("'").append(email).append("',")
                    .append("'").append(telefon).append("',")
                    .append("'").append(pol.toString()).append("',")
                    .append("'").append(tip).append("'");
            return sb.toString();
        }
        if (state.equals(State.GetAll)) {
            StringBuilder sb = new StringBuilder();
            sb.append(idIzvodjac)
                    .append("'").append(ime).append("',")
                    .append("'").append(prezime).append("',")
                    .append("'").append(email).append("',")
                    .append("'").append(telefon).append("',")
                    .append("'").append(pol.toString()).append("',")
                    .append("'").append(tip).append("'");
            return sb.toString();
        }
        return "getAtributes mora da se ispise za izvodjaca";
    }

    @Override
    public void setId(Long id) {
        this.idIzvodjac = id;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public List<DomainObject> getList() {
        List<DomainObject> listaDomenskihObjekata = new ArrayList<>();
        for (VrstaIzvodjaca vrstaIzvodjaca : listaVrsta) {
            listaDomenskihObjekata.add(vrstaIzvodjaca);
        }
        System.out.println(listaDomenskihObjekata);
        return listaDomenskihObjekata;
    }

    @Override
    public String getAgregateTableName() {
        return "izvodjacvrsta";
    }

    @Override
    public String getIdColumnName() {
        return "idIzvodjaca";
    }

    @Override
    public Long getID() {
        return idIzvodjac;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String getConectedTableName() {
        return "vrstaizvodjaca";
    }

    @Override
    public String getConectedTebleID() {
        return "vrstaID";
    }

    @Override
    public DomainObject createConnectedDomainObjectFromResultSet(ResultSet rs) throws SQLException {
        return new VrstaIzvodjaca()
                .appendNazivVrste(rs.getString("o2.nazivVrste"))
                .appendVrstaID(rs.getLong("o2.vrstaID"));
    }

    @Override
    public void addToList(DomainObject connectedDomainObject) {
        this.listaVrsta.add((VrstaIzvodjaca) connectedDomainObject);
    }

//    private Long idIzvodjac;
//    private String ime;
//    private String prezime;
//    private String email;
//    private String telefon;
//    private Pol pol;
    @Override
    public String getUpdateAtributes() {
        return "ime='" + ime + 
                "', prezime='" + prezime +
                "', pol='" + pol.toString() + 
                "',email='" + email +
                "',brojTelefona='" +telefon + "'";
    }

    @Override
    public String getDomainObjectAgregateIdColumnName() {
        return "idIzvodjaca";
    }

    @Override
    public String getAgregateConectedTableID() {
        return "vrstaID";
    }

    public Izvodjac appendState(State state){
        this.state =state;
        return this;
    }
    

}
