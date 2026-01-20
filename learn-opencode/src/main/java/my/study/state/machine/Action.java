package my.study.state.machine;

@FunctionalInterface
public interface Action {
  void execute(WorkflowContext context);
}
