/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel.data;

/**
 * Деталь для заказа в workshop
 *
 * @author elduderino
 */
public class OrderPart {

    private String ident;   // идентификатор
    private int cost;       //стоимость покупки

    public OrderPart() {
    }

    public OrderPart(String ident, int cost) {
        this.ident = ident;
        this.cost = cost;
    }

    public static OrderPart getBolt(){
        return new OrderPart("bolt", 5);
    }
    
    public static OrderPart getSteel(){
        return new OrderPart("steel ", 100);
    }
    
    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
