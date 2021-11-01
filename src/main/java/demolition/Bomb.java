package demolition;

import processing.core.PImage;
import processing.core.PApplet;
import processing.core.PFont;

public class Bomb {
    private int x;
    private int y;
    private int timer;
    private PImage sprite;
    private PImage sprite1;
    private PImage sprite2;
    private PImage sprite3;
    private PImage sprite4;
    private PImage sprite5;
    private PImage sprite6;
    private PImage sprite7;
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
    private PImage horizontalImg;
    private PImage vertical;
    private boolean explode;
    private int bombIndex;
    private boolean exploded;
    private char[][] arr;
    private char[] arrTxt;
    private String str1;
    private String mapstrUpd;
    private readMap rm1;
    private Player playerupdated;
    private RedEnemy redupdated;
    //public Player(int x, int y, PImage sprite, char[][] arr) {
    public Bomb(Player player, readMap rm1, int x, int y, String str, int indx, PImage bombImage, PImage bombImage1, PImage bombImage2, PImage bombImage3, PImage bombImage4, PImage bombImage5, PImage bombImage6, PImage bombImage7, PImage bombImage8, PImage center, PImage endBottom, PImage endLeft, PImage endRight, PImage endTop, PImage horizontal, PImage vertical) {
    //public Player(int x, int y, PImage sprite) {
        this.playerupdated = player;
        this.rm1 = rm1;
        this.str1 = str;
        this.mapstrUpd = "";
        this.x = x;
        this.y = y;
        this.timer = 0;
        this.bombIndex = indx;
        this.explode = false;
        this.exploded = false;
        this.sprite = bombImage;
        this.sprite1 = bombImage;
        this.sprite2 = bombImage;
        this.sprite3 = bombImage;
        this.sprite4 = bombImage;
        this.sprite5 = bombImage;
        this.sprite6 = bombImage;
        this.sprite7 = bombImage;
        this.bombImage = bombImage;
        this.bombImage1 = bombImage1;
        this.bombImage2 = bombImage2;
        this.bombImage3 = bombImage3;
        this.bombImage4 = bombImage4;
        this.bombImage5 = bombImage5;
        this.bombImage6 = bombImage6;
        this.bombImage7 = bombImage7;
        this.bombImage8 = bombImage8;
        this.center = center;
        this.endBottom = endBottom;
        this.endLeft = endLeft;
        this.endRight = endRight;
        this.endTop = endTop;
        this.horizontalImg = horizontal;
        this.vertical = vertical;
        this.arr = new char[13][15];
        //this.arrTxt = new char[1];
        int pos1=0;
        //System.out.println("hhhh");
        for(int i = 0; i < 13; i++)
            for(int j = 0; j < 15; j++) {
                //pos1 = (15*i)+j;
                arr[i][j] = str1.charAt(pos1);
                //System.out.println("hhhh");
                pos1++;
            }
        
        /*
        for(int i = 0; i < 13; i++){
            for(int j = 0; j < 15; j++)
                System.out.print(arr[i][j]);
            System.out.println();
        }*/
    }

    public void tick() {
        //System.out.println("inside tickbomb");
        if(isExploded() == false){
            if(this.timer==15){
                this.sprite = this.bombImage;
            } else if(this.timer == 15){
                this.sprite = this.bombImage1;
            } else if(this.timer == 30){
                this.sprite = this.bombImage2;
            } else if(this.timer == 45){
                this.sprite = this.bombImage3;
            } else if(this.timer == 60){
                this.sprite = this.bombImage4;
            } else if(this.timer == 75){
                this.sprite = this.bombImage5;
            } else if(this.timer == 90){
                this.sprite = this.bombImage6;
            } else if(this.timer == 105){
                this.sprite = this.bombImage7;
            } else if(this.timer == 120){
                this.sprite = this.bombImage8;
                //this.timer = 0;
                
            } else if((this.timer > 119) && (this.timer < 171)){
                //System.out.println(this.timer);
                this.sprite = this.bombImage8;
                this.explode = true;
                if(this.timer == 170){
                    this.timer = 0;
                    this.explode = false;
                    this.exploded = true;
                }
            }
            this.timer++;
        }
    }

    public boolean isExploded() {
        return this.exploded;
    }

    public int bombIndex() {
        return this.bombIndex;
    }
    
    public String stringUpdated() {
        return this.mapstrUpd;
    }

