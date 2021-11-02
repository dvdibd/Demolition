package demolition;

import processing.core.PImage;
import processing.core.PApplet;

import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;

public class readMap {
    private Wall wall;
    private Goal goal;
    private PImage emptyPic;
    private PImage goalPic;
    private PImage wallImage;
    private PImage broken;
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
    private JSONObject json1;
    private Empty empty;
    private YellowEnemy enemyY;
    private RedEnemy enemyR;
    //private Broken broken;
    private MapComp[][] mapTiles = new MapComp[13][15];
    private char[][] chararr = new char[13][15];
    private char[][] arr = new char[13][15];
    private char[][] chararrCurr = new char[13][15];
    private int[] playerP = new int[2];
    private int[] enemyYArr = new int[2];
    private int[] enemyRArr = new int[2];
    private boolean hasYEnemy = false;
    private boolean hasREnemy = false;
    private boolean playerStatus = false;
    private boolean levelChanged = false;
    private int levelTime;
    private int levels;
    private int currLevel = 0;
    private String mapstr;
    private String updatedMap = "";
    private String levelPath;
    public readMap( JSONObject json1, PImage wallImage , PImage goal, PImage emptyPic, PImage broken, PImage yellow1, PImage yellow2, PImage yellow3, PImage yellow4, PImage yellow5, PImage yellow6, PImage yellow7, PImage yellow8, PImage yellow9, PImage yellow10, PImage yellow11, PImage yellow12, PImage yellow13, PImage yellow14, PImage yellow15, PImage yellow16, PImage red1, PImage red2, PImage red3, PImage red4, PImage red5, PImage red6, PImage red7, PImage red8, PImage red9, PImage red10, PImage red11, PImage red12, PImage red13, PImage red14, PImage red15, PImage red16) {
        this.yellow1 = yellow1;
        this.yellow2 = yellow2;
        this.yellow3 = yellow3;
        this.yellow4 = yellow4;
        this.yellow5 = yellow5;
        this.yellow6 = yellow6;
        this.yellow7 = yellow7;
        this.yellow8 = yellow8;
        this.yellow9 = yellow9;
        this.yellow10 = yellow10;
        this.yellow11 = yellow11;
        this.yellow12 = yellow12;
        this.yellow13 = yellow13;
        this.yellow14 = yellow14;
        this.yellow15 = yellow15;
        this.yellow16 = yellow16;
        this.red1 = red1;
        this.red2 = red2;
        this.red3 = red3;
        this.red4 = red4;
        this.red5 = red5;
        this.red6 = red6;
        this.red7 = red7;
        this.red8 = red8;
        this.red9 = red9;
        this.red10 = red10;
        this.red11 = red11;
        this.red12 = red12;
        this.red13 = red13;
        this.red14 = red14;
        this.red15 = red15;
        this.red16 = red16;        
        this.emptyPic  = emptyPic;
        this.goalPic = goal;
        this.wallImage = wallImage;
        this.broken = broken;
        this.json1 = json1;
        int xMapVal = 0;
        int yMapVal = 64;
        levelPath = json1.getJSONArray("levels").getJSONObject(currLevel).getString("path");
        levels = json1.getJSONArray("levels").size();
        levelTime = json1.getJSONArray("levels").getJSONObject(currLevel).getInt("time");
        //System.out.println(levelTime);
        try {
            File myObj = new File(levelPath);
            Scanner myReader = new Scanner(myObj);
            int count = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                char[] ch=data.toCharArray();   
                for(int i = 0; i<15; i++){
                    chararr[count][i] = ch[i];
                    //System.out.println("");
                }
                count++;
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 13; i++)
            for(int j = 0; j < 15; j++)
                builder.append(chararr[i][j]);
        mapstr = builder.toString();
        
        for(int i = 0; i < 13; i++)
            for(int j = 0; j < 15; j++) {
                //pos1 = (15*i)+j;
                chararrCurr[i][j] = chararr[i][j];
            }
        hasREnemy = false;
        hasYEnemy = false;
        for (int i=0; i<13; i++) {
            xMapVal = 0;
            for (int j=0; j<15; j++) {
                char sym = chararrCurr[i][j];
                //mapTiles[i][j] = new Wall(xMapVal, yMapVal, wallImage);
                if(sym=='W'){
                    mapTiles[i][j] = new Wall(xMapVal, yMapVal, wallImage);
                } else if(sym=='G'){
                    mapTiles[i][j] = new Goal(xMapVal, yMapVal, goalPic);
                }  else if(sym==' '){
                    mapTiles[i][j] = new Empty(xMapVal, yMapVal, emptyPic);
                }  else if(sym=='P'){
                    //String str1 = "XXXXX";
                    //mapTiles[i][j] = new Player(xMapVal, yMapVal, emptyPic, chararr);
                    mapTiles[i][j] = new Empty(xMapVal, yMapVal, emptyPic);
                    //mapTiles[i][j] = new Player(xMapVal, yMapVal, emptyPic);
                    playerP[0] = xMapVal;
                    playerP[1] = yMapVal-17;
                }  else if(sym=='Y'){
                    mapTiles[i][j] = new Empty(xMapVal, yMapVal, emptyPic);
                    enemyYArr[0] = xMapVal;
                    enemyYArr[1] = yMapVal-17;
                    enemyY= new YellowEnemy(enemyYArr[0], enemyYArr[1], yellow1, yellow2, yellow3, yellow4, yellow5, yellow6, yellow7, yellow8, yellow9, yellow10, yellow11, yellow12, yellow13, yellow14, yellow15, yellow16, mapstr);
                    hasYEnemy = true;
                }  else if(sym=='R'){
                    mapTiles[i][j] = new Empty(xMapVal, yMapVal, emptyPic);
                    enemyRArr[0] = xMapVal;
                    enemyRArr[1] = yMapVal-17;
                    enemyR= new RedEnemy(enemyRArr[0], enemyRArr[1], red1, red2, red3, red4, red5, red6, red7, red8, red9, red10, red11, red12, red13, red14, red15, red16, mapstr);
                    hasREnemy = true;
                }  else if(sym=='B'){
                    mapTiles[i][j] = new Broken(xMapVal, yMapVal, broken);
                }
                xMapVal += 32; 
            }
            yMapVal += 32;
        }
    }

