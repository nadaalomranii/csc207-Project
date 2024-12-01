package app;

import javax.swing.JFrame;

public class main {
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                                            .addAddAssignmentView()
                                            .addAddCourseView()
                                            .addCourseListView()
                                            .addAssignmentListView()
                                            .addSignUpView()
                                            .addLoginView()
                                            .addCourseEditView()
                                            .addAddAssignmentUseCase()
                                            .addAddCourseUseCase()
                                            .addEditCourseUseCase()
                                            .addSignUpUseCase()
                                            .deleteAssignmentUseCase()
                                            .loginUseCase()
                                            .build();

        application.pack();
        application.setVisible(true);
    }
}
