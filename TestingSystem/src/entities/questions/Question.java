package entities.questions;

import entities.CategoryOfComplexity;
import entities.Subject;
import entities.TypeOfQuestion;

/**
 * Сущность вопрос
 *
 * @author Алексей
 */
public abstract class Question {

    private String title;
    private String formulation;
    private int point;
    private CategoryOfComplexity categoryOfComplexity;
    private Subject subject;
    private TypeOfQuestion typeOfQuestion;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public TypeOfQuestion getTypeOfQuestion() {
        return typeOfQuestion;
    }

    public void setTypeOfQuestion(TypeOfQuestion typeOfQuestion) {
        this.typeOfQuestion = typeOfQuestion;
    }

    public Question(String title, String formulation,
            int point, CategoryOfComplexity categoryOfComplexity,
            Subject subject, TypeOfQuestion typeOfQuestion) {
        this.title = title;
        this.formulation = formulation;
        if (point < 1) {
            point = 1;
        }
        this.point = point;
        this.categoryOfComplexity = categoryOfComplexity;
        this.typeOfQuestion = typeOfQuestion;
        this.subject = subject;
    }

    public CategoryOfComplexity getCategoryOfComplexity() {
        return categoryOfComplexity;
    }

    public void setCategoryOfComplexity(CategoryOfComplexity categoryOfComplexity) {
        this.categoryOfComplexity = categoryOfComplexity;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFormulation() {
        return formulation;
    }

    public void setFormulation(String text) {
        this.formulation = text;
    }

}
