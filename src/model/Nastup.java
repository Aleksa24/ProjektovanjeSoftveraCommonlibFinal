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
public class Nastup implements DomainObject{

    private Long idNastupa;
    private String nazivNastupa;
    private String opis;
    private Menadzer menadzer;
    private String lokacija;
    private List<StavkaNastupa> listaStavki;
    private State state;//po defoltu treba da se postavlja one to many ili regular save nzm jos
    private String kriterijum;
    
    private Nastup(String nazivNastupa, String opis, Menadzer menadzer, String lokacija) {
        this.nazivNastupa = nazivNastupa;
        this.opis = opis;
        this.menadzer = menadzer;
        this.lokacija = lokacija;
        this.state = State.RegularSave;//mozda izmeniti
        listaStavki = new ArrayList<>();
    }

    private Nastup(Long idNastupa, String nazivNastupa, String opis, Menadzer menadzer, String lokacija) {
        this.idNastupa = idNastupa;
        this.nazivNastupa = nazivNastupa;
        this.opis = opis;
        this.menadzer = menadzer;
        this.lokacija = lokacija;
        this.state = State.RegularSave;
        listaStavki = new ArrayList<>();
    }

    /**
     * za kriterijumsku pretragu
     * @param kriterijum 
     */
    public Nastup(String kriterijum) {
        this.kriterijum = kriterijum;
        this.state = State.KriterijumskaPretraga;
        listaStavki = new ArrayList<>();
    }
    
    
    /**
     * kreira nastup za Save
     * @param nazivNastupa
     * @param opis
     * @param menadzer
     * @param lokacija
     * @return 
     */
    public static Nastup NastupNoviFactory(String nazivNastupa, String opis, Menadzer menadzer, String lokacija) {
        Nastup n = new Nastup(nazivNastupa, opis, menadzer, lokacija);
        System.out.println(n);
        return n;
    }
    
    public Long getIdNastupa() {
        return idNastupa;
    }
    public String getNazivNastupa() {
        return nazivNastupa;
    }
    public String getOpis() {
        return opis;
    }
    public Menadzer getMenadzer() {
        return menadzer;
    }
    public String getLokacija() {
        return lokacija;
    }
    public List<StavkaNastupa> getListaStavki() {
        return listaStavki;
    }

