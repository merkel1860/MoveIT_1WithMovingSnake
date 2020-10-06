/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moveit;

/*
    Importing required dependencies

*/
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



/**
 *
 * @author sonne
 */
public class MoveIT extends JPanel {

    /*
        Local variables declaration
    */
    	private static Random random = new Random();
        public static Circle objCircle;
        private static final int FRAMEWIDTH = 400;
        private static final int FRAMEHEIGHT = 400;
        /*////////////////////////////////////////////////////////////
            Senario II
            We are about to implement a loop within main function
            that will be ended only on quitting program
            
        *////////////////////////////////////////////////////////////
        private static boolean isExit = true;
        private static int sense = 38;
        
        //////////////////////////////////////////////////////////////
        
    public MoveIT() {
        // Setting up background and Seize parameters for 
        // moving surface
        setBackground(Color.BLACK);
    	setPreferredSize(new Dimension(FRAMEWIDTH, FRAMEHEIGHT));
        addCircle(390, 390);
    }

    ////////////////////////////////////////////////////////
    /*
        This method draws a graphic each time we call repaint
        there is no need to call it directly, unless we realy want 
        some nasty error message for free
    */
     @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        objCircle.draw(g);
       
    }
    ////////////////////////////////////////////////////////
    /*
        THis method is called once, at the very beginning 
        when it came to draw our first circle.
        Keep in mind that its locate has been kept random
        just for rending the process unpredictable
    */
    public void addCircle(int maxX, int maxY) {
        objCircle = new Circle(random.nextInt(maxX), random.nextInt(maxY));
        repaint();
    }
    /////////////////////////////////////////////////////////////////////
    /*
        This method aims to move back and forth or up and down accordingly
        to arrow keys, the gray circle.
    
    */
    public void inMotionManualMod(Point p,KeyEvent e){
        // Moving Upward
        if(e.getKeyCode()==38){
            objCircle.setY(objCircle.getY()-1);
            
        }
        // Moving Downward
        if(e.getKeyCode()==40){
            objCircle.setY(objCircle.getY()+1);
            
        }
        // Moving LEFT
        if(e.getKeyCode()==37){
            objCircle.setX(objCircle.getX()-1);
            
        }
        // Moving RIGHT
        if(e.getKeyCode()==39){
            objCircle.setX(objCircle.getX()+1);
           
        }
        repaint();
        
        System.out.println("Im moving ...");
    }
    ////////////////////////////////////////////////////////////////////
    public void inMotionAutomaticMod(Point p, int e){
        
        // Moving UpwardinMotionAutomaticMod
        if(e == 38){
            objCircle.setY(objCircle.getY()-1);
        }
        // Moving Downward
        if(e == 40){
            objCircle.setY(objCircle.getY()+1);
            
        }
        // Moving LEFT
        if(e == 37){
            objCircle.setX(objCircle.getX()-1);
            
        }
        // Moving RIGHT
        if(e == 39){
            objCircle.setX(objCircle.getX()+1);
            
        }
        repaint();
        
    }       
    /////////////////////////////////////////////////////////////////////
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // TODO code application logic here
        JFrame frame = new JFrame();       
                // get an Instance of the playboard which is basically
                // where the snake is moving.
                MoveIT playBoard = new MoveIT();
                frame.add(playBoard);
                
                //*******************************************************
                // Adding Key Listener to the actual Frame
                /********************************************************
                    By adding a keyListener to the frame
                    make sure you implement all override methods;
                
                */
                //******************************************************
                /*
                this use of keyAdapter is an Alternative to KeyListener
                 either ways you get the seemingly almost the same result
                 except for the Adapter, you only implement those behaviors
                 that you need.
                   frame.addKeyListener(

                   new KeyAdapter() {

                     * Invoked when a key has been pressed.
                     *
                     * @param e

                    @Override
                    public void keyPressed(KeyEvent e) {
                        super.keyPressed(e);
                    }
                });*/


                frame.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                    
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        System.out.println("Key pressed code=" + e.getKeyCode() 
                                + ", char=" + e.getKeyChar());
                        playBoard.inMotionManualMod(objCircle.getXY(),e);
                        sense = e.getKeyCode();
                        
                        // If we get " Q " once for quitting program + loop is dead 
                        // then we wrap it up
                        if(e.getKeyCode()==81 && isExit == false){
                            System.exit(0);
                        }
                        // we stop loop first time we receive " Q "
                        if(e.getKeyCode()==81){
                            System.out.println("This is exit time");
                                isExit = false;                                
                        }
                        
                                                                   
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
                                
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
                
                 System.out.println("Main Thread "+Thread.currentThread().getName());
                 
                 while(isExit){
                    try {
                            Thread.sleep(100);
                            playBoard.inMotionAutomaticMod(objCircle.getXY(),sense);
                            System.out.println("I'm loco -->"+sense);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(MoveIT.class.getName()).log(Level.SEVERE, null, ex);
                        }
                
                }                 
    }
    
}
