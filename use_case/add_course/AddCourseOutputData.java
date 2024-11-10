package use_case.add_course;

/**
 * Output Data for the Add Course Use Case.
 */
public class AddCourseOutputData {

    private final String name;

    private final boolean useCaseFailed;

    public AddCourseOutputData(String name, boolean useCaseFailed) {
        this.name = name;
        this.useCaseFailed = useCaseFailed;
    }

    public String getName() {
        return name;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