    public void setIdNastupa(Long idNastupa) {
        this.idNastupa = idNastupa;
    }
    public void setNazivNastupa(String nazivNastupa) {
        this.nazivNastupa = nazivNastupa;
    }
    public void setOpis(String opis) {
        this.opis = opis;
    }
    public void setMenadzer(Menadzer menadzer) {
        this.menadzer = menadzer;
    }
    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }
    public void setListaStavki(List<StavkaNastupa> listaStavki) {
        this.listaStavki = listaStavki;
    }

    public Nastup appendIdNastupa(Long idNastupa) {
        this.idNastupa = idNastupa;
        return this;
    }
    public Nastup appendNazivNastupa(String nazivNastupa) {
        this.nazivNastupa = nazivNastupa;
        return this;
    }
    public Nastup appendOpis(String opis) {
        this.opis = opis;
        return this;
    }
    public Nastup appendMenadzer(Menadzer menadzer) {
        this.menadzer = menadzer;
        return this;
    }
    public Nastup appendLokacija(String lokacija) {
        this.lokacija = lokacija;
        return this;
    }
    public Nastup appendListaStavki(List<StavkaNastupa> listaStavki) {
        this.listaStavki = listaStavki;
        return this;
    }

    //-------------------------------------------------
    //-------------------------------------------------
    @Override
    public DomainObject createObjectFromResaultSet(ResultSet rs) throws SQLException {
        return new Nastup(rs.getLong("idNastupa"),
                rs.getString("nazivNastupa"),
                rs.getString("opis"),
                Menadzer.MenadzerWithIdOnlyFactory(rs.getLong("idMenadzer")),
                rs.getString("lokacija"));
    }

    @Override
    public String getObjectAtributes() {
        if (state.equals(State.KriterijumskaPretraga)) {
            return "idNastupa,nazivNastupa,opis,idMenadzer,lokacija";
        }
        if (state.equals(State.GetAll)) {
            return "idNastupa,nazivNastupa,opis,idMenadzer,lokacija";
        }
        if (state.equals(State.RegularSave)) {
            return "nazivNastupa,opis,idMenadzer,lokacija";
        }
        if (state.equals(State.Default)) {
            return "idNastupa,nazivNastupa,opis,idMenadzer,lokacija";
        }
        return "idNastupa,nazivNastupa,opis,idMenadzer,lokacija";
    }

    @Override
    public String getTabbleName() {
        return "nastup";
    }

    @Override
    public String getWhereCondition() {
        if (state.equals(State.KriterijumskaPretraga)) {
            return kriterijum;
        }
//        if (state.equals(State.Update)) {
//            state = State.ManyToMany;
//            return " idIzvodjaca = " + idIzvodjac + " AND tip = '" + tip +"'";
//        }
        return "idNastupa = " + idNastupa;
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String getAtributeValues() {
//        if (state.equals(State.ManyToMany)) {
//            StringBuilder sb = new StringBuilder();
//            sb.append("'").append(ime).append("',")
//                    .append("'").append(prezime).append("',")
//                    .append("'").append(email).append("',")
//                    .append("'").append(telefon).append("',")
//                    .append("'").append(pol.toString()).append("',")
//                    .append("'").append(tip).append("'");
//            return sb.toString();
//        }
        //nazivNastupa,opis,idMenadzer,lokacija
        if (state.equals(State.RegularSave)) {
            StringBuilder sb = new StringBuilder();
            sb.append("'").append(nazivNastupa).append("',")
                    .append("'").append(opis).append("',")
                    .append(menadzer.getIdMenadzer()).append(",")
                    .append("'").append(lokacija).append("'");
            return sb.toString();
        }
        return "getAtributes mora da se ispise za izvodjaca";
    }

    @Override
    public void setId(Long id) {
        this.idNastupa = id;
    }

    @Override
    public State getState() {
        return state;
    }

    //mislim da se nece koristiti
    @Override
    public List<DomainObject> getList() {
        List<DomainObject> listaDomenskihObjekata = new ArrayList<>();
        for (StavkaNastupa stavkaNastupa : listaStavki) {
            listaDomenskihObjekata.add(stavkaNastupa);
        }
        System.out.println(listaDomenskihObjekata);
        return listaDomenskihObjekata;
    }

    @Override
    public String getAgregateTableName() {
        return "NOT IMPLEMENTED";
    }

    @Override
    public String getIdColumnName() {
        return "idNastupa";
    }

    @Override
    public Long getID() {
        return idNastupa;
    }

    @Override
    public String getConectedTableName() {
        return "NOT IMPLEMENTED";
    }

    @Override
    public String getConectedTebleID() {
        return "NOT IMPLEMENTED";
    }

    @Override
    public DomainObject createConnectedDomainObjectFromResultSet(ResultSet rs) throws SQLException {
        return null;
    }

    //mislim da mi nece trebati
    @Override
    public void addToList(DomainObject connectedDomainObject) {
        this.listaStavki.add((StavkaNastupa) connectedDomainObject);
    }

//    Long idNastupa;
//    String nazivNastupa;
//    String opis;
//    Menadzer menadzer;
//    String lokacija;
    @Override
    public String getUpdateAtributes() {
        return "idNastupa='" + idNastupa + 
                "', nazivNastupa='" + nazivNastupa +
                "', opis='" + opis + 
                "',idMenadzer=" + menadzer.getIdMenadzer() +
                ",lokacija='" +lokacija + "'";
    }

    @Override
    public String getDomainObjectAgregateIdColumnName() {
        return "NOT IMPLEMENTED";
    }

    @Override
    public String getAgregateConectedTableID() {
        return "NOT IMPLEMENTED";
    }

    public Nastup appendState(State state) {
        this.state = state;
        return this;
    }

}
