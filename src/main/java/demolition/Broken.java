package demolition;

import processing.core.PImage;
import processing.core.PApplet;

public class Broken extends MapComp{
    private int x;
    private int y;

    private PImage sprite;

    public Broken(int x, int y, PImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public void tick() {
        //handles logic
    }

    public void draw(PApplet app) {
        //handles graphics
        app.image(this.sprite, this.x, this.y);
    }
}