    public MapComp[][] getMap() {
        return mapTiles;
    }

    public char[][] getArr() {
        return chararr;
    }
    public String getStringArr() {
        return mapstr;
    }
    public int[] getPlayArr() {
        return playerP;
    }
    public int[] getEnemyYArr() {
        return enemyYArr;
    }
    public int[] getEnemyRArr() {
        return enemyRArr;
    }
    public boolean hasYEnemy() {
        return hasYEnemy;
    }
    public boolean hasREnemy() {
        return hasREnemy;
    }
    public boolean hasLevelChanged() {
        boolean change = levelChanged;
        levelChanged = false;
        return change;

    }

    /*public int setPlayerLives() {

    }*/



    public void setPlayerLevel(boolean changeLevel) {
        if(changeLevel == true){
            if(currLevel < (levels + 1 )){
                
                currLevel = currLevel + 1;
                System.out.println("GOALLLLLLLLLLL  bjbhjbhj " + currLevel);
                levelChanged = true;
            }
        } else
            currLevel = currLevel;
    }
    public void setParaForLevel() {
        levelPath = json1.getJSONArray("levels").getJSONObject(currLevel).getString("path");
        levelTime = json1.getJSONArray("levels").getJSONObject(currLevel).getInt("time");
        int xMapVal = 0;
        int yMapVal = 64;
        try {
            File myObj = new File(levelPath);
            Scanner myReader = new Scanner(myObj);
            int count = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                char[] ch=data.toCharArray();   
                for(int i = 0; i<15; i++){
                    chararr[count][i] = ch[i];
                    //System.out.println("");
                }
                count++;
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 13; i++)
            for(int j = 0; j < 15; j++)
                builder.append(chararr[i][j]);
        mapstr = builder.toString();
        //setPlayerMap(String str2)
        for(int i = 0; i < 13; i++)
            for(int j = 0; j < 15; j++) {
                //pos1 = (15*i)+j;
                chararrCurr[i][j] = chararr[i][j];
            }
        enemyR = null;
        enemyY = null;
        hasREnemy = false;
        hasYEnemy = false;
        for (int i=0; i<13; i++) {
            xMapVal = 0;
            for (int j=0; j<15; j++) {
                char sym = chararrCurr[i][j];
                //mapTiles[i][j] = new Wall(xMapVal, yMapVal, wallImage);
                if(sym=='W'){
                    mapTiles[i][j] = new Wall(xMapVal, yMapVal, wallImage);
                } else if(sym=='G'){
                    mapTiles[i][j] = new Goal(xMapVal, yMapVal, goalPic);
                }  else if(sym==' '){
                    mapTiles[i][j] = new Empty(xMapVal, yMapVal, emptyPic);
                }  else if(sym=='P'){
                    //String str1 = "XXXXX";
                    //mapTiles[i][j] = new Player(xMapVal, yMapVal, emptyPic, chararr);
                    mapTiles[i][j] = new Empty(xMapVal, yMapVal, emptyPic);
                    //mapTiles[i][j] = new Player(xMapVal, yMapVal, emptyPic);
                    playerP[0] = xMapVal;
                    playerP[1] = yMapVal-17;
                }  else if(sym=='Y'){
                    mapTiles[i][j] = new Empty(xMapVal, yMapVal, emptyPic);
                    enemyYArr[0] = xMapVal;
                    enemyYArr[1] = yMapVal-17;
                    enemyY = new YellowEnemy(enemyYArr[0], enemyYArr[1], yellow1, yellow2, yellow3, yellow4, yellow5, yellow6, yellow7, yellow8, yellow9, yellow10, yellow11, yellow12, yellow13, yellow14, yellow15, yellow16, mapstr);
                    hasYEnemy = true;
                }  else if(sym=='R'){
                    mapTiles[i][j] = new Empty(xMapVal, yMapVal, emptyPic);
                    enemyRArr[0] = xMapVal;
                    enemyRArr[1] = yMapVal-17;
                    enemyR = new RedEnemy(enemyRArr[0], enemyRArr[1], red1, red2, red3, red4, red5, red6, red7, red8, red9, red10, red11, red12, red13, red14, red15, red16, mapstr);
                    hasREnemy = true;
                }  else if(sym=='B'){
                    mapTiles[i][j] = new Broken(xMapVal, yMapVal, broken);
                }
                xMapVal += 32; 
            }
            yMapVal += 32;
        }

        playerStatus = false;
    }
    
    public void setPos(int xCO, int yCO, int x, int y, char upd){
        chararrCurr[y][x] = upd;
        mapTiles[y][x] = new Empty(xCO, yCO, this.emptyPic);
        //System.out.println(" cvsdvsdvsd   " + mapTiles[y][x] + "end");
        //System.out.println( "updated map x coordinates  " + xCO);
        //System.out.println( "updated map y coordinates  " + yCO);
    }

    public boolean getPlayerStatus() {
        return playerStatus;
    }
    public YellowEnemy getEnemyY() {
        return enemyY;
    }
    public RedEnemy getEnemyR() {
        return enemyR;
    }
    public boolean getHasYEnemy() {
        //System.out.println("YYYYYY " + hasYEnemy);
        return hasYEnemy;

    }
    public boolean getHasREnemy() {
        //System.out.println("XXXXX " + hasREnemy);
        return hasREnemy;
    }
    public void setMap(String updated) {
        updatedMap = updated;
    }
    public int getLevelTime() {
        return levelTime;
    }
}