package charachters;

public class Wizard extends Character{
    private int potionUses = 3;
    public Wizard(){
        this.dowmPath = "src/images/wizard/wizard_down.gif";
        this.upPath = "src/images/wizard/wizard_up.gif";
        this.leftPath = "src/images/wizard/wizard_left.gif";
        this.rightPath = "src/images/wizard/wizard_right.gif";
        this.speed = 7;
        this.lives = 3;
    }
    public void usePotion(){
        potionUses = potionUses -1;
        if (potionUses==0){
            this.setObjectEquiped(false);
        }
    }
}
