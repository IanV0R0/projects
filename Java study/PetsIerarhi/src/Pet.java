public abstract class Pet {
    protected int pawsCount;
    protected String voice;

    protected Pet(String voice, int pawsCount) {
        this.voice = voice;
        this.pawsCount = pawsCount;
    }

    public abstract void giveVoice();

    private String getVoice() {
        return voice;
    }

    private void setVoice(String voice) {
        this.voice = voice;
    }

    public void sleep() {
        System.out.println("Сплю");
    }

    public void play() {
        System.out.println("Играю");
    }

    public int getPawsCount() {
        return pawsCount;
    }
}
