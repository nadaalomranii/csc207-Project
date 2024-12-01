package app;

import data_access.DataAccessInterface;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_assignment.AddAssignmentController;
import interface_adapter.add_assignment.AddAssignmentPresenter;
import interface_adapter.add_assignment.AddAssignmentViewModel;
import interface_adapter.add_course.AddCourseController;
import interface_adapter.add_course.AddCoursePresenter;
import interface_adapter.add_course.AddCourseViewModel;
import interface_adapter.assignment_list.AssignmentListViewModel;
import interface_adapter.course_list.CourseListController;
import interface_adapter.course_list.CourseListState;
import interface_adapter.course_list.CourseListViewModel;
import interface_adapter.edit_course.EditCourseController;
import interface_adapter.edit_course.EditCoursePresenter;
import interface_adapter.edit_course.EditCourseState;
import interface_adapter.edit_course.EditCourseViewModel;
import interface_adapter.delete_assignment.DeleteAssignmentController;
import interface_adapter.delete_assignment.DeleteAssignmentPresenter;
import interface_adapter.delete_assignment.DeleteAssignmentViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
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
import use_case.delete_assignment.DeleteAssignmentInputBoundary;
import use_case.delete_assignment.DeleteAssignmentInteractor;
import use_case.delete_assignment.DeleteAssignmentOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.*;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final CourseFactory courseFactory = new CommonCourseFactory();
    private final UserFactory userFactory = new CommonUserFactory();
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

    private DeleteAssignmentViewModel deleteAssignmentViewModel;

    private SignupView signupView;
    private SignupViewModel signupViewModel;

    private LoginView loginView;
    private LoginViewModel loginViewModel;

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
        CourseListState state = courseListViewModel.getState();
        User user = state.getUser();
        List<Course> courses = userDataAccessObject.getCourses(user);
        courseListView = new CourseListView(courseListViewModel, courses, viewManagerModel, addCourseViewModel);
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
     * Adds the signup view to the application.
     * @return this builder
     */
    public AppBuilder addSignUpView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
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
                viewManagerModel, addCourseViewModel, courseListViewModel);
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

    public AppBuilder addSignUpUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(
                viewManagerModel, signupViewModel, courseListViewModel);
        final SignupInputBoundary signupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);
        final SignupController signupController = new SignupController(signupInteractor);
        signupView.setSignupController(signupController);
        return this;
    }

    public AppBuilder addCourseListUseCase() {
        final CourseListController courseListController = new CourseListController();

        courseListView.setCourseListController(courseListController);
        return this;
    }

    public AppBuilder deleteAssignmentUseCase() {
        final DeleteAssignmentOutputBoundary deleteAssignmentOutputBoundary = new DeleteAssignmentPresenter(assignmentListViewModel, viewManagerModel);
        final DeleteAssignmentInputBoundary deleteAssignmentInteractor = new DeleteAssignmentInteractor(
                userDataAccessObject, deleteAssignmentOutputBoundary);

        final DeleteAssignmentController deleteAssignmentController = new DeleteAssignmentController(deleteAssignmentInteractor);
        assignmentListView.setDeleteAssignmentController(deleteAssignmentController);
        return this;
    }

    public AppBuilder loginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, courseListViewModel, loginViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
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
        viewManagerModel.setState(assignmentListView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
