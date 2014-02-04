/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game;

/**
 *
 * @author elduderino
 */
public class Settings {

    // размеры окна
    public Integer windowWidth;
    public Integer windowHeight;
    public Boolean fullscreen;

    // размеры камеры (размеры пропорциональны файлам с ресурсами)
    public Integer cameraWidth;
    public Integer cameraHeight;

    //размеры карты в текстуре для карт
    public Integer cardHeight;
    public Integer cardWidth;
    
    //игровые правила
    public Integer cardCount;   //количество карт в руке

    
    //текстуры
    public String welcomeTexture;   //экран привествия
    public String aboutTexture;     //экран about
    public String boardTexture;     //стол
    public String deckTexture;      //колода
    public String itemsTexture;     //башни, рубашки карт, и прочие текстуры
    public String titleTexture;     //логотим Arcomage
    
    //размеры текстур
    public Integer welcomeTextureWidth;
    public Integer welcomeTextureHeight;
    public Integer deckTextureWidth;
    public Integer deckTextureHeight;
    
    
    
}
