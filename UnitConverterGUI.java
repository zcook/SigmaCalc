
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * Created by Zane Cook on 5/14/2017.
 *
 * UnitConverterGUI initializes and sets the events for the UI of the unit converter application.
 *
 */

 class UnitConverterGUI{

   private Stage mainWindow;

    private ComboBox fromUnitsComboBox = new ComboBox();
    private ComboBox toUnitsComboBox = new ComboBox();
    private ComboBox precisionComboBox;
    private Label applicationTitle;
    private Label resultUnitsLabel;
    private Label inputUnitsLabel ;
    private TextField valueInputBox;
    private Label resultsLabel;
    private String conversionType;
    private Label conversionFormula;
    private String programName = "SigmaCalc";
    private String programVersion = "2.0";


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
       startSceneBorderLayout.setTop(MenuBarLayout());
       startSceneBorderLayout.setCenter(UserInterfaceLayout());
       UnitConverterScene = new Scene(startSceneBorderLayout, mainWindowWidth, mainWindowHeight);
       UnitConverterScene.getStylesheets().add("UnitConverterGUI.css");
        mainWindow.setMinHeight(420);
        mainWindow.setMinWidth(400);

        mainWindow.setMaxHeight(450);
        mainWindow.setMaxWidth(430);
        mainWindow.setResizable(false);



       return UnitConverterScene;

        }

   private MenuBar MenuBarLayout(){

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

   private VBox UserInterfaceLayout(){

      applicationTitle = new Label("Unit Converter");

      Separator formulaSeparator = new Separator();
      Region spacerNode = new Region();
      spacerNode.setMinHeight(10);

      VBox userInterface = new VBox(10);


      applicationTitle.setAlignment(Pos.BASELINE_CENTER);
      applicationTitle.getStyleClass().add("label-applicationTitle");
      applicationTitle.setPadding(new Insets(0,0,0,0));
      userInterface.setAlignment(Pos.CENTER);
      formulaSeparator.setOrientation(Orientation.HORIZONTAL);
      formulaSeparator.setPadding(new Insets(0,0,0,0));
      userInterface.getChildren().add(applicationTitle);
      userInterface.getChildren().add(UnitsComboBoxesLayout());
      userInterface.getChildren().add(PrecisionComboBoxLayout());
      userInterface.getChildren().add(InputAndResultsLayout());
      userInterface.getChildren().add(formulaSeparator);
      userInterface.getChildren().add(ConversionFormulaLayout());
      userInterface.getChildren().add(CalculateAndSwapButtonLayout());

      return userInterface;
   }

   private HBox UnitsComboBoxesLayout(){

      HBox InputBoxLayout = new HBox(20);

      InputBoxLayout.getChildren().addAll(FromUnitComboBoxLayout(), /*PrecisionComboBoxLayout(),*/ToUnitComboBoxLayout());
      InputBoxLayout.setAlignment(Pos.BASELINE_CENTER);
       InputBoxLayout.setPadding(new Insets(0,10,0,10));

      return InputBoxLayout;

   }

   private VBox FromUnitComboBoxLayout() {

      Label fromUnitsLabel = new Label();

      VBox fromUnitComboBoxLayout = new VBox(0);

      fromUnitsLabel.setText("From");
      fromUnitsLabel.getStyleClass().add("label-general");

      fromUnitsComboBox.setMinWidth(180);


      fromUnitComboBoxLayout.getChildren().addAll(fromUnitsLabel, fromUnitsComboBox);
      fromUnitComboBoxLayout.setAlignment(Pos.BASELINE_CENTER);

       fromUnitsComboBox.setOnAction(e-> fromUnitsComboBox_OnChange());

      return fromUnitComboBoxLayout;
   }

   private VBox ToUnitComboBoxLayout() {

      Label toUnitsLabel = new Label();

      VBox toUnitComboBoxLayout = new VBox(0);

      toUnitsLabel.setText("To");
      toUnitsLabel.getStyleClass().add("label-general");

      toUnitsComboBox.setMinWidth(180);


      toUnitComboBoxLayout.getChildren().addAll(toUnitsLabel, toUnitsComboBox);
      toUnitComboBoxLayout.setAlignment(Pos.BASELINE_CENTER);

       toUnitsComboBox.setOnAction( e-> toUnitComboBox_OnChange());

      return toUnitComboBoxLayout;
   }

   private VBox PrecisionComboBoxLayout(){

      Label precisionLabel = new Label();
      precisionComboBox = new ComboBox();
      VBox precisionBoxLayout = new VBox(0);

      precisionLabel.setText("Precision");
      precisionLabel.getStyleClass().add("label-general");

      precisionComboBox.setMinWidth(100);

      precisionBoxLayout.getChildren().addAll(precisionLabel, precisionComboBox);
      precisionBoxLayout.setAlignment(Pos.BASELINE_CENTER);
      precisionBoxLayout.setPadding(new Insets(10,0,0,0));
      VBox.setMargin(precisionComboBox, new Insets(0,0,0,0));

       precisionComboBox.getItems().addAll("0","0.1","0.12","0.123","0.1234","0.12345","0.123456");
       precisionComboBox.setValue("0");

       precisionComboBox.setOnAction(e-> PrecisionComboBox_OnChange());

      return precisionBoxLayout;
   }

   private VBox InputBoxLayout(){

      inputUnitsLabel = new Label("Input");
      inputUnitsLabel.getStyleClass().add("label-inputAndResultsUnits");


      valueInputBox = new TextField();
      valueInputBox.setEditable(true);
      valueInputBox.setMaxWidth(100);
      valueInputBox.getStyleClass().add("label-results");
      VBox valueInputBoxLayout = new VBox(8);

      valueInputBoxLayout.setAlignment(Pos.TOP_CENTER);
      valueInputBoxLayout.setPadding(new Insets(5,0,0,20));
      valueInputBoxLayout.getChildren().addAll(inputUnitsLabel,valueInputBox);

       valueInputBox.setOnAction(event -> InputBox_OnChange());


      return valueInputBoxLayout;
   }

   private VBox ResultsLayout(){

        resultUnitsLabel = new Label("Results:");
        resultUnitsLabel.setAlignment(Pos.BASELINE_CENTER);
        resultUnitsLabel.getStyleClass().add("label-inputAndResultsUnits");


        resultsLabel = new Label();
        resultsLabel.getStyleClass().add("label-results");


        VBox resultLabelLayout = new VBox(10);


        resultLabelLayout.setPadding(new Insets(0,20,0,0));
        resultLabelLayout.setAlignment(Pos.BASELINE_CENTER);
        resultLabelLayout.getChildren().addAll(resultUnitsLabel, resultsLabel);

        return resultLabelLayout;
    }

   private HBox InputAndResultsLayout(){


        HBox inputAndResultsLayout = new HBox(0);
        Region spacerNode = new Region();
        spacerNode.setPadding(new Insets(0,10,0,10));
        HBox.setHgrow(spacerNode, Priority.ALWAYS);

        inputAndResultsLayout.setPadding(new Insets(10,0,0,0));
        inputAndResultsLayout.getChildren().addAll( InputBoxLayout(),spacerNode, ResultsLayout());

        return inputAndResultsLayout;
    }

   private VBox ConversionFormulaLayout(){

      Label conversionFormulaLabel = new Label("Conversion Formula");
      conversionFormulaLabel.getStyleClass().addAll("label-general","label-conversionFormula");

      conversionFormula = new Label();

      VBox conversionFormulaLayout = new VBox(10);

      conversionFormula.setText("");
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

   private HBox CalculateAndSwapButtonLayout(){
        HBox calculateAndSwapButtonLayout = new HBox(20);

        Button swapButton = new Button("Swap Units");
        Button calculate = new Button("Calculate");
        Region spacerNode = new Region();

        calculateAndSwapButtonLayout.setPadding(new Insets(5,10,20,10));
        spacerNode.setPadding(new Insets(0,20,0,20));
        HBox.setHgrow(spacerNode,Priority.ALWAYS);
        swapButton.setPrefHeight(40);
        calculate.setPrefHeight(40);

        swapButton.setOnAction(e-> SwapButton_OnClick());
        swapButton.setOnKeyPressed(e-> SwapButton_OnEnter(e));
        calculate.setOnAction(e-> Calculate_OnClick());
        calculate.setOnKeyPressed(e-> Calculate_OnEnter(e));


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


       InitializeUnitChange();

       fromUnitsComboBox.getItems().addAll(linearUnits);
       toUnitsComboBox.getItems().addAll(linearUnits);
       fromUnitsComboBox.setValue(linearUnits.get(0));
       toUnitsComboBox.setValue(linearUnits.get(0));

       applicationTitle.setText("Linear Conversion");
       conversionType = "Linear";

    }

   private void VolumeMenuItem_OnClick(){

        ArrayList <String> volumeUnits = new ArrayList<>();

        volumeUnits.add("Gallons");
        volumeUnits.add("Liters");
        volumeUnits.add("Milliliters");
        volumeUnits.add("Cubic Inches");
        volumeUnits.add("Cubic Centimeters");
        volumeUnits.add("Cubic Millimeters");

        InitializeUnitChange();

        fromUnitsComboBox.getItems().addAll(volumeUnits);

        toUnitsComboBox.getItems().addAll(volumeUnits);

       fromUnitsComboBox.setValue(volumeUnits.get(0));
       toUnitsComboBox.setValue(volumeUnits.get(0));

       applicationTitle.setText("Volume Conversion");
       conversionType = "Volume";


   }

   private void MassMenuItem_OnClick(){

        ArrayList<String> massUnits = new ArrayList<>();

        massUnits.add("Tons");
        massUnits.add("Pounds");
        massUnits.add("Ounces");
        massUnits.add("Tonnes");
        massUnits.add("Kilograms");
        massUnits.add("Grams");


        InitializeUnitChange();

        fromUnitsComboBox.getItems().addAll(massUnits);
        toUnitsComboBox.getItems().addAll(massUnits);

       fromUnitsComboBox.setValue(massUnits.get(0));
       toUnitsComboBox.setValue(massUnits.get(0));

       applicationTitle.setText("Mass Conversion");
       conversionType = "Mass";

    }

   private void TemperatureMenuItem_OnClick(){

        ArrayList <String> tempUnits = new ArrayList<>();

        tempUnits.add("Fahrenheit");
        tempUnits.add("Celsius");
        tempUnits.add("Kelvin");

        InitializeUnitChange();

        fromUnitsComboBox.getItems().addAll(tempUnits);
        toUnitsComboBox.getItems().addAll(tempUnits);

       fromUnitsComboBox.setValue(tempUnits.get(0));
       toUnitsComboBox.setValue(tempUnits.get(0));

       applicationTitle.setText("Temperature Conversion");
       conversionType = "Temperature";

    }

   private void fromUnitsComboBox_OnChange(){

       if (fromUnitsComboBox.getValue() != null){
             inputUnitsLabel.setText(fromUnitsComboBox.getValue().toString());

             if (CanCalculate()) {
               CalculateResults();

           }

       }

    }

   private void toUnitComboBox_OnChange(){

      if (toUnitsComboBox.getValue()!=null) {
          resultUnitsLabel.setText(toUnitsComboBox.getValue().toString());

          if (CanCalculate()) {
              CalculateResults();
          }
      }

    }

   private void PrecisionComboBox_OnChange(){
        if (CanCalculate()){
            CalculateResults();
        }
    }

   private void Calculate_OnClick(){

        if (CanCalculate()) {
            CalculateResults();
        }
    }

   private void Calculate_OnEnter(javafx.scene.input.KeyEvent e){

       if (IsEnterButton(e) && CanCalculate()){

           CalculateResults();
       }
    }

   private void InputBox_OnChange(){

        if (CanCalculate()) {
            CalculateResults();
        }
    }

   private void AboutMenu_OnClick(){

      MessageBox.show("About "+ programName, programName + " Version "+programVersion +
              " \n\n A multi application suite to assist with everyday calculations / conversions.\n");
   }

   private void SwapButton_OnClick(){

       if (CanCalculate()) {
           SwapUnits();
       }
   }

   private void SwapButton_OnEnter(javafx.scene.input.KeyEvent e){
        if (IsEnterButton(e) && CanCalculate()){

            SwapUnits();
        }
    }

   private void SwapUnits(){
        String tempString = fromUnitsComboBox.getValue().toString();
        fromUnitsComboBox.setValue(toUnitsComboBox.getValue().toString());
        toUnitsComboBox.setValue(tempString);
        inputUnitsLabel.setText(fromUnitsComboBox.getValue().toString());
        resultUnitsLabel.setText(toUnitsComboBox.getValue().toString());
    }

   private void InitializeUnitChange(){
        fromUnitsComboBox.getItems().clear();
        toUnitsComboBox.getItems().clear();
        valueInputBox.clear();
        resultsLabel.setText("");
        conversionFormula.setText("");
    }

   private void CalculateResults(){

       String fromUnits = fromUnitsComboBox.getValue().toString();
       String toUnits = toUnitsComboBox.getValue().toString();
       String precision = precisionComboBox.getValue().toString();
       String inputValue = valueInputBox.getText();
       String answer;
       String formula;
       String selectedFormat;

       UnitConverter unitConverter = new UnitConverter(inputValue,fromUnits,toUnits,conversionType,precision);
       answer = unitConverter.convertUnits();
       formula = unitConverter.getFormula(answer);
       selectedFormat = "%,"+unitConverter.setPrecision("" + precision)+"f";


       try {
          resultsLabel.setText(String.format(selectedFormat,Double.parseDouble(answer)));
          conversionFormula.setText(formula);

       }
       catch (Exception e){

           System.out.println("Error in calculate method");
       }
   }


   private boolean IsNumber(String testNumber){

       boolean isNumber = true;
       try{
           Double.parseDouble(testNumber);
       }
       catch (Exception e){
           isNumber = false;

       }

       return isNumber;
   }

   private boolean CanCalculate(){

       boolean canCalculate = false;

       if (fromUnitsComboBox.getValue() != null && toUnitsComboBox.getValue() != null && IsNumber(valueInputBox.getText())){

           canCalculate = true;
       }

       return canCalculate;
   }

   private boolean IsEnterButton (javafx.scene.input.KeyEvent e){
       boolean isEnterButton = false;

       if (e.getCode()== KeyCode.ENTER){

           isEnterButton = true;
       }

       return isEnterButton;
   }

}

