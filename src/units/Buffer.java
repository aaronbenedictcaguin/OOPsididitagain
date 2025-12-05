package units;

import java.awt.*;

public class Buffer extends Unit {

    public Buffer(int x, int y) {
        this.x = x;
        this.y = y;

        this.name = "Genisis Sage";

        this.maxHp = 1700;
        this.curHp = maxHp;
        this.atk = 250;
        this.magicAtk = 300;
        this.def = 270;
        this.energy = 6;

        this.moveRange = 2;
        this.attackRange = 1;
    }

    @Override
    public void draw(Graphics2D g2d, int tileSize, int offsetX, int offsetY) {
        g2d.setColor(Color.RED);
        g2d.fillOval(
            offsetX + x * tileSize + 10,
            offsetY + y * tileSize + 10,
            tileSize - 20,
            tileSize - 20
        );
    }
}