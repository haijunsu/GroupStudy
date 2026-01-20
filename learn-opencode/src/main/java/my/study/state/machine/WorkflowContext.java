package my.study.state.machine;

public class WorkflowContext {
  private String id;
  private State currentState;
  private String data;

  public WorkflowContext(String id, String data) {
    this.id = id;
    this.data = data;
    this.currentState = State.PENDING; // Default initial state
  }

  public String getId() {
    return id;
  }

  public State getCurrentState() {
    return currentState;
  }

  public void setCurrentState(State currentState) {
    this.currentState = currentState;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "WorkflowContext{"
        + "id='"
        + id
        + '\''
        + ", currentState="
        + currentState
        + ", data='"
        + data
        + '\''
        + '}';
  }
}
