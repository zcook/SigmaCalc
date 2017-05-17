/**
 * Created by Zane on 5/14/2017.
 */


import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

 class MainWindowGUI {

    private Stage mainWindow;

    Scene BuildMainWindowGUI(Stage window){


        mainWindow = window;

        //Main Window Components
        Scene startScene;
        BorderPane startSceneBorderLayout;


        javafx.scene.control.Label splashScreen;
        //Method Variables

        int mainWindowWidth = 300;
        int mainWindowHeight = 300;

        //Initialize Main Window Components
        splashScreen = new javafx.scene.control.Label("SplashScreen");
        startSceneBorderLayout = new BorderPane();

        //Build Scene
        startSceneBorderLayout.setTop(BuildMenuBar());
        startSceneBorderLayout.setCenter(splashScreen);
        startScene = new Scene(startSceneBorderLayout,mainWindowWidth,mainWindowHeight);

        return startScene;

    }

    private MenuBar BuildMenuBar(){

        javafx.scene.control.MenuBar menuBar;
        javafx.scene.control.Menu applicationMenu;
        javafx.scene.control.Menu helpMenu;
        javafx.scene.control.MenuItem unitConverter;
        javafx.scene.control.MenuItem calPlanner;
        javafx.scene.control.MenuItem aboutMenu;

        //Initialize Menu Items
        menuBar = new javafx.scene.control.MenuBar();
        applicationMenu = new javafx.scene.control.Menu("Application");
        helpMenu=new javafx.scene.control.Menu("Help");
        unitConverter = new javafx.scene.control.MenuItem("Unit Converter");
        calPlanner = new javafx.scene.control.MenuItem("CalPlanner");
        aboutMenu = new javafx.scene.control.MenuItem("About");

        //Build Menus
        menuBar.getMenus().addAll(applicationMenu,helpMenu);
        applicationMenu.getItems().addAll(calPlanner, unitConverter);
        helpMenu.getItems().addAll(aboutMenu);

        unitConverter.setOnAction(event -> UnitConverterMenu_OnClick());
        calPlanner.setOnAction(event -> CalPlannerMenu_OnClick());
        aboutMenu.setOnAction(event -> AboutMenu_OnClick());

        return menuBar;
    }

    private void UnitConverterMenu_OnClick(){

        mainWindow.setScene(SceneBuilder.BuildUnitConverterScene(mainWindow));
    }

    private void CalPlannerMenu_OnClick(){

        mainWindow.setScene(SceneBuilder.BuildCalPlannerScene(mainWindow));
    }

    private void AboutMenu_OnClick(){

        MessageBox.show("about selected","Message Box");
    }
}
