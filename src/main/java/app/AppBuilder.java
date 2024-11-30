package app;

import data_access.DataAccessInterface;
import entity.AssignmentFactory;
import entity.CommonAssignmentFactory;
import entity.CommonCourseFactory;
import entity.CourseFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_assignment.AddAssignmentController;
import interface_adapter.add_assignment.AddAssignmentPresenter;
import interface_adapter.add_assignment.AddAssignmentViewModel;
import interface_adapter.add_course.AddCourseController;
import interface_adapter.add_course.AddCoursePresenter;
import interface_adapter.add_course.AddCourseViewModel;
import interface_adapter.assignment_list.AssignmentListViewModel;
import interface_adapter.course_list.CourseListViewModel;
import interface_adapter.edit_course.EditCourseController;
import interface_adapter.edit_course.EditCoursePresenter;
import interface_adapter.edit_course.EditCourseState;
import interface_adapter.edit_course.EditCourseViewModel;
import use_case.add_assignment.AddAssignmentInteractor;
import use_case.add_assignment.AddAssignmentOutputBoundary;
import use_case.add_assignment.AddAssignmentInputBoundary;
import use_case.add_course.AddCourseOutputBoundary;
import use_case.add_course.AddCourseInputBoundary;
import use_case.add_course.AddCourseInteractor;
import use_case.edit_course.EditCourseInputBoundary;
import use_case.edit_course.EditCourseInputData;
import use_case.edit_course.EditCourseInteractor;
import use_case.edit_course.EditCourseOutputBoundary;
import view.*;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final CourseFactory courseFactory = new CommonCourseFactory();
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

    private CourseEditView courseEditView;
    private EditCourseViewModel editCourseViewModel;


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
        assignmentListView = new AssignmentListView(assignmentListViewModel);
        cardPanel.add(assignmentListView, assignmentListView.getViewName());
        return this;
    }

    /**
     * Adds the Assignment List View to the application.
     * @return this builder
     */
    public AppBuilder addCourseEditView() {
        editCourseViewModel = new EditCourseViewModel();
        courseEditView = new CourseEditView(editCourseViewModel);
        cardPanel.add(courseEditView, courseEditView.getViewName());
        return this;
    }

    /**
     * Adds the Add Assignment Use Case to the application.
     * @return this builder
     */
    public AppBuilder addAddAssignmentUseCase() {
        final AddAssignmentOutputBoundary addAssignmentOutputBoundary = new AddAssignmentPresenter(
                assignmentListViewModel, viewManagerModel);
        final AddAssignmentInputBoundary addAssignmentInteractor = new AddAssignmentInteractor(
                userDataAccessObject, addAssignmentOutputBoundary, assignmentFactory);

        final AddAssignmentController addAssignmentController = new AddAssignmentController(addAssignmentInteractor);
        assignmentAddView.setAddAssignmentController(addAssignmentController);
        return this;
    }

    /**
     * Adds the Add Course Use Case to the application.
     * @return this builder
     */
    public AppBuilder addAddCourseUseCase() {
        final AddCourseOutputBoundary addCourseOutputBoundary = new AddCoursePresenter(
                viewManagerModel, courseListViewModel);
        final AddCourseInputBoundary addCourseInteractor = new AddCourseInteractor(
                userDataAccessObject, addCourseOutputBoundary, courseFactory);

        final AddCourseController addCourseController = new AddCourseController(addCourseInteractor);
        courseAddView.setAddCourseController(addCourseController);
        return this;
    }

    /**
     * Adds the Edit Course Use Case to the application
     * @return this builder
     */
    public AppBuilder addEditCourseUseCase() {
        final EditCourseOutputBoundary editCourseOutputBoundary = new EditCoursePresenter(
                editCourseViewModel, assignmentListViewModel, viewManagerModel);
        final EditCourseInputBoundary editCourseInteractor = new EditCourseInteractor(
                userDataAccessObject, editCourseOutputBoundary, courseFactory);

        final EditCourseController editCourseController = new EditCourseController(editCourseInteractor);
        courseEditView.setEditCourseController(editCourseController);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the Course List View to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("AssignMate");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(courseEditView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
