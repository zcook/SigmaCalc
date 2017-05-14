import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by Zane on 5/14/2017.
 */
public class UnitConverterGUI{

   private Stage mainWindow;

    Scene BuildUnitConverterGUI(Stage window) {

       mainWindow = window;

       //Main Window Components
       Scene UnitConverterScene;
       BorderPane startSceneBorderLayout;
       javafx.scene.control.MenuBar menuBar;
       javafx.scene.control.Menu applicationMenu;
       javafx.scene.control.Menu helpMenu;
       javafx.scene.control.MenuItem calPlanner;
       javafx.scene.control.MenuItem aboutMenu;

       javafx.scene.control.Label splashScreen;
       //Method Variables

       int mainWindowWidth = 300;
       int mainWindowHeight = 300;

       //Initialize Main Window Components
       splashScreen = new javafx.scene.control.Label("UnitConverterGUI");
       startSceneBorderLayout = new BorderPane();

       //Initialize Menu Items
       menuBar = new javafx.scene.control.MenuBar();
       applicationMenu = new javafx.scene.control.Menu("Application");
       helpMenu = new javafx.scene.control.Menu("Help");
       calPlanner = new javafx.scene.control.MenuItem("CalPlanner");
       aboutMenu = new javafx.scene.control.MenuItem("About");

       //Build Scene
       startSceneBorderLayout.setTop(menuBar);
       startSceneBorderLayout.setCenter(splashScreen);
       UnitConverterScene = new Scene(startSceneBorderLayout, mainWindowWidth, mainWindowHeight);


       //Build Menus
       menuBar.getMenus().addAll(applicationMenu, helpMenu);
       applicationMenu.getItems().addAll(calPlanner);
       helpMenu.getItems().addAll(aboutMenu);


       calPlanner.setOnAction(event -> mainWindow.setScene(SceneBuilder.BuildCalPlannerScene(mainWindow)));
       aboutMenu.setOnAction(event -> MessageBox.show("about selected", "Message Box"));

       return UnitConverterScene;

        }

    }

