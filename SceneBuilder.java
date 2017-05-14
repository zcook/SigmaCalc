/**
 *
 * Initializes all internal applications (CalPlanner, UnitConverter etc...)
 *
 * Created by Zane Cook on 5/14/2017.
 */


import javafx.stage.*;
import javafx.scene.*;



  class SceneBuilder {

      static Scene BuildMainWindowScene(Stage window){

        MainWindowGUI mainWindow = new MainWindowGUI();

        return mainWindow.BuildMainWindowGUI(window);

    }

      static Scene BuildUnitConverterScene(Stage window){
         UnitConverterGUI unitConverterGUI = new UnitConverterGUI();
         return unitConverterGUI.BuildUnitConverterGUI(window);
      }

      static Scene BuildCalPlannerScene(Stage window){

          CalPlannerGUI calPlannerGUI = new CalPlannerGUI();
          return calPlannerGUI.BuildCalPlannerGUI(window);
      }


} //End of SceneBuilder Class
