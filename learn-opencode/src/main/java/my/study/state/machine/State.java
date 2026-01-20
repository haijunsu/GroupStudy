package my.study.state.machine;

public enum State {
  PENDING,
  SEND_TO_MANAGER,
  RETURN_TO_CLERK,
  ACK,
  ACK_WITH_FOLLOWUP,
  CLOSE
}
