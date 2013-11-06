/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processmodel;

import processmodel.data.Detail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import processmodel.data.Shop;

/**
 * Все структуры мира , не относящиеся к фабрике
 *
 * @author elduderino
 */
public class OutWorld {

    public List<Detail> detailList;
    public List<Shop> shopList;

    private static OutWorld outWorld = new OutWorld();

    public static OutWorld getOutWorld() {
        return outWorld;
    }

    public OutWorld() {
        detailList = new ArrayList<Detail>();
        shopList = new ArrayList<Shop>();
    }

    public static void loadDefaultShop() {
        outWorld.shopList.add(Shop.getTATAShop());
        outWorld.shopList.add(Shop.getMODESTShop());
    }

    public static void loadDefaultDetails() {
        outWorld.detailList.add(Detail.getZORRO());
    }

    public static void loadFromFile() {
        ObjectMapper om = new ObjectMapper();
        try {
            outWorld = om.readValue(new File("outworld.json"), OutWorld.class);
        } catch (JsonProcessingException jpe) {
            System.err.println(jpe);
        } catch (IOException ex) {
            Logger.getLogger(Plant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void saveToFile() {

        ObjectMapper om = new ObjectMapper();
        try {
            ObjectWriter writer = om.writer().withDefaultPrettyPrinter();
            writer.writeValue(new File("outworld.json"), outWorld);

        } catch (JsonProcessingException jpe) {
            System.err.println(jpe);
        } catch (IOException ex) {
            Logger.getLogger(Plant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Возвращает самый дешёвый магазин с данной продукцией
     *
     * @param ident
     * @param day
     * @return
     */
    public Shop getShop(String ident, Integer day, Integer deliverDayBefore) {
        Shop res = null;
        Integer resCost = null;

        for (Shop shop : shopList) {
            if (shop.isWork(day) && shop.price.containsKey(ident) && (day + shop.bookTime <= deliverDayBefore)) {
                Integer curCost = shop.price.get(ident);
                if (resCost == null || resCost > curCost) {
                    resCost = curCost;
                    res = shop;
                }
            }
        }
        return res;

    }

    public Detail getDetail(String orderPartIdent) {
        for (Detail detail : detailList) {
            if (detail.ident.equals(orderPartIdent)) {
                return detail;
            }
        }
        return null;
    }

}
