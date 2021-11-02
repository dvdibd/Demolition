package demolition;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONArray;
import processing.core.PFont;
import processing.data.JSONObject;
import java.util.ArrayList;

public class App extends PApplet {
    public static final int WIDTH = 480;
    public static final int HEIGHT = 480;
    public static final int FPS = 60;

    private Player player;
    private Timer timer;
    private Lives lives;
    private YellowEnemy enemyY;
    private RedEnemy enemyR;
    private Wall wall;
    
    private MapComp[][] mapTiles;
    private char[][] maparr;
    private String mapstr;
    private String mapstrUpdated;
    private int[] playerPos;
    private int[] enemyYPos;
    private int[] enemyRPos;
    private PImage wallImage;
    private PImage goal;
    private PImage emptyPic;
    private PImage broken;
    private PImage timerImg;
    private PImage bombImage;
    private PImage bombImage1;
    private PImage bombImage2;
    private PImage bombImage3;
    private PImage bombImage4;
    private PImage bombImage5;
    private PImage bombImage6;
    private PImage bombImage7;
    private PImage bombImage8;
    private PImage center;
    private PImage endBottom;
    private PImage endLeft;
    private PImage endRight;
    private PImage endTop;
    private PImage horizontal;
    private PImage vertical;
    private PImage yellow1;
    private PImage yellow2;
    private PImage yellow3;
    private PImage yellow4;
    private PImage yellow5;
    private PImage yellow6;
    private PImage yellow7;
    private PImage yellow8;
    private PImage yellow9;
    private PImage yellow10;
    private PImage yellow11;
    private PImage yellow12;
    private PImage yellow13;
    private PImage yellow14;
    private PImage yellow15;
    private PImage yellow16;
    private PImage red1;
    private PImage red2;
    private PImage red3;
    private PImage red4;
    private PImage red5;
    private PImage red6;
    private PImage red7;
    private PImage red8;
    private PImage red9;
    private PImage red10;
    private PImage red11;
    private PImage red12;
    private PImage red13;
    private PImage red14;
    private PImage red15;
    private PImage red16;
    private boolean hasYEnemy = false;
    private boolean hasREnemy = false;
    private PFont font;
    private ArrayList<Bomb> bombs;
    private boolean setBomb = false;
    private boolean bombAdded = false;
    private int bombXCoords;
    private int bombYCoords;
    private Bomb bomb;

