import javafx.geometry.Insets;
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

 class CalPlannerGUI {

   private Stage mainWindow;

   private Label applicationTitle;
    private String programName = "SigmaCalc";
    private String programVersion = "2.0";
    private DatePicker fromDate;
    private DatePicker toDate;
    private TextField weeksInput;
    private TextField daysInput;
    private RadioButton fromStartDateRadioButton;
    private RadioButton fromEndDateRadioButton;
    private Label results = new Label("Results");


    Scene BuildCalPlannerGUI(Stage window){

        mainWindow = window;

        //Main Window Components
        Scene CalPlannerScene;
        BorderPane startSceneBorderLayout;

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

        return menuBar;
    }
    private VBox LeadTimeUserInterface(){

        applicationTitle = new Label("Lead Time Planner");
        applicationTitle.setAlignment(Pos.CENTER);
        applicationTitle.getStyleClass().add("label-applicationTitle");

        Separator answerSeparator = new Separator();

        VBox userInterface = new VBox(20);
        userInterface.setAlignment(Pos.CENTER);

        userInterface.getChildren().add(applicationTitle);
        userInterface.getChildren().add(DatePickerLayout());
        userInterface.getChildren().add(WeeksAndDaysLayout());
        userInterface.getChildren().add(FromStartDate_FromEndDate_Selection_Layout());
        userInterface.getChildren().add(answerSeparator);
        userInterface.getChildren().add(results);
        userInterface.getChildren().add(ButtonLayout());

        return userInterface;
    }
    private HBox DatePickerLayout(){

        HBox datePickerLayout = new HBox(10);
        datePickerLayout.setPadding(new Insets(0,10,0,10));
        datePickerLayout.getChildren().addAll(FromDateLayout(),ToDateLayout());

        return datePickerLayout;
    }

    private VBox FromDateLayout(){

        VBox fromDateLayout = new VBox(10);
        fromDateLayout.setAlignment(Pos.CENTER);

        Label fromDateLabel = new Label("Start Date");
        fromDate = new DatePicker();

        fromDateLayout.getChildren().addAll(fromDateLabel,fromDate);
        return fromDateLayout;
    }

    private VBox ToDateLayout(){

        VBox toDateLayout = new VBox(10);
        toDateLayout.setAlignment(Pos.CENTER);

        Label toDateLabel = new Label("End Date");
        toDate = new DatePicker();

        toDateLayout.getChildren().addAll(toDateLabel,toDate);
        return toDateLayout;
    }

    private HBox WeeksAndDaysLayout(){

        HBox weeksAndDaysLayout = new HBox(10);
        weeksAndDaysLayout.setAlignment(Pos.CENTER);
        weeksAndDaysLayout.getChildren().addAll(WeeksLayout(),DaysLayout());

        return weeksAndDaysLayout;
    }

    private VBox WeeksLayout(){

        Label weeksLabel = new Label("Weeks");
        weeksInput = new TextField();

        VBox weeksLayout = new VBox(10);
        weeksLayout.setAlignment(Pos.CENTER);

        weeksLayout.getChildren().addAll(weeksLabel,weeksInput);

        return weeksLayout;
    }

    private VBox DaysLayout(){

        Label daysLabel = new Label("Days");
        daysInput = new TextField();

        VBox daysLayout = new VBox(10);
        daysLayout.setAlignment(Pos.CENTER);

        daysLayout.getChildren().addAll(daysLabel,daysInput);

        return daysLayout;
    }

    private HBox FromStartDate_FromEndDate_Selection_Layout(){

        HBox selectionLayout = new HBox();
        selectionLayout.setAlignment(Pos.CENTER);

        VBox radioButtonLayout = new VBox(20);
        radioButtonLayout.setAlignment(Pos.CENTER_LEFT);

        fromStartDateRadioButton = new RadioButton("From Start Date");
        fromStartDateRadioButton.setAlignment(Pos.BOTTOM_LEFT);
        fromEndDateRadioButton = new RadioButton("From End Date");
        fromEndDateRadioButton.setAlignment(Pos.BOTTOM_LEFT);

        radioButtonLayout.getChildren().addAll(fromStartDateRadioButton, fromEndDateRadioButton);
        selectionLayout.getChildren().add(radioButtonLayout);

        return selectionLayout;
    }

    private HBox ButtonLayout(){

        Button stepButton = new Button("Step");
        stepButton.setMinHeight(40);
        stepButton.setMinWidth(80);
        Button calculateButton = new Button("Calculate");
        calculateButton.setMinHeight(40);
        calculateButton.setMinWidth(80);

        HBox buttonLayout = new HBox(20);
        buttonLayout.setAlignment(Pos.CENTER);

        buttonLayout.getChildren().addAll(stepButton,calculateButton);

        return buttonLayout;
    }






    private void UnitConverterMenu_OnClick(){

        mainWindow.setScene(SceneBuilder.BuildUnitConverterScene(mainWindow));
    }

    private void AboutMenu_OnClick(){

        MessageBox.show("About "+ programName, programName + " Version "+programVersion +
                " \n\n A multi application suite to assist with everyday calculations / conversions.\n");
    }

}
