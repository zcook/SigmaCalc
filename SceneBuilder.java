/**
 * Main Window for Program
 * Initializes all internal applications (CalPlanner, UnitConverter etc...)
 * Calls all necessary methods located in other classes to perform required actions
 * Created by Zane Cook on 5/15/2015.
 */


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.*;
import javafx.scene.*;



public  class SceneBuilder {

   public static Scene BuildMainWindowScene(){
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

       unitConverter.setOnAction(e-> MessageBox.show("Unit Converter Selected","Message Box"));
       calPlanner.setOnAction(event -> MessageBox.show("CalPlanner Selected","Message Box"));
       aboutMenu.setOnAction(event -> MessageBox.show("about selected","Message Box"));

       return startScene;
    }


} //End of MainWindow Class
