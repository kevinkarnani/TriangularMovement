package com.henrydangprg.triangularmovement.application;

import com.henrydangprg.triangularmovement.component.Encoder;
import com.henrydangprg.triangularmovement.component.Ghost;
import com.henrydangprg.triangularmovement.component.Motor;
import com.henrydangprg.triangularmovement.component.Triangle;
import com.henrydangprg.triangularmovement.component.Wire;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

// Java 8 code
public class Main extends Application {

	public final double WIDTH = 800;
	public final double HEIGHT = 600;
	private Triangle triangle;
	Line leftWire, topWire, rightWire;
	private Wire wire;
	private Ghost ghost;
	private Motor leftMotor, rightMotor, topMotor;
	private Encoder encoder;
	boolean goNorth, goSouth, goEast, goWest;

	/**
	 * Launches the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		encoder = new Encoder();
		rightMotor = new Motor(encoder);
		leftMotor = new Motor(encoder);
		topMotor = new Motor(encoder);
		triangle = new Triangle();
		ghost = new Ghost(leftMotor, topMotor, rightMotor);
		wire = new Wire(ghost, leftWire, topWire, rightWire);
		
		Group layout = new Group(ghost.setPosition(), triangle.getTriangle(),
				wire.getLeftWire(), wire.getRightWire(),
				wire.getTopWire());

		triangle.sendToBack();

		Scene scene = new Scene(layout, WIDTH, HEIGHT, Color.BLACK);

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case UP:
					goNorth = true;
					break;
				case DOWN:
					goSouth = true;
					break;
				case LEFT:
					goWest = true;
					break;
				case RIGHT:
					goEast = true;
					break;
				}
			}
		});

		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case UP:
					goNorth = false;
					break;
				case DOWN:
					goSouth = false;
					break;
				case LEFT:
					goWest = false;
					break;
				case RIGHT:
					goEast = false;
					break;
				}
			}
		});

		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				
			}
		};

		timer.start();
		stage.setTitle("Simulation");
		stage.setScene(scene);
		stage.show();

	}

	private void movementHandler(KeyEvent event) {
		switch (event.getCode()) {

		}
	}
}
