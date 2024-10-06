package src;

import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import java.util.Scanner;

class GameRunner extends JFrame implements KeyListener
{
  private Player user;
  private int sideLimit;
  private RedBlock block;
  private Enemy enemy;
  private int difficulty;

  
  /**
   * Constructs a GameRunner object that will have a sideLimit, s, one user that will be 
   * controllable by the player, one block that will randomly respawn as the previous one is picked 
   * up, and one enemy that will chase the user. 
   *
   * @param int s will be assigned to sideLimit and will define the bounds of the GamerRunner.
   */
  public GameRunner(int s)
  {
    Scanner inputs = new Scanner(System.in);
    System.out.println("Welcome to Hungry Green Block. Your mission is to eat\n" +
                       "red blocks. Each red block will grant you 1 point. But watch out:\n" +
                       "Orange blocks end the game!!\nUse W A S D to move the Green Block.");

    boolean isValid = false;
    while (!isValid)
    {
      System.out.println("Please select a difficulty:\nEasy = 1\nMedium = 2\nHard = 3");
      int temp = inputs.nextInt();

      if (temp >= 1 && temp <= 3)
      {
        difficulty = temp;
        isValid = true;
      }
      else
      {
        System.out.println("Invalid input, please enter a valid difficulty.");
      }
    }
    
    sideLimit = s;
    
    this.getContentPane().setBackground(Color.black);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(sideLimit, sideLimit);
    this.setLayout(null);
    this.addKeyListener(this);
    this.setVisible(true);

    user = new Player(50, sideLimit, 10);
    this.add(user);

    block = new RedBlock(25, sideLimit, user);
    this.add(block);

    enemy = new Enemy(35, sideLimit, user);
    this.add(enemy);

    inputs.close();
  }

  /**
	 * Does nothing (necessary for KeyListener implementation).
   *
   * @param KeyEvent e event the listener listens for.
	 */
  @Override
  public void keyTyped(KeyEvent e)
  {
      
  }
  
  /**
	 * Listens for keys 'w', 'a', 's', or 'd' to move the user up, left, down, or right, respectively.
   * Also lets user pick up block and facilitates the movement of the enemy.
   *
   * @param KeyEvent e event the listener listens for.
	 */
  @Override
  public void keyPressed(KeyEvent e)
  {
    switch (e.getKeyChar())
    {
      case 'w':
        if (user.getY() > 0)
        {
          user.setLocation(user.getX(), user.getY() - user.getSpeed());

          enemy.moveToward(user, enemy.getSpeed());

          if (user.isTouchingBlock(block))
          {
            this.pickUpBlock(user, block);
          }
          
          if (user.isTouchingEnemy(enemy))
          {
            this.close();
          }
        }
        break;
      case 'a':
        if (user.getX() > 0)
        {
          user.setLocation(user.getX() - user.getSpeed(), user.getY());

          enemy.moveToward(user, enemy.getSpeed());

          if (user.isTouchingBlock(block))
          {
            this.pickUpBlock(user, block);
          }

          if (user.isTouchingEnemy(enemy))
          {
            this.close();
          }
        }
        break;
      case 's': 
        if (user.getY() < sideLimit - user.getSideLength())
        {
          user.setLocation(user.getX(), user.getY() + user.getSpeed());

          enemy.moveToward(user, enemy.getSpeed());

          if (user.isTouchingBlock(block))
          {
            this.pickUpBlock(user, block);
          }

          if (user.isTouchingEnemy(enemy))
          {
            this.close();
          }
        }
        break;
      case 'd': 
        if (user.getX() < sideLimit - user.getSideLength())
        {
          user.setLocation(user.getX() + user.getSpeed(), user.getY());

          enemy.moveToward(user, enemy.getSpeed());

          if (user.isTouchingBlock(block))
          {
            this.pickUpBlock(user, block);
          }

          if (user.isTouchingEnemy(enemy))
          {
            this.close();
          }
        }
        break;
    }
  }

  /**
	 * Does nothing (necessary for KeyListener implementation).
   *
   * @param KeyEvent e event the listener listens for.
	 */
  @Override
  public void keyReleased(KeyEvent e)
  {
   
  }

  /**
   * Precondition: Player is already touching RedBlock.
	 * Player picks up RedBlock and then RedBlock moves randomly. Score increments.
   *
   * @param Player p Player that will pick up RedBlock.
   * @param RedBlock block Redblock that will be picked up by Player.
	 */
  public void pickUpBlock(Player p, RedBlock block)
  {
    block.moveRandomly(p);
    p.incrementScore();
    System.out.println(p.getScore());

    switch (difficulty)
    {
      case 1:
        if (user.getScore() % 6 == 0 && user.getScore() > 0)
        {
          enemy.incrementSpeed(user);
        }
        break;
      case 2:
        if (user.getScore() % 4 == 0 && user.getScore() > 0)
        {
          enemy.incrementSpeed(user);
        }
        break;
      case 3:
        if (user.getScore() % 2 == 0 && user.getScore() > 0)
        {
          enemy.incrementSpeed(user);
        }
        break;
    }
  }

  /**
	 * Ends the program.
	 */
  public void close()
  {
    this.setVisible(false);
    this.dispose();
  }

  /**
	 * Accessor method for instance variable sideLimit.
   *
   * @return sideLimit 
	 */
  public int getSideLimit()
  {
    return sideLimit;
  }
}