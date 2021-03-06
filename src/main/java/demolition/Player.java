package demolition;

import processing.core.PImage;
import processing.core.PApplet;
import processing.core.PFont;
import java.util.Scanner;

public class Player extends MapComp{
    private int x;
    private int y;
    private int move;
    private int moveType;
    private char[][] arr;
    private char[] arrTxt;
    private String str1;
    private int level;
    private int timer;
    private int timerSprite;
    //private PImage font;
    private boolean isSafe;
    private int playerInitXPos;
    private int playerInitYPos;
    private int goalInitXPos;
    private int goalInitYPos;
    private int firstFrame;
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
    private PFont font;
    private readMap rm1;
    private boolean goalReached = false;
    private Bomb bomb;
    private YellowEnemy yEnemy;
    private int yEnemyPosX;
    private int yEnemyPosY;
    private int rEnemyPosX;
    private int rEnemyPosY;
    private RedEnemy rEnemy;
    private Lives lives;
    private YouLose loseSend;
    private Timer timerMain;
    //public Player(int x, int y, PImage sprite, char[][] arr) {
    public Player(Timer timer, YouLose loseSend, Lives lives, RedEnemy rEnemy, YellowEnemy yEnemy, Bomb bomb, readMap rm1, int x, int y, PFont font, PImage spriteL1, PImage spriteL2, PImage spriteL3, PImage spriteL4, PImage spriteR1, PImage spriteR2, PImage spriteR3, PImage spriteR4, PImage spriteU1, PImage spriteU2, PImage spriteU3, PImage spriteU4, PImage spriteP1, PImage spriteP2, PImage spriteP3, PImage spriteP4, String str1) {
    //public Player(int x, int y, PImage sprite) {
        this.timerMain = timer;
        this.loseSend = loseSend;
        this.lives = lives;
        this.rEnemy = rEnemy;
        this.yEnemyPosX = 0;
        this.yEnemyPosY = 0;
        this.rEnemyPosX = 0;
        this.rEnemyPosY = 0;
        this.yEnemy = yEnemy;
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
        this.font = font;
        this.x = x;
        this.y = y;
        this.move = 0;
        this.moveType = 0;
        //this.arr = arr;
        this.str1 = str1;
        this.level = 1;
        this.timer = 0;
        this.isSafe = false;
        this.firstFrame = 0;
        this.arr = new char[13][15];
        this.rm1 = rm1;
        this.bomb = bomb;
        //this.arrTxt = new char[1];
        
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


                //System.out.println(arr[i][j]);
                pos1++;
            }
        