    public void setPlayerMap(String str2) {
        int pos1=0;
        for(int i = 0; i < 13; i++)
            for(int j = 0; j < 15; j++) {
                //pos1 = (15*i)+j;
                arr[i][j] = str2.charAt(pos1);
                //System.out.println(arr[i][j]);
                pos1++;
            }
    }
    
    public void draw(PApplet app) {
        //handles graphics
        //System.out.println("inside draw bomb: x and y = "+this.x+"   "+this.y);
        int xMapPos = this.x / 32;
        int yMapPos = ((this.y) / 32) -2;
        //System.out.println("xxxx" + xMapPos);
        //System.out.println("yyyy" + yMapPos);
        if(isExploded() == false){
            app.image(this.sprite, this.x, this.y); 
            if(explode == true){
                /*
                app.image(this.center, this.x, this.y);
                System.out.println( "NEXT TILE:::" + arr[xMapPos + 1][yMapPos] + "AAA");
                //System.out.println(arr[xMapPos + 1][yMapPos]);
                if((arr[xMapPos + 1][yMapPos] == ' ') || (arr[xMapPos + 1][yMapPos] == 'B')) {                
                    app.image(this.horizontalImg, this.x + 32, this.y);
                    if(arr[xMapPos + 1][yMapPos] == 'B'){
                        //System.out.println( "NEXT TILE:::" + arr[xMapPos + 1][yMapPos] + "AAA");
                        //arr[xMapPos + 1][yMapPos] = ' ';
                        rm1.setPos(this.x + 32, this.y, xMapPos + 1 , yMapPos, ' ');
                        this.playerupdated.setPos(this.x +32, this.y, xMapPos + 1 , yMapPos, ' ');
                        arr[xMapPos + 1][yMapPos] = ' ';
                        //System.out.println( "XXXXXXXXXXXXXXX    " + this.x);
                        //System.out.println( "YYYYYYYYYYYYYYY    " + this.y);
                    } else if((arr[xMapPos + 2][yMapPos] == ' ') ){
                        app.image(this.endRight, this.x + 64, this.y);
                    }
                }

                if((arr[xMapPos][yMapPos + 1] == ' ') || (arr[xMapPos][yMapPos + 1] == 'B')) {  
                    app.image(this.vertical, this.x, this.y + 32);
                    //if((arr[xMapPos][yMapPos + 2] == ' ') || (arr[xMapPos][yMapPos + 2] == 'B')){
                    app.image(this.endBottom, this.x, this.y + 64);
                    //}
                }
                if((arr[xMapPos - 1][yMapPos] == ' ') || (arr[xMapPos - 1][yMapPos] == 'B')) {  
                    app.image(this.horizontalImg, this.x - 32, this.y);
                    if((arr[xMapPos - 2][yMapPos] == ' ') || (arr[xMapPos - 2][yMapPos] == 'B')){
                        app.image(this.endLeft, this.x - 64, this.y);
                    }
                }
                //if((arr[xMapPos][yMapPos] == ' ') && (arr[xMapPos][yMapPos] == 'B'))
                //if((arr[xMapPos][yMapPos + 1] == ' ') && (arr[xMapPos][yMapPos + 1] == 'B')) {

                //}
                //if((arr[xMapPos][yMapPos] == ' ') && (arr[xMapPos][yMapPos] == 'B'))
                if((arr[xMapPos][yMapPos - 1] == ' ') || (arr[xMapPos][yMapPos - 1] == 'B')) { 
                    app.image(this.endTop, this.x, this.y - 32);
                }*/
                app.image(this.center, this.x, this.y);

                if((arr[yMapPos + 1][xMapPos] == ' ') || (arr[yMapPos + 1][xMapPos] == 'B')) {
                    app.image(this.vertical, this.x, this.y + 32);
                    if(arr[yMapPos + 1][xMapPos] == 'B'){
                        rm1.setPos(this.x, this.y + 32, xMapPos , yMapPos + 1, ' ');
                        this.playerupdated.setPos(this.x, this.y + 32, xMapPos, yMapPos + 1 , ' ');
                        //this.redupdated.setPos(this.x, this.y + 32, xMapPos, yMapPos + 1 , ' ');
                        arr[yMapPos + 1][xMapPos] = ' ';
                    } else if((arr[yMapPos + 2][xMapPos] == ' ') || (arr[yMapPos + 2][xMapPos] == 'B') ){
                        app.image(this.endBottom, this.x, this.y + 64); 
                        System.out.println("XXXXXX" + arr[yMapPos + 2][xMapPos]);
                        if(arr[yMapPos + 2][xMapPos] == 'B'){
                            //System.out.println("XXXXXX" + xMapPos);
                            //System.out.println("YYYYYY" + (yMapPos + 2));
                            rm1.setPos(this.x, this.y + 64, xMapPos , yMapPos + 2, ' ');
                            this.playerupdated.setPos(this.x, this.y + 64, xMapPos, yMapPos + 2 , ' ');

                            //this.redupdated.setPos(this.x, this.y + 64, xMapPos, yMapPos + 2 , ' ');
                            
                            arr[yMapPos + 2][xMapPos] = ' ';
                        }
                    }
                }

                if((arr[yMapPos][xMapPos + 1] == ' ') || (arr[yMapPos][xMapPos + 1] == 'B')) {                
                    app.image(this.horizontalImg, this.x + 32, this.y);
                    if(arr[yMapPos][xMapPos + 1] == 'B'){
                        rm1.setPos(this.x + 32, this.y, xMapPos + 1 , yMapPos, ' ');
                        this.playerupdated.setPos(this.x + 32, this.y, xMapPos + 1 , yMapPos, ' ');
                        //this.redupdated.setPos(this.x +32, this.y, xMapPos + 1 , yMapPos, ' ');
                        //arr[yMapPos + 1][xMapPos] = ' ';
                        arr[yMapPos][xMapPos + 1] = ' ';
                    } else if((arr[yMapPos][xMapPos + 2] == ' ') || (arr[yMapPos][xMapPos + 2] == 'B') || (xMapPos + 2) < 13){
                        app.image(this.endRight, this.x + 64, this.y);
                        if(arr[yMapPos][xMapPos + 2] == 'B'){
                            rm1.setPos(this.x + 64, this.y, xMapPos + 2 , yMapPos, ' ');
                            this.playerupdated.setPos(this.x + 64, this.y, xMapPos + 2 , yMapPos, ' ');
                            //this.redupdated.setPos(this.x + 64, this.y, xMapPos + 2 , yMapPos, ' ');
                            //arr[yMapPos + 2][xMapPos] = ' ';
                            arr[yMapPos][xMapPos + 2] = ' ';
                        }
                    }
                }

                if((arr[yMapPos][xMapPos - 1] == ' ') || (arr[yMapPos][xMapPos - 1] == 'B')) {  
                    app.image(this.horizontalImg, this.x - 32, this.y);
                    //app.image(this.endLeft, this.x - 64, this.y);
                    if(arr[yMapPos][xMapPos - 1] == 'B'){
                        rm1.setPos(this.x - 32, this.y, xMapPos - 1 , yMapPos, ' ');
                        this.playerupdated.setPos(this.x - 32, this.y, xMapPos - 1 , yMapPos, ' ');
                        arr[xMapPos - 1][yMapPos] = ' ';
                    } else if((arr[yMapPos][xMapPos - 2] == ' ') || (arr[yMapPos][xMapPos - 2] == 'B') ){
                        app.image(this.endLeft, this.x - 64, this.y);
                        if(arr[yMapPos][xMapPos - 2] == 'B'){
                            rm1.setPos(this.x - 64, this.y, xMapPos - 2 , yMapPos, ' ');
                            this.playerupdated.setPos(this.x - 64, this.y, xMapPos - 2 , yMapPos, ' ');
                            arr[xMapPos - 2][yMapPos] = ' ';
                        }
                    }
                }
                //if((arr[yMapPos + 1][xMapPos] == ' ') || (arr[yMapPos + 1][xMapPos] == 'B')) {

                if((arr[yMapPos - 1][xMapPos] == ' ') || (arr[yMapPos - 1][xMapPos] == 'B')) {
                    app.image(this.endTop, this.x, this.y - 32);
                    if(arr[yMapPos - 1][xMapPos] == 'B'){
                        rm1.setPos(this.x, this.y - 32, xMapPos , yMapPos - 1, ' ');
                        this.playerupdated.setPos(this.x, this.y - 32, xMapPos, yMapPos - 1 , ' ');
                        //this.redupdated.setPos(this.x, this.y - 32, xMapPos, yMapPos - 1 , ' ');
                        arr[yMapPos - 1][xMapPos] = ' ';
                    }
                }
            }
            
        }
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 13; i++)
            for(int j = 0; j < 15; j++)
                builder.append(arr[i][j]);
        this.mapstrUpd = builder.toString();
    }
}