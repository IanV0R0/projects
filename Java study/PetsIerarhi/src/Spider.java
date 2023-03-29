public class Spider extends Pet {
    public Spider() {
        super("FSH", 8);
    }
    @Override
    public void giveVoice() {
        System.out.println(voice);
    }
}