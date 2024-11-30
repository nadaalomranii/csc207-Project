package interface_adapter.send_notification;

import interface_adapter.ViewModel;

public class SendNotificationViewModel extends ViewModel<SendNotificationState> {
    public SendNotificationViewModel() {
        super("send notification");
        setState(new SendNotificationState());
    }
}
