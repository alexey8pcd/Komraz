package entities;

/**
 * Сущность тип вопроса
 * @author Алексей
 */
public class TypeOfQuestion {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeOfQuestion(String name) {
        this.name = name;
    }
}
