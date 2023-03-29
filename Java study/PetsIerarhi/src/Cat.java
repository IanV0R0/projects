public class Cat extends Pet{
    public  Cat() {
        super("МЯУ", 4);
    }
    @Override
    public void giveVoice(){
        System.out.println(voice);
    }
    public void catchMouse() {
        System.out.println("Поймал мышку");
    }

}
