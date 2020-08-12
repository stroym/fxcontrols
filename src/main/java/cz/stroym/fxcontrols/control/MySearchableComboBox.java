package cz.stroym.fxcontrols.control;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Skin;
import lombok.Getter;

@Getter
public class MySearchableComboBox<T> extends ComboBox<T> {
  
  public MySearchableComboBox() {
    super();
  }
  
  public MySearchableComboBox(ObservableList<T> items) {
    super(items);
  }
  
  @Override
  protected Skin<?> createDefaultSkin() {
    return new MySearchableComboBoxSkin<>(this);
  }
  
  
}
