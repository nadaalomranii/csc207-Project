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
                                            .addAddAssignmentUseCase()
                                            .addAddCourseUseCase()
                                            .build();

        application.pack();
        application.setVisible(true);
    }
}
