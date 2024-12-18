package use_case.edit_course;

import data_access.DataAccessInterface;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.course_list.CourseListViewModel;
import interface_adapter.edit_course.EditCoursePresenter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class EditCourseInteractorTest {
    @Test
    void successTest() {
        CourseFactory courseFactory = new CommonCourseFactory();
        UserFactory userFactory = new CommonUserFactory();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CourseListViewModel courseListViewModel = new CourseListViewModel();

        Course course = courseFactory.create("Software", "csc207", new ArrayList<>());
        User user = userFactory.create("Nada", "nadaalomrani", "nada", "nada@gmail.com");

        EditCourseInputData inputData = new EditCourseInputData("Software Design", "csc207", user);

        DataAccessInterface courseRepository = new DataAccessInterface();

        courseRepository.save(user);
        courseRepository.saveCourse(course, user);

        EditCourseOutputBoundary successPresenter = new EditCourseOutputBoundary() {
            @Override
            public void prepareSuccessView(EditCourseOutputData editCourseOutputData) {
                assertEquals(editCourseOutputData.getCourseName(), courseRepository.checkName(inputData.getCourseCode(), user));
                assertEquals(editCourseOutputData.getCourseCode(), "csc207");
            }

            @Override
            public void prepareFailView() {
                // TODO:
            }

            @Override
            public void switchToAssignmentListView() {
                viewManagerModel.setState(courseListViewModel.getViewName());
                viewManagerModel.firePropertyChanged();
                assertEquals("Course List", viewManagerModel.getState());
            }
        };

        EditCourseInputBoundary interactor = new EditCourseInteractor(courseRepository, successPresenter, courseFactory);
        interactor.execute(inputData);

        // Check that the course name has been changed
        assertEquals(inputData.getCourseName(), courseRepository.checkName(inputData.getCourseCode(), user));

        // Testing if it correctly switches to the course list view
        interactor.switchToAssignmentListView();

    }
}
