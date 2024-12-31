//RUN THIS FILE

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Main {

    static class Music {
        File file;
        AudioInputStream stream;
        Clip clip;
        public Music(String link) throws UnsupportedAudioFileException, IOException, LineUnavailableException { {
            file = new File(link);
            stream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(stream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-10.0f);

            clip.start();
        }
    }
        public void stopErrorMessage() {
        }}

    static class Drawer extends JComponent implements ActionListener{
        private int danceFrame = 0;
	    private int danceMove = 0;
	    private int maxFrame = 75;
	    private Timer timer;
        private int ghostDisplacment=0;
        private int ghostdx = 3;
        public Drawer(){
            timer = new Timer(112,this);
            timer.start();
        }
        public void paintComponent(Graphics g1) {
            int xMax = getWidth(); int yMax = getHeight();
            Graphics2D g = (Graphics2D) g1;
            g.setColor(new Color(23, 24, 69));
            g.fill (new Rectangle(0, 0, xMax, yMax));//Adds bg

        
            House house1 = new House(100,300,400,400);

            Ghost ghost1 = new Ghost(100+ghostDisplacment, 30, 1);
            Ghost ghost2 = new Ghost(240+ghostDisplacment,60,2);
            Ghost ghost3 = new Ghost(430+ghostDisplacment,80,1);

            Dude dude1 = new Dude(420,520,30,120, Color.red, Color.red, Color.green);
            Dude dude2 = new Dude(240,505,20,130, Color.black, Color.black, Color.CYAN);
            Dude dude3 = new Dude(120,580,30,80, Color.white, Color.red, Color.red);

            Moon moon1 = new Moon(500,-100,160,200);

            house1.draw(g);
            ghost1.draw(g);
            ghost2.draw(g);
            ghost3.draw(g);
            moon1.draw(g);
            dude1.draw(g, danceMove);
            dude2.draw(g,danceMove);
            dude3.draw(g,danceMove);
            g.setColor(Color.yellow);
            g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 30)); 
            g.drawString("BEWARE THE SPOOKY MACARENA!!!", 20,300);
        }

        public void actionPerformed(ActionEvent e){
            repaint();
            updateDance();
        }
        private void updateDance(){
            danceFrame++;
            if(danceFrame>maxFrame){danceFrame=0;}
            if(danceFrame<=60) {danceMove = danceFrame/5;}
            else {danceMove=danceFrame;}
            this.ghostDisplacment+=(int)(this.ghostdx*Math.random()*4);
            if(this.ghostDisplacment>25){this.ghostdx*=-1;}
            if(this.ghostDisplacment<-25){this.ghostdx*=-1;}
        }
	}

    static class Ghost{
        private int xLeft;
        private int yTop;
        private int scale;
        public Ghost(int x, int y, int s){
            xLeft = x;
            yTop = y;
            scale = s;
        }
    
        public void draw(Graphics2D g2){
            g2.setColor(Color.WHITE);
            Ellipse2D.Double body = new Ellipse2D.Double(xLeft, yTop, 50*scale, 70*scale);
            g2.fill(body);
            Rectangle body2 = new Rectangle(xLeft, yTop + (35*scale), 50*scale, 50*scale);
            g2.fill(body2);
            Ellipse2D.Double b1 = new Ellipse2D.Double(xLeft, yTop + (80*scale), 15*scale, 15*scale);
            g2.fill(b1);
            Ellipse2D.Double b2 = new Ellipse2D.Double(xLeft + (10*scale), yTop + (80*scale), 30*scale, 15*scale);
            g2.fill(b2);
            Ellipse2D.Double b3 = new Ellipse2D.Double(xLeft + (35*scale), yTop + (80*scale), 15*scale, 15*scale);
            g2.fill(b3);
            g2.setColor(Color.BLACK);
            Ellipse2D.Double eye1 = new Ellipse2D.Double(xLeft + (10*scale), yTop + (15*scale), 10*scale, 20*scale);
            g2.fill(eye1);
            Ellipse2D.Double eye2 = new Ellipse2D.Double(xLeft + (30*scale), yTop + (15*scale), 10*scale, 20*scale);
            g2.fill(eye2);
            Ellipse2D.Double mouth = new Ellipse2D.Double(xLeft + (18*scale), yTop + (45*scale), 15*scale, 20*scale);
            g2.fill(mouth);
        }

    }



    static class Moon {
        private int x, y, w, h;

        public Moon(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.w = width;
            this.h = height;
        }

        public void draw(Graphics2D g2) {

            Ellipse2D.Double moon = new Ellipse2D.Double(this.x, this.y, this.w, this.h);
            g2.setColor(Color.yellow);
            g2.fill(moon);
        }
    }


    public static class House {
		private int x, y, width, height;
		public House(int x, int y, int width, int height) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}
		
		public void draw(Graphics2D g2) {
			Rectangle houseLeftBody = new Rectangle(x,y+height/4,width/3,height*3/4);
			Rectangle houseMiddleBody = new Rectangle(x+width/3,y+height*75/200,width/3,height-height*75/200);
			Rectangle houseRightBody = new Rectangle(x+width*2/3,y+height/4,width/3,height*3/4);
			Rectangle window1 = new Rectangle(x+width*12/150, y+height*2/5, width/6, height/8);
			Rectangle window2 = new Rectangle(x+width*112/150, y+height*2/5, width/6, height/8);
			Rectangle window3 = new Rectangle(x+width*62/150, y+height*5/11, width/6, height/8);
			Rectangle door = new Rectangle(x+width*65/150,y+height*160/200, width*20/150, height * 40/200);
			int[] leftRoofX = {x,x+width*25/150,x+width/3};
			int[] leftRoofY = {y+height/4,y,y+height/4};
			int[] middleRoofX = {x+width/3,x+width*75/150,x+width*2/3};
			int[] middleRoofY = {y+height*75/200,y+height/4,y+height*75/200};
			int[] rightRoofX = {x+width*2/3,x+width*125/150,x+width};
			int[] rightRoofY = {y+height/4,y,y+height/4};
			g2.setColor(new Color(30, 32, 41));
			g2.fill(houseRightBody);
			g2.fill(houseMiddleBody);
			g2.fill(houseLeftBody);
			g2.setColor(Color.black);
			g2.fillPolygon(leftRoofX, leftRoofY, leftRoofX.length);
			g2.fillPolygon(middleRoofX, middleRoofY, middleRoofX.length);
			g2.fillPolygon(rightRoofX, rightRoofY, rightRoofX.length);
			g2.setColor(Color.yellow);
			g2.fill(window1);
			g2.fill(window2);
			g2.fill(window3);
			g2.setColor(new Color(4, 54, 25));
			g2.fill(door);
		}
		
	}
	

    public static class Dude {

            private int x = 0;
            private int y = 0;
            private int w = 40;
            private int h = 40;
            private Color bod; private Color arm; private Color leg;
            
        public Dude(int x, int y,int w,int h, Color arm, Color leg, Color bod)
        {
            this.bod=bod; this.leg=leg; this.arm=arm;
            this.x=x;this.y=y;this.w=w;this.h=h;//height is only body height
        }

        public void draw(Graphics2D g, int pos)
        {
            Ellipse2D.Double head = new Ellipse2D.Double(x-w/2,y-h/4,w,h/3.5);
            Rectangle body = new Rectangle(x-w,y,w*2,h);
                if(pos>12&&pos<70) {int factor = pos%2; factor = factor*2-1; body.translate(factor*w/4, 0);}
            Point armLBase = new Point(x-w/2,y+h*2/6);
            Point armRBase = new Point(x+w/2,y+h*2/6);
            Point elbowR = this.computeRElbow(pos);
            Point elbowL = this.computeLElbow(pos);
            Point armLPoint = this.computeLArm(pos);
            Point armRPoint = this.computeRArm(pos);
            Line2D.Double arm1L = new Line2D.Double(armLBase,elbowL);
            Line2D.Double arm1R = new Line2D.Double(armRBase, elbowR);
            Line2D.Double arm2L = new Line2D.Double(elbowL,armLPoint);
            Line2D.Double arm2R = new Line2D.Double(elbowR, armRPoint);
            Point legLBase = new Point(x+w/5-w/2,y+h*7/8);
            Point legRBase = new Point(x+w-w/5-w/2,y+h*7/8);
            Point legLPoint = this.computeLLeg(pos);
            Point legRPoint = this.computeRLeg(pos);
            Line2D.Double legL = new Line2D.Double(legLBase,legLPoint);
            Line2D.Double legR = new Line2D.Double(legRBase, legRPoint);
            

            g.setColor(leg);
            g.setStroke(new BasicStroke(6));
            g.draw(legR);
            g.draw(legL);

            g.setColor(bod);
            g.fill(head);
            g.fill(body);

            
            g.setColor(arm);
            g.setStroke(new BasicStroke(4));
            g.draw(arm1R);
            g.draw(arm1L);
            g.draw(arm2R);
            g.draw(arm2L);
            

        }



        private Point computeRArm(int pos) {
            switch(pos) {
                case 0: return new Point(x+w,y+h*2/6+h/10);
            case 1: return new Point(x+w,y+h*2/6+h/10);
            
                case 2: return new Point(x+w,y+h*2/6-h/10);
            case 3: return new Point(x+w,y+h*2/6-h/10);
            
                case 4: return new Point(x-w,y);
            case 5: return new Point(x-w,y);//add this to others todo
            
            case 6: return new Point(x+w/2, y-h/8);
            case 7: return new Point(x+w/2, y-h/8);

            case 8: return new Point(x-w, y+h);
            case 9: return new Point(x-w, y+h);

            case 10: return new Point(x+w,y+h*4/5);
            case 11: return new Point(x+w,y+h*4/5);

            case 12: return new Point(x+w,y+h*4/5);
            default: return new Point(x+w,y+h*4/5);
            }
        }
        private Point computeRElbow(int pos) {
            switch(pos){
                case 0: return new Point(x+w,y+h*2/6+h/10);
            case 1: return new Point(x+w,y+h*2/6+h/10);
            
                case 2: return new Point(x+w,y+h*2/6-h/10);
                case 3: return new Point(x+w,y+h*2/6-h/10);//this too
                
                case 4: return new Point(x+w/3,y+h/2);
                case 5: return new Point(x+w/3,y+h/2);//add this to others todo

            case 6: return new Point(x+w*3/2, y-h/8);
            case 7: return new Point(x+w*3/2, y-h/8);

            case 8: return new Point(x+w*5/4, y+h*3/4);
            case 9: return new Point(x+w*5/4, y+h*3/4);

            case 10: return new Point(x+2*w,y+h/2);
            case 11: return new Point(x+2*w,y+h/2);

            case 12: return new Point(x+2*w,y+h/2);
            default: return new Point(x+2*w,y+h/2);
            }
        }

        private Point computeLArm(int pos) {
            switch(pos) {
                case 0: return new Point(x-w/2,y+h*2/6);
            case 1: return new Point(x-w,y+h*2/6+h/10);
        
            case 2: return new Point(x-w,y+h*2/6+h/10);
            case 3: return new Point(x-w,y+h*2/6-h/10);
            
            case 4: return new Point(x-w,y+h*2/6-h/10);
            case 5: return new Point(x+w,y);//add this to others todo

            case 6: return new Point(x+w,y);
            case 7: return new Point(x-w/2, y-h/8);
            
            case 8: return new Point(x-w/2, y-h/8);
            case 9: return new Point(x+w, y+h);

            case 10: return new Point(x+w, y+h);
            case 11: return new Point(x-w,y+h*4/5);

            case 12: return new Point(x-w,y+h*4/5);
            default: return new Point(x-w,y+h*4/5);
            }
        }
            private Point computeLElbow(int pos) {
                switch(pos){
                    case 0: return new Point(x-w/2,y+h*2/6);
                case 1: return new Point(x-w,y+h*2/6+h/10);

                case 2: return new Point(x-w,y+h*2/6+h/10);
                case 3: return new Point(x-w,y+h*2/6-h/10);//this too
                
                case 4: return new Point(x-w,y+h*2/6-h/10);
                case 5: return new Point(x-w/3,y+h/2);//add this to others todo
                
                case 6: return new Point(x-w/3,y+h/2);
                case 7: return new Point(x-w*3/2, y-h/8);
            
                case 8: return new Point(x-w*3/2, y-h/8);
            case 9: return new Point(x-w*5/4, y+h*3/4);
                
            case 10: return new Point(x-w*5/4, y+h*3/4);
                case 11: return new Point(x-2*w,y+h/2);
                
                case 12: return new Point(x-2*w,y+h/2);
                default: return new Point(x-2*w,y+h/2);
            }
        }

        private Point computeRLeg(int pos) {
            return new Point(x+w/2,y+h*3/2);
        }
        private Point computeLLeg(int pos) {
            return new Point(x-w/2,y+h*3/2);
        }


}




    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        JFrame frame = new JFrame();
        frame.setSize(600,740);
        frame.setTitle("A Veritable Amalgamation of Themetically-Clashing Ideas");
        frame.setDefaultCloseOperation(3);

        Drawer component = new Drawer();
        frame.add(component);
        Music song = new Music("song.wav");
        song.stopErrorMessage();

        frame.setVisible(true);
    }
















}
