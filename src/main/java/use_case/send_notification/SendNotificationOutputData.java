package use_case.send_notification;


import entity.Assignment;

import java.util.List;

/**
 * Output Data for the Send Notification Use Case.
 */
public class SendNotificationOutputData {

    List<Assignment> newlyScheduledAssignments;

    public SendNotificationOutputData(List<Assignment> newlyScheduledAssignments) {
        this.newlyScheduledAssignments = newlyScheduledAssignments;
    }


}
