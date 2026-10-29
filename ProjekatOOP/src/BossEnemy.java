public class BossEnemy extends Enemy {
 
    public BossEnemy(String type, int x, int y, int width, int height, int damage, int health) {
        super(type, x, y, width, height, damage, health);
    }
 
    @Override
    public int getEffectiveDamage() {
        return getDamage() * 2;
    }
 
    @Override
    public String toString() {
        return "BossEnemy[" + getType() + "] @ (" + getX() + "," + getY() + ") "
                + getWidth() + "x" + getHeight() + " DMG=" + getDamage() + "x2 HP=" + getHealth();
    }
}
 
