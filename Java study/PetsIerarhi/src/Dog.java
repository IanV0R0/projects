public class Dog extends Pet {
    public Dog() {
        super("woof", 4);
    }
    @Override
    public void giveVoice() {
        System.out.println(voice);
    }
    public void bringStick() {
        System.out.println("Принёс палочку, как хороший мальчик!");
    }
}