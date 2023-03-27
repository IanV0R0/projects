// Выделите абстрактный класс Duck,
// который будет содержать общие свойства и методы классов FlyingDuck и RubberDuck
abstract class Duck1 {
    String name;
    protected Duck1(String name) {
        this.name = name;
    }

    public  String getName() {
        return name;
    }

    public void swim() {
        System.out.println("Да, я умею плавать!");
    }

    public void quack() {
        System.out.println("Кря!");
    }


}

class FlyingDuck extends Duck1{
    protected FlyingDuck() {
        super ("Я - обычная утка. Кря!");
    }
    @Override
    public String getName() {
        return name;
    }

    public void fly() {
        System.out.println("Лечу куда хочу.");
    }

    public void eat() {
        System.out.println("Обычно кушаю разные семена, но иногда нахожу хлебушек.");
    }
}

class RubberDuck extends Duck1 {
    protected RubberDuck() {
        super ("Я - резиновая уточка!");
    }
    @Override
    public String getName() {
        return name;
    }
}


public class Duck {

    public static void main(String[] args) {
        FlyingDuck flyingDuck = new FlyingDuck();
        RubberDuck rubberDuck = new RubberDuck();

        System.out.println("Сначала о себе расскажет обычная уточка:");
        System.out.println(flyingDuck.getName());
        flyingDuck.swim();
        flyingDuck.fly();
        flyingDuck.eat();
        flyingDuck.quack();

        System.out.println("А теперь - резиновая:");
        System.out.println(rubberDuck.getName());
        rubberDuck.swim();
        rubberDuck.quack();
    }

}