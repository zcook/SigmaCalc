import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by Zane on 5/14/2017.
 */
public class CalPlannerGUI {

   private Stage mainWindow;

    Scene BuildCalPlannerGUI(Stage window){

        mainWindow = window;

        //Main Window Components
        Scene CalPlannerScene;
        BorderPane startSceneBorderLayout;
        javafx.scene.control.MenuBar menuBar;
        javafx.scene.control.Menu applicationMenu;
        javafx.scene.control.Menu helpMenu;
        javafx.scene.control.MenuItem unitConverter;
        javafx.scene.control.MenuItem aboutMenu;

        javafx.scene.control.Label splashScreen;


        int mainWindowWidth = 300;
        int mainWindowHeight = 300;

        //Initialize Main Window Components
        splashScreen = new javafx.scene.control.Label("CalPlannerGUI");
        startSceneBorderLayout = new BorderPane();

        //Initialize Menu Items
        menuBar = new javafx.scene.control.MenuBar();
        applicationMenu = new javafx.scene.control.Menu("Application");
        helpMenu = new javafx.scene.control.Menu("Help");
        unitConverter = new javafx.scene.control.MenuItem("Unit Converter");
        aboutMenu = new javafx.scene.control.MenuItem("About");

        //Build Scene
        startSceneBorderLayout.setTop(menuBar);
        startSceneBorderLayout.setCenter(splashScreen);
        CalPlannerScene = new Scene(startSceneBorderLayout, mainWindowWidth, mainWindowHeight);


        //Build Menus
        menuBar.getMenus().addAll(applicationMenu, helpMenu);
        applicationMenu.getItems().addAll(unitConverter);
        helpMenu.getItems().addAll(aboutMenu);


        unitConverter.setOnAction(event -> mainWindow.setScene(SceneBuilder.BuildUnitConverterScene(mainWindow)));
        aboutMenu.setOnAction(event -> MessageBox.show("about selected", "Message Box"));

        return CalPlannerScene;
    }
}
