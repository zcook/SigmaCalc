import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by Zane on 5/14/2017.
 */

 class CalPlannerGUI {

   private Stage mainWindow;

    Scene BuildCalPlannerGUI(Stage window){

        mainWindow = window;

        //Main Window Components
        Scene CalPlannerScene;
        BorderPane startSceneBorderLayout;


        javafx.scene.control.Label splashScreen;


        int mainWindowWidth = 300;
        int mainWindowHeight = 300;

        //Initialize Main Window Components
        splashScreen = new javafx.scene.control.Label("CalPlannerGUI");
        startSceneBorderLayout = new BorderPane();



        //Build Scene
        startSceneBorderLayout.setTop(BuildMenuBar());
        startSceneBorderLayout.setCenter(splashScreen);
        CalPlannerScene = new Scene(startSceneBorderLayout, mainWindowWidth, mainWindowHeight);




        return CalPlannerScene;
    }

    private MenuBar BuildMenuBar(){

        javafx.scene.control.MenuBar menuBar;
        javafx.scene.control.Menu applicationMenu;
        javafx.scene.control.Menu helpMenu;
        javafx.scene.control.MenuItem unitConverter;
        javafx.scene.control.MenuItem aboutMenu;

        //Initialize Menu Items
        menuBar = new javafx.scene.control.MenuBar();
        applicationMenu = new javafx.scene.control.Menu("Application");
        helpMenu = new javafx.scene.control.Menu("Help");
        unitConverter = new javafx.scene.control.MenuItem("Unit Converter");
        aboutMenu = new javafx.scene.control.MenuItem("About");

        //Build Menus
        menuBar.getMenus().addAll(applicationMenu, helpMenu);
        applicationMenu.getItems().addAll(unitConverter);
        helpMenu.getItems().addAll(aboutMenu);


        unitConverter.setOnAction(event -> UnitConverterMenu_OnClick());
        aboutMenu.setOnAction(event -> AboutMenu_OnClick());

        return menuBar;
    }

    private void UnitConverterMenu_OnClick(){

        mainWindow.setScene(SceneBuilder.BuildUnitConverterScene(mainWindow));
    }

    private void AboutMenu_OnClick(){

        MessageBox.show("about selected", "Message Box");
    }

}
