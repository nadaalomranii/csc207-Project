package interface_adapter.send_notification;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_assignment.AddAssignmentState;
import interface_adapter.add_assignment.AddAssignmentViewModel;
import use_case.send_notification.SendNotificationOutputBoundary;
import use_case.send_notification.SendNotificationOutputData;

public class SendNotificationPresenter implements SendNotificationOutputBoundary {
    private final SendNotificationViewModel sendNotificationViewModel;
    private final AddAssignmentViewModel addAssignmentViewModel;
    private final ViewManagerModel viewManagerModel;

    public SendNotificationPresenter(SendNotificationViewModel sendNotificationViewModel,
                                     AddAssignmentViewModel addAssignmentViewModel,
                                     ViewManagerModel viewManagerModel) {
        this.sendNotificationViewModel = sendNotificationViewModel;
        this.addAssignmentViewModel = addAssignmentViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    //TODO: check these
    @Override
    public void prepareSuccessView(SendNotificationOutputData outputData){
        AddAssignmentState addAssignmentState = addAssignmentViewModel.getState();
        this.addAssignmentViewModel.setState(addAssignmentState);
        addAssignmentViewModel.firePropertyChanged();
        sendNotificationViewModel.firePropertyChanged("notifications scheduled");
        viewManagerModel.setState(addAssignmentViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage){
        //On fail, pop up that says notification couldn't be scheduled, then go back to addAssignmentView
        AddAssignmentState addAssignmentState = addAssignmentViewModel.getState();
        this.addAssignmentViewModel.setState(addAssignmentState);
        addAssignmentViewModel.firePropertyChanged();
        sendNotificationViewModel.firePropertyChanged(errorMessage);
        viewManagerModel.setState(addAssignmentViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToAddAssignmentView(){
        viewManagerModel.setState(addAssignmentViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
