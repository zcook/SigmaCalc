import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Zane on 5/14/2017.
 */

 class UnitConverterGUI{

   private Stage mainWindow;

    Scene BuildUnitConverterGUI(Stage window) {

       mainWindow = window;

       //Main Window Components
       Scene UnitConverterScene;
       BorderPane startSceneBorderLayout;


       //Method Variables

       int mainWindowWidth = 350;
       int mainWindowHeight = 350;

       //Initialize Main Window Components

       startSceneBorderLayout = new BorderPane();



       //Build Scene
       startSceneBorderLayout.setTop(BuildMenuBar());
       startSceneBorderLayout.setCenter(BuildUserInterface());
       UnitConverterScene = new Scene(startSceneBorderLayout, mainWindowWidth, mainWindowHeight);

       return UnitConverterScene;

        }

   private MenuBar BuildMenuBar(){

      javafx.scene.control.MenuBar menuBar;
      javafx.scene.control.Menu applicationMenu;
      javafx.scene.control.Menu helpMenu;
      javafx.scene.control.MenuItem calPlanner;
      javafx.scene.control.MenuItem aboutMenu;

      //Initialize Menu Items
      menuBar = new javafx.scene.control.MenuBar();
      applicationMenu = new javafx.scene.control.Menu("Application");
      helpMenu = new javafx.scene.control.Menu("Help");
      calPlanner = new javafx.scene.control.MenuItem("CalPlanner");
      aboutMenu = new javafx.scene.control.MenuItem("About");

      //Build Menus
      menuBar.getMenus().addAll(applicationMenu, helpMenu);
      applicationMenu.getItems().addAll(calPlanner);
      helpMenu.getItems().addAll(aboutMenu);


      calPlanner.setOnAction(event -> CalPlannerMenu_OnClick());
      aboutMenu.setOnAction(event -> AboutMenu_OnClick());

      return menuBar;
   }

   private VBox BuildUserInterface(){

      Label applicationTitle = new Label("Unit Converter");
      Separator formulaSeparator = new Separator();

      VBox userInterface = new VBox(10);

      applicationTitle.setAlignment(Pos.BASELINE_CENTER);
      userInterface.setAlignment(Pos.BASELINE_CENTER);
      formulaSeparator.setOrientation(Orientation.HORIZONTAL);
      formulaSeparator.setPadding(new Insets(30,0,10,0));

      userInterface.getChildren().add(applicationTitle);
      userInterface.getChildren().add(BuildComboBoxesLayout());
      userInterface.getChildren().add(InputAndResultsLayout());
      userInterface.getChildren().add(formulaSeparator);
      userInterface.getChildren().add(BuildConversionFormulaLayout());

      return userInterface;
   }

   private HBox BuildComboBoxesLayout(){

      HBox InputBoxLayout = new HBox(10);

      InputBoxLayout.getChildren().addAll(BuildFromUnitComboBoxLayout("Linear"), BuildPrecisionComboBoxLayout(), BuildToUnitComboBoxLayout());
      InputBoxLayout.setAlignment(Pos.TOP_CENTER);

      return InputBoxLayout;

   }

   private VBox BuildFromUnitComboBoxLayout(String conversionType) {

      ComboBox fromUnits = new ComboBox();

          switch (conversionType) {

            case "Initialize": {
               fromUnits.getItems().add("");
            }
            break;

            case "Linear": {
               fromUnits.getItems().addAll(
                       "Miles",
                       "Yards");
            }
         }




      Label fromUnitsLabel = new Label();


      VBox fromUnitComboBoxLayout = new VBox(0);

      fromUnitsLabel.setText("From");
      fromUnits.setMinWidth(100);

      fromUnitComboBoxLayout.getChildren().addAll(fromUnitsLabel, fromUnits);
      fromUnitComboBoxLayout.setAlignment(Pos.BASELINE_CENTER);

      return fromUnitComboBoxLayout;
   }

   private VBox BuildToUnitComboBoxLayout() {

      Label toUnitsLabel = new Label();
      ComboBox toUnits = new ComboBox();
      VBox toUnitComboBoxLayout = new VBox(0);

      toUnitsLabel.setText("To");
      toUnits.setMinWidth(100);

      toUnitComboBoxLayout.getChildren().addAll(toUnitsLabel, toUnits);
      toUnitComboBoxLayout.setAlignment(Pos.BASELINE_CENTER);

      return toUnitComboBoxLayout;
   }

   private VBox BuildPrecisionComboBoxLayout(){

      Label precisionLabel = new Label();
      ComboBox precisionInputBox = new ComboBox();
      VBox precisionBoxLayout = new VBox(0);
      precisionBoxLayout.setMargin(precisionLabel,new Insets(20));

      precisionLabel.setText("Precision");
      precisionInputBox.setMinWidth(100);

      precisionBoxLayout.getChildren().addAll(precisionLabel,precisionInputBox);
      precisionBoxLayout.setAlignment(Pos.BASELINE_CENTER);

      return precisionBoxLayout;
   }

   private VBox BuildValueInputBoxLayout(){

      Label inputLabel = new Label("Input Value");

      TextField valueInputBox = new TextField();
      valueInputBox.setEditable(true);
      valueInputBox.setPrefWidth(100);
      VBox valueInputBoxLayout = new VBox(0);

      valueInputBoxLayout.getChildren().addAll(inputLabel,valueInputBox);
      valueInputBoxLayout.setAlignment(Pos.BASELINE_CENTER);

      return valueInputBoxLayout;
   }

   private VBox BuildConversionFormulaLayout(){

      Label conversionFormulaLabel = new Label("Conversion Formula");
      Label conversionFormula = new Label();

      VBox conversionFormulaLayout = new VBox(10);

      conversionFormula.setText("x=x");
      conversionFormula.setAlignment(Pos.TOP_CENTER);
      conversionFormulaLabel.setAlignment(Pos.TOP_CENTER);

      HBox hbConvForm = new HBox(conversionFormula);
      HBox hbConvFormlbl = new HBox(conversionFormulaLabel);
      hbConvForm.setAlignment(Pos.TOP_CENTER);
      hbConvFormlbl.setAlignment(Pos.TOP_CENTER);

      conversionFormulaLayout.getChildren().addAll(hbConvFormlbl,hbConvForm);
      conversionFormulaLayout.setAlignment(Pos.TOP_CENTER);

      return conversionFormulaLayout;

   }

   private VBox BuildResultsLayout(){

      Label resultLabel = new Label("Results: "+"is");
      resultLabel.setAlignment(Pos.BASELINE_CENTER);


      VBox resultLabelLayout = new VBox(0);
      resultLabelLayout.setAlignment(Pos.CENTER_LEFT);

      resultLabelLayout.getChildren().addAll(resultLabel);

      return resultLabelLayout;
   }

   private BorderPane InputAndResultsLayout(){
      BorderPane inputAndResultsLayout = new BorderPane();
      inputAndResultsLayout.setLeft(BuildValueInputBoxLayout());
      inputAndResultsLayout.setRight(BuildResultsLayout());
      inputAndResultsLayout.setCenter(new Label("Equals"));

      return inputAndResultsLayout;
   }

   private void CalPlannerMenu_OnClick(){

      mainWindow.setScene(SceneBuilder.BuildCalPlannerScene(mainWindow));
   }

   private void AboutMenu_OnClick(){

      MessageBox.show("about selected", "Message Box");
   }

}

