package charachters;

public class Priest extends Character{
    public Priest(){
        this.dowmPath = "src/images/priest/priest_down.gif";
        this.upPath = "src/images/priest/priest_up.gif";
        this.leftPath = "src/images/priest/priest_left.gif";
        this.rightPath = "src/images/priest/priest_right.gif";
        this.speed = 5;
        this.lives =4;
    }
    public void useObject(){
        this.lives=4;
        this.setObjectEquiped(false);
    }

}
