/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.State;

/**
 *
 * @author Aleksa
 */
public class StavkaNastupa implements DomainObject{
    
    private Long idStavkeNastupa;
    private Long idNastupa;
    private Date vreme;
    private Long trajanje;
    private DomainObject izvodjac;
    private State state;//regular save vrv
    private String kriterijum;
    private String tipIzvodjaca;

    private StavkaNastupa(Long idStavkeNastupa, Long idNastupa, Date vreme, Long trajanje, DomainObject izvodjac) {
        this.idStavkeNastupa = idStavkeNastupa;
        this.idNastupa = idNastupa;
        this.vreme = vreme;
        this.trajanje = trajanje;
        this.izvodjac = izvodjac;
        state = State.DoesntGeneratePrimaryKey;
        System.out.println("Klasa Izvodjaca u stavci sa instanceof:" + izvodjac.getClass());
        if (this.izvodjac instanceof Izvodjac) {
            this.tipIzvodjaca = "IZVODJAC";
        }else this.tipIzvodjaca ="BEND";
        System.out.println("kada se kreiralo postavilo je ovaj tipIzvodjaca: " + tipIzvodjaca);
    }

    /**
     * id ce se dodeliti u na klijentskoj strani sa append
     */
    public StavkaNastupa() {
        state = State.DoesntGeneratePrimaryKey;
        idStavkeNastupa = -1l;
    }
    /**
     * za kriterijumsk pretragu
     * @param kriterijum 
     */
    public StavkaNastupa(String kriterijum) {
        state = State.KriterijumskaPretraga;
        this.kriterijum = kriterijum;
        idStavkeNastupa = -1l;
    }

    public Long getIdNastupa() {
        return idNastupa;
    }

    public void setIdNastupa(Long idNastupa) {
        this.idNastupa = idNastupa;
    }
    public StavkaNastupa appendIdNastupa(Long idNastupa) {
        this.idNastupa = idNastupa;return this;
    }
    
    public Long getIdStavkeNastupa() {
        return idStavkeNastupa;
    }

    public Date getVreme() {
        return vreme;
    }

    public Long getTrajanje() {
        return trajanje;
    }

    public void setKriterijum(String kriterijum) {
        this.kriterijum = kriterijum;
    }

    public void setTipIzvodjaca(String tipIzvodjaca) {
        this.tipIzvodjaca = tipIzvodjaca;
    }
    public StavkaNastupa appendTipIzvodjaca(String tipIzvodjaca) {
        this.tipIzvodjaca = tipIzvodjaca;return this;
    }

    public DomainObject getIzvodjac() {
        return izvodjac;
    }

    public void setIdStavkeNastupa(Long idStavkeNastupa) {
        this.idStavkeNastupa = idStavkeNastupa;
    }

    public void setVreme(Date vreme) {
        this.vreme = vreme;
    }

    public void setTrajanje(Long trajanje) {
        this.trajanje = trajanje;
    }

    public void setIzvodjac(DomainObject izvodjac) {
        this.izvodjac = izvodjac;
        if (this.izvodjac instanceof Izvodjac) {
            this.tipIzvodjaca = "IZVODJAC";
        }else this.tipIzvodjaca ="BEND";
    }
    public StavkaNastupa appendIdStavkeNastupa(Long idStavkeNastupa) {
        this.idStavkeNastupa = idStavkeNastupa;return this;
    }

    public StavkaNastupa appendVreme(Date vreme) {
        this.vreme = vreme;return this;
    }

    public StavkaNastupa appendTrajanje(Long trajanje) {
        this.trajanje = trajanje;return this;
    }

    public StavkaNastupa appendIzvodjac(DomainObject izvodjac) {
        this.izvodjac = izvodjac;
        if (this.izvodjac instanceof Izvodjac) {
            this.tipIzvodjaca = "IZVODJAC";
        }else this.tipIzvodjaca ="BEND";
        return this;
    }

