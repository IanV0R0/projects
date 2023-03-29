package shop.product;

public enum Category {
    FOOD("Food"),
    TECH("Technical stuff"),
    SPORT("Sport items"),
    CLOTHES("Casual clothes");

    private final String description;

    Category(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "description='" + description + '\'' +
                '}';
    }
}
