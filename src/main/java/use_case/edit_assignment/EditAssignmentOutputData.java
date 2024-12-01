package src.main.java.use_case.edit_assignment;
/**
 * Output Data for the Edit Assignment Use Case.
 */
public class EditAssignmentOutputData {

    private final float newScore;

    private final boolean useCaseFailed;

    public EditAssignmentOutputData(float newScore, boolean useCaseFailed) {
        this.newScore = newScore;
        this.useCaseFailed = useCaseFailed;
    }

    public float getNewScore() {return newScore;}

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
