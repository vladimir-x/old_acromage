/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.data;

/**
 *
 * @author elduderino
 */
public class Detail {

    public String ident;   //идентификатор
    public Integer space;  //оценка занимаемого места. (например вес или объём)
    public Integer normStore;   //количество деталей, которое желательно иметь в запасе на складе

    public static Detail getZORRO() {
        Detail d = new Detail();
        d.ident = "ZORRO";
        d.space = 7;
        d.normStore = 10;
        return d;
    }
}
