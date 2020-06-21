package cz.stroym.fxcontrols.control;

import cz.stroym.fxcontrols.util.FXUtils;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Getter
public class AutocompleteTextField<T> extends TextField {
  
  private final SortedSet<T> entries           = new TreeSet<>();
  private final ContextMenu  candidatesContext = new ContextMenu();
  
  @Setter
  private int maxEntries = 10;
  
  public AutocompleteTextField() {
    super();
    
    textProperty().addListener((observable, oldValue, newValue) -> {
      String entryText = getText();
      
      if (entryText == null || entryText.isEmpty()) {
        candidatesContext.hide();
      } else {
        List<T> candidates = entries.stream()
                                    .filter(e -> e.toString().toLowerCase().contains(entryText.toLowerCase()))
                                    .collect(Collectors.toList());
        
        if (!candidates.isEmpty()) {
          populatePopup(candidates, entryText);
          
          if (!candidatesContext.isShowing()) {
            candidatesContext.show(AutocompleteTextField.this, Side.BOTTOM, 0, 0);
          }
        } else {
          candidatesContext.hide();
        }
      }
    });
    
    focusedProperty().addListener((observableValue, oldValue, newValue) -> candidatesContext.hide());
  }
  
  private void populatePopup(List<T> candidates, String entryText) {
    List<CustomMenuItem> candidatesItems = new LinkedList<>();
    
    if (candidates.size() > maxEntries) {
      candidates = candidates.subList(0, maxEntries);
    }
    
    for (T candidate : candidates) {
      Label entryLabel = new Label("", FXUtils.buildTextFlowHighlight(candidate.toString(), entryText));
      entryLabel.setPrefHeight(Font.getDefault().getSize() - 2);
      CustomMenuItem item = new CustomMenuItem(entryLabel, true);
      candidatesItems.add(item);
      
      item.setOnAction(actionEvent -> {
        setText(candidate.toString());
        positionCaret(candidate.toString().length());
        candidatesContext.hide();
      });
    }
    
    candidatesContext.getItems().clear();
    candidatesContext.getItems().addAll(candidatesItems);
  }
  

  
}
