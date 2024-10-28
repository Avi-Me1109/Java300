//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////


//
// Title:    Bouncing Bug Class
// Course:   CS 300 Fall 2023
//
// Author:   Avi Raj Balam
// Email:    abalam@wisc.edu
// Lecturer: (Hobbes LeGault or Mark Mansi or Mouna Kacem)
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Online Sources: https://processing.org/reference#structure
//
///////////////////////////////////////////////////////////////////////////////
// Below is a javadoc class header to complete
/**
 * Main game class with main function that runs the game and holds all logics
 * 
 * @author Avi Raj Balam
 */
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.Random;

/**
 * The main class for the Froggie Feeding Frenzie game. Manages game actors, scoring, 
 * and game logic.
 *
 */
public class FrogGame extends PApplet {
  private ArrayList<GameActor> gameActors; // Array list of the gameActors in the game
  private int score; // The player's current score
  private PImage backgroundImg; // The image to use for the background
  private boolean isGameOver; // Keeps track if the game is over, is true if the game is over
  private Random randGen; // Random number generator
  private static final int BUG_COUNT = 5; // How many bugs should be on the screen at all times
  
  /**
   * Entry point for the game, sets up the Processing environment.
   * @param args Command-line arguments
   */
  public static void main(String[] args) {
    PApplet.main("FrogGame");
  }
  
  @Override
  public void settings() {
    this.size(800, 600); // Set the game window size
  }
  
  @Override
  public void setup() {
    this.getSurface().setTitle("Froggie Feeding Frenzie"); // Set the game window title
    randGen = new Random(); // Initialize the random number generator
    backgroundImg = loadImage("images/background.jpg"); // Load the background image
    this.imageMode(PApplet.CENTER);
    this.rectMode(PApplet.CENTER);
    this.focused = true;
    this.textAlign(PApplet.CENTER); 
    this.textSize(30); 

    gameActors = new ArrayList<GameActor>(); // Initialize the list of game actors

    // Set the processing variable for relevant classes
    Hitbox.setProcessing(this);
    GameActor.setProcessing(this);
    Tongue.setProcessing(this);

    initGame(); // Initialize the game
  }

  @Override
  public void draw() {
    image(backgroundImg, 0, 0); // Draw the background image

    if (!isGameOver) { // Check if the game is not over
      for (GameActor actor : gameActors) {
        actor.draw();
        
        if (actor instanceof Moveable) {
          Moveable moveableActor = (Moveable) actor;
          moveableActor.move(); // Move the actor
        }
      }
      
      runGameLogicChecks(); // Run game logic checks

      text("Health: " + getFrogHealth(), 80, 40); // Display frog's health
      text("Score: " + score, 240, 40); // Display player's score
    } else {
      text("GAME OVER", width / 2, height / 2); // Display "GAME OVER" message
    }
  }



   /**
    * Adds a new bug to the game with a random type and position.
    */
   private void addNewBug() {
     int bugType = randGen.nextInt(4); // Generate a random bug type
     float x = randGen.nextFloat(width); // Random x-coordinate
     float y = randGen.nextFloat(height - 150); // Random y-coordinate
  
     if (bugType == 0) {
       Bug regularBug = new Bug(x, y, 25);
       gameActors.add(regularBug); // Add a regular bug
     } else if (bugType == 1) {
       BouncingBug bouncingBug = new BouncingBug(x, y, 2, 5);
       gameActors.add(bouncingBug); // Add a bouncing bug
     } else if (bugType == 2) {
       int[] tintColor = { randGen.nextInt(256), randGen.nextInt(256), randGen.nextInt(256) };
       CirclingBug circlingBug = new CirclingBug(x, y, 25, tintColor);
       gameActors.add(circlingBug); // Add a circling bug
     } else if (bugType == 3) {
       StrongBug strongBug = new StrongBug(x, y, 3);
       gameActors.add(strongBug); // Add a strong bug
     }
   }
  
   @Override
   public void mousePressed() {
     for (GameActor actor : gameActors) {
       if (actor instanceof Frog) {
         Frog frog = (Frog) actor;
         frog.mousePressed(); // Handle mouse press for the frog
       }
     }
   }
  
   @Override
   public void mouseReleased() {
     for (GameActor actor : gameActors) {
       if (actor instanceof Frog) {
         Frog frog = (Frog) actor;
         frog.mouseReleased(); // Handle mouse release for the frog
       }
     }
   }
  
   @Override
   public void keyPressed() {
     int a = 0;
     
     if (a == 0) {
       if (key == ' ') {
         for (GameActor actor : gameActors) {
           if (actor instanceof Frog) {
             Frog frog = (Frog) actor;
             frog.startAttack(); // Start the frog's attack
             a++;
           } else if (key == 'r') {
             initGame(); // Restart the game
           }
         }
       }
     } else if (a == 1) {
       if (key == ' ') {
         for (GameActor actor : gameActors) {
           if (actor instanceof Frog) {
             Frog frog = (Frog) actor;
             frog.stopAttack(); // Stop the frog's attack
             a--;
           } else if (key == 'r') {
             initGame(); // Restart the game
           }
         }
       }
     }
   }
  
   /**
    * Initializes the game by setting the initial score, game state, and adding actors.
    */
   public void initGame() {
     score = 0; // Reset the player's score
     isGameOver = false; // Reset the game state
     gameActors.clear(); // Clear the list of game actors
     
     Frog frog = new Frog(width / 2, height - 100, 100);
     gameActors.add(frog); // Add the frog
     
     for (int i = 0; i < BUG_COUNT; i++) {
       addNewBug(); // Add new bugs to the game
     }
   }
   
   /**
    * Runs various game logic checks including checking for collisions and game over conditions.
    */
   private void runGameLogicChecks() {
     // Check various game logic conditions here...
   }
  
   /**
    * Retrieves the current health of the frog.
    * @return The current health of the frog.
    */
   private int getFrogHealth() {
     for (GameActor actor : gameActors) {
       if (actor instanceof Frog) {
         return ((Frog) actor).getHealth();
       }
     }
     return 0; // Return 0 if frog actor is not found
   }
  }

