package application;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			HBox top = new HBox();
			HBox totalStuff = new HBox();
			HBox bottom = new HBox();
			Scene scene = new Scene(root,500,500);
			Button multiply = new Button("Multiply");
			Button divide = new Button("Divide");
			Button add = new Button("Add");
			Button subtract = new Button("Subtract");
			Label value1L = new Label("Value 1: ");
			Label value2L = new Label("Value 2: ");
			Label total = new Label(" ");
			Label totalL = new Label("Total: ");
			TextField value1 = new TextField("0.0");
			TextField value2 = new TextField("0.0");
			value1.setStyle("-fx-background-color: transparent;");
			value2.setStyle("-fx-background-color: transparent;");
			multiply.setStyle("-fx-background-color: transparent;");
			divide.setStyle("-fx-background-color: transparent;");
			add.setStyle("-fx-background-color: transparent;");
			subtract.setStyle("-fx-background-color: transparent;");
			totalStuff.setAlignment(Pos.CENTER);
			totalStuff.setSpacing(8);
			totalStuff.getChildren().addAll(totalL, total);
			top.getChildren().addAll(value1L, value1 , value2L, value2);
			top.setAlignment(Pos.CENTER);
			top.setSpacing(8);
			root.add(top, 0, 0);
			root.add(totalStuff, 0, 1);
			bottom.getChildren().addAll(add, subtract, multiply, divide);
			bottom.setAlignment(Pos.CENTER);
			bottom.setSpacing(50);
			root.add(bottom, 0, 2);
			root.setAlignment(Pos.CENTER);
			root.setVgap(20);
			multiply.setOnAction(e -> 
			{
				total.setText(String.valueOf(Math.round(Double.parseDouble(value1.getText()) * Double.parseDouble(value2.getText())*100000.0)/100000.0));
			});
			divide.setOnAction(e ->
			{
				try
				{
					total.setText(String.valueOf(Math.round(Double.parseDouble(value1.getText()) / Double.parseDouble(value2.getText())*100000.0)/100000.0));
				} catch (ArithmeticException f) {
					total.setText(String.valueOf((char) 8734));
					JOptionPane.showMessageDialog(null, "You just divided by zero");
				}
			});
			add.setOnAction(e ->
			{
				total.setText(String.valueOf(Math.round((Double.parseDouble(value1.getText()) + Double.parseDouble(value2.getText()))*100000.0)/100000.0));
			});
			subtract.setOnAction(e ->
			{
				total.setText(String.valueOf(Math.round((Double.parseDouble(value1.getText()) - Double.parseDouble(value2.getText()))*100000.0)/100000.0));
			});







			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
