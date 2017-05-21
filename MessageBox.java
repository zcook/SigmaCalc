/**
 * Created by Zane on 5/13/2017.
 */

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.scene.*;

 class MessageBox {

   private static Stage messageBox;


     static void show(String title, String message){

         Scene messageBoxScene;
         BorderPane messageBoxLayout;
         Label msg;
         Button okButton;

        okButton = new Button("OK");
        msg = new Label(message);

        messageBoxLayout = new BorderPane();
        messageBoxLayout.setCenter(msg);
        messageBoxLayout.setBottom(okButton);
        okButton.setMinSize(60,15);
        messageBoxLayout.setAlignment(okButton,Pos.TOP_CENTER);

        messageBoxScene = new Scene(messageBoxLayout);

        messageBox = new Stage();


        messageBox.setTitle(title);
        messageBox.setScene(messageBoxScene);
        messageBox.setMinHeight(200);
        messageBox.setMinWidth(400);
        messageBox.initModality(Modality.APPLICATION_MODAL);


        okButton.setOnAction(e-> okButtonClick());

        messageBox.show();
    }

    private static void okButtonClick(){

        messageBox.close();
    }


}
