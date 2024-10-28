//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    SpaceStation is a Java simulation game where players interact within a space station, avoiding an impostor among them. (AMONG US CLONE)
// Course:   CS 300 Fall 2023
//
// Author:   Avi Raj Balam
// Email:    abalam@wisc.edu
// Lecturer: (Hobbes LeGault or Mark Mansi or Mouna Kacem)
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Online Sources:  https://www.geeksforgeeks.org/passing-and-returning-objects-in-java/# Finding out how to pass and return Objects in Java
//
///////////////////////////////////////////////////////////////////////////////

/*
 * Imported Libraries according to specification
 */

import java.io.File;
import processing.core.PImage;

/**
 * This class represents a space station simulation game. Players can be created,
 * moved, interacted with, and the impostor player can eliminate others.
 */
public class SpaceStation {

  private static int bgColor;
  //private static PImage sprite;
  private static Amogus players[];
  private static PImage background;
  private static int NUM_PLAYERS = 8;
  private static int impostorIndex;

  /**
   * Initializes the space station game. Loads the background image, creates player
   * objects, and selects an impostor player.
   */
  public static void setup() {
    // @see Utility#loadImage(String)
    background = Utility.loadImage("images" + File.separator + "background.jpeg");
    players = new Amogus[NUM_PLAYERS];
    int color = Utility.randGen.nextInt(1, 4);
    impostorIndex = Utility.randGen.nextInt(1, NUM_PLAYERS);
    System.out.print("Impostor index: " + impostorIndex);
    players[0] = new Amogus(color, 600, 400, false);
  }

  /**
   * Draws the game's visuals, including background and player characters.
   */
  public static void draw() {
    Utility.image(background, 600, 500);
    for (int x = 0; x < NUM_PLAYERS; x++) {
      if (players[x] != null) {
        players[x].draw();
        if (x != impostorIndex && players[impostorIndex] != null) {
          if (overlap(players[x], players[impostorIndex])) {
            players[x].unalive();
          }
        }
      }
    }
  }

  /**
   * The main entry point of the program that runs the application.
   * 
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    Utility.runApplication();
  }

  /**
   * Handles keypress events. When the 'a' key is pressed, it creates new player
   * characters.
   */
  public static void keyPressed() {
    // @throws NullPointerException if Utility is not properly initialized
    char key = Utility.key();
    if (key == 'a') {
      for (int x = 0; x < NUM_PLAYERS; x++) {
        if (players[x] == null) {
          if (x == impostorIndex) {
            int horizontal_space = Utility.randGen.nextInt(Utility.width());
            int vertical_space = Utility.randGen.nextInt(Utility.height());
            int color = Utility.randGen.nextInt(1, 4);
            players[x] = new Amogus(color, horizontal_space, vertical_space, true);
          } else {
            int horizontal_space = Utility.randGen.nextInt(Utility.width());
            int vertical_space = Utility.randGen.nextInt(Utility.height());
            int color = Utility.randGen.nextInt(1, 4);
            players[x] = new Amogus(color, horizontal_space, vertical_space, false);
          }
          break;
        }
      }
    }
  }

  /**
   * Checks if the mouse cursor is over a player character.
   * 
   * @param Player the player object to check
   * @return true if the mouse cursor is over the player, false otherwise
   */
  public static boolean isMouseOver(Amogus Player) {
    // @param Player the player object to check
    // @return true if the mouse cursor is over the player, false otherwise
    double Player_center_position_x = Player.getX();
    double Player_center_position_y = Player.getY();
    PImage Player_image = Player.image();
    double Player_width = Player_image.width;
    double Player_height = Player_image.height;
    double PlayerX = Player_center_position_x - (Player_width / 2);
    double PlayerY = Player_center_position_y - (Player_height / 2);
    int mouseX = Utility.mouseX();
    int mouseY = Utility.mouseY();
    return mouseX >= PlayerX && mouseX <= PlayerX + Player_width - 1 &&
        mouseY >= PlayerY && mouseY <= PlayerY + Player_height - 1;
  }

  /**
   * Checks if two player characters overlap.
   * 
   * @param p1 the first player
   * @param p2 the second player
   * @return true if the players overlap, false otherwise
   */
  public static boolean overlap(Amogus p1, Amogus p2) {
    // @param p1 the first player
    // @param p2 the second player
    // @return true if the players overlap, false otherwise
    double p1_center_position_x = p1.getX();
    double p1_center_position_y = p1.getY();
    PImage p1_image = p1.image();
    double p1_width = p1_image.width;
    double p1_height = p1_image.height;

    double p2_center_position_x = p2.getX();
    double p2_center_position_y = p2.getY();
    PImage p2_image = p2.image();
    double p2_width = p2_image.width;
    double p2_height = p2_image.height;

    return p1_center_position_x >= p2_center_position_x - p2_width / 2 && p1_center_position_x <= p2_center_position_x + p2_width / 2 &&
        p1_center_position_y >= p2_center_position_y - p2_height / 2 && p1_center_position_y <= p2_center_position_y + p2_height / 2;
  }

  /**
   * Handles mouse press events. Initiates dragging if the mouse is over a player.
   */
  public static void mousePressed() {
    for (int x = 0; x < NUM_PLAYERS; x++) {
      if (isMouseOver(players[x])) {
        players[x].startDragging();
        break;
      }
    }
  }

  /**
   * Handles mouse release events. Stops dragging if the mouse is over a player.
   */
  public static void mouseReleased() {
    for (int x = 0; x < NUM_PLAYERS; x++) {
      if (isMouseOver(players[x])) {
        players[x].stopDragging();
        break;
      }
    }
  }
}
