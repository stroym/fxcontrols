module cz.stroym.fxcontrols {
  requires javafx.controls;
  requires javafx.fxml;
  
  requires static lombok;
  requires org.controlsfx.controls;
  
  opens image;
//  opens css;
  
  exports cz.stroym.fxcontrols.control to javafx.fxml, cz.stroym.fxnotes;
}
