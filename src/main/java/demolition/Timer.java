package demolition;

import processing.core.PImage;
import processing.core.PApplet;
import processing.core.PFont;

public class Timer{
    private int x;
    private int y;
    private int baseTime;
    private int time;
    private PImage sprite;
    private PFont font;
    private readMap rm2;
    private int timer;
    private boolean changed;
    public Timer(readMap rm, int x, int y, PFont font, PImage sprite, int time) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.time = time;
        this.font = font;
        this.rm2 = rm;
        this.baseTime = this.rm2.getLevelTime();
        this.timer = 0;
        this.changed = this.rm2.hasLevelChanged();
    }

    public void tick() {
        this.changed = this.rm2.hasLevelChanged();
        //handles logic
        //System.out.println(text(char c, float x, float y));
        if(this.changed == true) {
            this.baseTime = this.rm2.getLevelTime();
            this.timer = 0;
            
        }
        //System.out.println(this.timer);
        this.timer++;
    }

    public void setTime(int plTime) {
        this.time = plTime;
        //handles logic
        //System.out.println(text(char c, float x, float y));
    }
    public int getTime() {
        return this.time;
    }
    public int getPlayerTime() {
        //System.out.println(timer/60);
        return timer/60;
    }
    public void draw(PApplet app) {
        //handles graphics
        app.image(this.sprite, this.x, this.y);
        //app.text(180 - this.time, 290, 42);
        //app.text(this.time, 290.0, 16.0);
        app.textFont(font);
        app.fill(0, 0, 0);
        app.text(this.baseTime - getPlayerTime(), 290, 42);
        //app.text(244, 250, 16);
    }
}