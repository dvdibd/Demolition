package demolition;

import processing.core.PImage;
import processing.core.PApplet;
import processing.core.PFont;

public class YouLose{

    private PFont font;
    private boolean getLoss;

    public YouLose(PFont font) {
        this.font = font;
        this.getLoss = false;
    }

    public void tick() {

    }

    public void endGame(boolean ifEndGame){
        if(ifEndGame == true) {
            this.getLoss = true;
        }
    } 
    public boolean getLoseGame() {
        return this.getLoss;
    }
    public void draw(PApplet app) {
        
        app.textFont(font);
        app.fill(1, 1, 1);
        app.text("GAME OVER", 190, 220);
        //app.text(244, 250, 16);
    }
}