
public class GameObject {
    private int x;
    private int y;
    private int width;
    private int height;
	
    
    public GameObject(int x, int y, int width, int height) {
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
	}


	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	
	
	 public boolean intersects(GameObject other) {
	        if (other == null) {
	            return false;
	        }
	        return this.x < other.x + other.width
	                && this.x + this.width > other.x
	                && this.y < other.y + other.height
	                && this.y + this.height > other.y;
	    }
	
	 
	@Override
    public String toString() {
        return "GameObject @ (" + x + "," + y + ") " + width + "x" + height;
	}    
























}