    public App() {
        this.bombs = new ArrayList<Bomb>();
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }
    readMap rm;
    public void setup() {
        frameRate(FPS);
        // Load images during setup
        this.wallImage = this.loadImage("src/main/resources/wall/solid.png");
        this.goal = this.loadImage("src/main/resources/goal/goal.png");
        this.emptyPic = this.loadImage("src/main/resources/empty/empty.png");
        this.broken = this.loadImage("src/main/resources/broken/broken.png");
        this.timerImg = this.loadImage("src/main/resources/icons/clock.png");
        this.bombImage = this.loadImage("src/main/resources/bomb/bomb.png");
        this.bombImage1 = this.loadImage("src/main/resources/bomb/bomb1.png");
        this.bombImage2 = this.loadImage("src/main/resources/bomb/bomb2.png");
        this.bombImage3 = this.loadImage("src/main/resources/bomb/bomb3.png");
        this.bombImage4 = this.loadImage("src/main/resources/bomb/bomb4.png");
        this.bombImage5 = this.loadImage("src/main/resources/bomb/bomb5.png");
        this.bombImage6 = this.loadImage("src/main/resources/bomb/bomb6.png");
        this.bombImage7 = this.loadImage("src/main/resources/bomb/bomb7.png");
        this.bombImage8 = this.loadImage("src/main/resources/bomb/bomb8.png");
        this.center = this.loadImage("src/main/resources/explosion/centre.png");
        this.endBottom = this.loadImage("src/main/resources/explosion/end_bottom.png");
        this.endLeft = this.loadImage("src/main/resources/explosion/end_left.png");
        this.endRight = this.loadImage("src/main/resources/explosion/end_right.png");
        this.endTop = this.loadImage("src/main/resources/explosion/end_top.png");
        this.horizontal = this.loadImage("src/main/resources/explosion/horizontal.png");
        this.vertical = this.loadImage("src/main/resources/explosion/vertical.png");
        this.yellow1 = this.loadImage("src/main/resources/yellow_enemy/yellow_left1.png");
        this.yellow2 = this.loadImage("src/main/resources/yellow_enemy/yellow_left2.png");
        this.yellow3 = this.loadImage("src/main/resources/yellow_enemy/yellow_left3.png");
        this.yellow4 = this.loadImage("src/main/resources/yellow_enemy/yellow_left4.png");
        this.yellow5 = this.loadImage("src/main/resources/yellow_enemy/yellow_right1.png");
        this.yellow6 = this.loadImage("src/main/resources/yellow_enemy/yellow_right2.png");
        this.yellow7 = this.loadImage("src/main/resources/yellow_enemy/yellow_right3.png");
        this.yellow8 = this.loadImage("src/main/resources/yellow_enemy/yellow_right4.png");
        this.yellow9 = this.loadImage("src/main/resources/yellow_enemy/yellow_up1.png");
        this.yellow10 = this.loadImage("src/main/resources/yellow_enemy/yellow_up2.png");
        this.yellow11 = this.loadImage("src/main/resources/yellow_enemy/yellow_up3.png");
        this.yellow12 = this.loadImage("src/main/resources/yellow_enemy/yellow_up4.png");
        this.yellow13 = this.loadImage("src/main/resources/yellow_enemy/yellow_down1.png");
        this.yellow14 = this.loadImage("src/main/resources/yellow_enemy/yellow_down2.png");
        this.yellow15 = this.loadImage("src/main/resources/yellow_enemy/yellow_down3.png");
        this.yellow16 = this.loadImage("src/main/resources/yellow_enemy/yellow_down4.png");
        this.red1 = this.loadImage("src/main/resources/red_enemy/red_left1.png");
        this.red2 = this.loadImage("src/main/resources/red_enemy/red_left2.png");
        this.red3 = this.loadImage("src/main/resources/red_enemy/red_left3.png");
        this.red4 = this.loadImage("src/main/resources/red_enemy/red_left4.png");
        this.red5 = this.loadImage("src/main/resources/red_enemy/red_right1.png");
        this.red6 = this.loadImage("src/main/resources/red_enemy/red_right2.png");
        this.red7 = this.loadImage("src/main/resources/red_enemy/red_right3.png");
        this.red8 = this.loadImage("src/main/resources/red_enemy/red_right4.png");
        this.red9 = this.loadImage("src/main/resources/red_enemy/red_up1.png");
        this.red10 = this.loadImage("src/main/resources/red_enemy/red_up2.png");
        this.red11 = this.loadImage("src/main/resources/red_enemy/red_up3.png");
        this.red12 = this.loadImage("src/main/resources/red_enemy/red_up4.png");
        this.red13 = this.loadImage("src/main/resources/red_enemy/red_down1.png");
        this.red14 = this.loadImage("src/main/resources/red_enemy/red_down2.png");
        this.red15 = this.loadImage("src/main/resources/red_enemy/red_down3.png");
        this.red16 = this.loadImage("src/main/resources/red_enemy/red_down4.png");
        this.font = createFont("src/main/resources/PressStart2P-Regular.ttf" , 15);
        JSONObject json1 = loadJSONObject("config.json");
        //this.bombs.add(new Bomb(400, 210, bombImage));
        //readMap rm = new readMap(json1, wallImage, goal, emptyPic, broken);
        this.rm = new readMap(json1, wallImage, goal, emptyPic, broken, yellow1, yellow2, yellow3, yellow4, yellow5, yellow6, yellow7, yellow8, yellow9, yellow10, yellow11, yellow12, yellow13, yellow14, yellow15, yellow16, red1, red2, red3, red4, red5, red6, red7, red8, red9, red10, red11, red12, red13, red14, red15, red16);
        mapTiles = rm.getMap();
        maparr = rm.getArr();
        playerPos = rm.getPlayArr();
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 13; i++)
            for(int j = 0; j < 15; j++)
                builder.append(maparr[i][j]);
        mapstr = builder.toString();
        mapstr = mapstr.replace('Y' , ' ');
        mapstr = mapstr.replace('R' , ' ');
        mapstr = mapstr.replace('P' , ' ');
        //System.out.println();
        this.player = new Player(this.bomb, this.rm, playerPos[0], playerPos[1], this.font, this.loadImage("src/main/resources/player/player_left1.png"), this.loadImage("src/main/resources/player/player_left2.png"), this.loadImage("src/main/resources/player/player_left3.png"), this.loadImage("src/main/resources/player/player_left4.png"), this.loadImage("src/main/resources/player/player_right1.png"), this.loadImage("src/main/resources/player/player_right2.png"), this.loadImage("src/main/resources/player/player_right3.png"), this.loadImage("src/main/resources/player/player_right4.png"), this.loadImage("src/main/resources/player/player_up1.png"), this.loadImage("src/main/resources/player/player_up2.png"), this.loadImage("src/main/resources/player/player_up3.png"), this.loadImage("src/main/resources/player/player_up4.png"), this.loadImage("src/main/resources/player/player1.png"), this.loadImage("src/main/resources/player/player2.png"), this.loadImage("src/main/resources/player/player3.png"), this.loadImage("src/main/resources/player/player4.png"), mapstr);
        //this.player = new Player(playerPos[0], playerPos[1], this.loadImage("src/main/resources/player/player.gif"));
        /*
        if(rm.hasYEnemy() == true){
            hasYEnemy = true;
            enemyYPos = rm.getEnemyYArr();
            this.enemyY = new YellowEnemy(enemyYPos[0], enemyYPos[1], this.loadImage("src/main/resources/yellow_enemy/yellow_left1.png"), this.loadImage("src/main/resources/yellow_enemy/yellow_left2.png"), this.loadImage("src/main/resources/yellow_enemy/yellow_left3.png"), this.loadImage("src/main/resources/yellow_enemy/yellow_left4.png"), this.loadImage("src/main/resources/yellow_enemy/yellow_right1.png"), this.loadImage("src/main/resources/yellow_enemy/yellow_right2.png"), this.loadImage("src/main/resources/yellow_enemy/yellow_right3.png"), this.loadImage("src/main/resources/yellow_enemy/yellow_right4.png"), this.loadImage("src/main/resources/yellow_enemy/yellow_up1.png"), this.loadImage("src/main/resources/yellow_enemy/yellow_up2.png"), this.loadImage("src/main/resources/yellow_enemy/yellow_up3.png"), this.loadImage("src/main/resources/yellow_enemy/yellow_up4.png"), this.loadImage("src/main/resources/yellow_enemy/yellow_down1.png"), this.loadImage("src/main/resources/yellow_enemy/yellow_down2.png"), this.loadImage("src/main/resources/yellow_enemy/yellow_down3.png"), this.loadImage("src/main/resources/yellow_enemy/yellow_down4.png"), mapstr);
        }
        if(rm.hasREnemy() == true){
            hasREnemy = true;
            enemyRPos = rm.getEnemyRArr();
            this.enemyR = new RedEnemy(enemyRPos[0], enemyRPos[1], this.loadImage("src/main/resources/red_enemy/red_left1.png"), this.loadImage("src/main/resources/red_enemy/red_left2.png"), this.loadImage("src/main/resources/red_enemy/red_left3.png"), this.loadImage("src/main/resources/red_enemy/red_left4.png"), this.loadImage("src/main/resources/red_enemy/red_right1.png"), this.loadImage("src/main/resources/red_enemy/red_right2.png"), this.loadImage("src/main/resources/red_enemy/red_right3.png"), this.loadImage("src/main/resources/red_enemy/red_right4.png"), this.loadImage("src/main/resources/red_enemy/red_up1.png"), this.loadImage("src/main/resources/red_enemy/red_up2.png"), this.loadImage("src/main/resources/red_enemy/red_up3.png"), this.loadImage("src/main/resources/red_enemy/red_up4.png"), this.loadImage("src/main/resources/red_enemy/red_down1.png"), this.loadImage("src/main/resources/red_enemy/red_down2.png"), this.loadImage("src/main/resources/red_enemy/red_down3.png"), this.loadImage("src/main/resources/red_enemy/red_down4.png"), mapstr);
        }*/
        this.timer = new Timer(this.rm, 250, 16, this.font, this.loadImage("src/main/resources/icons/clock.png"), getTime());
        this.lives = new Lives(this.rm, 150, 16, this.font, this.loadImage("src/main/resources/icons/player.png"));
        //this.timer = new Timer(250, 16, this.font, bombImage, getTime());
        //System.out.println(this.player.getPlayerTime());
        //this.bombs.add(new Bomb(250, 250, bombImage));
    }

    public void draw() {
        this.fill(256,148,4);
        
        //this.timer = new Timer(250, 16, this.font, bombImage, getTime());

        
        //this.bomb.tick();
        //this.player.tick();
        if(this.rm.getHasYEnemy() == true)
            this.rm.getEnemyY().tick();
        if(this.rm.getHasREnemy() == true)
            this.rm.getEnemyR().tick();
        for (int i=0; i<13; i++)
            for (int j=0; j<15; j++)
                this.mapTiles[i][j].tick();

        this.timer.tick();
        this.lives.tick();
        if(this.bombAdded == true){
            for (Bomb bombCurr : this.bombs) {
                bombCurr.tick();
                
                //System.out.println(bombCurr.bombIndex());
                //System.out.println("inside tickbomb");
                //if(bombCurr.isExploded() == true)
                    //this.bombs.remove(bombCurr.bombIndex());
                //else {
                    //this.bombs.remove(bombCurr.bombIndex());
                //}
            }
        }
        this.player.tick();
        this.rect(-1, -1, WIDTH + 2, HEIGHT + 2);
        

        
        for (int i=0; i<13; i++)
            for (int j=0; j<15; j++) 
                this.mapTiles[i][j].draw(this);

        //this.player.draw(this);
        if(this.rm.getHasYEnemy() == true)
            this.rm.getEnemyY().draw(this);
        /*
        if(hasREnemy == true)
            this.enemyR.draw(this);
        */
        if(this.rm.getHasREnemy() == true)
            this.rm.getEnemyR().draw(this);

        this.timer.setTime(getTime());
        this.timer.draw(this);
        this.lives.draw(this);
        if(this.bombAdded == true){
            for (Bomb bombCurr : this.bombs) {
                bombCurr.draw(this);
                this.mapstrUpdated = bombCurr.stringUpdated();
                //System.out.println(this.mapstrUpdated);
            }
        }
        if(this.setBomb == true){
            bombs.add(new Bomb(this.lives, this.player, this.rm, this.bombXCoords, this.bombYCoords, this.rm.getStringArr(), bombs.size(), bombImage, bombImage1, bombImage2, bombImage3, bombImage4, bombImage5, bombImage6, bombImage7, bombImage8, center, endBottom, endLeft, endRight, endTop, horizontal, vertical));
            //System.out.println("size    ==    " + bombs.size());
            this.bombAdded = true;
            this.setBomb = false;
            
        }
        this.player.draw(this);
        //this.timer.setTime(getTime());
        //this.bomb.draw(this);
        //System.out.println(getTime());
        //this.rm.setMap(this.mapstrUpdated);
        

    }

    public void keyPressed() {
        if (this.keyCode == 37) {
            this.player.moveLeft();
        } else if (this.keyCode == 38) {
            this.player.moveUp();
        } else if (this.keyCode == 39) {
            this.player.moveRight();
        } else if (this.keyCode == 40) {
            this.player.moveDown();
        } else if (this.keyCode == 32) {
            //this.player.moveDown();
            this.setBomb = true;
            this.bombXCoords = this.player.getXCoOrds();
            this.bombYCoords = this.player.getYCoOrds() + 17;
            //System.out.println("XXXXXXXXX    " + this.player.getXCoOrds());
            //System.out.println("YYYYYYYYY    " + this.player.getYCoOrds());
            //System.out.println(this.setBomb);
        }
        
    }

    public char[][] getMapArr() {
        return maparr;
    }

    public int getTime() {
        
        return this.player.getPlayerTime();
    }


    public static void main(String[] args) {
        PApplet.main("demolition.App");
    }
}

//gradle run
//gradle test
//gradle jacocoTestReport