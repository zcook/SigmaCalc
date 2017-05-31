/**
 * Created by Zane on 5/14/2017.
 */


import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



class MainWindowGUI {

    private Stage mainWindow;
    private String splashScreenPath = "file:C:\\Users\\Zane\\Dropbox\\Misc\\Electronics & Computer Science\\Java\\JavaPrograms\\SigmaCalc\\Images\\SigmaCalc Spash Screen Logo.png";
    private Image splahScreenImage = new Image(splashScreenPath);
    ImageView splashScreen = new ImageView(splahScreenImage);

    Scene BuildMainWindowGUI(Stage window){


        mainWindow = window;

        //Main Window Components
        Scene startScene;
        BorderPane startSceneBorderLayout;


        //Method Variables

        int mainWindowWidth = 400;
        int mainWindowHeight = 420;

        //Initialize Main Window Components
       // splashScreen = new javafx.scene.control.Label("SplashScreen");
        startSceneBorderLayout = new BorderPane();

        //Build Scene
        startSceneBorderLayout.setTop(BuildMenuBar());
        startSceneBorderLayout.setCenter(splashScreen);
        startScene = new Scene(startSceneBorderLayout,mainWindowWidth,mainWindowHeight);
        mainWindow.setResizable(false);

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

        MessageBox.show("About selected","Message Box");
    }
}
