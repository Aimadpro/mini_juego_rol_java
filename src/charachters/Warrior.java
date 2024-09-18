package charachters;

public class Warrior extends Character{
    private int swordUses=3;
    final private String warriorAttackGifUp ="src/images/warrior/upWarriorGif.gif";
    final private String warriorAttackGifDown ="src/images/warrior/downWarriorGif.gif";
    final private String getWarriorAttackGifRight ="src/images/warrior/rightWarriorGif.gif";
    final private String getWarriorAttackGifLeft = "src/images/warrior/leftWarriorGif.gif";

    public Warrior(){
        this.dowmPath = "src/images/warrior/warrior_down.gif";
        this.upPath = "src/images/warrior/warrior_up.gif";
        this.leftPath = "src/images/warrior/warrior_left.gif";
        this.rightPath = "src/images/warrior/warrior_right.gif";
        this.speed = 4;
        this.lives=5;
    }

    public int getSwordUses() {
        return swordUses;
    }


    public String getWarriorAttackGifUp() {
        return warriorAttackGifUp;
    }

    public String getWarriorAttackGifDown() {
        return warriorAttackGifDown;
    }

    public String getGetWarriorAttackGifRight() {
        return getWarriorAttackGifRight;
    }

    public String getGetWarriorAttackGifLeft() {
        return getWarriorAttackGifLeft;
    }

    public void useSword(){

        swordUses = swordUses-1;
        if (swordUses==0)
            this.setObjectEquiped(false);
        }
    }
