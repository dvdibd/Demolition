package demolition;

import processing.core.PImage;
import processing.core.PApplet;
import java.util.Random;

public class RedEnemy extends MapComp{
    private int x;
    private int y;    
    private int move;
    private int moveType;
    private PImage sprite;
    private char[][] arr;
    private String str1;
    private int level;
    private int timer;
    private PImage font;
    private boolean isSafe;
    private int playerInitXPos;
    private int playerInitYPos;
    private int goalInitXPos;
    private int goalInitYPos;
    private int firstFrame;
    private boolean isMove;
    private int numberCont;

    private int timerSprite;
    private int spriteCounter;
    private int moveTypeSprite;
    private PImage spritePrint;

    private PImage spriteL1;
    private PImage spriteL2;
    private PImage spriteL3;
    private PImage spriteL4;
    private PImage spriteR1;
    private PImage spriteR2;
    private PImage spriteR3;
    private PImage spriteR4;
    private PImage spriteU1;
    private PImage spriteU2;
    private PImage spriteU3;
    private PImage spriteU4;
    private PImage spriteP1;
    private PImage spriteP2;
    private PImage spriteP3;
    private PImage spriteP4;    
    public RedEnemy(int x, int y, PImage spriteL1, PImage spriteL2, PImage spriteL3, PImage spriteL4, PImage spriteR1, PImage spriteR2, PImage spriteR3, PImage spriteR4, PImage spriteU1, PImage spriteU2, PImage spriteU3, PImage spriteU4, PImage spriteP1, PImage spriteP2, PImage spriteP3, PImage spriteP4, String str1) {
        this.spriteL1 = spriteL1;
        this.spriteL2 = spriteL2;
        this.spriteL3 = spriteL3;
        this.spriteL4 = spriteL4;
        this.spriteR1 = spriteR1;
        this.spriteR2 = spriteR2;
        this.spriteR3 = spriteR3;
        this.spriteR4 = spriteR4;
        this.spriteU1 = spriteU1;
        this.spriteU2 = spriteU2;
        this.spriteU3 = spriteU3;
        this.spriteU4 = spriteU4;
        this.spriteP1 = spriteP1;
        this.spriteP2 = spriteP2;
        this.spriteP3 = spriteP3;
        this.spriteP4 = spriteP4;
        this.moveTypeSprite = 0;
        this.spritePrint = spriteP4;
        this.spriteCounter = 0;        
                
        
        this.x = x;
        this.y = y;
        this.move = 0;
        this.moveType = 0;
        //this.arr = arr;
        this.str1 = str1;
        this.level = 1;
        this.timer = 0;
        this.isSafe = false;
        this.isMove = false;
        this.firstFrame = 0;
        this.numberCont = 4;
        this.arr = new char[13][15];
        int pos1=0;
        for(int i = 0; i < 13; i++)
            for(int j = 0; j < 15; j++) {
                //pos1 = (15*i)+j;
                arr[i][j] = str1.charAt(pos1);
                if(this.firstFrame == 0){
                    if(arr[i][j] == 'P'){
                        this.playerInitXPos = i;
                        this.playerInitYPos = j+2;
                    } else if(arr[i][j] == 'G'){
                        this.goalInitXPos = i;
                        this.goalInitYPos = j+2;
                    }
                }

                pos1++;
            }
        

    }
    public void setPos(int xCO, int yCO, int x, int y, char upd){
        arr[y][x] = upd;
        //mapTiles[x][y] = new Empty((x * 32), ((y + 2) * 32), this.emptyPic);
        //System.out.println(" updated player pos" + arr[x][y] + "ended");
    }
    public void tick() {
        //handles logic
        int xMapPos = this.x / 32;
        int yMapPos = ((this.y + 17) / 32)-2;
        int number = 1;
        if(this.timer == 1){
            //System.out.println(this.timer);

            if(numberCont == 1) {
                if((arr[yMapPos][xMapPos-1] != 'W') & (arr[yMapPos][xMapPos-1] != 'B') & ((xMapPos-1) > 0) ){
                    this.x += -32;
                    this.moveTypeSprite = 2;
                    //System.out.println("left");
                    isMove = true;
                } else {
                    number = getRandNum();
                    //System.out.println("no left");
                    isMove = false;
                }
            } else if(numberCont == 2) {
                if((arr[yMapPos + 1][xMapPos] != 'W') & (arr[yMapPos + 1][xMapPos] != 'B')  ){
                    this.y += 32;
                    //System.out.println("down");
                    this.moveTypeSprite = 0;
                    isMove = true;
                } else {
                    number = getRandNum();
                    //System.out.println("no down");
                    isMove = false;
                }
            } else if(numberCont == 3) {
                if((arr[yMapPos - 1][xMapPos] != 'W') & (arr[yMapPos - 1][xMapPos] != 'B')  ){
                    this.y += -32;
                    this.moveTypeSprite = 4;
                    //System.out.println("up");
                    //isMove = true;
                } else {
                    number = getRandNum();
                    //System.out.println("no up");
                    isMove = false;
                }
            } else if(numberCont == 4) {
                if((arr[yMapPos][xMapPos+1] != 'W') & (arr[yMapPos][xMapPos+1] != 'B') & ((xMapPos+1) < 14) ){
                    this.x += 32;
                    this.moveTypeSprite = 1;
                    //System.out.println("right");
                    //isMove = true;
                } else {
                    number = getRandNum();
                    //System.out.println("no right");
                    isMove = false;
                }
            }
            while(isMove == false) {
                if(number == 1) {
                    if((arr[yMapPos][xMapPos-1] != 'W') & (arr[yMapPos][xMapPos-1] != 'B') & ((xMapPos-1) > 0) ){
                        this.x += -32;
                        //System.out.println("left");
                        isMove = true;
                        numberCont = 1;
                        this.moveTypeSprite = 2;
                    } else {
                        number = getRandNum();
                        //System.out.println("jo left");
                        isMove = false;
                    }
                } else if(number == 2) {
                    if((arr[yMapPos + 1][xMapPos] != 'W') & (arr[yMapPos + 1][xMapPos] != 'B')  ){
                        this.y += 32;
                        //System.out.println("down");
                        isMove = true;
                        numberCont = 2;
                        this.moveTypeSprite = 0;
                    } else {
                        number = getRandNum();
                        //System.out.println("no down");
                        isMove = false;
                    }
                } else if(number == 3) {
                    if((arr[yMapPos - 1][xMapPos] != 'W') & (arr[yMapPos - 1][xMapPos] != 'B')  ){
                        this.y += -32;
                        //System.out.println("up");
                        isMove = true;
                        numberCont = 3;
                        this.moveTypeSprite = 4;
                    } else {
                        number = getRandNum();
                        //System.out.println("no up");
                        isMove = false;
                    }
                } else if(number == 4) {
                    if((arr[yMapPos][xMapPos+1] != 'W') & (arr[yMapPos][xMapPos+1] != 'B') & ((xMapPos+1) < 14) ){
                        this.x += 32;
                        //System.out.println("right");
                        isMove = true;
                        numberCont = 4;
                        this.moveTypeSprite = 1;
                    } else {
                        number = getRandNum();
                        //System.out.println("no right");
                        isMove = false;
                    }
                }
            }
        } 

        if(this.timerSprite == 12){
            //System.out.println("abcdef");
            if(spriteCounter == 0){
                //System.out.println("aaaaa");
                if(this.moveTypeSprite == 0){
                    this.spritePrint = this.spriteP1;
                } else if(this.moveTypeSprite == 1){
                    this.spritePrint = this.spriteR1;
                } else if(this.moveTypeSprite == 2) {
                    this.spritePrint = this.spriteL1;
                } else if(this.moveTypeSprite == 3) {
                    this.spritePrint = this.spriteP1;
                } else if(this.moveTypeSprite == 4) {
                    this.spritePrint = this.spriteU1;
                }
                spriteCounter++;
            } else if(spriteCounter == 1) {
                //System.out.println("bbbbbbbbbb");
                if(this.moveTypeSprite == 0){
                    this.spritePrint = this.spriteP2;
                } else if(this.moveTypeSprite == 1){
                    this.spritePrint = this.spriteR2;
                } else if(this.moveTypeSprite == 2) {
                    this.spritePrint = this.spriteL2;
                } else if(this.moveTypeSprite == 3) {
                    this.spritePrint = this.spriteP2;
                } else if(this.moveTypeSprite == 4) {
                    this.spritePrint = this.spriteU2;
                }
                spriteCounter++;
            } else if(spriteCounter == 2) {
                //System.out.println("ccccccccc");
                if(this.moveTypeSprite == 0){
                    this.spritePrint = this.spriteP3;
                } else if(this.moveTypeSprite == 1){
                    this.spritePrint = this.spriteR3;
                } else if(this.moveTypeSprite == 2) {
                    this.spritePrint = this.spriteL3;
                } else if(this.moveTypeSprite == 3) {
                    this.spritePrint = this.spriteP3;
                } else if(this.moveTypeSprite == 4) {
                    this.spritePrint = this.spriteU3;
                }
                spriteCounter++;
            } else if(spriteCounter == 3) {
                //System.out.println("dddddddddd");
                if(this.moveTypeSprite == 0){
                    this.spritePrint = this.spriteP4;
                } else if(this.moveTypeSprite == 1){
                    this.spritePrint = this.spriteR4;
                } else if(this.moveTypeSprite == 2) {
                    this.spritePrint = this.spriteL4;
                } else if(this.moveTypeSprite == 3) {
                    this.spritePrint = this.spriteP4;
                } else if(this.moveTypeSprite == 4) {
                    this.spritePrint = this.spriteU4;
                }
                spriteCounter = 0;
            }
            this.timerSprite = 0;
        }


        if(this.timer == 60){
            this.timer = 0;
            //isMove = false;
            //System.out.println(this.timer);
        }
        this.timer++;
        this.timerSprite++;
    }
   
    public int getRandNum() {
        int num = (int)(Math.random() * 4);
        //System.out.println(num);
        
        return num + 1;
        
    }
    public void setPlayerMap(String str2) {
        int pos1=0;
        for(int i = 0; i < 13; i++)
            for(int j = 0; j < 15; j++) {
                //pos1 = (15*i)+j;
                arr[i][j] = str2.charAt(pos1);
                if(this.firstFrame == 0){
                    if(arr[i][j] == 'P'){
                        this.playerInitXPos = i;
                        this.playerInitYPos = j+2;
                    } else if(arr[i][j] == 'G'){
                        this.goalInitXPos = i;
                        this.goalInitYPos = j+2;
                    }
                }


                //System.out.println(arr[i][j]);
                pos1++;
            }
    }
    public void setPlayerPosition(int[] playArr) {
        this.x = playArr[0];
        this.y = playArr[1];
    }
    public int getXCoOrdsREnemy() {
        return this.x;
    }

    public int getYCoOrdsREnemy() {
        return this.y;
    }
    public void draw(PApplet app) {
        //handles graphics
        app.image(this.spritePrint, this.x, this.y);
    }
}