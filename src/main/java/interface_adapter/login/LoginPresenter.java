package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.course_list.CourseListState;
import interface_adapter.course_list.CourseListViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final CourseListViewModel courseListViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          CourseListViewModel courseListViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.courseListViewModel = courseListViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the Course List view.

        final CourseListState courseListState = courseListViewModel.getState();
        this.courseListViewModel.setState(courseListState);
        this.courseListViewModel.firePropertyChanged();

        this.viewManagerModel.setState(courseListViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }
}
