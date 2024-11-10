package app;

import data_access.DataAccessInterface;
import entity.AssignmentFactory;
import entity.CommonAssignmentFactory;
import interface_adapter.add_assignment.AddAssignmentController;
import interface_adapter.add_assignment.AddAssignmentPresenter;
import interface_adapter.add_assignment.AddAssignmentViewModel;
import interface_adapter.add_course.AddCourseController;
import interface_adapter.add_course.AddCoursePresenter;
import interface_adapter.add_course.AddCourseViewModel;
import use_case.add_assignment.AddAssignmentInteractor;
import use_case.add_assignment.AddAssignmentOutputBoundary;
import use_case.add_assignment.AddAssignmentInputBoundary;
import use_case.add_course.AddCourseOutputBoundary;
import use_case.add_course.AddCourseInputBoundary;
import use_case.add_course.AddCourseInteractor;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final AssignmentFactory assignmentFactory = new CommonAssignmentFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private final DataAccessInterface userDataAccessObject = new DataAccessInterface();

    private AddAssignmentViewModel addAssignmentViewModel;
    private AssignmentAddView assignmentAddView;

    private AddCourseViewModel addCourseViewModel;
    private CourseAddView courseAddView;

    private CourseListView courseListView;
    private CourseListViewModel courseListViewModel;

    private AssignmentListView assignmentListView;
    private AssignmentListViewModel assignmentListViewModel;


    public AppBuilder() { cardPanel.setLayout(cardLayout); }

    /**
     * Adds the Add Assignment View to the application.
     * @return this builder
     */
    public AppBuilder addAddAssignmentView() {
        addAssignmentViewModel = new AddAssignmentViewModel();
        assignmentAddView = new AssignmentAddView(addAssignmentViewModel);
        cardPanel.add(assignmentAddView, assignmentAddView.getViewName());
        return this;
    }

    /**
     * Adds the Add Course View to the application.
     * @return this builder
     */
    public AppBuilder addAddCourseView() {
        addCourseViewModel = new AddCourseViewModel();
        courseAddView = new CourseAddView(addCourseViewModel);
        cardPanel.add(courseAddView, courseAddView.getViewName());
        return this;
    }

    /**
     * Adds the Course List View to the application.
     * @return this builder
     */
    public AppBuilder addCourseListView() {
        courseListViewModel = new CourseListViewModel();
        courseListView = new CourseListView(courseListViewModel);
        cardPanel.add(courseListView, courseListView.getViewName());
        return this;
    }

    /**
     * Adds the Assignment list View to the application.
     * @return this builder
     */
    public AppBuilder addAssignmentListView() {
        assignmentListViewModel = new AssignmentListViewModel();
        assignmentListView = new AssignmentListView(courseListViewModel);
        cardPanel.add(assignmentListView, assignmentListView.getViewName());
        return this;
    }

    /**
     * Adds the Add Assignment Use Case to the application.
     * @return this builder
     */
    public AppBuilder addAddAssignmentUseCase() {
        final AddAssignmentOutputBoundary addAssignmentOutputBoundary = new AddAssignmentPresenter(
                viewManagerModel, addAssignmentViewModel, addAssignmentViewModel);
        final AddAssignmentInputBoundary addAssignmentInteractor = new AddAssignmentInteractor(
                userDataAccessObject, addAssignmentOutputBoundary);

        final AddAssignmentController addAssignmentController = new AddAssignmentController(addAssignmentInteractor);
        addAssignmentView.setLoginController(addAssignmentController);
        return this;
    }

    /**
     * Adds the Add Course Use Case to the application.
     * @return this builder
     */
    public AppBuilder addAddCourseUseCase() {
        final AddCourseOutputBoundary addCourseOutputBoundary = new AddCoursePresenter(
                viewManagerModel, addAssignmentViewModel, addAssignmentViewModel);
        final AddCourseInputBoundary addCourseInteractor = new AddCourseInteractor(
                userDataAccessObject, addCourseOutputBoundary);

        final AddCourseController addCourseController = new AddCourseController(addCourseInteractor);
        CourseAddView.setLoginController(addCourseController);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the Course List View to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Courses: ");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(courseListView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
