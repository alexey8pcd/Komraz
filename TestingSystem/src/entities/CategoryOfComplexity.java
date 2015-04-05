package entities;

/**
 * Сущность категория сложности
 * @author Алексей
 */
public class CategoryOfComplexity {
    private String name;

    public CategoryOfComplexity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
