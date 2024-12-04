package use_case.add_course;

import data_access.DataAccessInterface;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.course_list.CourseListViewModel;
import org.junit.jupiter.api.Test;
import use_case.add_course.AddCourseInputData;
import use_case.add_course.AddCourseOutputBoundary;

import javax.swing.text.View;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AddCourseInteractorTest {
    /***
     * Test adding a course for the first time
     */
    @Test
    void successTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CourseListViewModel courseListViewModel = new CourseListViewModel();

        UserFactory userFactory = new CommonUserFactory();
        User user = userFactory.create("Nada", "nadaalomrani", "password", "nada@gmail.com");

        AddCourseInputData courseInputData = new AddCourseInputData("Software Design", "CSC207", user);
        CourseFactory factory = new CommonCourseFactory();
        Course course = factory.create(courseInputData.getName(), courseInputData.getCode(), new ArrayList<>());

        DataAccessInterface courseRepository = new DataAccessInterface();

        courseRepository.save(user);

        AddCourseOutputBoundary successPresenter = new AddCourseOutputBoundary() {
            @Override
            public void prepareSuccessView(AddCourseOutputData outputData) {
                assertEquals(outputData.getCode(), course.getCode());
                assertEquals(outputData.getName(), course.getName());
            }

            @Override
            public void prepareFailView(AddCourseOutputData addCourseOutputData, String errorMessage) {
                fail("Use case failure is unexpected/n"+errorMessage);
            }

//            @Override
//            public void switchToCourseView() {
//                viewManagerModel.setState(courseListViewModel.getViewName());
//                viewManagerModel.firePropertyChanged();
//                assertEquals("Course List", viewManagerModel.getState());
//            }
        };

        AddCourseInputBoundary interactor = new AddCourseInteractor(courseRepository, successPresenter, factory);
        interactor.execute(courseInputData);
    }


    /***
     * Test saving the same course twice.
     */
    @Test
    void failTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CourseListViewModel courseListViewModel = new CourseListViewModel();

        UserFactory userFactory = new CommonUserFactory();
        User user = userFactory.create("Nada", "nadaalomrani", "password", "nada@gmail.com");

        CourseFactory factory = new CommonCourseFactory();
        DataAccessInterface courseRepository = new DataAccessInterface();

        AddCourseInputData courseInputData = new AddCourseInputData("Software Design", "CSC207", user);

        Course course = factory.create(courseInputData.getName(), courseInputData.getCode(), new ArrayList<>());

        courseRepository.save(user);
        courseRepository.saveCourse(course, user);

        AddCourseOutputBoundary successPresenter = new AddCourseOutputBoundary() {

            @Override
            public void prepareSuccessView(AddCourseOutputData outputData) {
                fail("Use case success is unexpected");
            }

            @Override
            public void prepareFailView(AddCourseOutputData outputData, String errorMessage) {
                assert(!outputData.getCourses().contains(course));
                assertNull(outputData.getCourse());
                assertTrue(outputData.isUseCaseFailed());
            }

//            @Override
//            public void switchToCourseView() {
//                viewManagerModel.setState(courseListViewModel.getViewName());
//                viewManagerModel.firePropertyChanged();
//                assertEquals("Course List", viewManagerModel.getState());
//            }
        };

        AddCourseInputBoundary interactor = new AddCourseInteractor(courseRepository, successPresenter, factory);
        interactor.execute(courseInputData);
    }
}
