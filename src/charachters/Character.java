package charachters;

public class Character {
    protected String dowmPath;
    protected String name;
    protected String upPath;
    protected String leftPath;
    protected String rightPath;
    protected int speed;
    protected int lives;
    protected int damage;
    protected int gold;
    protected boolean objectEquiped;

    public boolean isObjectEquiped() {
        return objectEquiped;
    }

    public void setObjectEquiped(boolean objectEquiped) {
        this.objectEquiped = objectEquiped;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getDowmPath() {
        return dowmPath;
    }

    public void setDowmPath(String dowmPath) {
        this.dowmPath = dowmPath;
    }

    public String getUpPath() {
        return upPath;
    }

    public void setUpPath(String upPath) {
        this.upPath = upPath;
    }

    public String getLeftPath() {
        return leftPath;
    }

    public void setLeftPath(String leftPath) {
        this.leftPath = leftPath;
    }

    public String getRightPath() {
        return rightPath;
    }

    public void setRightPath(String rightPath) {
        this.rightPath = rightPath;
    }
    public void addGold(){
        this.gold = this.gold+1;
    }
    public void addObject(){
        this.objectEquiped = true;
    }
    public void getDamageFromEnemy(){
        if (this.lives>0) {
            this.lives = this.lives - 1;
        }
    }
}
