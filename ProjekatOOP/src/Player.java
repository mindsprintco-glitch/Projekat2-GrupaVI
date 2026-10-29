public class Player extends GameObject {
    private String name;
    private int health;
 
    public Player(String name, int x, int y, int width, int height, int health) {
        super(x, y, width, height);
        setName(name);
        setHealth(health);
    }
 
    public String getName() { return name; }
    public int getHealth() { return health; }
 
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Ime igrača ne smije biti prazno.");
        }
        this.name = capitalizeWords(name);
    }
 
    public void setHealth(int health) {
        if (health < 0 || health > 100) {
            throw new IllegalArgumentException("Health mora biti između 0 i 100.");
        }
        this.health = health;
    }
 
 
    private static String capitalizeWords(String text) {
        String[] words = text.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(Character.toUpperCase(w.charAt(0)));
            sb.append(w.substring(1).toLowerCase());
        }
        return sb.toString();
    }
 
    @Override
    public String toString() {
        return "Player[" + name + "] @ (" + getX() + "," + getY() + ") "
                + getWidth() + "x" + getHeight() + " HP=" + health;
    }
}
 