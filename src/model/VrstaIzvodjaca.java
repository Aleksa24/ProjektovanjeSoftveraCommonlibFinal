/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import util.State;

/**
 *
 * @author Aleksa
 */
public class VrstaIzvodjaca implements DomainObject{

    private Long vrstaID;
    private String nazivVrste;
    private State state = State.RegularSave;//default nista ne znaci

    public VrstaIzvodjaca() {
    }
    
    private VrstaIzvodjaca(Long vrstaID, String nazivVrste) {
        this.vrstaID = vrstaID;
        this.nazivVrste = nazivVrste;
    }
    
    public static VrstaIzvodjaca vrstaizvodjacaFacIzvodjaca(Long vrstaID, String nazivVrste){
        return new VrstaIzvodjaca(vrstaID, nazivVrste);
    }
    
    @Override
    public DomainObject createObjectFromResaultSet(ResultSet rs) throws SQLException {
        return new VrstaIzvodjaca()
            .appendVrstaID(rs.getLong("vrstaID"))
            .appendNazivVrste(rs.getString("nazivVrste"));
    }

    @Override
    public String getObjectAtributes() {
        return "vrstaID,nazivVrste";
    }

    @Override
    public String getTabbleName() {
        return "vrstaizvodjaca";
    }

    @Override
    public String getWhereCondition() {
        return "vrstaID = " + vrstaID;
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String getAtributeValues() {
        StringBuilder sb = new StringBuilder();
            sb.append(vrstaID)
              .append(",'").append(nazivVrste).append("'");
            return sb.toString();
    }

    @Override
    public void setId(Long id) {
        vrstaID = id;
    }

    public Long getVrstaID() {
        return vrstaID;
    }
    public String getNazivVrste() {
        return nazivVrste;
    }
    
    public void setVrstaID(Long vrstaID) {
        this.vrstaID = vrstaID;
    }
    public void setNazivVrste(String nazivVrste) {
        this.nazivVrste = nazivVrste;
    }
    
    public VrstaIzvodjaca appendVrstaID(Long vrstaID) {
        this.vrstaID = vrstaID;
        return this;
    }
    public VrstaIzvodjaca appendNazivVrste(String nazivVrste) {
        this.nazivVrste = nazivVrste;
        return this;
    }

    @Override
    public String toString() {
        return "-" + nazivVrste;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public List<DomainObject> getList() {
        return null;
    }

    @Override
    public String getAgregateTableName() {
        return "izvodjacvrsta";
    }

    @Override
    public String getIdColumnName() {
        return "vrstaID";
    }

    @Override
    public Long getID() {
        return this.vrstaID;
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
        return null;
    }

    @Override
    public void addToList(DomainObject connectedDomainObject) {
        
    }

    @Override
    public String getUpdateAtributes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDomainObjectAgregateIdColumnName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAgregateConectedTableID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
