package src;

import javax.swing.*;
import java.awt.Color;

class RedBlock extends JLabel
{
  private int sideLength;
  private int maxCoordinate;
  
  public RedBlock(int s, int sL, Player user)
  {
    sideLength = s;
    maxCoordinate = sL - s;
    
    this.setBounds(0, 0, s, s);
    this.setBackground(Color.red);
    this.setVisible(true);
    this.setOpaque(true);

    this.moveRandomly(user);
  }

  public int getSideLength()
  {
    return sideLength;
  }

  public void moveRandomly(Player user)
  {
    do
    {
      this.setLocation((int)(Math.random() * maxCoordinate), (int)(Math.random() * maxCoordinate));
    } while (user.isTouchingBlock(this));
  }
}

