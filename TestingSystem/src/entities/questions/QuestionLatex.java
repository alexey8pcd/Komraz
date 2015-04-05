package entities.questions;

import entities.CategoryOfComplexity;
import entities.Subject;
import entities.TypeOfQuestion;

/**
 * Сущность Вопрос с Latex записью
 * @author Алексей
 */
public class QuestionLatex extends Question {

    private String transcription;

    public QuestionLatex(String title, String formulation,
            int point, CategoryOfComplexity categoryOfComplexity,
            Subject subject, TypeOfQuestion typeOfQuestion,
            String transcription) {
        super(title, formulation, point,
                categoryOfComplexity, subject, typeOfQuestion);
        this.transcription = transcription;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }
    
    

}
