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
import javafx.stage.Stage;


public class Main extends Application{

        public static void main (String[] args){

            launch(args);
        }

    @Override
    public void start(Stage mainWindow) throws Exception {

        String programTitle = "SigmaCalc";


        mainWindow.setScene(SceneBuilder.BuildMainWindowScene(mainWindow));
        mainWindow.setTitle(programTitle);

        mainWindow.show();

    }



    }