    //-------------------------------------------------
    //-------------------------------------------------
    @Override
    public DomainObject createObjectFromResaultSet(ResultSet rs) throws SQLException {
        try {
            DomainObject domainObject;
            tipIzvodjaca = rs.getString("tipIzvodjaca");
            if (tipIzvodjaca.equals("IZVODJAC")) {
                domainObject = new Izvodjac()
                        .appendIdIzvodjac(rs.getLong("idIzvodjac")).appendState(State.Default);
            }else {
                domainObject = new Bend()
                        .appendIdIzvodjaca(rs.getLong("idIzvodjac")).appendState(State.Default);
            }
            return new StavkaNastupa(rs.getLong("idStavkeNastupa"),
                    rs.getLong("idNastupa"),
                    new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("vreme")),
                    rs.getLong("trajanje"),
                    domainObject);
        } catch (ParseException ex) {
            Logger.getLogger(StavkaNastupa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getObjectAtributes() {
        if (state.equals(State.KriterijumskaPretraga)) {
            return "idStavkeNastupa,idNastupa,vreme,trajanje,idIzvodjac,tipIzvodjaca";
        }
        if (state.equals(State.GetAll)) {
            return "idStavkeNastupa,idNastupa,vreme,trajanje,idIzvodjac,tipIzvodjaca";
        }
        if (state.equals(State.RegularSave)) {
            return "idStavkeNastupa,idNastupa,vreme,trajanje,idIzvodjac,tipIzvodjaca";
        }
        if (state.equals(State.DoesntGeneratePrimaryKey)) {
            return "idStavkeNastupa,idNastupa,vreme,trajanje,idIzvodjac,tipIzvodjaca";
        }
        return "idStavkeNastupa,idNastupa,vreme,trajanje,idIzvodjac,tipIzvodjaca";
    }

    @Override
    public String getTabbleName() {
        return "stavkanastupa";
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
        return "idNastupa = " + idNastupa + " AND idStavkeNastupa=" + idStavkeNastupa;
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String getAtributeValues() {
        //idStavkeNastupa,idNastupa,vreme,trajanje,idIzvodjac,tipIzvodjaca
        if (state.equals(State.DoesntGeneratePrimaryKey)) {
            StringBuilder sb = new StringBuilder();
            sb.append(idStavkeNastupa).append(",")
                    .append(idNastupa).append(",")
                    .append("'").append(new java.sql.Date(vreme.getTime())).append("',")
                    .append("").append(trajanje).append(",")
                    .append(izvodjac.getID()).append(",")
                    .append("'").append(tipIzvodjaca).append("'");
            return sb.toString();
        }
        return "getAtributes mora da se ispise za stavku izvodjaca";
    }

    @Override
    public void setId(Long id) {
        this.idStavkeNastupa = id;
    }

    @Override
    public State getState() {
        return state;
    }

    //mislim da se nece koristiti
    @Override
    public List<DomainObject> getList() {
        List<DomainObject> listaDomenskihObjekata = new ArrayList<>();
//        for (StavkaNastupa stavkaNastupa : listaStavki) {
//            listaDomenskihObjekata.add(stavkaNastupa);
//        }
//        System.out.println(listaDomenskihObjekata);
        return listaDomenskihObjekata;
    }

    @Override
    public String getAgregateTableName() {
        return "NOT IMPLEMENTED";
    }

    @Override
    public String getIdColumnName() {
        return "idStavkeNastupa";
    }

    @Override
    public Long getID() {
        return idStavkeNastupa;
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
    }

//    private Long idStavkeNastupa;
//    private Long idNastupa;
//    private Date vreme;
//    private Long trajanje;
//    private DomainObject izvodjac;
    //idStavkeNastupa,idNastupa,vreme,trajanje,idIzvodjac
    @Override
    public String getUpdateAtributes() {
        return "idStavkeNastupa=" + idStavkeNastupa + 
                ", idNastupa=" + idNastupa +
                ", vreme='" + (new java.sql.Date(vreme.getTime())) + 
                "',trajanje=" + trajanje +
                ",izvodjac=" +izvodjac.getID() +
                ",tipIzvodjaca='" +tipIzvodjaca + "'";
    }

    @Override
    public String getDomainObjectAgregateIdColumnName() {
        return "NOT IMPLEMENTED";
    }

    @Override
    public String getAgregateConectedTableID() {
        return "NOT IMPLEMENTED";
    }

    public StavkaNastupa appendKriterijum(String kriterijum) {
        this.kriterijum = kriterijum;
        return this;
    }
    public StavkaNastupa appendState(State state) {
        this.state = state;
        return this;
    }

    public String getTipIzvodjaca() {
        return tipIzvodjaca;
    }
    
    
    
}