package my.study.state.machine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    logger.info("Initializing State Machine...");

    // 1. Configure the State Machine
    StateMachine stateMachine =
        new StateMachine.Builder()
            // Pending -> Send To Manager (Notify Manager)
            .source(State.PENDING)
            .on(Event.SUBMIT_TO_MANAGER)
            .target(State.SEND_TO_MANAGER, EmailAction.notifyManager())

            // Send To Manager -> Return To Clerk (Notify Clerk)
            .source(State.SEND_TO_MANAGER)
            .on(Event.RETURN_FOR_CORRECTION)
            .target(State.RETURN_TO_CLERK, EmailAction.notifyClerk())

            // Return To Clerk -> Pending (No email, just state change)
            .source(State.RETURN_TO_CLERK)
            .on(Event.SUBMIT_TO_MANAGER)
            .target(State.SEND_TO_MANAGER, EmailAction.notifyManager()) // Re-submit

            // Send To Manager -> Ack (Notify Customer)
            .source(State.SEND_TO_MANAGER)
            .on(Event.MANAGER_APPROVE)
            .target(State.ACK, EmailAction.notifyCustomer())

            // Send To Manager -> Ack With Followup (Notify Customer + Special Ops)
            .source(State.SEND_TO_MANAGER)
            .on(Event.MANAGER_APPROVE_WITH_FOLLOWUP)
            .target(
                State.ACK_WITH_FOLLOWUP,
                EmailAction.notifyCustomer(),
                ctx ->
                    logger.info(
                        "   [Action] Triggering specialized Follow-up Task for {}", ctx.getId()))

            // Ack -> Close
            .source(State.ACK)
            .on(Event.CLOSE_CASE)
            .target(State.CLOSE, ctx -> logger.info("   [Action] Archiving case {}", ctx.getId()))

            // AckWithFollowup -> Close
            .source(State.ACK_WITH_FOLLOWUP)
            .on(Event.CLOSE_CASE)
            .target(
                State.CLOSE,
                ctx -> logger.info("   [Action] Archiving complex case {}", ctx.getId()))
            .build();

    // 2. Create a Context (The Object)
    WorkflowContext item = new WorkflowContext("DOC-001", "Expense Report");
    logger.info("Created: {}", item);

    // 3. Simulate Workflow
    logger.info("--- Step 1: User submits ---");
    stateMachine.fire(item, Event.SUBMIT_TO_MANAGER);

    logger.info("--- Step 2: Manager rejects ---");
    stateMachine.fire(item, Event.RETURN_FOR_CORRECTION);

    logger.info("--- Step 3: User re-submits ---");
    stateMachine.fire(item, Event.SUBMIT_TO_MANAGER);

    logger.info("--- Step 4: Manager approves with follow-up ---");
    stateMachine.fire(item, Event.MANAGER_APPROVE_WITH_FOLLOWUP);

    logger.info("--- Step 5: Closing case ---");
    stateMachine.fire(item, Event.CLOSE_CASE);

    logger.info("Final State: {}", item);
  }
}
