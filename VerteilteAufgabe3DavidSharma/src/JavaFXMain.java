
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class JavaFXMain extends Application {

	@Override
	public void start(Stage primaryStage) {
		BorderPane border = new BorderPane();

		HBox hboxUP = new HBox();
		hboxUP.setPadding(new Insets(15, 12, 15, 12));
		hboxUP.setSpacing(10);
		hboxUP.setStyle("-fx-background-color: #7FD590");
		Text ueberschrift = new Text("A3 : Binomialkoeffizient");
		ueberschrift.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		hboxUP.getChildren().add(ueberschrift);

		HBox hboxDown = new HBox();
		hboxDown.setPadding(new Insets(15, 12, 15, 12));
		hboxDown.setSpacing(10);
		hboxDown.setStyle("-fx-background-color: #7FD590");
		Text autor = new Text("Author: David Sharma");
		autor.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		hboxDown.getChildren().add(autor);

		border.setTop(hboxUP);
		border.setBottom(hboxDown);

		GridPane root = new GridPane();
		root.setVgap(10); 


		Label l = new Label("Eingabe N");
		l.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		l.setAlignment(Pos.CENTER);
		
		Label k = new Label ("Eingabe K");
		k.setFont(Font.font("Arial", FontWeight.BOLD, 15));

		k.setAlignment(Pos.CENTER);
	
		
		
		border.setCenter(root);
		root.setPadding(new Insets(30));
		root.add(l, 0, 0);
		root.add(k, 0,1);
	
		TextField nEingabe = new TextField ();
		TextField kEingabe = new TextField ();
		
		root.add(nEingabe, 2, 0);
		root.add(kEingabe, 2,1);
		
		Label ergebnis = new Label("Ergebnis");
		root.add(ergebnis, 0, 6);
		ergebnis.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		
		Label ergebnis1 =new Label("");
		root.add(ergebnis1, 1, 6);
		
		Label aktiveThreads = new Label ("Aktive Threads");
		root.add(aktiveThreads, 0, 7);
		
		Label aktiveThreadsAusgabe = new Label ("");
		root.add(aktiveThreadsAusgabe, 2, 7);
		
        Image image = new Image("Ausgangsformel.png");
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
		
        root.add(iv1, 0, 9);
		
		Button b = new Button("Berechnen");
		b.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				int n = Integer.parseInt(nEingabe.getText());
				int k = Integer.parseInt(kEingabe.getText());
				
				if (n<=0||k<=0){
					return;
				}
				
				
				Main m = new Main();
				ergebnis1.setText(String.valueOf(m.calculate(n, k, Main.counter)));;
				aktiveThreadsAusgabe.setText(String.valueOf(Main.counter));
				Main.counter=0;
			}
		});
		root.add(b, 0, 5);
		Scene scene = new Scene(border, 500, 500);
		primaryStage.setTitle("Aufgabe 3 - Binomialkoeffizient");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
	
}

