package demolition;

import processing.core.PImage;
import processing.core.PApplet;
import processing.core.PFont;

public class EndScreen{

    private PFont font;
    private boolean getEnd;

    public EndScreen(PFont font) {
        this.font = font;
        this.getEnd = false;
    }

    public void tick() {

    }

    public void endGame(boolean ifEndGame){
        if(ifEndGame == true) {
            this.getEnd = true;
        }
    } 
    public boolean getEndGame() {
        return this.getEnd;
    }
    public void draw(PApplet app) {
        
        app.textFont(font);
        app.fill(0, 0, 0);
        app.text("YOU WIN", 190, 220);
        //app.text(244, 250, 16);
    }
}