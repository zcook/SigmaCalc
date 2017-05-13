/**
 * SigmaCalc
 *
 * Launches various applications that perform common tasks needed by Engineers.
 * Unit Conversion, Various date queries such as how many work days within a range of dates using CalPlanner
 * Common formula references / calculation.
 * Created by Zane Cook on 5/15/2015.
 */

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Main extends Application{

        public static void main (String[] args){

            launch(args);
        }

    @Override
    public void start(Stage mainWindow) throws Exception {


        Scene startScene;
        BorderPane startSceneBorderLayout;
        MenuBar menuBar;
        Menu applicationMenu;
        Menu helpMenu;
        MenuItem unitConverter;
        MenuItem calPlanner;
        MenuItem aboutMenu;

        Label splashScreen;

        String programTitle = "SigmaCalc";
        int mainWindowWidth = 300;
        int mainWindowHeight = 300;

        splashScreen = new Label("SplashScreen");

        startSceneBorderLayout = new BorderPane();
        menuBar = new MenuBar();
        startSceneBorderLayout.setTop(menuBar);
        startSceneBorderLayout.setCenter(splashScreen);
        startScene = new Scene(startSceneBorderLayout,mainWindowWidth,mainWindowHeight);
        applicationMenu = new Menu("Application");
        helpMenu=new Menu("Help");

        unitConverter = new MenuItem("Unit Converter");
        calPlanner = new MenuItem("CalPlanner");
        aboutMenu = new MenuItem("About");

        applicationMenu.getItems().addAll(calPlanner, unitConverter);
        helpMenu.getItems().addAll(aboutMenu);

        menuBar.getMenus().addAll(applicationMenu,helpMenu);


        mainWindow.setScene(startScene);
        mainWindow.setTitle(programTitle);

        mainWindow.show();



        unitConverter.setOnAction(e-> MessageBox.show("UnitConverter Selected", "Message Box"));
        calPlanner.setOnAction(event -> MessageBox.show("CalPlanner Selected","Message Box"));
        aboutMenu.setOnAction(event -> MessageBox.show("about selected","Message Box"));


    }
}
