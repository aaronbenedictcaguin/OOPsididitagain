package units;

import java.awt.*;

public abstract class Unit {

    public int x, y;
    public String name;
    public String role;
    public int curHp, maxHp;
    public int atk, magicAtk;
    public int def;
    public int energy;

    public int moveRange;
    public int attackRange;

    public abstract void draw(Graphics2D g2d, int tileSize, int offsetX, int offsetY);

    public int getX() { 
        return x; 
    }

    public int getY() { 
        return y; 
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Image picSmall;
    public Image picLarge;

    public String basicDesc;
    public String skillDesc;
    public String ultimateDesc;
}