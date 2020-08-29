package cz.stroym.fxcontrols.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.util.List;

@Getter
public class TagBox2<T> extends VBox {
  
  private final MySearchableComboBox<T> comboBox = new MySearchableComboBox<>();
  private final ScrollableFlowPane      flowPane = new ScrollableFlowPane();
  
  //tags need to be added elsewhere; trying to make it work in the scope of the TagBox is living hell
  //so don't worry where the tags come from here
  private ObservableList<T> globalTags = FXCollections.emptyObservableList();
  private ObservableList<T> itemTags   = FXCollections.emptyObservableList();
  
  public TagBox2() {
    this.comboBox.setPrefHeight(20);
    this.comboBox.prefWidthProperty().bind(this.widthProperty());
    
    this.flowPane.setFitToWidth(true);
    this.flowPane.prefHeightProperty().bind(this.heightProperty().subtract(20));
    
    this.getChildren().addAll(flowPane, comboBox);
  }
  
  public TagBox2(List<T> globalTags) {
    this();
    
    setGlobalTags(globalTags);
  }
  
  public TagBox2(List<T> globalTags, List<T> itemTags) {
    this();
    
    setGlobalTags(globalTags);
    setItemTags(itemTags);
  }
  
  public TagBox2(ObservableList<T> globalTags) {
    this();
    
    this.globalTags = globalTags;
  }
  
  public TagBox2(ObservableList<T> globalTags, ObservableList<T> itemTags) {
    this();
    
    this.globalTags = globalTags;
    this.itemTags = itemTags;
  }
  
  public void setGlobalTags(List<T> globalTags) {
    setGlobalTags(globalTags);
  }
  
  public void setGlobalTags(ObservableList<T> globalTags) {
    this.globalTags = globalTags;
  }
  
  public void setItemTags(List<T> itemTags) {
    setItemTags(itemTags);
  }
  
  public void setItemTags(ObservableList<T> itemTags) {
    this.itemTags = itemTags;
  }
  
  //  public TagBox2() {
  //  }
  //
  //  public void addTag(String tag) {
  //    scrollableFlowPane.addFlowChild(new TagItem<String>(scrollableFlowPane, tag));
  //
  //    scrollableFlowPane.requestLayout();
  //    scrollableFlowPane.getFlowPane().requestLayout();
  //  }
  //
  //  public void setComboBoxItems(ObservableList<String> items) {
  //    this.autoCompleteTextField.setItems(items);
  //  }
  
}
