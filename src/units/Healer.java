package units;

import java.awt.*;

public class Healer extends Unit {

    public Healer(int x, int y) {
        this.x = x;
        this.y = y;

        this.name = "Grand Eleanor";

        this.maxHp = 2200;
        this.curHp = maxHp;
        this.atk = 130;
        this.magicAtk = 200;
        this.def = 540;
        this.energy = 3;

        this.moveRange = 2;
        this.attackRange = 1;
    }

    @Override
    public void draw(Graphics2D g2d, int tileSize, int offsetX, int offsetY) {
        g2d.setColor(Color.GREEN);
        g2d.fillOval(
            offsetX + x * tileSize + 10,
            offsetY + y * tileSize + 10,
            tileSize - 20,
            tileSize - 20
        );
    }
}