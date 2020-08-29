package cz.stroym.fxcontrols.control;

import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import lombok.Getter;

@Getter
public class TagBox extends VBox {
  
  private final MySearchableComboBox<String> comboBox           = new MySearchableComboBox<>();
  private final ScrollableFlowPane           scrollableFlowPane = new ScrollableFlowPane();
  
  public TagBox() {
    this.comboBox.setPrefHeight(20);
    this.comboBox.prefWidthProperty().bind(this.widthProperty());
  
    //    comboBox.setOnKeyPressed(event -> {
    //      if (event.getCode() == KeyCode.ENTER) {
    //        addTag(comboBox.getValue());
    //        comboBox.getEditor().clear();
    //      }
    //    });
  
    //    comboBox.getSelectionModel().selectedItemProperty().addListener(newValue -> {
    //      addTag(comboBox.getValue());
    //      comboBox.getEditor().clear();
    //    });
  
    comboBox.valueProperty().addListener((obs, oldItem, newItem) -> {
      System.out.println(oldItem);
      System.out.println(newItem);
    
      if (newItem != null && newItem != oldItem) {
        addTag(comboBox.getValue());
      }
    });
  
    this.scrollableFlowPane.setFitToWidth(true);
    this.scrollableFlowPane.prefHeightProperty().bind(this.heightProperty().subtract(20));
  
    this.getChildren().add(scrollableFlowPane);
    this.getChildren().add(comboBox);
  }
  
  public void addTag(String tag) {
    scrollableFlowPane.addFlowChild(new TagItem<>(scrollableFlowPane, tag));
    
    scrollableFlowPane.requestLayout();
    scrollableFlowPane.getFlowPane().requestLayout();
  }
  
  public void setComboBoxItems(ObservableList<String> items) {
    this.comboBox.setItems(items);
  }
  
}
