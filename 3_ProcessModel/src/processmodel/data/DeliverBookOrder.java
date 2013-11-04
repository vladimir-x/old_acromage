/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package processmodel.data;

/**
 *
 * @author Dude
 */
public class DeliverBookOrder {

    public DeliverBookOrder(Integer startDay, Integer endDay, String ident, Integer count) {
        this.startDay = startDay;
        this.endDay = endDay;
        this.ident = ident;
        this.count = count;
    }

    
    
    public Integer startDay;
    public Integer endDay;
    
    public String ident;
    public Integer count;
    
}
