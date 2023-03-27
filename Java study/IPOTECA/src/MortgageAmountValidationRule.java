// Дополните класс для проверки суммы ипотеки пользователя
public class MortgageAmountValidationRule extends ValidationRule <Integer>  {

    public MortgageAmountValidationRule(Integer value) {
        super(value, "Минимальный размер ипотеки - 1.000.000, а максимальный - 10.000.000");
    }

    // Объявите и реализуйте метод для проверки суммы ипотеки ниже
    @Override
    public boolean isValid() {
        boolean result = true;
        if (value <= 10000000 && value >= 1000000){
            result = true;
        }
        else result = false;
        return result;
    }

}