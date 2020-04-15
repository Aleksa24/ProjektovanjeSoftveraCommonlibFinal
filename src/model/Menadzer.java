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
public class Menadzer implements DomainObject {

    private Long idMenadzer;
    private String username;
    private String password;
    private String imeMenadzera;
    private String prezimeMenadzera;
    private String telefon;
    private String email;
    private Pol pol;
    private State state = State.Login;
    private String kriterijum = "";

    private Menadzer(){
        
    }
    
    private Menadzer(Long idMenadzer) {
        this.state = State.KriterijumskaPretraga;
        this.kriterijum = "idMenadzer = " + idMenadzer;
        this.idMenadzer = idMenadzer;
    }
    /**
     * 
     * @param username
     * @param password 
     */
    private Menadzer(String username, String password) {
        this.username = username;
        this.password = password;
        this.state = State.Login;
    }

    /**
     * 
     * @param username
     * @param password
     * @param imeMenadzera
     * @param prezimeMenadzera
     * @param telefon
     * @param email
     * @param pol 
     */
    private Menadzer(String username, String password, String imeMenadzera, String prezimeMenadzera, String telefon, String email, Pol pol) {
        this.idMenadzer = null;
        this.username = username;
        this.password = password;
        this.imeMenadzera = imeMenadzera;
        this.prezimeMenadzera = prezimeMenadzera;
        this.telefon = telefon;
        this.email = email;
        this.pol = pol;
        this.state = State.RegularSave;
    }

    public static Menadzer menadzerLoginFactory(String username, String password) {
        Menadzer m = new Menadzer(username, password);
        m.setState(State.Login);
        return m;
    }

    public static Menadzer menadzerCreateFactory(String username, String password, String imeMenadzera, String prezimeMenadzera, String telefon, String email, Pol pol) {
        Menadzer m = new Menadzer(username, password, imeMenadzera, prezimeMenadzera, telefon, email, pol);
        m.setState(State.RegularSave);
        return m;
    }
    
    public static Menadzer MenadzerWithIdOnlyFactory(Long idMenadzer) {
        Menadzer m = new Menadzer(idMenadzer);
        return m;
    }

    public Long getIdMenadzer() {
        return idMenadzer;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getImeMenadzera() {
        return imeMenadzera;
    }

    public String getPrezimeMenadzera() {
        return prezimeMenadzera;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getEmail() {
        return email;
    }

    public Pol getPol() {
        return pol;
    }

    public void setIdMenadzer(Long idMenadzer) {
        this.idMenadzer = idMenadzer;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImeMenadzera(String imeMenadzera) {
        this.imeMenadzera = imeMenadzera;
    }

    public void setPrezimeMenadzera(String prezimeMenadzera) {
        this.prezimeMenadzera = prezimeMenadzera;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }

    public Menadzer appendIdMenadzer(Long idMenadzer) {
        this.idMenadzer = idMenadzer;
        return this;
    }

    public Menadzer appendUsername(String username) {
        this.username = username;
        return this;
    }

    public Menadzer appendPassword(String password) {
        this.password = password;
        return this;
    }

    public Menadzer appendImeMenadzera(String imeMenadzera) {
        this.imeMenadzera = imeMenadzera;
        return this;
    }

    public Menadzer appendPrezimeMenadzera(String prezimeMenadzera) {
        this.prezimeMenadzera = prezimeMenadzera;
        return this;
    }

    public Menadzer appendTelefon(String telefon) {
        this.telefon = telefon;
        return this;
    }

    public Menadzer appendEmail(String email) {
        this.email = email;
        return this;
    }

    public Menadzer appendPol(Pol pol) {
        this.pol = pol;
        return this;
    }

    @Override
    public DomainObject createObjectFromResaultSet(ResultSet rs) throws SQLException{
        return new Menadzer()
            .appendIdMenadzer(rs.getLong("idmenadzer"))
            .appendUsername(rs.getString("username"))
            .appendPassword(rs.getString("password"))
            .appendImeMenadzera(rs.getString("imemenadzera"))
            .appendPrezimeMenadzera(rs.getString("prezimemenadzera"))
            .appendTelefon(rs.getString("telefon"))
            .appendEmail(rs.getString("email"))
            .appendPol(Pol.valueOf(rs.getString("pol")));
    }

    @Override
    public String getObjectAtributes() {
        if (state.equals(State.RegularSave)) {
            return "username,password,imemenadzera,prezimemenadzera,telefon,email,pol";
        }
        if (state.equals(State.Default)) {
            return "idmenadzer,username,password,imemenadzera,prezimemenadzera,telefon,email,pol";
        }
        if (state.equals(State.KriterijumskaPretraga)) {
            return "idmenadzer,username,password,imemenadzera,prezimemenadzera,telefon,email,pol";
        }
        return "idmenadzer,username,password,imemenadzera,prezimemenadzera,telefon,email,pol";
    }

    @Override
    public String getTabbleName() {
        return "menadzer";
    }

    @Override
    public String getWhereCondition() {
        if (state.equals(State.Login) ) {
            return "username = '" + username + "' AND password = '" + password + "'"; 
        }        
        if (state.equals(State.RegularSave) ) {
            return "idmenadzer = " + idMenadzer; 
        }        
        if (state.equals(State.Default) ) {
            return "idmenadzer = " + idMenadzer; 
        }        
        return "idmenadzer = " + idMenadzer;
    }
    /**
     * LOGIN        -username,password
     * SAVE           -idmenadzer         //not implemented
     * allConditions-all                //not implemented
     * @param state
     */
    @Override
    public void setState(State state){
        this.state = state;
    }

    //"username,password,imemenadzera,prezimemenadzera,telefon,email,pol"
    @Override
    public String getAtributeValues() {
        if (state.equals(State.RegularSave)) {
            StringBuilder sb = new StringBuilder();
            sb.append("'").append(username).append("',")
                    .append("'").append(password).append("',")
                    .append("'").append(imeMenadzera).append("',")
                    .append("'").append(prezimeMenadzera).append("',")
                    .append("'").append(telefon).append("',")
                    .append("'").append(email).append("',")
                    .append("'").append(pol.toString()).append("'");
            return sb.toString();
        }
        return "getAtributes mora da se ispise za menadzera";
    }

    @Override
    public void setId(Long id) {
        idMenadzer = id;
    }

    @Override
    public String toString() {
        return  imeMenadzera + " " + prezimeMenadzera;
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
        return null;
    }

    @Override
    public String getIdColumnName() {
        return "idmenadzer";
    }

    @Override
    public Long getID() {
        return idMenadzer;
    }

    @Override
    public String getConectedTableName() {
        return null;
    }

    @Override
    public String getConectedTebleID() {
        return null;
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
    public Menadzer appendState(State state){
        this.state =state;
        return this;
    }
    
}
