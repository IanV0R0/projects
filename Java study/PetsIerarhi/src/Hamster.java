public class Hamster extends Pet {
    public Hamster() {
        super("Yam", 4);
    }
    @Override
    public void giveVoice() {
        System.out.println(voice);
    }
    public void hideFood(){
        System.out.println("Вся еда — в щёчках!");
    }
}