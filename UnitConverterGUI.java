import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by Zane on 5/14/2017.
 */

 class UnitConverterGUI{

   private Stage mainWindow;

    private ComboBox fromUnits = new ComboBox();
    private ComboBox toUnits = new ComboBox();
    private Label applicationTitle;

    Scene BuildUnitConverterGUI(Stage window) {

       mainWindow = window;

       //Main Window Components
       Scene UnitConverterScene;
       BorderPane startSceneBorderLayout;


       //Method Variables

       int mainWindowWidth = 400;
       int mainWindowHeight = 400;

       //Initialize Main Window Components

       startSceneBorderLayout = new BorderPane();



       //Build Scene
       startSceneBorderLayout.setTop(BuildMenuBar());
       startSceneBorderLayout.setCenter(BuildUserInterface());
       UnitConverterScene = new Scene(startSceneBorderLayout, mainWindowWidth, mainWindowHeight);
        mainWindow.setMinHeight(420);
        mainWindow.setMinWidth(400);

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

       Menu conversionType = new Menu("Conversion Type");
       MenuItem linearConversionMenuItem = new MenuItem("Linear");
       MenuItem volumeConversionMenuItem = new MenuItem("Volume");
       MenuItem massConversionMenuItem = new MenuItem("Mass");
       MenuItem temperatureConversionMenuItem = new MenuItem("Temperature");


      //Build Menus
      menuBar.getMenus().addAll(applicationMenu,conversionType, helpMenu);
      applicationMenu.getItems().addAll(calPlanner);
      conversionType.getItems().addAll(
               linearConversionMenuItem,
               volumeConversionMenuItem,
               massConversionMenuItem,
               temperatureConversionMenuItem);

      helpMenu.getItems().addAll(aboutMenu);


      calPlanner.setOnAction(event -> CalPlannerMenu_OnClick());

      linearConversionMenuItem.setOnAction(e-> LinearMenuItem_OnClick());
       volumeConversionMenuItem.setOnAction(e->VolumeMenuItem_OnClick());
       massConversionMenuItem.setOnAction(e->MassMenuItem_OnClick());
       temperatureConversionMenuItem.setOnAction(e-> TemperatureMenuItem_OnClick());

      aboutMenu.setOnAction(event -> AboutMenu_OnClick());


      return menuBar;
   }

   private VBox BuildUserInterface(){

      applicationTitle = new Label("Unit Converter");

      Separator formulaSeparator = new Separator();

      VBox userInterface = new VBox(10);


      applicationTitle.setAlignment(Pos.BASELINE_CENTER);
      applicationTitle.setFont(Font.font(18));
      userInterface.setAlignment(Pos.BASELINE_CENTER);
      formulaSeparator.setOrientation(Orientation.HORIZONTAL);
      formulaSeparator.setPadding(new Insets(30,0,10,0));

      userInterface.getChildren().add(applicationTitle);
      userInterface.getChildren().add(BuildComboBoxesLayout());
      userInterface.getChildren().add(InputAndResultsLayout());
      userInterface.getChildren().add(formulaSeparator);
      userInterface.getChildren().add(BuildConversionFormulaLayout());
      userInterface.getChildren().add(CalculateAndSwapButtonLayout());


      return userInterface;
   }

   private HBox BuildComboBoxesLayout(){

      HBox InputBoxLayout = new HBox(10);

      InputBoxLayout.getChildren().addAll(BuildFromUnitComboBoxLayout(), BuildPrecisionComboBoxLayout(), BuildToUnitComboBoxLayout());
      InputBoxLayout.setAlignment(Pos.TOP_CENTER);
       InputBoxLayout.setPadding(new Insets(0,10,0,10));

      return InputBoxLayout;

   }

   private VBox BuildFromUnitComboBoxLayout() {



      Label fromUnitsLabel = new Label();

      VBox fromUnitComboBoxLayout = new VBox(0);

      fromUnitsLabel.setText("From");
      fromUnits.setMinWidth(120);


      fromUnitComboBoxLayout.getChildren().addAll(fromUnitsLabel, fromUnits);
      fromUnitComboBoxLayout.setAlignment(Pos.BASELINE_CENTER);

      return fromUnitComboBoxLayout;
   }

   private VBox BuildToUnitComboBoxLayout() {

      Label toUnitsLabel = new Label();

      VBox toUnitComboBoxLayout = new VBox(0);

      toUnitsLabel.setText("To");
      toUnits.setMinWidth(120);


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

       precisionInputBox.getItems().addAll("0","0.1","0.123","0.1234","0.12345");
       precisionInputBox.setValue("0");

      return precisionBoxLayout;
   }

   private VBox BuildValueInputBoxLayout(){

      Label inputUnitsLabel = new Label("Input Units");

      TextField valueInputBox = new TextField();
      valueInputBox.setEditable(true);
      valueInputBox.setPrefWidth(100);
      VBox valueInputBoxLayout = new VBox(0);

      valueInputBoxLayout.setAlignment(Pos.BASELINE_CENTER);
      valueInputBoxLayout.setPadding(new Insets(0,0,0,20));
      valueInputBoxLayout.getChildren().addAll(inputUnitsLabel,valueInputBox);


      return valueInputBoxLayout;
   }

   private VBox BuildResultsLayout(){

        Label resultUnitsLabel = new Label("Results: "+"Units");
        resultUnitsLabel.setAlignment(Pos.BASELINE_CENTER);

        Label results = new Label("Results");


        VBox resultLabelLayout = new VBox(0);


        resultLabelLayout.setPadding(new Insets(0,20,0,0));
        resultLabelLayout.setAlignment(Pos.BASELINE_CENTER);
        resultLabelLayout.getChildren().addAll(resultUnitsLabel,results);

        return resultLabelLayout;
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

   private HBox InputAndResultsLayout(){
      HBox inputAndResultsLayout = new HBox();
       inputAndResultsLayout.setPadding(new Insets(10,0,0,0));
       Region spacerNode = new Region();
       spacerNode.setPadding(new Insets(0,10,0,10));
       HBox.setHgrow(spacerNode, Priority.ALWAYS);

      inputAndResultsLayout.getChildren().addAll(BuildValueInputBoxLayout(), spacerNode, BuildResultsLayout());

       HBox.setMargin(BuildValueInputBoxLayout(),new Insets(0,0,0,80));
       HBox.setMargin(BuildValueInputBoxLayout(),new Insets(0,80,0,0));


      return inputAndResultsLayout;
   }

    private HBox CalculateAndSwapButtonLayout(){
        HBox calculateAndSwapButtonLayout = new HBox(20);

        Button swapButton = new Button("Swap Units");
        Button calculate = new Button("Calculate");
        Region spacerNode = new Region();

        calculateAndSwapButtonLayout.setPadding(new Insets(20,10,20,10));
        spacerNode.setPadding(new Insets(0,20,0,20));
        HBox.setHgrow(spacerNode,Priority.ALWAYS);
        swapButton.setPrefHeight(40);
        calculate.setPrefHeight(40);



        calculateAndSwapButtonLayout.getChildren().addAll(swapButton,spacerNode,calculate);

        return calculateAndSwapButtonLayout;
    }




   private void CalPlannerMenu_OnClick(){

      mainWindow.setScene(SceneBuilder.BuildCalPlannerScene(mainWindow));
   }
   private void LinearMenuItem_OnClick(){

        ArrayList<String> linearUnits = new ArrayList<>() ;

        linearUnits.add("Miles");
        linearUnits.add("Feet");
        linearUnits.add("Inches");
        linearUnits.add("Kilometers");
        linearUnits.add("Meters");
        linearUnits.add("Millimeters");


       fromUnits.getItems().clear();
       toUnits.getItems().clear();
       fromUnits.getItems().addAll(linearUnits);
       toUnits.getItems().addAll(linearUnits);
       fromUnits.setValue(linearUnits.get(0));
       toUnits.setValue(linearUnits.get(0));

       applicationTitle.setText("Linear Conversion");

    }
   private void VolumeMenuItem_OnClick(){

        ArrayList <String> volumeUnits = new ArrayList<>();

        volumeUnits.add("Gallons");
        volumeUnits.add("Liters");
        volumeUnits.add("Milliliters");
        volumeUnits.add("Cubic Inches");
        volumeUnits.add("Cubic Centimeters");
        volumeUnits.add("Cubic Millimeters");

        fromUnits.getItems().clear();
        toUnits.getItems().clear();
        fromUnits.getItems().addAll(volumeUnits);
        toUnits.getItems().addAll(volumeUnits);

       fromUnits.setValue(volumeUnits.get(0));
       toUnits.setValue(volumeUnits.get(0));

       applicationTitle.setText("Volume Conversion");

   }
   private void MassMenuItem_OnClick(){

        ArrayList<String> massUnits = new ArrayList<>();

        massUnits.add("Tones");
        massUnits.add("Pounds");
        massUnits.add("Ounces");
        massUnits.add("Tonnes");
        massUnits.add("Kilograms");
        massUnits.add("Grams");

        fromUnits.getItems().clear();
        toUnits.getItems().clear();
        fromUnits.getItems().addAll(massUnits);
        toUnits.getItems().addAll(massUnits);

       fromUnits.setValue(massUnits.get(0));
       toUnits.setValue(massUnits.get(0));

       applicationTitle.setText("Mass Conversion");
    }
   private void TemperatureMenuItem_OnClick(){

        ArrayList <String> tempUnits = new ArrayList<>();

        tempUnits.add("Fahrenheit");
        tempUnits.add("Celsius");
        tempUnits.add("Kelvin");

        fromUnits.getItems().clear();
        toUnits.getItems().clear();

        fromUnits.getItems().addAll(tempUnits);
        toUnits.getItems().addAll(tempUnits);

       fromUnits.setValue(tempUnits.get(0));
       toUnits.setValue(tempUnits.get(0));

       applicationTitle.setText("Temperature Conversion");
    }

   private void AboutMenu_OnClick(){

      MessageBox.show("about selected", "Message Box");
   }

}

