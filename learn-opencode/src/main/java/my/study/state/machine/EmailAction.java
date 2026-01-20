package my.study.state.machine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailAction implements Action {
  private static final Logger logger = LoggerFactory.getLogger(EmailAction.class);

  private final String recipientRole;
  private final String template;

  public EmailAction(String recipientRole, String template) {
    this.recipientRole = recipientRole;
    this.template = template;
  }

  @Override
  public void execute(WorkflowContext context) {
    // Mock sending email
    logger.info(
        "   [Action] Sending Email to '{}' | Subject: Item {} | Body: {} (Status: {})",
        recipientRole,
        context.getId(),
        template,
        context.getCurrentState());
  }

  // Static factories for common scenarios
  public static EmailAction notifyManager() {
    return new EmailAction("Manager", "Please review pending item.");
  }

  public static EmailAction notifyClerk() {
    return new EmailAction("Clerk", "Item returned for correction.");
  }

  public static EmailAction notifyCustomer() {
    return new EmailAction("Customer", "Your item status has been updated.");
  }
}
