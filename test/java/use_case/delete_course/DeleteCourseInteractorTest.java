package use_case.delete_course;

import data_access.DataAccessInterface;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.course_list.CourseListViewModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteCourseInteractorTest {
    @Test
    void successTest() {
        CourseFactory courseFactory = new CommonCourseFactory();
        UserFactory userFactory = new CommonUserFactory();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CourseListViewModel courseListViewModel = new CourseListViewModel();

        // This is the course we want to save then delete
        Course course = courseFactory.create("Software Design", "CSC207", new ArrayList<>());
        User user = userFactory.create("Nada", "nadaalomrani", "nada", "nada@gmail.com");
        DeleteCourseInputData inputData = new DeleteCourseInputData("Software Design", "CSC207", user);

        DataAccessInterface courseRepository = new DataAccessInterface();

        // save the course into the data
        courseRepository.save(user);
        courseRepository.saveCourse(course, user);

        // create a successPresenter that tests if the test cases are like what we expect

        DeleteCourseOutputBoundary successPresenter = new DeleteCourseOutputBoundary() {
            @Override
            public void prepareSuccessView(DeleteCourseOutputData deleteCourseOutputData) {
                // TODO: Why is the output data only outputs the course code, not the course object?
                assertEquals(deleteCourseOutputData.getCourseCode(), course.getCode());
                assertEquals(deleteCourseOutputData.getUser(), user);
                assertEquals(deleteCourseOutputData.getCourses(), new ArrayList<>());
            }

            @Override
            public void switchToCourseListView() {
                viewManagerModel.setState(courseListViewModel.getViewName());
                viewManagerModel.firePropertyChanged();
                assertEquals("Course List", viewManagerModel.getState());
            }
        };
        DeleteCourseInputBoundary interactor = new DeleteCourseInteractor(courseRepository, successPresenter, courseFactory);
        interactor.execute(inputData);

        // check that the course no longer exists in the data
        boolean checkExists = courseRepository.existsByCode(course.getCode(), user);
        assertFalse(checkExists);

        // Testing that it correctly switches to the course list view
        interactor.switchToCourseListView();
    }
}
