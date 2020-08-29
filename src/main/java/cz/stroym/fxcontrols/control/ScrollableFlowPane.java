package cz.stroym.fxcontrols.control;

import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import lombok.Getter;

@Getter
public class ScrollableFlowPane extends ScrollPane {
  
  private final FlowPane flowPane = new FlowPane(Orientation.HORIZONTAL, 1, 1);
  
  public ScrollableFlowPane() {
    this.setHbarPolicy(ScrollBarPolicy.NEVER);
    this.flowPane.prefWidthProperty().bind(this.widthProperty());
    this.setContent(flowPane);
    this.flowPane.heightProperty().addListener(observable -> this.setVvalue(1D));
  }
  
  public ObservableList<Node> getFlowChildren() {
    return this.flowPane.getChildren();
  }
  
  public void addFlowChild(Node child) {
    this.flowPane.getChildren().add(child);
  }
  
  public void removeFlowChild(Node child) {
    this.flowPane.getChildren().remove(child);
  }
  
}
