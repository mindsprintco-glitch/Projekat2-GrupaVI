public class Enemy extends GameObject {
    private String type;
    private int damage;
    private int health;
 
    public Enemy(String type, int x, int y, int width, int height, int damage, int health) {
        super(x, y, width, height);
        setType(type);
        setDamage(damage);
        setHealth(health);
    }
 
    public String getType() { return type; }
    public int getDamage() { return damage; }
    public int getHealth() { return health; }
 
    public void setType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Tip neprijatelja ne smije biti prazan.");
        }
        this.type = type.trim();
    }
 
    public void setDamage(int damage) {
        if (damage < 0 || damage > 100) {
            throw new IllegalArgumentException("Damage mora biti između 0 i 100.");
        }
        this.damage = damage;
    }
 
    public void setHealth(int health) {
        if (health < 0 || health > 100) {
            throw new IllegalArgumentException("Health mora biti između 0 i 100.");
        }
        this.health = health;
    }
 
   
    public boolean typeContains(String query) {
        if (query == null) {
            return false;
        }
        return type.toLowerCase().contains(query.trim().toLowerCase());
    }
 
 
    public int getEffectiveDamage() {
        return damage;
    }
 
    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + type + "] @ (" + getX() + "," + getY() + ") "
                + getWidth() + "x" + getHeight() + " DMG=" + damage + " HP=" + health;
    }
}
