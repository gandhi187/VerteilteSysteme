package frontend;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logikParallelismus.MatrixMulti;
import masterWorkerNew.MatrixMultiMasterWorker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends Application {

	MatrixMulti matrixMulti = new MatrixMulti();
	MatrixMultiMasterWorker matrixMultiMasterWorker = new MatrixMultiMasterWorker();
	@Override
	public void start(Stage primaryStage) {
		BorderPane border = new BorderPane();

		HBox hboxUP = new HBox();
		hboxUP.setPadding(new Insets(15, 12, 15, 12));
		hboxUP.setSpacing(10);
		hboxUP.setStyle("-fx-background-color: #7B7BCD;");
		Text ueberschrift = new Text("A2 : Matrix Multiplikation");
		ueberschrift.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		hboxUP.getChildren().add(ueberschrift);

		HBox hboxDown = new HBox();
		hboxDown.setPadding(new Insets(15, 12, 15, 12));
		hboxDown.setSpacing(10);
		hboxDown.setStyle("-fx-background-color: #7B7BCD;");
		Text autor = new Text("Author: David Sharma");
		autor.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		hboxDown.getChildren().add(autor);

		border.setTop(hboxUP);
		border.setBottom(hboxDown);

		GridPane root = new GridPane();
		root.setVgap(10); 
		GridPane matrix1 = generateMatrix(matrixMulti.getMatrix1());
		GridPane matrix = generateMatrix(matrixMulti.getMatrix2());

		Label l = new Label("X");
		l.setAlignment(Pos.CENTER);
		root.setHalignment(l, HPos.CENTER);
		Text m1 = new Text("Matrix A  ");
		Text m2 = new Text("Matrix B  ");
		border.setCenter(root);
		root.setPadding(new Insets(30));
		root.add(m1, 0, 0);
		root.add(matrix, 1, 0);
		root.add(l, 1, 1);
		root.add(m2, 0, 2);
		root.add(matrix1, 1, 2);

		Label anzahlThreadsLabel = new Label("Anzahl Threads");
		root.add(anzahlThreadsLabel, 0, 3);
		Label auswahlAlgo = new Label("Auswahl Algorithmus");
		root.add(auswahlAlgo, 0, 4);
		List<Integer> threads = new ArrayList<Integer>();

		for (int a = 1; a<=25; a++){
			threads.add(a);
		}
		
		ObservableList<Integer> items = FXCollections.observableArrayList(
				
				threads);
		final ComboBox comboBox = new ComboBox(items);
		comboBox.setItems(items);
		root.add(comboBox, 1, 3);

		ObservableList<String> algos = FXCollections.observableArrayList("Resultats-Parallelismus-Parad.","Master-Worker");
		final ComboBox comboBoxAlgo = new ComboBox(algos);
		comboBoxAlgo.setItems(algos);
		root.add(comboBoxAlgo, 1, 4);

		Label ergebnis = new Label("Ergebnis");
		root.add(ergebnis, 0, 7);
		ergebnis.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		
		Label sleepLabel = new Label("Sleep-Zeit");
		root.add(sleepLabel, 0, 6);
		
		TextField sleepTime = new TextField("Angabe in MS");
		root.add(sleepTime, 1, 6);
		
		
		Button b = new Button("Berechnen");
		b.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
				if (comboBox.getValue()==null){
					return;
				}
				
				GridPane ergMatrix = new GridPane();
				if (comboBoxAlgo.getValue()==null){
					return;
				}
				if (comboBoxAlgo.getValue().equals("Resultats-Parallelismus-Parad.")){
				ergMatrix = generateMatrix(matrixMulti.calculate((int) comboBox.getValue(),Integer.parseInt(sleepTime.getText())));
				root.add(ergMatrix, 1, 7);
				}
				
				if (comboBoxAlgo.getValue().equals("Master-Worker")){
					ergMatrix = generateMatrix(matrixMultiMasterWorker.calculate((int) comboBox.getValue(),Integer.parseInt(sleepTime.getText())));
					root.add(ergMatrix, 1, 7);
					}
				
			}
		});
		root.add(b, 1, 5);
		Scene scene = new Scene(border, 500, 800);
		primaryStage.setTitle("Aufgabe 2 - Matrix Multiplikation");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public GridPane generateMatrix(int[][] matrixModel) {
		GridPane matrix = new GridPane();

		int SIZE = matrixModel.length;

		int length = SIZE;
		int width = SIZE;

		for (int y = 0; y < length; y++) {
			for (int x = 0; x < width; x++) {

				Random rand = new Random();
				int rand1 = rand.nextInt(2);

				// Create a new TextField in each Iteration
				TextField tf = new TextField();
				tf.setPrefHeight(20);
				tf.setPrefWidth(40);
				tf.setAlignment(Pos.CENTER);
				tf.setEditable(false);
				tf.setText(Integer.toString(matrixModel[y][x]));

				// Iterate the Index using the loops
				matrix.setRowIndex(tf, y);
				matrix.setColumnIndex(tf, x);
				matrix.getChildren().add(tf);
			}
		}
		return matrix;

	}

}