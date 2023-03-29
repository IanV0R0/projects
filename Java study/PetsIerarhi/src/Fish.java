public class Fish extends Pet {
    public Fish() {
        super("BULK", 3);
    }
    @Override
    public void giveVoice() {
        System.out.println(voice);
    }
}
