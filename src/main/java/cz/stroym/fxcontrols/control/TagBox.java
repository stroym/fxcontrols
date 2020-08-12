package cz.stroym.fxcontrols.control;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lombok.Getter;
import org.controlsfx.control.SearchableComboBox;

import java.util.Locale;

@Getter
public class TagBox extends VBox {
  
  private final SearchableComboBox<String> comboBox           = new SearchableComboBox<>();
  private final ScrollableFlowPane         scrollableFlowPane = new ScrollableFlowPane();
  
  public TagBox() {
    this.comboBox.setPrefHeight(20);
    this.comboBox.prefWidthProperty().bind(this.widthProperty());
    
    comboBox.setOnKeyPressed(event -> {
      System.out.println();
      if (event.getCode() == KeyCode.ENTER) {
        tagButton(scrollableFlowPane, comboBox.getValue());
        comboBox.getEditor().clear();
      }
    });
    
    this.scrollableFlowPane.setFitToWidth(true);
    this.scrollableFlowPane.prefHeightProperty().bind(this.heightProperty().subtract(20));
    
    this.getChildren().add(scrollableFlowPane);
    this.getChildren().add(comboBox);
  }
  
  public void tagButton(ScrollableFlowPane pane, String tag) {
    ImageView closeImg = new ImageView(new Image("image/x-button-icon.png"));
    closeImg.setFitHeight(10);
    closeImg.setFitWidth(10);
    
    Button result = new Button(tag, closeImg);
    result.setMinHeight(pane.heightProperty().divide(3).subtract(2).doubleValue());
    result.setPrefHeight(pane.heightProperty().divide(3).subtract(2).doubleValue());
    result.setMaxHeight(pane.heightProperty().divide(3).subtract(2).doubleValue());
    result.setFont(Font.font(result.getPrefHeight() * 0.6));
    result.setContentDisplay(ContentDisplay.RIGHT);
    
    result.setOnAction(event -> {
      pane.getFlowChildren().remove(result);
    });
    
    pane.addFlowChild(result);
    
    pane.requestLayout();
    pane.getFlowPane().requestLayout();
  }
  
  public void setComboBoxItems(ObservableList<String> items) {
    this.comboBox.setItems(items);
  }
  
}
