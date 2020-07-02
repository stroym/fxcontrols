package cz.stroym.fxcontrols.control;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchableListView<T> extends ListView<T> {
  
  private String capturedInput = "";
  
  public SearchableListView() {
    setupListeners();
    registerCellFactory();
  }
  
  public SearchableListView(ObservableList<T> items) {
    super(items);
    setupListeners();
    registerCellFactory();
  }
  
  //TODO popup or hide records not matching criteria and show them from the top instead of (just) scrolling to them
  private void registerCellFactory() {
    this.setCellFactory(new Callback<>() {
      @Override
      public ListCell<T> call(ListView<T> listView) {
        return new ListCell<>() {
          @Override
          protected void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);
            
            //              if (!capturedInput.equals("") && !item.toString().contains(capturedInput)) {
            //                setVisible(false);
            //              }
            
            setText(empty ? null : getItem() == null ? "" : getItem().toString());
            if (item == getSelectionModel().getSelectedItem()) {
              setStyle(
                  "-fx-background-color: transparent;" +
                  "-fx-text-fill: orange;" +
                  "-fx-font-weight: bold;"
              );
            } else {
              setStyle("");
            }
          }
        };
      }
    });
  }
  
  private void setupListeners() {
    //handle special key presses
    this.setOnKeyPressed(event -> {
      //      handleKeys(event);
      if (!event.getCode().isDigitKey() && !event.getCode().isLetterKey()) {
        handleKeys(event);
      }
    });
    
    
    //TODO backspace issues
    //add char to filter when typed
    this.setOnKeyTyped(event -> {
      capturedInput += event.getCharacter();
      System.out.println(capturedInput);
      
      if (!capturedInput.isBlank()) {
        searchAndFocusItem();
      }
    });
    
    //autosort items when any item change occurs
    this.getItems().addListener(new ListChangeListener() {
      @Override
      public void onChanged(ListChangeListener.Change change) {
        setItems(getItems().sorted());
      }
    });
    
  }
  
  private void handleKeys(KeyEvent event) {
    switch (event.getCode()) {
      case ENTER:
        capturedInput = "";
        break;
      case BACK_SPACE:
        if (capturedInput.length() > 1) {
          capturedInput = capturedInput.substring(0, capturedInput.length() - 1);
        }
        break;
      default:
    }
    
    System.out.println(capturedInput);
    
    event.consume();
  }
  
  private void searchAndFocusItem() {
    T foundItem = null;
    
    for (T item : this.getItems()) {
      System.out.println("Matching " + item.toString() + "with " + capturedInput);
      if (item.toString().startsWith(capturedInput)) {
        foundItem = item;
        break;
      }
    }
    
    if (foundItem != null) {
      System.out.println("Matched " + foundItem.toString() + "with " + capturedInput);
      
      scrollTo(getItems().indexOf(foundItem));
      getSelectionModel().select(foundItem);
    } else {
      getSelectionModel().select(-1);
    }
  }
  
}
