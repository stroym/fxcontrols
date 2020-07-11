package cz.stroym.fxcontrols.control;

import cz.stroym.fxcontrols.exception.NoSelectionException;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchableListView<T> extends ListView<T> {
  
  private final ContextMenu inputContext = new ContextMenu();
  
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
  
  private void registerCellFactory() {
    this.setCellFactory(new Callback<>() {
      @Override
      public ListCell<T> call(ListView<T> listView) {
        return new ListCell<>() {
          @Override
          protected void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);
            setText(empty ? null : getItem() == null ? "" : getItem().toString());
            
            if (item == getSelectionModel().getSelectedItem()) {
              setStyle(
                  "-fx-background-color: transparent;" +
                  "-fx-text-fill: orange;" +
                  "-fx-font-weight: bolder;"
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
    //handle key presses
    this.setOnKeyPressed(event -> {
      event.consume();
      
      switch (event.getCode()) {
        case ENTER:
          capturedInput = "";
          break;
        case BACK_SPACE:
        case DELETE:
          if (capturedInput.length() > 0) {
            capturedInput = capturedInput.substring(0, capturedInput.length() - 1);
          }
          
          searchAndFocusItem();
          break;
        default:
          capturedInput += event.getText();
          searchAndFocusItem();
      }
      
      showContext();
    });
    
    this.inputContext.setOnHidden(event -> {
      event.consume();
      
      capturedInput = "";
    });
    
    //sort items after any change
    this.getItems().addListener((ListChangeListener<T>) change -> setItems(getItems().sorted()));
  }
  
  private void searchAndFocusItem() {
    if (!capturedInput.isBlank()) {
      for (T item : getItems()) {
        if (item.toString().startsWith(capturedInput)) {
          scrollTo(getItems().indexOf(item));
          getSelectionModel().select(item);
          
          return;
        }
      }
    }
    
    //if no match is found or if capturedInput is empty, deselect
    getSelectionModel().select(-1);
  }
  
  private void showContext() {
    if (!capturedInput.isBlank()) {
      Label          entryLabel = new Label(capturedInput);
      CustomMenuItem item       = new CustomMenuItem(entryLabel, true);
      
      inputContext.getItems().clear();
      inputContext.getItems().add(item);
      
      if (!inputContext.isShowing()) {
        //TODO try to align context better (maybe top right inside parent?)
        inputContext.show(this, Side.TOP, 0, 0);
      }
    } else {
      if (inputContext.isShowing()) {
        inputContext.hide();
      }
    }
  }
  
  public T getSelectedItem() throws NoSelectionException {
    T item = this.getSelectionModel().getSelectedItem();
    
    if(item!= null){
      return item;
    } else {
      throw new NoSelectionException();
    }
    
  }
  
}
