package demolition;

import processing.core.PImage;
import processing.core.PApplet;
import processing.core.PFont;

public class Timer{
    private int x;
    private int y;
    private int time;
    private PImage sprite;
    private PFont font;

    public Timer(int x, int y, PFont font, PImage sprite, int time) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.time = time;
        this.font = font;
    }

    public void tick() {
        //handles logic
        //System.out.println(text(char c, float x, float y));
    }

    public void setTime(int plTime) {
        this.time = plTime;
        //handles logic
        //System.out.println(text(char c, float x, float y));
    }
    public int getTime() {
        return this.time;
    }
    

    public void draw(PApplet app) {
        //handles graphics
        app.image(this.sprite, this.x, this.y);
        //app.text(180 - this.time, 290, 42);
        //app.text(this.time, 290.0, 16.0);
        app.textFont(font);
        app.fill(0, 0, 0);
        app.text(180 - getTime(), 290, 42);
        //app.text(244, 250, 16);
    }
}