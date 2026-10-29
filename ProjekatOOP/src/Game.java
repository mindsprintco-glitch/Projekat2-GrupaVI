import java.util.ArrayList;

public class Game {
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<String> eventLog;

    public Game(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player ne smije biti null.");
        }
        this.player = player;
        this.enemies = new ArrayList<>();
        this.eventLog = new ArrayList<>();
    }

    public Player getPlayer() { return player; }
    public ArrayList<Enemy> getEnemies() { return enemies; }
    public ArrayList<String> getEventLog() { return eventLog; }

   
    public boolean checkCollision(Player p, Enemy e) {
        return p.intersects(e);
    }

    
    public void decreaseHealth(Player p, Enemy e) {
        int damage = e.getEffectiveDamage();
        int oldHealth = p.getHealth();
        int newHealth = Math.max(0, oldHealth - damage);
        p.setHealth(newHealth);
        eventLog.add("HIT: Player by " + e.getType() + " for " + damage
                + " -> HP " + oldHealth + " -> " + newHealth);
    }

    public void addEnemy(Enemy e) {
        if (e == null) {
            throw new IllegalArgumentException("Neprijatelj ne smije biti null.");
        }
        enemies.add(e);
        eventLog.add("ADD: Enemy " + e.getType() + " added at (" + e.getX() + "," + e.getY() + ")");
    }

    public ArrayList<Enemy> findByType(String query) {
        ArrayList<Enemy> result = new ArrayList<>();
        for (Enemy e : enemies) {
            if (e.typeContains(query)) {
                result.add(e);
            }
        }
        return result;
    }

   
    public ArrayList<Enemy> collidingWithPlayer() {
        ArrayList<Enemy> result = new ArrayList<>();
        for (Enemy e : enemies) {
            if (checkCollision(player, e)) {
                result.add(e);
            }
        }
        return result;
    }


    public void resolveCollisions() {
        for (Enemy e : collidingWithPlayer()) {
            decreaseHealth(player, e);
        }
    }

 
    public static Enemy parseEnemy(String line) {
        String[] parts = line.split(";");
        if (parts.length < 4) {
            throw new IllegalArgumentException("Neispravan format: " + line);
        }
        String type = parts[0].trim();
        String[] pos = parts[1].trim().split(",");
        String[] size = parts[2].trim().toLowerCase().split("x");
        int x = Integer.parseInt(pos[0].trim());
        int y = Integer.parseInt(pos[1].trim());
        int w = Integer.parseInt(size[0].trim());
        int h = Integer.parseInt(size[1].trim());
        int damage = Integer.parseInt(parts[3].trim());
        int health = 100; 

        boolean boss = parts.length > 4 && parts[4].trim().equalsIgnoreCase("boss");
        if (boss) {
            return new BossEnemy(type, x, y, w, h, damage, health);
        }
        return new MeleeEnemy(type, x, y, w, h, damage, health);
    }

    public static void main(String[] args) {
        Player player = new Player("  marko   marković ", 10, 5, 32, 32, 85);
        Game game = new Game(player);

        game.addEnemy(new MeleeEnemy("Wolf", 100, 100, 16, 16, 15, 40));
        game.addEnemy(parseEnemy("Goblin;12,5;16x16;20;boss"));

        System.out.println(" *Neprijatelji* ");
        for (Enemy e : game.getEnemies()) {
            System.out.println(e);
        }

        System.out.println("\n *Stanje igrača prije kolizije* ");
        System.out.println(game.getPlayer());

        game.resolveCollisions();

        System.out.println("\n *Stanje igrača poslije kolizije* ");
        System.out.println(game.getPlayer());

        System.out.println("\n *Event log* ");
        for (String entry : game.getEventLog()) {
            System.out.println(entry);
        }
    }
}