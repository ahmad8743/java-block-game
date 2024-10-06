package src;

import javax.swing.*;
import java.awt.Color;

class Player extends JLabel 
{
  private int score;
  private int sideLength;
  private int speed;


  /**
   * Constructs a Player object with sideLength, s. Player will be autoset to green and gameRunnerSL 
   * is the side length of the GameRunner the Player object is running in, which will be used to determine 
   * the spawn point. spd will be assigned to speed.
   *
   * @param int s assigned to sideLength.
   * @param int gameRunnerSL is the sideLimit of the GameRunner and will be used to determine the spawn point.
   * @param int spd assigns the Player’s speed.
   */
  public Player(int s, int gameRunnerSL, int spd)
  {
    sideLength = s;
    speed = spd;
    int spawnPoint = (gameRunnerSL / 2) - (s / 2); // spawns in the middle of the screen
    
    this.setBounds(spawnPoint, spawnPoint, sideLength, sideLength);
    this.setBackground(Color.green);
    this.setVisible(true);
    this.setOpaque(true);
    score = 0;
  }

  /**
   * Checks to see if Player is touching RedBlock.
   * 
   * @param RedBlock block Player checks to se if it is touching this RedBlock.
   * @return true if touching, false otherwise.
   */
  public boolean isTouchingBlock(RedBlock block)
  {
    if (this.getX() + this.sideLength >= block.getX() 
      && this.getX() <= block.getX() + block.getSideLength())
    {
      if (this.getY() + this.sideLength >= block.getY() 
        && this.getY() <= block.getY() + block.getSideLength())
      {
        return true;
      }
    }

    return false;
  }

  /**
   * Checks to see if Player is touching Enemy.
   * 
   * @param Enemy enemy Player checks to se if it is touching this Enemy.
   * @return true if touching, false otherwise.
   */
  public boolean isTouchingEnemy(Enemy enemy)
  {
    if (this.getX() + this.sideLength >= enemy.getX() 
      && this.getX() <= enemy.getX() + enemy.getSideLength())
    {
      if (this.getY() + this.sideLength >= enemy.getY() 
        && this.getY() <= enemy.getY() + enemy.getSideLength())
      {
        return true;
      }
    }

    return false;
  }

  /**
   * Increments the Player's score.
   */
  public void incrementScore()
  {
    score++;
  }

  /**
   * Accessor method for the Player's sideLength.
   * 
   * @return the sideLength of the Player.
   */
  public int getSideLength()
  {
    return sideLength;
  }

  /**
   * Accessor method for the Player's score.
   * 
   * @return the score of the Player.
   */
  public int getScore()
  {
    return score;
  }

  /**
   * Accessor method for the Player's speed.
   * 
   * @return the speed of the Player.
   */
  public int getSpeed()
  {
    return speed;
  }
}