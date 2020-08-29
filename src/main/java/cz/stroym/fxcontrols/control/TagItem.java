package cz.stroym.fxcontrols.control;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class TagItem<T> extends HBox {
  
  private final Label  textValue;
  private final Button removeButton;
  
  public TagItem(ScrollableFlowPane parent, T tag) {
    double height = parent.heightProperty().divide(3).subtract(2).get();
    
    this.setMinHeight(height);
    this.setPrefHeight(height);
    this.setMaxHeight(height);
    this.setAlignment(Pos.CENTER_LEFT);
    
    Label label = new Label(tag.toString());
    
    ImageView closeImg = new ImageView(new Image("image/x-button-icon.png"));
    closeImg.setFitHeight(height / 2);
    closeImg.setFitWidth(height / 2);
    
    Button button = new Button("", closeImg);
    button.setOnAction(event -> {
      parent.removeFlowChild(this);
    });
    
    this.textValue = label;
    this.removeButton = button;
    
    this.getChildren().addAll(textValue, removeButton);
  }
  
}
