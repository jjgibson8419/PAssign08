/**
 * author: joseph gibson
 * date created: november 12, 2024
 * date last modified: november 14, 2024
 * class: csci 1302
 * description: create a security keypadpane class that implements the provided keypadpane class and adds required functionality as needed
 */
package keypad;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import keypad.*;

public class PAssign08 extends Application {

    // data members
    static class SecurityKeyPad extends KeyPadPane {
        private final String correctInput;
        private TextField displayField;
        private Label statusLabel;
        private StringBuilder userInput;

        // constructor for the security key pad
        public SecurityKeyPad(TextField displayField, Label status, String correctCode) {
            this.displayField = displayField;
            this.statusLabel = status;
            this.correctInput = correctCode; // default for project is '0000'
            this.userInput = new StringBuilder();

            registerEventHandlers();

            this.displayField.setEditable(false);
            this.statusLabel.setText("Enter the 4-digit passcode to enter:");
        }

        // method from provided keypadpane class with overriden actions to access the provided buttons and add functionality
        @Override
        public void registerEventHandlers() {
            for (Button button : listButtons) {
                button.setOnAction(e -> handleButtonClick(button));
            }

            btnAsterisk.setOnAction(e -> resetInput());
            btnPound.setOnAction(e -> checkCode());
        }

        private void handleButtonClick(Button button) {
            if (userInput.length() < 4) {
                userInput.append(button.getText());
                displayField.setText(userInput.toString());
            }
        }

        // reset that is used for the asterisk button and incorrect input code
        private void resetInput() {
            userInput.setLength(0);
            displayField.clear();
            statusLabel.setText("Enter the 4-digit passcode to enter: ");
        }

        // checks code to ensure that input is correct or to reset for the correct code, eventually
        private void checkCode() {
            if (userInput.toString().equals(correctInput)) {
                statusLabel.setText("Passcode is correct, welcome!");
            } else {
                resetInput();
            }
        }
    }

    // stage to create gui as needed
    @Override
    public void start(Stage primaryStage) {
        TextField displayField = new TextField();
        displayField.setEditable(false);
        displayField.setPromptText("Enter Passcode");

        Label statusLabel = new Label("Enter the 4-digit code to unlock");

        SecurityKeyPad securityKeyPad = new SecurityKeyPad(displayField, statusLabel, "0000"); // constructs the security keypadpane instance

        VBox vbox = new VBox(10, displayField, statusLabel, securityKeyPad);

        BorderPane bpane = new BorderPane(vbox);
        Scene scene = new Scene(bpane, 300, 400);

        primaryStage.setTitle("Security KeyPad");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

