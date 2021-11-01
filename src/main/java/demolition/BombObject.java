package demolition;

import processing.core.PImage; 
import processing.core.PApplet; 

public abstract class BombObject {
    protected int x;
    protected int y;

    protected PImage sprite;

    public BombObject(int x, int y, PImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public abstract void tick();

    public void draw(PApplet app) {
        app.image(this.sprite, this.x, this.y);
    }
}