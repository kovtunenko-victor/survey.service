package fabrique.studio.test.task.errors;

public class SurveyServiceException extends Exception {   
    private static final long serialVersionUID = 1L;

    public SurveyServiceException() {
        super();
    }
    
    public SurveyServiceException(String message) {
        super(message);
    }
    
    public SurveyServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
