package entity;

/**
 * An implementation of the Assignment interface.
 */
public class CommonAssignment implements Assignment {
    private final String name;
    private final float grade;
    private final float weight;
    private final String dueDate;

    public CommonAssignment(String name, float grade, float weight, String dueDate) {
        this.name = name;
        this.grade = grade;
        this.weight = weight;
        this.dueDate = dueDate;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getGrade() {
        return grade;
    }

    @Override
    public float getWeight() {
        return weight;
    }

    @Override
    public String getDueDate() {
        return dueDate;
    }
}
