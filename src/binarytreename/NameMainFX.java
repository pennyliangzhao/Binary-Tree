package binarytreename;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.stage.Stage;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class NameMainFX extends Application {
	private final Text text = new Text();
	private final TextField textField = new TextField();
	Person firtNameTreeRoot;
	Person lastNameTreeRoot;
	private final Pane pane = new Pane();
	private final Label label = new Label();
	private final TextArea textArea = new TextArea();
	Stage primaryStage = null;
	Scene scene;

	@Override

	public void start(Stage primaryStage) throws Exception {

		HBox hBox = new HBox(10);
		// Button section
		VBox vBox = new VBox(10);
		vBox.setPadding(new Insets(20));
		vBox.setPrefWidth(190);
		vBox.setAlignment(Pos.TOP_CENTER);
		textArea.setPrefHeight(300);

		String bip = "src/binaryTreeName/song.mp3";
		Media m = new Media(Paths.get(bip).toUri().toString());
		AudioClip mediaPlayer = new AudioClip(m.getSource());

		mediaPlayer.play();
		mediaPlayer.setCycleCount(2);

		Button firstNameTree = new Button("Firstname Tree");
		ImageView firstNameTreeImage = new ImageView(getClass().getResource("nametree.png").toExternalForm());
		firstNameTreeImage.setFitHeight(20);
		firstNameTree.setGraphic(firstNameTreeImage);
		firstNameTreeImage.setPreserveRatio(true);
		firstNameTree.setContentDisplay(ContentDisplay.RIGHT);
		firstNameTree.setPrefSize(140, 20);
		generateTrees();
		firstNameTree.setCursor(Cursor.HAND);
		firstNameTree.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			pane.getChildren().clear();// Clean the pane before drawing the tree
			drawTree(firtNameTreeRoot, 500, 50, (int) (pane.getPrefWidth() / 4), 50);
		});

		/*
		 * in-order depth-first (sorted) breadth-first (by levels in tree) pre-order
		 * /post-order depth-first
		 */
		MenuButton printFMenu = new MenuButton("Traversing By");
		ImageView printFMenuImage = new ImageView(getClass().getResource("sorting.png").toExternalForm());
		printFMenuImage.setFitHeight(20);
		printFMenu.setGraphic(printFMenuImage);
		printFMenuImage.setPreserveRatio(true);
		printFMenu.setContentDisplay(ContentDisplay.LEFT);

		printFMenu.setMnemonicParsing(true);

		MenuItem breadthFirstTraversalF = new MenuItem("Breadth First");
		breadthFirstTraversalF.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent argo) {
				textArea.clear();
				breadthFirstTraversal(firtNameTreeRoot);
			}
		});
		MenuItem preOrderF = new MenuItem("Pre-order");
		preOrderF.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent argo) {
				textArea.clear();
				preOrderPrintAll(firtNameTreeRoot);
			}
		});
		MenuItem inOrderF = new MenuItem("In Order");
		inOrderF.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				textArea.clear();
				inOrderPrintAll(firtNameTreeRoot);
			}

		});
		MenuItem postOrderF = new MenuItem("Post Order");
		postOrderF.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent argo) {
				textArea.clear();
				postOrderPrintAll(firtNameTreeRoot);
			}
		});
		printFMenu.getItems().addAll(breadthFirstTraversalF, preOrderF, inOrderF, postOrderF);
		printFMenu.setPrefSize(140, 20);
		printFMenu.setCursor(Cursor.HAND);

		Button lastNameTree = new Button("Lastname Tree");
		ImageView lastNameTreeImage = new ImageView(getClass().getResource("nametree.png").toExternalForm());
		lastNameTreeImage.setFitHeight(20);
		lastNameTree.setGraphic(lastNameTreeImage);
		lastNameTreeImage.setPreserveRatio(true);
		lastNameTree.setContentDisplay(ContentDisplay.RIGHT);
		lastNameTree.setPrefSize(140, 20);
		lastNameTree.setCursor(Cursor.HAND);
		lastNameTree.setOnMouseClicked(event -> {
			pane.getChildren().clear();// Clean the pane before drawing the tree
			drawTree(lastNameTreeRoot, 400, 40, 250, 50);
		});

		MenuButton printLMenu = new MenuButton("Traversing By");
		ImageView printLMenuImage = new ImageView(getClass().getResource("sorting.png").toExternalForm());
		printLMenuImage.setFitHeight(20);
		printLMenu.setGraphic(printLMenuImage);
		printLMenuImage.setPreserveRatio(true);
		printLMenu.setContentDisplay(ContentDisplay.LEFT);

		MenuItem breadthFirstTraversalL = new MenuItem("Breadth First");
		breadthFirstTraversalL.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent argo) {
				textArea.clear();
				breadthFirstTraversal(lastNameTreeRoot);
			}
		});
		MenuItem preOrderL = new MenuItem("Pre-order");
		preOrderL.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent argo) {
				textArea.clear();
				preOrderPrintAll(lastNameTreeRoot);
			}
		});

		MenuItem inOrderL = new MenuItem("In Order");
		inOrderL.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				textArea.clear();
				inOrderPrintAll(lastNameTreeRoot);
			}

		});
		MenuItem postOrderL = new MenuItem("Post Order");
		postOrderL.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent argo) {
				textArea.clear();
				postOrderPrintAll(lastNameTreeRoot);
			}
		});
		printLMenu.getItems().addAll(breadthFirstTraversalL, preOrderL, inOrderL, postOrderL);
		printLMenu.setPrefSize(140, 20);
		printLMenu.setCursor(Cursor.HAND);

		text.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));
		text.setText("Find People?");
		textField.setPromptText("Name length");
		textField.setPrefColumnCount(20);
		textField.setCursor(Cursor.HAND);

		textField.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent keyEvent) {
				text.setText("Find People?");
				if (keyEvent.getCode().equals(KeyCode.ENTER)) {
					try {
						int nameLength = Integer.parseInt(textField.getText());
						ArrayList<Person> people = findByNameLength(firtNameTreeRoot, nameLength);
						displayPeopleList(people);
					} catch (NumberFormatException e) {
						text.setText("Please enter a nubmer");

					}
				}
			}
		});

		Button quit = new Button("Exit");
		quit.setPrefSize(140, 20);
		quit.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> Platform.exit());
		quit.setCursor(Cursor.HAND);

		Button remove = new Button("      Remove     ");
		ImageView removeTreeImage = new ImageView(getClass().getResource("nametree.png").toExternalForm());
		removeTreeImage.setFitHeight(20);
		remove.setGraphic(removeTreeImage);
		removeTreeImage.setPreserveRatio(true);
		remove.setContentDisplay(ContentDisplay.RIGHT);
		remove.setPrefSize(140, 20);
		remove.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> pane.getChildren().clear());
		remove.setCursor(Cursor.HAND);

		Button deezer = new Button();
		ImageView deezerImage = new ImageView(getClass().getResource("startmusic.gif").toExternalForm());
		deezer.setStyle("-fx-background-color: transparent");
		deezerImage.setFitHeight(20);
		deezer.setGraphic(deezerImage);
		deezerImage.setPreserveRatio(true);
		deezer.setContentDisplay(ContentDisplay.RIGHT);
		deezer.setPrefSize(140, 20);

		Button play = new Button("Play music");
		play.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
		ImageView playImage = new ImageView(getClass().getResource("playmusic.png").toExternalForm());
		play.setStyle("-fx-background-color: transparent");
		playImage.setFitHeight(20);
		play.setLayoutX(880);
		play.setLayoutY(540);
		play.setGraphic(playImage);
		playImage.setPreserveRatio(true);
		play.setContentDisplay(ContentDisplay.RIGHT);
		play.setPrefSize(140, 20);
		play.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> mediaPlayer.play());
		play.setCursor(Cursor.HAND);

		Button stop = new Button("Stop Music");
		stop.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
		ImageView stopImage = new ImageView(getClass().getResource("stopmusic.png").toExternalForm());
		stop.setStyle("-fx-background-color: transparent");
		stopImage.setFitHeight(20);
		stop.setLayoutX(880);
		stop.setLayoutY(560);
		stop.setGraphic(stopImage);
		stopImage.setPreserveRatio(true);
		stop.setContentDisplay(ContentDisplay.RIGHT);
		stop.setPrefSize(140, 20);
		stop.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> mediaPlayer.stop());
		stop.setCursor(Cursor.HAND);

		vBox.getChildren().addAll(firstNameTree, printFMenu, lastNameTree, printLMenu, remove, text, textField,
				textArea, quit, deezer);

		VBox displayBox = new VBox(10);
		Border bor = new Border(new BorderStroke(Paint.valueOf("#458B00"), BorderStrokeStyle.DASHED,
				new CornerRadii(10), new BorderWidths(5)));
		displayBox.setBorder(bor);
		pane.setPrefWidth(1000);
		pane.setPrefHeight(600);

		Image image2 = new Image(getClass().getResourceAsStream("swing.gif"));
		label.setGraphic(new ImageView(image2));
		label.setLayoutX(180);
		label.setLayoutY(260);
		label.setTextFill(Color.BLACK);
		label.setAlignment(Pos.CENTER);
		label.setContentDisplay(ContentDisplay.BOTTOM);
		label.setFont(Font.font("Edwardian Script ITC", FontWeight.BOLD, FontPosture.REGULAR, 50));

		displayBox.getChildren().addAll(pane, play, stop);

		displayBox.setStyle("-fx-background-color:cornsilk;-fx-background-radius: 20");
		Background displayBackground = new Background(createImage("mapletree.png"));
		pane.setBackground(displayBackground);
		hBox.getChildren().addAll(vBox, displayBox);
		Image image = new Image(getClass().getResourceAsStream("bird.png"));
		displayBox.setCursor(new ImageCursor(image));

		VBox.setVgrow(displayBox, Priority.ALWAYS);
		HBox.setHgrow(displayBox, Priority.ALWAYS);
		Scene scene = new Scene(hBox, 1200, 600);
		Background hBoxBackground = new Background(createImage("leaves.gif"));
		hBox.setBackground(hBoxBackground);

		primaryStage.setTitle("Binary Search Tree");
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image("BinaryTreeName.png"));
		primaryStage.show();

	}

	private static BackgroundImage createImage(String url) {
		try {
			return new BackgroundImage(new Image(new FileInputStream(url)), BackgroundRepeat.REPEAT,
					BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
					new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public HBox addHBox() {

		HBox hbox = new HBox();

		hbox.setPadding(new Insets(15, 12, 15, 12));

		hbox.setSpacing(10);

		hbox.setStyle("-fx-background-color: #336699;");

		hbox.getChildren().addAll();

		return hbox;

	}

	public void generateTrees() {
		try {
			Scanner scan = new Scanner(new File("mswdev.csv"));
			while (scan.hasNext()) {
				String firstName = scan.next().trim();
				String lastName = scan.nextLine().trim();

				if (firtNameTreeRoot == null) {
					firtNameTreeRoot = new Person(firstName, lastName);
				} else {
					addPersonFirstName(firtNameTreeRoot, new Person(firstName, lastName));
				}
				if (lastNameTreeRoot == null) {
					lastNameTreeRoot = new Person(firstName, lastName);
				} else {
					addPersonLastName(lastNameTreeRoot, new Person(firstName, lastName));
				}

			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void drawTree(Person person, int x, int y, int xGap, int yGap) {
		if (person.before != null) {
			Line line = new Line(x, y, x - xGap, y + yGap);
			pane.getChildren().add(line);
			drawTree(person.before, x - xGap, y + yGap, xGap / 2, yGap);
		}

		if (person.after != null) {
			Line line = new Line(x, y, x + xGap, y + yGap);
			pane.getChildren().add(line);
			drawTree(person.after, x + xGap, y + yGap, xGap / 2, yGap);
		}
		// Show full name
		person.setOnMouseClicked(event -> {
			label.setText(person.getFullName());
			if (pane.getChildren().contains(label)) {
				pane.getChildren().remove(label);
			}
			pane.getChildren().addAll(label);
		});

		Image image1 = new Image(getClass().getResourceAsStream("mapleleaf.png"));
		person.setGraphic(new ImageView(image1));
		person.setTextFill(Color.BLACK);
		person.setContentDisplay(ContentDisplay.CENTER);
		person.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 13));
		person.setLayoutX(x - 25);
		person.setLayoutY(y-10);
		pane.getChildren().addAll(person);
	}

	public void addPersonFirstName(Person currentP, Person newP) {
		if (currentP.firstName.compareTo(newP.firstName) > 0) {
			if (currentP.before == null) {
				currentP.before = newP;
			} else {
				addPersonFirstName(currentP.before, newP);
			}
		} else {
			if (currentP.after == null) {
				currentP.after = newP;
			} else {
				addPersonFirstName(currentP.after, newP);
			}
		}
	}

	public void addPersonLastName(Person currentP, Person newP) {
		if (currentP.lastName.compareTo(newP.lastName) > 0) {
			if (currentP.before == null) {
				currentP.before = newP;
			} else {
				addPersonLastName(currentP.before, newP);
			}
		} else {
			if (currentP.after == null) {
				currentP.after = newP;
			} else {
				addPersonLastName(currentP.after, newP);
			}
		}
	}

	public void preOrderPrintAll(Person root) {
		if (root != null)
			preOrderPrintNames(root);

		else
			System.out.println("Root needs to be added first");

	}

	public void preOrderPrintNames(Person current) {
		if (current != null) {

			textArea.appendText(current.getFullName() + "\n");
			preOrderPrintNames(current.before);
			preOrderPrintNames(current.after);

		}
	}

	public void inOrderPrintAll(Person root) {
		if (root != null)
			inOrderPrintNames(root);
		else
			System.out.println("Top needs to be added first");
	}

	public void inOrderPrintNames(Person current) {
		if (current != null) {

			inOrderPrintNames(current.before);
			textArea.appendText(current.getFullName() + "\n");
			inOrderPrintNames(current.after);

		}
	}

	public void postOrderPrintAll(Person root) {
		if (root != null)
			postOrderPrintNames(root);
		else
			System.out.println("Top needs to be added first");
	}

	public void postOrderPrintNames(Person current) {
		if (current != null) {

			postOrderPrintNames(current.after);
			postOrderPrintNames(current.before);
			textArea.appendText(current.getFullName() + "\n");

		}
	}

	// Breadth first traversal
	public void breadthFirstTraversal(Person root) {
		ArrayDeque<Person> nodesQueue = new ArrayDeque<>();
		nodesQueue.offer(root);

		while (!nodesQueue.isEmpty()) {
			Person p = nodesQueue.poll();
			textArea.appendText(p.getFullName() + "\n");

			if (p.getBefore() != null) {
				nodesQueue.offer(p.getBefore());
			}

			if (p.getAfter() != null) {
				nodesQueue.offer(p.getAfter());
			}
		}
	}

	// Finding the person object
	public Person find(Person person, String name) {
		if (person != null) {
			if (person.getFirstName().equalsIgnoreCase(name)) {
				return person;
			}
			Person ans = find(person.getBefore(), name);
			if (ans != null) {
				return ans;
			}
			return find(person.getAfter(), name);
		} else {
			return null;
		}
	}

	public ArrayList<Person> findByNameLength(Person root, int nameLength) {
		Stack<Person> todo = new Stack<Person>();
		ArrayList<Person> people = new ArrayList<>();
		todo.push(root);
		while (!todo.isEmpty()) {
			Person p = todo.pop();
			if (p.getFirstName().length() >= nameLength) {
				people.add(p);
			}
			if (p.getBefore() != null) {
				todo.push(p.getBefore());
			}
			if (p.getAfter() != null) {
				todo.push(p.getAfter());
			}
		}
		return people;
	}

	public void displayPeopleList(ArrayList<Person> people) {
		textArea.clear();
		for (Person p : people) {
			textArea.appendText(p.getFullName() + "\n");
			System.out.println(p.getFullName());
		}
	}

	public static void main(String[] args) {

		launch(args);

	}
}