package demolition;

import processing.core.PImage;
import processing.core.PApplet;
import processing.core.PFont;

public class Lives{
    private int x;
    private int y;
    private PImage sprite;
    private PFont font;
    private readMap rm2;
    private int lives;

    public Lives(readMap rm, int x, int y, PFont font, PImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.font = font;
        this.rm2 = rm;
        this.lives = rm.getLives();
    }

    public void tick() {

    }
    public int getLives() {
        return lives;
    }
    public void changeLives(){
        //if(status == true) boolean status
        lives = lives-1;
    } 

    public void draw(PApplet app) {
        app.image(this.sprite, this.x, this.y);
        app.textFont(font);
        app.fill(0, 0, 0);
        app.text(lives, 190, 42);
        //app.text(244, 250, 16);
    }
}