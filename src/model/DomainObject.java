/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import util.State;

/**
 *
 * @author Aleksa
 */
public interface DomainObject extends Serializable{

    public DomainObject createObjectFromResaultSet(ResultSet rs) throws SQLException;

    public String getObjectAtributes();

    public String getTabbleName();

    public String getWhereCondition();

    public void setState(State state);

    public String getAtributeValues();

    public void setId(Long id);

    public State getState();

    public List<DomainObject> getList();

    /**
     * vraca agregatnu tabelu
     * @return 
     */
    public String getAgregateTableName();

    public String getIdColumnName();

    public Long getID();

    /**
     * vraca ime tabele sa kojom se povezuje many to many
     * @return 
     */
    public String getConectedTableName();

    /**
     * vraca ID od tabele sa kojom se povezuje many to many
     * @return 
     */
    public String getConectedTebleID();

    public DomainObject createConnectedDomainObjectFromResultSet(ResultSet rs) throws SQLException;

    /**
     * dodaje u listu ako treba
     * @param connectedDomainObject 
     */
    public void addToList(DomainObject connectedDomainObject);

    /**
     * imenaAtributa = vrednostAtributa
     * @return 
     */
    public String getUpdateAtributes();

    /**
     * vraca ime id kolone domenskog objekta u agregatnoj tabeli
     * @return 
     */
    public String getDomainObjectAgregateIdColumnName();

    /**
     * vraca ime id kolone other objekta u agregatnoj tabeli
     * @return 
     */
    public String getAgregateConectedTableID();



    
}
