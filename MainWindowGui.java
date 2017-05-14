/**
 * Created by Zane on 5/14/2017.
 */
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainWindowGUI {

    private Stage mainWindow;

    public Scene BuildMainWindowGUI(Stage window){


        mainWindow = window;

        //Main Window Components
        Scene startScene;
        BorderPane startSceneBorderLayout;
        javafx.scene.control.MenuBar menuBar;
        javafx.scene.control.Menu applicationMenu;
        javafx.scene.control.Menu helpMenu;
        javafx.scene.control.MenuItem unitConverter;
        javafx.scene.control.MenuItem calPlanner;
        javafx.scene.control.MenuItem aboutMenu;

        javafx.scene.control.Label splashScreen;
        //Method Variables

        int mainWindowWidth = 300;
        int mainWindowHeight = 300;

        //Initialize Main Window Components
        splashScreen = new javafx.scene.control.Label("SplashScreen");
        startSceneBorderLayout = new BorderPane();

        //Initialize Menu Items
        menuBar = new javafx.scene.control.MenuBar();
        applicationMenu = new javafx.scene.control.Menu("Application");
        helpMenu=new javafx.scene.control.Menu("Help");
        unitConverter = new javafx.scene.control.MenuItem("Unit Converter");
        calPlanner = new javafx.scene.control.MenuItem("CalPlanner");
        aboutMenu = new javafx.scene.control.MenuItem("About");

        //Build Scene
        startSceneBorderLayout.setTop(menuBar);
        startSceneBorderLayout.setCenter(splashScreen);
        startScene = new Scene(startSceneBorderLayout,mainWindowWidth,mainWindowHeight);


        //Build Menus
        menuBar.getMenus().addAll(applicationMenu,helpMenu);
        applicationMenu.getItems().addAll(calPlanner, unitConverter);
        helpMenu.getItems().addAll(aboutMenu);

        unitConverter.setOnAction(event -> window.setScene(SceneBuilder.BuildUnitConverterScene(mainWindow)));
        calPlanner.setOnAction(event -> window.setScene(SceneBuilder.BuildCalPlannerScene(mainWindow)));
        aboutMenu.setOnAction(event -> MessageBox.show("about selected","Message Box"));

        return startScene;

    }
}
