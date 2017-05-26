import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by Zane on 5/14/2017.
 */

 class CalPlannerGUI {

   private Stage mainWindow;

   private Label applicationTitle;
    private String programName = "SigmaCalc";
    private String programVersion = "2.0";
    private DatePicker fromDatePicker;
    private TextField fromStartDateInputBox;
    private DatePicker toDatePicker;
    private TextField fromEndDateInputBox;
    private TextField weeksInputBox;
    private TextField daysInputBox;
    private RadioButton fromStartDateRadioButton;
    private RadioButton fromEndDateRadioButton;
    private Button stepButton;
    private Label results = new Label("Results");
    private ComboBox numberOfWorkDaysComboBox;
    private Boolean isFromStartDate = false;
    private Boolean isFromEndDate = false;

    BorderPane startSceneBorderLayout;


    Scene BuildCalPlannerGUI(Stage window){

        mainWindow = window;

        //Main Window Components
        Scene CalPlannerScene;


        int mainWindowWidth = 400;
        int mainWindowHeight = 420;

        //Initialize Main Window Components

        startSceneBorderLayout = new BorderPane();



        //Build Scene
        startSceneBorderLayout.setTop(MenuBarLayout());
        startSceneBorderLayout.setCenter(LeadTimeUserInterface());
        CalPlannerScene = new Scene(startSceneBorderLayout, mainWindowWidth, mainWindowHeight);
        CalPlannerScene.getStylesheets().add("CalPlannerGUI.css");
        mainWindow.setMinHeight(420);
        mainWindow.setMinWidth(400);

        return CalPlannerScene;
    }

    private MenuBar MenuBarLayout(){

        javafx.scene.control.MenuBar menuBar;
        javafx.scene.control.Menu applicationMenu;
        javafx.scene.control.Menu helpMenu;
        Menu planTypeMenu;
        MenuItem workDayPlanner;
        MenuItem leadTimePlanner;
        javafx.scene.control.MenuItem unitConverter;
        javafx.scene.control.MenuItem aboutMenu;

        //Initialize Menu Items
        menuBar = new javafx.scene.control.MenuBar();
        applicationMenu = new javafx.scene.control.Menu("Application");
        unitConverter = new javafx.scene.control.MenuItem("Unit Converter");

        planTypeMenu = new Menu("Plan Type");
        workDayPlanner = new MenuItem("Work Day Planner");
        leadTimePlanner = new MenuItem("Lead Time Planner");


        helpMenu = new javafx.scene.control.Menu("Help");
        aboutMenu = new javafx.scene.control.MenuItem("About");

        //Build Menus
        menuBar.getMenus().addAll(applicationMenu,planTypeMenu,helpMenu);

        applicationMenu.getItems().addAll(unitConverter);
        planTypeMenu.getItems().addAll(workDayPlanner,leadTimePlanner);
        helpMenu.getItems().addAll(aboutMenu);


        unitConverter.setOnAction(event -> UnitConverterMenu_OnClick());
        aboutMenu.setOnAction(event -> AboutMenu_OnClick());

        workDayPlanner.setOnAction(e-> WorkDayPlannerMenu_OnClick());
        leadTimePlanner.setOnAction(e-> LeadTimePlannerMenu_OnClick());

        return menuBar;
    }
    private VBox LeadTimeUserInterface(){

        applicationTitle = new Label("Lead Time Planner");
        applicationTitle.setAlignment(Pos.CENTER);
        applicationTitle.getStyleClass().add("label-applicationTitle");
        applicationTitle.setPadding(new Insets(10,0,10,0));

        Separator answerSeparator = new Separator();

        VBox leadTimeUserInterface = new VBox(15);
        leadTimeUserInterface.setAlignment(Pos.TOP_CENTER);


        results.getStyleClass().add("label-results");
        results.setAlignment(Pos.TOP_CENTER);
        results.setPadding(new Insets(0,0,10,0));

        leadTimeUserInterface.getChildren().add(applicationTitle);
        leadTimeUserInterface.getChildren().add(DatePickerLayout());
        leadTimeUserInterface.getChildren().add(WeeksAndDaysLayout());
        leadTimeUserInterface.getChildren().add(FromStartDate_FromEndDate_Selection_Layout());
        leadTimeUserInterface.getChildren().add(answerSeparator);
        leadTimeUserInterface.getChildren().add(results);
        leadTimeUserInterface.getChildren().add(LeadTimeButtonLayout());

        fromStartDateRadioButton.setSelected(true);
        FromStartDateRadioButton_OnSelect();

        return leadTimeUserInterface;
    }
    private VBox WorkDayUserInterface(){

        Button calculateButton = new Button("Calculate");
        calculateButton.setMinHeight(40);
        calculateButton.setMinWidth(80);
        VBox workDayUserInterface = new VBox(20);
        Separator resultsSeparator = new Separator();

        results.getStyleClass().add("label-results");
        results.setPadding(new Insets(0,0,10,0));
        results.setAlignment(Pos.TOP_CENTER);

        workDayUserInterface.setAlignment(Pos.TOP_CENTER);
        workDayUserInterface.setPadding(new Insets(20,20,20,20));

        workDayUserInterface.getChildren().add(applicationTitle);
        applicationTitle.setPadding(new Insets(0,0,10,0));
        workDayUserInterface.getChildren().add(DatePickerLayout());


        workDayUserInterface.getChildren().add(WorkDayComboBoxLayout());
        workDayUserInterface.getChildren().add(resultsSeparator);
        workDayUserInterface.getChildren().add(results);


        workDayUserInterface.getChildren().add(calculateButton);

        return workDayUserInterface;

    }
    private HBox DatePickerLayout(){

        Region spacerNode = new Region();
        spacerNode.setMinWidth(50);

        HBox datePickerLayout = new HBox(10);
        datePickerLayout.setPadding(new Insets(0,10,10,10));
        datePickerLayout.getChildren().addAll(FromDateLayout(),spacerNode,ToDateLayout());

        return datePickerLayout;
    }

    private VBox FromDateLayout(){

        VBox fromDateLayout = new VBox(10);
        fromDateLayout.setAlignment(Pos.CENTER);

        Label fromDateLabel = new Label("Start Date");
        fromDateLabel.getStyleClass().add("label-general");
        fromDatePicker = new DatePicker();


        fromDatePicker.getEditor().setVisible(false);
        fromDatePicker.setMaxWidth(0);
        fromDatePicker.setFocusTraversable(false);



        fromStartDateInputBox = new TextField();

        HBox fromDateInputLayout = new HBox(0);
        fromDateInputLayout.setAlignment(Pos.CENTER);
        fromDateInputLayout.getChildren().addAll(fromStartDateInputBox,fromDatePicker);

        fromDateLayout.getChildren().addAll(fromDateLabel, fromDateInputLayout);

        fromDatePicker.setOnAction(e-> FromDatePicker_OnSelect());
        fromStartDateInputBox.setOnKeyPressed(e -> AnyControl_OnEnter(e));
        fromStartDateInputBox.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean lostFocus, Boolean gainedFocus) {

                if (lostFocus){

                    ParseStartDate();
                    
                }

                else if (gainedFocus){


                }
            }
        });



        return fromDateLayout;
    }

    private VBox ToDateLayout(){

        VBox toDateLayout = new VBox(10);
        toDateLayout.setAlignment(Pos.CENTER);

        Label toDateLabel = new Label("End Date");
        toDateLabel.getStyleClass().add("label-general");
        toDatePicker = new DatePicker();
        toDatePicker.setMaxWidth(0);
        toDatePicker.getEditor().setVisible(false);
        toDatePicker.setFocusTraversable(false);

        fromEndDateInputBox = new TextField();

        HBox toDateInputLayout = new HBox(0);
        toDateInputLayout.setAlignment(Pos.CENTER);
        toDateInputLayout.getChildren().addAll(fromEndDateInputBox,toDatePicker);

        toDateLayout.getChildren().addAll(toDateLabel, toDateInputLayout);

        toDatePicker.setOnAction(e-> ToDatePicker_OnSelect());
        fromEndDateInputBox.setOnKeyPressed(e-> AnyControl_OnEnter(e));
        fromEndDateInputBox.focusedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean lostFocus, Boolean gainedFocus) {

                if (lostFocus){

                    ParseEndDate();

                }

                else if (gainedFocus){


                }
            }
        });

        return toDateLayout;
    }

    private HBox WeeksAndDaysLayout(){

        Region spacerNode = new Region();
        spacerNode.setMinWidth(100);

        HBox weeksAndDaysLayout = new HBox(10);
        weeksAndDaysLayout.setAlignment(Pos.CENTER);
        weeksAndDaysLayout.getChildren().addAll(WeeksLayout(),spacerNode,DaysLayout());

        return weeksAndDaysLayout;
    }

    private VBox WeeksLayout(){

        Label weeksLabel = new Label("Weeks");
        weeksLabel.getStyleClass().add("label-general");
        weeksInputBox = new TextField();
        weeksInputBox.setMaxWidth(40);
        weeksInputBox.setText("0");

        VBox weeksLayout = new VBox(10);
        weeksLayout.setAlignment(Pos.CENTER);

        weeksLayout.getChildren().addAll(weeksLabel, weeksInputBox);

        weeksInputBox.setOnKeyPressed(e-> AnyControl_OnEnter(e));

        return weeksLayout;
    }

    private VBox DaysLayout(){

        Label daysLabel = new Label("Days");
        daysLabel.getStyleClass().add("label-general");
        daysInputBox = new TextField();
        daysInputBox.setMaxWidth(40);
        daysInputBox.setText("0");

        VBox daysLayout = new VBox(10);
        daysLayout.setAlignment(Pos.CENTER);

        daysLayout.getChildren().addAll(daysLabel, daysInputBox);

        daysInputBox.setOnKeyPressed(e-> AnyControl_OnEnter(e));

        return daysLayout;
    }

    private HBox FromStartDate_FromEndDate_Selection_Layout(){

        HBox selectionLayout = new HBox();
        selectionLayout.setAlignment(Pos.CENTER);
        selectionLayout.setPadding(new Insets(10,0,10,0));

        VBox radioButtonLayout = new VBox(10);
        radioButtonLayout.setAlignment(Pos.CENTER_LEFT);

        fromStartDateRadioButton = new RadioButton("From Start Date");
        fromStartDateRadioButton.setAlignment(Pos.BOTTOM_LEFT);
        fromEndDateRadioButton = new RadioButton("From End Date");
        fromEndDateRadioButton.setAlignment(Pos.BOTTOM_LEFT);

        radioButtonLayout.getChildren().addAll(fromStartDateRadioButton, fromEndDateRadioButton);
        selectionLayout.getChildren().add(radioButtonLayout);

        fromStartDateRadioButton.setOnAction(e-> FromStartDateRadioButton_OnSelect());
        fromEndDateRadioButton.setOnAction(e-> FromEndDateRadioButton_OnSelect());

        return selectionLayout;
    }

    private HBox LeadTimeButtonLayout(){

        stepButton = new Button("Step");
        stepButton.setMinHeight(38);
        stepButton.setMinWidth(80);

        Button calculateButton = new Button("Calculate");
        calculateButton.setMinHeight(38);
        calculateButton.setMinWidth(80);

        Region spacerNode = new Region();
        spacerNode.setMinWidth(120);

        HBox buttonLayout = new HBox(20);
        buttonLayout.setAlignment(Pos.CENTER);

        buttonLayout.getChildren().addAll(stepButton,spacerNode,calculateButton);
        calculateButton.setOnAction(e-> LeadTimeCalculate_OnClick());
        calculateButton.setOnKeyPressed(e-> AnyControl_OnEnter(e));
        stepButton.setOnAction(e-> StepButton_OnClick());
        stepButton.setOnKeyPressed(e-> StepButton_OnEnter(e));

        return buttonLayout;
    }

    private VBox WorkDayComboBoxLayout(){

        numberOfWorkDaysComboBox = new ComboBox();
        Label numberOfWorkDaysLabel = new Label("Number of Workdays");
        numberOfWorkDaysLabel.getStyleClass().add("label-general");

        ArrayList<String> numberOfWorkDaysArray = new ArrayList<>();

        numberOfWorkDaysArray.add("4");
        numberOfWorkDaysArray.add("5");
        numberOfWorkDaysArray.add("6");
        numberOfWorkDaysArray.add("7");

        numberOfWorkDaysComboBox.getItems().addAll(numberOfWorkDaysArray);

        VBox workDayComboBoxLayout = new VBox(10);
        workDayComboBoxLayout.setAlignment(Pos.CENTER);
        workDayComboBoxLayout.setPadding(new Insets(10,0,20,0));
        workDayComboBoxLayout.getChildren().addAll(numberOfWorkDaysLabel,numberOfWorkDaysComboBox);

        return workDayComboBoxLayout;
    }




    private void UnitConverterMenu_OnClick(){

        mainWindow.setScene(SceneBuilder.BuildUnitConverterScene(mainWindow));
    }

    private void AboutMenu_OnClick(){

        MessageBox.show("About "+ programName, programName + " Version "+programVersion +
                " \n\n A multi application suite to assist with everyday calculations / conversions.\n");
    }

    private void WorkDayPlannerMenu_OnClick(){
        startSceneBorderLayout.setCenter(WorkDayUserInterface());
        applicationTitle.setText("Work Day Planner");

    }

    private void LeadTimePlannerMenu_OnClick(){
        startSceneBorderLayout.setCenter(LeadTimeUserInterface());
        applicationTitle.setText("Lead Time Planner");
        isFromStartDate = false;
        isFromEndDate = false;
    }

    private void FromStartDateRadioButton_OnSelect(){
        isFromStartDate = true;
        isFromEndDate = false;
        fromEndDateRadioButton.setSelected(false);
        fromEndDateInputBox.clear();
        fromStartDateInputBox.setDisable(false);
        fromDatePicker.setDisable(false);
        fromEndDateInputBox.setDisable(true);
        toDatePicker.setDisable(true);

    }

    private void FromEndDateRadioButton_OnSelect(){
        isFromEndDate = true;
        isFromStartDate = false;
        fromStartDateRadioButton.setSelected(false);
        fromStartDateInputBox.setDisable(true);
        fromStartDateInputBox.clear();
        fromDatePicker.setDisable(true);
        fromEndDateInputBox.setDisable(false);
        toDatePicker.setDisable(false);


    }

    private void LeadTimeCalculate_OnClick(){

        if (isFromStartDate){
            CalculateLeadTime(fromStartDateInputBox.getText());
        }

        else if (isFromEndDate){
            CalculateLeadTime (fromEndDateInputBox.getText());
        }



    }

    private void StepButton_OnClick(){

    }

    private void StepButton_OnEnter(KeyEvent e){

        if (e.getCode() == KeyCode.ENTER){

            StepLeadTime();
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

    private void AnyControl_OnEnter(javafx.scene.input.KeyEvent e){

        if (e.getCode() == KeyCode.ENTER){

            if (IsNumber(weeksInputBox.getText())&&IsNumber(daysInputBox.getText())){
                if (isFromStartDate){
                    CalculateLeadTime(fromStartDateInputBox.getText());
                }
                else if (isFromEndDate){
                    CalculateLeadTime(fromEndDateInputBox.getText());
                }
            }

                if (weeksInputBox.isFocused() || daysInputBox.isFocused()) {
                    stepButton.requestFocus();
                }

                if (fromStartDateInputBox.isFocused()){

                ParseStartDate();
                }

                if (fromEndDateInputBox.isFocused()){

                    ParseEndDate();
                }

        }
    }

    private void FromDatePicker_OnSelect(){
        fromStartDateInputBox.setText(ConvertToUsDateFormat(fromDatePicker.getValue().toString()));
    }

    private void ToDatePicker_OnSelect(){
        fromEndDateInputBox.setText(ConvertToUsDateFormat(toDatePicker.getValue().toString()));
    }

    private String ConvertToUsDateFormat(String date){

        String rawDate = date;

        String [] parsedDate;
        String convertedDate;
        parsedDate = rawDate.split("-");

       convertedDate = parsedDate[1] +"/"+parsedDate[2]+"/"+parsedDate[0];


       return convertedDate;
    }

    private void CalculateLeadTime( String date){

        String leadTimeWeeks = weeksInputBox.getText();
        String leadTimeDays = daysInputBox.getText();

        CalPlanner leadTimeCalPlanner = new CalPlanner(date,leadTimeWeeks,leadTimeDays);

        if (IsNumber(weeksInputBox.getText())&& IsNumber(daysInputBox.getText())) {

            if (isFromStartDate) {
                results.setText(leadTimeCalPlanner.getLeadTimeFromStartDate());

            } else if (isFromEndDate) {
                results.setText(leadTimeCalPlanner.getLeadTimeFromEndDate());

            } else MessageBox.show("Error", "You Must Select From Start Date or From End Date");
        }

        else {

            MessageBox.show("Error", "Input Must Be A Number");

        }
    }

    private void StepLeadTime(){

        if (!results.getText().equals("")) {

            if (isFromStartDate) {
                fromStartDateInputBox.setText(results.getText());
                weeksInputBox.requestFocus();

            } else if (isFromEndDate) {

                fromEndDateInputBox.setText(results.getText());
                weeksInputBox.requestFocus();
            }
        }
    }

    private String ParseInput(String date, String singleInputStartOrEnd){

        CalPlanner cpParseOnly = new CalPlanner();
        String formattedDate;

            formattedDate = cpParseOnly.GetFormattedInputDate(date,singleInputStartOrEnd);


            return formattedDate;


    }

    private void ParseStartDate(){

        fromStartDateInputBox.setText(ParseInput(fromStartDateInputBox.getText(),"Start"));
    }

    private void ParseEndDate(){

        fromEndDateInputBox.setText(ParseInput(fromEndDateInputBox.getText(),"End"));
    }

}

