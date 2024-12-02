package interface_adapter.send_notification;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_assignment.AddAssignmentState;
import interface_adapter.add_assignment.AddAssignmentViewModel;
import interface_adapter.assignment_list.AssignmentListState;
import interface_adapter.assignment_list.AssignmentListViewModel;
import use_case.send_notification.SendNotificationOutputBoundary;
import use_case.send_notification.SendNotificationOutputData;

public class SendNotificationPresenter implements SendNotificationOutputBoundary {
    private final SendNotificationViewModel sendNotificationViewModel;
    private final AssignmentListViewModel assignmentListViewModel;
    private final ViewManagerModel viewManagerModel;

    public SendNotificationPresenter(SendNotificationViewModel sendNotificationViewModel,
                                     AssignmentListViewModel assignmentListViewModel,
                                     ViewManagerModel viewManagerModel) {
        this.sendNotificationViewModel = sendNotificationViewModel;
        this.assignmentListViewModel = assignmentListViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    //TODO: check these
    @Override
    public void prepareSuccessView(SendNotificationOutputData outputData){
        AssignmentListState assignmentListState = assignmentListViewModel.getState();
        SendNotificationState sendNotificationState = sendNotificationViewModel.getState();
        this.assignmentListViewModel.setState(assignmentListState);
        sendNotificationState.setNewlyScheduledAssignments(outputData.getNewlyScheduledAssignments());
        assignmentListViewModel.firePropertyChanged();
        sendNotificationViewModel.firePropertyChanged("notifications scheduled");
        viewManagerModel.setState(assignmentListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage){
        //On fail, pop up that says notification couldn't be scheduled, then go back to addAssignmentView
        AssignmentListState addAssignmentState = assignmentListViewModel.getState();
        this.assignmentListViewModel.setState(addAssignmentState);
        assignmentListViewModel.firePropertyChanged();
        sendNotificationViewModel.firePropertyChanged(errorMessage);
        viewManagerModel.setState(assignmentListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToAddAssignmentView(){
        viewManagerModel.setState(assignmentListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
