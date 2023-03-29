public class Practicum {

    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.catchMouse();
        cat.giveVoice();

        Dog dog = new Dog();
        dog.bringStick();
        dog.play();

        Hamster hamster = new Hamster();
        hamster.hideFood();
        hamster.sleep();

        Fish fish = new Fish();
        fish.sleep();

        Spider spider = new Spider();
        System.out.println("У паука " + spider.getPawsCount() + " лапок.");
    }
}

/*
Чтобы объявить абстрактный класс, используйте ключевое слово abstract перед объявлением класса
 — public abstract class Pet.
Чтобы указать, что классы наследуется от абстрактного, используйте ключевое слово extends и название
абстрактного класса — public class Cat extends Pet.
Реализации методов sleep(), play()  у всех питомцев совпадают — эти методы можно вынести в абстрактный
 класс в виде обычных, не абстрактных методов.
Во всех классах есть метод с одинаковой сигнатурой giveVoice(), но значение, которое возвращает метод,
 отличается. Это можно выразить с помощью добавления в класс Pet конструктора с
  параметром Pet(String voice) и перенести метод в абстрактный класс.
Ключевое слово super позволяет вызвать метод или конструктор суперкласса, а также обратиться к его полям.
 (Вызов конструктора класса-родителя через super должен быть первой строкой в конструкторе класса-наследника.)
Геттеры и сеттеры нужны для работы с полями класса, закрытыми модификатором private.
 */



/*
В уроке вы столкнулись с задачей выбора методов будущего абстрактного класса.
 Такие решения лучше принимать до написания программы. Этот этап — проектирование будущего решения,
  отличает дилетантов от профессионалов. Теперь, опираясь на результаты этапа проектирования,
   вы можете реализовать свою иерархию классов для питомцев.
У любого домашнего питомца Pet есть несколько действий:
спать sleep() (при вызове этого метода ваша программа должна выводить слово Сплю),
играть play() (программа должна вывести Играю)
издавать какой-то звук giveVoice().
а также свойство — количество лапок pawsCount.
Дополнительно к базовым возможностям, кошка может поймать мышку catchMouse()
 (в этом случае выведите Поймала мышку!), хомяк — спрятать еду hideFood() (Вся еда — в щёчках!),
  а собака — принести палку bringStick() (Принёс палочку, как хороший мальчик!).
 */