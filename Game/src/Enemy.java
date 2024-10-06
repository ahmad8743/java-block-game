package src;

import javax.swing.*;
import java.awt.Color;

class Enemy extends JLabel 
{
  private int sideLength;
  private int maxCoordinate;
  private int speed;
  
  public Enemy(int s, int sL, Player user)
  {
    sideLength = s;
    maxCoordinate = sL - s;
    speed = 1;

    this.setBounds(0, 0, sideLength, sideLength);
    this.setBackground(Color.orange);
    this.setVisible(true);
    this.setOpaque(true);

    this.moveRandomly(user);
  }

  public void moveRandomly(Player user)
  {
    do
    {
      this.setLocation((int)(Math.random() * maxCoordinate), (int)(Math.random() * maxCoordinate));
    } while (user.isTouchingEnemy(this));
  }

  public void moveToward(Player user, int speed)
  {
    this.setLocation(this.getX() + (int)(speed * Enemy.calculateXVector(this.getX(), user.getX(), this.getY(), user.getY())),
                     this.getY() + (int)(speed * Enemy.calculateYVector(this.getX(), user.getX(), this.getY(), user.getY())));
  }

  public int getSideLength()
  {
    return sideLength;
  }

  private static double calculateXVector(int x1, int x2, int y1, int y2)
  {
    int xDifference = x2 - x1;
    int yDifference = y2 - y1;
    double magnitude = Math.sqrt(Math.pow(xDifference, 2) + Math.pow(yDifference, 2));

    return xDifference / magnitude;
  }

  private static double calculateYVector(int x1, int x2, int y1, int y2)
  {
    int xDifference = x2 - x1;
    int yDifference = y2 - y1;
    double magnitude = Math.sqrt(Math.pow(xDifference, 2) + Math.pow(yDifference, 2));
    
    return yDifference / magnitude;
  }

  public void incrementSpeed(Player user)
  {
    if (speed <= user.getSpeed())
    {
      speed++;
    }
  }

  public int getSpeed()
  {
    return speed;
  }
}
