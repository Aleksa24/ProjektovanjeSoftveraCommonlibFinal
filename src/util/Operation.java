/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;

/**
 *
 * @author student1
 */
public interface Operation extends Serializable {

    public static final int OPERATION_LOGIN = 1;
    public static final int OPERATION_LOGOUT_MENADZER = 2;
    public static int OPERATION_SAVE_MENADZER = 3;
    public static int OPERATION_GET_ALL_VRSTAIZVODJACA = 4;
    public static int OPERATION_SAVE_IZVODJAC = 5;
    public static int OPERATION_PRETRAZI_PO_KRITERIJUMU = 6;
    public static int OPERATION_UCITAJ_IZVODJACA = 7;
    public static int OPERATION_UPDATE_IZVODJACA = 8;
    public static int OPERATION_UCITAJ_LISTU_IZVODJACA = 9;
    public static int OPERATION_ZAPAMTI_BEND = 10;
    public static int OPERATION_PRETRAZI_PO_KRITERIJUMU_BEND = 11;
    public static int OPERATION_UCITAJ_BEND = 12;
    public static int OPERATION_UPDATE_BEND = 13;
    public static int OPERATION_UCITAJ_LISTU_BENDOVA = 14;
    public static int OPERATION_SAVE_NASTUP = 15;
    public static int OPERATION_PRETRAZI_PO_KRITERIJUMU_NASTUP = 16;
    public static int OPERATION_UCITAJ_NASTUP = 17;
    public static int OPERATION_UPDATE_NASTUP = 18;
}
