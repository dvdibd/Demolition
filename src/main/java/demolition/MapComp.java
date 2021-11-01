package demolition;

import processing.core.PImage;
import processing.core.PApplet;

public abstract class MapComp {

    /*private int x;
    private int y;

    private PImage sprite;

    public MapComp(int x, int y, PImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }*/

    public abstract void tick();

    public abstract void draw(PApplet app);
}