package my.study.state.machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StateMachine {

  private static final Logger logger = LoggerFactory.getLogger(StateMachine.class);

  private final Map<StateEventPair, Transition> transitionTable;

  public StateMachine(Map<StateEventPair, Transition> transitionTable) {
    this.transitionTable = transitionTable;
  }

  public void fire(WorkflowContext context, Event event) {
    State currentState = context.getCurrentState();
    StateEventPair key = new StateEventPair(currentState, event);

    if (transitionTable.containsKey(key)) {
      Transition transition = transitionTable.get(key);

      logger.debug(
          "Transitioning from {} to {} on event {}", currentState, transition.targetState, event);

      // 1. Update State
      context.setCurrentState(transition.targetState);

      // 2. Execute Actions (Side effects like Email)
      for (Action action : transition.actions) {
        action.execute(context);
      }
    } else {
      logger.warn("No transition defined for State: {} and Event: {}", currentState, event);
      // Optionally throw exception
    }
  }

  // --- Helper Classes ---

  private static class StateEventPair {
    final State state;
    final Event event;

    StateEventPair(State state, Event event) {
      this.state = state;
      this.event = event;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      StateEventPair that = (StateEventPair) o;
      return state == that.state && event == that.event;
    }

    @Override
    public int hashCode() {
      return Objects.hash(state, event);
    }
  }

  private static class Transition {
    final State targetState;
    final List<Action> actions;

    Transition(State targetState, List<Action> actions) {
      this.targetState = targetState;
      this.actions = actions;
    }
  }

  // --- Builder for Configuration ---

  public static class Builder {
    private final Map<StateEventPair, Transition> table = new HashMap<>();

    public TransitionBuilder source(State sourceState) {
      return new TransitionBuilder(this, sourceState);
    }

    public StateMachine build() {
      return new StateMachine(table);
    }
  }

  public static class TransitionBuilder {
    private final Builder parent;
    private final State source;

    public TransitionBuilder(Builder parent, State source) {
      this.parent = parent;
      this.source = source;
    }

    public TargetBuilder on(Event event) {
      return new TargetBuilder(this, event);
    }
  }

  public static class TargetBuilder {
    private final TransitionBuilder parent;
    private final Event event;

    public TargetBuilder(TransitionBuilder parent, Event event) {
      this.parent = parent;
      this.event = event;
    }

    public Builder target(State targetState, Action... actions) {
      List<Action> actionList = new ArrayList<>();
      if (actions != null) {
        Collections.addAll(actionList, actions);
      }
      parent.parent.table.put(
          new StateEventPair(parent.source, event), new Transition(targetState, actionList));
      return parent.parent; // Return to main builder
    }
  }
}
