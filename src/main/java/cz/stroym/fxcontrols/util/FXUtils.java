package cz.stroym.fxcontrols.util;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class FXUtils {
  
  public static TextFlow buildTextFlowHighlight(String candidateText, String entryText) {
    int entryIndex = candidateText.toLowerCase().indexOf(entryText.toLowerCase());
    
    Text highlight = new Text(candidateText.substring(entryIndex, entryIndex + entryText.length()));
    highlight.setFill(Color.ORANGE);
    highlight.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, Font.getDefault().getSize()));
    
    return new TextFlow(new Text(candidateText.substring(0, entryIndex)),
                        highlight,
                        new Text(candidateText.substring(entryIndex + entryText.length()))
    );
  }
  
}
