package cz.stroym.fxcontrols.control;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchableListView<T> extends ListView<T> {
  
  private String capturedInput = "";
  
  public SearchableListView() {
    setupSearch();
  }
  
  public SearchableListView(ObservableList<T> items) {
    super(items);
    setupSearch();
  }
  
  private void setupSearch() {
    setOnKeyTyped(event -> {
      //      if (this.getSelectionModel().getSelectedItem() == null || this.getSelectionModel().getSelectedItem().) {
      handleKeys(event);
      
      //      }
      
    });
  }
  
  private void handleKeys(KeyEvent event) {
    KeyCode code = event.getCode();
    switch (code) {
      case ENTER:
        capturedInput = "";
        break;
      case BACK_SPACE:
        if (capturedInput.length() > 1) {
          capturedInput = capturedInput.substring(0, capturedInput.length() - 2);
        }
        break;
      default:
        capturedInput += event.getCharacter();
    }
    
    searchAndFocusItem();
    System.out.println(capturedInput);
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