        for(int i = 0; i < 13; i++){
            for(int j = 0; j < 15; j++)
                System.out.print(arr[i][j]);
            System.out.println();
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
        if(goalReached == false){
            if((this.rm1.getHasYEnemy() == true) && (this.rm1.getEnemyY().getIfYEnemyDied() == false)){
                this.yEnemyPosX = this.rm1.getEnemyY().getXCoOrdsYEnemy();
                this.yEnemyPosY = this.rm1.getEnemyY().getYCoOrdsYEnemy();
                if((this.yEnemyPosX == this.x) && (this.yEnemyPosY == this.y)){
                    this.lives.changeLives();
                    
                    if(this.lives.getLives() > 0) {
                        rm1.setParaForLevel();
                        setPlayerMap(rm1.getStringArr());
                        setPlayerPosition(rm1.getPlayArr());
                        //this.timerMain.resetTime(true);
                        this.moveType = 0;
                    } else {
                        this.loseSend.endGame(true);
                    }
                }
                //System.out.println("YENEMY XXXX" + this.yEnemyPosX);
                //System.out.println("YENEMY YYYY" + this.yEnemyPosY);
                //System.out.println("player XXXX" + this.x);
                //System.out.println("player YYYY" + this.y);
            }
            if((this.rm1.getHasREnemy() == true) && (this.rm1.getEnemyR().getIfREnemyDied() == false)){
                this.rEnemyPosX = this.rm1.getEnemyR().getXCoOrdsREnemy();
                this.rEnemyPosY = this.rm1.getEnemyR().getYCoOrdsREnemy();
                if((this.rEnemyPosX == this.x) && (this.rEnemyPosY == this.y)){
                    //this.lives.changeLives();
                    this.lives.changeLives();
                    
                    if(this.lives.getLives() > 0){
                        rm1.setParaForLevel();
                        setPlayerMap(rm1.getStringArr());
                        setPlayerPosition(rm1.getPlayArr());
                        //this.timerMain.resetTime(true);
                        this.moveType = 0;
                    } else {
                        this.loseSend.endGame(true);
                    }
                }
                //System.out.println("YENEMY XXXX" + this.yEnemyPosX);
                //System.out.println("YENEMY YYYY" + this.yEnemyPosY);
                //System.out.println("player XXXX" + this.x);
                //System.out.println("player YYYY" + this.y);
            }
            if(this.moveType == 1){
                if((arr[yMapPos][xMapPos + 1] != 'W') & (arr[yMapPos][xMapPos + 1] != 'B') & ((xMapPos + 1) < 14) ){
                    this.x += this.move;
                    this.moveType = 0;
                    /*
                    if(this.rm1.getHasYEnemy() == true){
                        this.yEnemyPosX = this.rm1.getEnemyY().getXCoOrdsYEnemy();
                        this.yEnemyPosY = this.rm1.getEnemyY().getYCoOrdsYEnemy();
                        if((this.yEnemyPosX == this.x) && (this.yEnemyPosY == this.y)){
                            this.lives.changeLives();
                            rm1.setParaForLevel();
                            setPlayerMap(rm1.getStringArr());
                            setPlayerPosition(rm1.getPlayArr());
                        }
                        //System.out.println("YENEMY XXXX" + this.yEnemyPosX);
                        //System.out.println("YENEMY YYYY" + this.yEnemyPosY);
                        //System.out.println("player XXXX" + this.x);
                        //System.out.println("player YYYY" + this.y);
                    }*/
                    if(arr[yMapPos][xMapPos + 1] == 'G'){
                        System.out.println("GOALLLLLLLLLLL");
                        
                        rm1.setPlayerLevel(true);
                        if(rm1.setEnd() == false) {
                            rm1.setParaForLevel();
                            setPlayerMap(rm1.getStringArr());
                            setPlayerPosition(rm1.getPlayArr());

                            //this.bomb.setPlayerMap(rm1.getStringArr());
                            goalReached = true;
                        }
                    }
                }
            } else if(this.moveType == 2) {
                if((arr[yMapPos][xMapPos - 1] != 'W') & (arr[yMapPos][xMapPos - 1] != 'B') & ((xMapPos - 1) > 0) ){
                    this.x += this.move;
                    this.moveType = 0;
                    /*
                    if(this.rm1.getHasYEnemy() == true){
                        this.yEnemyPosX = this.rm1.getEnemyY().getXCoOrdsYEnemy();
                        this.yEnemyPosY = this.rm1.getEnemyY().getYCoOrdsYEnemy();
                        if((this.yEnemyPosX == this.x) && (this.yEnemyPosY == this.y)){
                            this.lives.changeLives();
                            rm1.setParaForLevel();
                            setPlayerMap(rm1.getStringArr());
                            setPlayerPosition(rm1.getPlayArr());
                        }
                        //System.out.println("YENEMY XXXX" + this.yEnemyPosX);
                        //System.out.println("YENEMY YYYY" + this.yEnemyPosY);
                        //System.out.println("player XXXX" + this.x);
                        //System.out.println("player YYYY" + this.y);
                    }*/
                    if(arr[yMapPos][xMapPos - 1] == 'G'){
                        System.out.println("GOALLLLLLLLLLL");
                        rm1.setPlayerLevel(true);
                        if(rm1.setEnd() == false) {
                            rm1.setParaForLevel();
                            setPlayerMap(rm1.getStringArr());
                            setPlayerPosition(rm1.getPlayArr());
                            //this.bomb.setPlayerMap(rm1.getStringArr());
                            goalReached = true;
                        }
                    }
                }
            }else if(this.moveType == 3) {
                if((arr[yMapPos + 1][xMapPos] != 'W') & (arr[yMapPos + 1][xMapPos] != 'B')  ){
                    this.y += this.move;
                    this.moveType = 0;
                    /*
                    if(this.rm1.getHasYEnemy() == true){
                        this.yEnemyPosX = this.rm1.getEnemyY().getXCoOrdsYEnemy();
                        this.yEnemyPosY = this.rm1.getEnemyY().getYCoOrdsYEnemy();
                        if((this.yEnemyPosX == this.x) && (this.yEnemyPosY == this.y)){
                            this.lives.changeLives();
                            rm1.setParaForLevel();
                            setPlayerMap(rm1.getStringArr());
                            setPlayerPosition(rm1.getPlayArr());
                        }
                        //System.out.println("YENEMY XXXX" + this.yEnemyPosX);
                        //System.out.println("YENEMY YYYY" + this.yEnemyPosY);
                        //System.out.println("player XXXX" + this.x);
                        //System.out.println("player YYYY" + this.y);
                    }*/
                    if(arr[yMapPos + 1][xMapPos] == 'G') {
                        System.out.println("GOALLLLLLLLLLL");
                        rm1.setPlayerLevel(true);
                        if(rm1.setEnd() == false) {
                            rm1.setParaForLevel();
                            setPlayerMap(rm1.getStringArr());
                            setPlayerPosition(rm1.getPlayArr());
                            //this.bomb.setPlayerMap(rm1.getStringArr());
                            goalReached = true;
                        }
                    }
                }
            }else if(this.moveType == 4) {
                if((arr[yMapPos - 1][xMapPos] != 'W') & (arr[yMapPos - 1][xMapPos] != 'B')  ){
                    this.y += this.move;
                    this.moveType = 0;
                    /*
                    if(this.rm1.getHasYEnemy() == true){
                        this.yEnemyPosX = this.rm1.getEnemyY().getXCoOrdsYEnemy();
                        this.yEnemyPosY = this.rm1.getEnemyY().getYCoOrdsYEnemy();
                        if((this.yEnemyPosX == this.x) && (this.yEnemyPosY == this.y)){
                            this.lives.changeLives();
                            rm1.setParaForLevel();
                            setPlayerMap(rm1.getStringArr());
                            setPlayerPosition(rm1.getPlayArr());
                        }
                        //System.out.println("YENEMY XXXX" + this.yEnemyPosX);
                        //System.out.println("YENEMY YYYY" + this.yEnemyPosY);
                        //System.out.println("player XXXX" + this.x);
                        //System.out.println("player YYYY" + this.y);
                    }*/
                    if(arr[yMapPos - 1][xMapPos] == 'G') {
                        System.out.println("GOALLLLLLLLLLL");
                        rm1.setPlayerLevel(true);
                        if(rm1.setEnd() == false) {
                            rm1.setParaForLevel();
                            setPlayerMap(rm1.getStringArr());
                            setPlayerPosition(rm1.getPlayArr());
                            //this.bomb.setPlayerMap(rm1.getStringArr());
                            goalReached = true;
                        }
                    }
                }
            }
            //this.spritePrint = this.spriteL1;
            //System.out.println("AAAAAAAAAAAAAAAAAAAAA");
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
                //isMove = false;
                //System.out.println(this.timer);
            }        
            this.timer++;
            this.timerSprite++;
        }
        //System.out.println(getPlayerTime());
        goalReached = rm1.getPlayerStatus();
        
    }

    public boolean moveRight() {
        this.move = 32;
        this.moveType = 1;
        this.moveTypeSprite = 1;
        return true;
    }
    public boolean moveLeft() {
        this.move = -32;
        this.moveType = 2;
        this.moveTypeSprite = 2;
        return true;
    }
    public boolean moveDown() {
        this.move = 32;
        this.moveType = 3;
        this.moveTypeSprite = 3;
        return true;
    }
    public boolean moveUp() {
        this.move = -32;
        this.moveType = 4;
        this.moveTypeSprite = 4;
        return true;
    }

    public int getPlayerTime() {
        //System.out.println(timer/60);
        return timer/60;
    }
    
    public int getPlayerLevel() {
        return 0;
    }

    public int getXCoOrds() {
        return this.x;
    }

    public int getYCoOrds() {
        return this.y;
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
    public void resetPlayerPosition(boolean playerReset) {
        if(playerReset = true) {
            setPlayerPosition(rm1.getPlayArr());
            rm1.setParaForLevel();
            setPlayerMap(rm1.getStringArr());
        }
        
    }
    public void setPlayerPosition(int[] playArr) {
        this.x = playArr[0];
        this.y = playArr[1];
        
    }

    public void draw(PApplet app) {
        //handles graphics
        if(goalReached == false)
            app.image(this.spritePrint, this.x, this.y);
        
        //app.createFont("PressStart2P-Regular.ttf", 12, true, );
        
        //app.textFont(font);
        //app.fill(0, 0, 0);
        //app.text(180 - getPlayerTime(), 290, 42);
        //app.text(244, 250, 16);
        this.firstFrame++;
    }
}