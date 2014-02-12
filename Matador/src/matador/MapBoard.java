/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matador;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

/**
 *
 * @author and3k5
 */
public class MapBoard extends javax.swing.JPanel {

    /**
     * Creates new form MapBoard
     */
    public MapBoard() {
        initComponents();
        mouse = new Point(-1,-1);
        addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mouse=e.getPoint();
            }
        });
        try {
            diceimg=ImageIO.read(new File("images/DICE.png"));
        } catch (IOException ex) {
            System.out.println("Could not find image...");
            Logger.getLogger(MapBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void rotateBy(int degrees,Graphics2D g2d) {
        /*g2d.translate(-(this.getWidth()/2), -(this.getHeight()/2));
        g2d.rotate(degrees*Math.PI/180);
        g2d.translate((this.getWidth()/2), (this.getHeight()/2));
        */
        AffineTransform transform=new AffineTransform();
        int size = Math.min(this.getWidth(),this.getHeight());
        transform.rotate(Math.toRadians(degrees), 0 + size/2, 0 + size/2);
        g2d.transform(transform);
    }
    private Point mouse;
    public double easeNone (double t,double b , double c, double d) {
            return c*t/d + b;
    }
    private BufferedImage diceimg;
    private void drawCircle(Graphics graphics) {
        try {
        Graphics2D g2d = (Graphics2D)graphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(0,0,0));
        float circleLineWidth = 2;
        int circleSize = Math.min(this.getWidth(),this.getHeight());
        g2d.setStroke(new BasicStroke(circleLineWidth));
        int circle0_x=(int)(circleLineWidth/2);
        int circle0_y=(int)(circleLineWidth/2);
        int circle0_w=(int)(circleSize-circleLineWidth);
        int circle0_h=(int)(circleSize-circleLineWidth);
        int circle1_x=(int)((circleLineWidth/2)+((circleSize-circleLineWidth)/4));
        int circle1_y=(int)((circleLineWidth/2)+((circleSize-circleLineWidth)/4));
        int circle1_w=(int)((circleSize-circleLineWidth)-((circleSize-circleLineWidth)/2));
        int circle1_h=(int)((circleSize-circleLineWidth)-((circleSize-circleLineWidth)/2));
        
        
        int degWidth = 0;
        degWidth = (360/Game.fields.size());
        int w=this.getWidth();
        int h=this.getHeight();
        
        int iW=circle1_w-circle1_x;
        int iH=circle1_h-circle1_y;
        int oW=circle1_w-circle0_w;
        int oH=circle1_h-circle0_h;
        int fieldN=0;
        for (int i=0;i<360;i+=degWidth) {
            g2d.setColor(new Color(0,0,0));
            int x1=(int) (circleSize/2+Math.cos(i*Math.PI/180)*iW);
            int y1=(int) (circleSize/2+Math.sin(i*Math.PI/180)*iH);
            int x2=(int) (circleSize/2+Math.cos(i*Math.PI/180)*-oW);
            int y2=(int) (circleSize/2+Math.sin(i*Math.PI/180)*-oH);
            int x3=(int) (circleSize/2+Math.cos((i+degWidth)*Math.PI/180)*iW);
            int y3=(int) (circleSize/2+Math.sin((i+degWidth)*Math.PI/180)*iH);
            int x4=(int) (circleSize/2+Math.cos((i+degWidth)*Math.PI/180)*-oW);
            int y4=(int) (circleSize/2+Math.sin((i+degWidth)*Math.PI/180)*-oH);
            
            // fill
            GeneralPath fillPath = new GeneralPath();
            fillPath.moveTo(x1, y1);
            fillPath.lineTo(x2, y2);
            fillPath.lineTo(x4, y4);
            fillPath.lineTo(x3, y3);
            fillPath.closePath();
            
            Color fillColor=new Color(0,0,0);
            Field field = Game.fields.get(fieldN);
            Boolean mustFill = false;
            if (field.getClass()==Street.class) {
                Street street=((Street)field);
                fillColor=Game.streetgroups.get(street.GroupID).color;
                mustFill=true;
            }
            int fx=(int)Math.sin(System.nanoTime())*10;
            int fy=(int)Math.cos(System.nanoTime())*10;
            if (mustFill) {
                g2d.setColor(fillColor);
                g2d.fill(fillPath);
                g2d.setColor(new Color(0,0,0));
            }
            
            if (fillPath.contains(mouse)) {
                setHoverField(fieldN,g2d);
                g2d.setColor(new Color(0, 0, 0, 128));
                g2d.fill(fillPath);
                g2d.setColor(new Color(0,0,0));
            }else{
                //System.out.println("Mouse outside");
            }
            
            // Text
            int textRotation=(int)(i+(degWidth/2)+(circleLineWidth/2));
            rotateBy(textRotation,g2d);
            Font font = new Font("Verdana", Font.PLAIN, (int)(10*Math.max(1.0,(circleSize/450.0))));
            //System.out.println("circleSize: "+circleSize+" sizeP: "+Math.max(1.0,(circleSize/450.0)));
            g2d.setFont(font);
            if (field.getClass()==Street.class) {
                Street street=((Street)field);
                int x=(int) (circleSize-g2d.getFontMetrics().stringWidth(street.Name)-circleLineWidth*2);
                int y=circleSize/2;
                g2d.drawString(street.Name,x,y);
            }else if (field.getClass()==Start.class) {
                String cap = "Start";
                g2d.drawString(cap, circleSize-g2d.getFontMetrics().stringWidth(cap)-circleLineWidth*2, circleSize/2);
            }else if (field.getClass()==Brewery.class) {
                Brewery brewery=((Brewery)field);
                int x=(int) (circleSize-g2d.getFontMetrics().stringWidth(brewery.Name)-circleLineWidth*2);
                int y=circleSize/2;
                g2d.drawString(brewery.Name,x,y);
            }else if (field.getClass()==GoToPrison.class) {
                String cap="Gå til fængsel";
                g2d.drawString(cap, circleSize-g2d.getFontMetrics().stringWidth(cap)-circleLineWidth*2, circleSize/2);
            }else if (field.getClass()==IncomeTax.class) {
                String cap="Betal inkomst skat";
                g2d.drawString(cap, circleSize-g2d.getFontMetrics().stringWidth(cap)-circleLineWidth*2, circleSize/2);
            }else if (field.getClass()==Parking.class) {
                String cap="Parkering";
                g2d.drawString(cap, circleSize-g2d.getFontMetrics().stringWidth(cap)-circleLineWidth*2, circleSize/2);
            }else if (field.getClass()==Prison.class) {
                String cap="Fængsel";
                g2d.drawString(cap, circleSize-g2d.getFontMetrics().stringWidth(cap)-circleLineWidth*2, circleSize/2);
            }else if (field.getClass()==ShippingLines.class) {
                ShippingLines shiplines=((ShippingLines)field);
                int x=(int) (circleSize-g2d.getFontMetrics().stringWidth(shiplines.Name)-circleLineWidth*2);
                int y=circleSize/2;
                g2d.drawString(shiplines.Name,x,y);
            }else if (field.getClass()==StateTax.class) {
                String cap="Ekstra statsskat";
                g2d.drawString(cap, circleSize-g2d.getFontMetrics().stringWidth(cap)-circleLineWidth*2, circleSize/2);
            }else if (field.getClass()==TryLuck.class) {
                String cap="Prøv lykken";
                g2d.drawString(cap, circleSize-g2d.getFontMetrics().stringWidth(cap)-circleLineWidth*2, circleSize/2);
            }
            fieldN++;
            rotateBy(-textRotation,g2d);
            
            // line
            g2d.drawLine(x1,y1,x2,y2);
        }
        g2d.drawArc(circle0_x, circle0_y, circle0_w, circle0_h, 0, 360);
        g2d.drawArc(circle1_x, circle1_y, circle1_w, circle1_h, 0, 360);
        int i=1;
        int dotSize=30;
        for (Player player : Game.players) {
            int x_1=0;
            int y_1=0;
            int x_2=0;
            int y_2=0;
            int x=0;
            int y=0;
            x_1=(int) (circleSize/2+Math.cos((player.Position*degWidth+degWidth/2)*Math.PI/180)*iW);
            y_1=(int) (circleSize/2+Math.sin((player.Position*degWidth+degWidth/2)*Math.PI/180)*iW);
            x_2=(int) (circleSize/2+Math.cos((player.Position*degWidth+degWidth/2)*Math.PI/180)*-oW);
            y_2=(int) (circleSize/2+Math.sin((player.Position*degWidth+degWidth/2)*Math.PI/180)*-oH);
            x=(int)easeNone(i,x_1,x_2-x_1,Game.players.size());
            y=(int)easeNone(i,y_1,y_2-y_1,Game.players.size());
            g2d.setColor(player.Color);
            g2d.fillArc(x-dotSize/2, y-dotSize/2, dotSize, dotSize, 0, 360);
            g2d.setColor(new Color(0,0,0));

            
            
            i++;
        }
        //Rectangle clip = new Rectangle(256*(Game.dices[0].number-1), 0, 256, 256);
        //diceimg
        //g2d.setClip(clip);
        int diceSize=64;
        //System.out.println(256*(Game.dices[0].number-1));
        g2d.drawImage(diceimg.getSubimage(256*(Game.dices[0].number-1), 0, 256, 256),this.getWidth()-diceSize*2, this.getHeight()-diceSize, diceSize, diceSize, this);
        g2d.drawImage(diceimg.getSubimage(256*(Game.dices[1].number-1), 0, 256, 256),this.getWidth()-diceSize, this.getHeight()-diceSize, diceSize, diceSize, this);
        }
        catch (NullPointerException error) {
            // Stupid netbeans form designer..
        }
    }
    public void setHoverField(int fid,Graphics2D g2d) {
        Field field = Game.fields.get(fid);
        String description = "Felt:\n";
        ArrayList<Class<?>> classes = new ArrayList(Arrays.asList(new Object[]{Brewery.class,GoToPrison.class,IncomeTax.class,Parking.class,Prison.class,ShippingLines.class,Start.class,StateTax.class,Street.class,TryLuck.class}));
        // Brewery.class
        // GoToPrison.class
        // IncomeTax.class
        // Parking.class
        // Prison.class
        // ShippingLines.class
        // Start.class
        // StateTax.class
        // Street.class
        // TryLuck.class
        switch (classes.indexOf(field.getClass())) {
            case 0:
                // Brewery
                Brewery brew = (Brewery)field;
                description+="Bryggeri:\n"+brew.Name+"\n";
                if (brew.Owner!=-1) {
                    description+="Ejes af "+Game.players.get(brew.Owner).Name+"\n";
                    if (!brew.Mortgage) {
                        description+="Leje: Terningens øjne * "+(brew.CountBrewery()*100)+" kr.\n";
                    }else{
                        description+="Pantsat.\n";
                    }
                }else{
                    description+="Ejes ikke\n";
                }
                description+="Pris værdi:"+brew.Price+" kr.";
                
                break;
            case 1:
                // GoToPrison
                description+="Gå til fængsel.\nBrik rykkes til fængslet.";
                break;
            case 2:
                // incomeTax
                description+="Inkomst skat.\nBetal 10% eller 4000 kr.";
                break;
            case 3:
                // Parking
                description+="Parkering.\nGiver 4000 kr.";
                break;
            case 4:
                // Prison
                description+="Fængsel.";
                break;
            case 5:
                // ShippingLines
                ShippingLines sl = (ShippingLines)field;
                description+="Redderi:\n";
                if (sl.Owner!=-1) {
                    description+="Ejes af "+Game.players.get(sl.Owner).Name+"\n";
                    if (!sl.Mortgage) {
                        description+="Leje: "+(int)((new double[]{0,500,1000,2000,4000})[(sl.CountShippingLines())])+" kr.\n";
                    }else{
                        description+="Pantsat.\n";
                    }
                }else{
                    description+="Ejes ikke\n";
                }
                description+="Pris værdi:"+sl.Price+" kr.";
                break;
            case 6:
                // Start
                description+="Start felt";
                break;
            case 7:
                // Statetax
                description+="Ekstra ordinær statsskat.\nBetal 2000 kr.";
                break;
            case 8:
                // Street
                Street street = (Street)field;
                description+="Redderi:\n";
                if (street.Owner!=-1) {
                    description+="Ejes af "+Game.players.get(street.Owner).Name+"\n";
                    if (!street.Mortgage) {
                        description+="Leje: "+street.Taxes[street.Houses]+" kr.\n";
                    }else{
                        description+="Pantsat.\n";
                    }
                }else{
                    description+="Ejes ikke\n";
                }
                description+="Pris værdi:"+street.Price+" kr.";
                break;
            case 9:
                // Tryluck
                description+="Prøv lykken";
                break;
            default:
                description="WAT";
                break;
            
        }
        //description+="";
        
        drawMultilineString(description,g2d, (this.getWidth()/2), this.getHeight()/2);
        //g2d.drawString(description, (this.getWidth()/2)-g2d.getFontMetrics().stringWidth(description)/2, this.getHeight()/3);
        //jLabel2.setText(description);
    }
    private void drawMultilineString(String str,Graphics2D g2d,int x, int y) {
        for (String line : str.split("\n")) {
            g2d.drawString(line, x-g2d.getFontMetrics().stringWidth(line)/2, (y+=g2d.getFontMetrics().getHeight())-g2d.getFontMetrics().getHeight()*str.split("\n").length/2);
        }
    }
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        drawCircle(graphics);
    }

    
    
    /*@Override
    protected void processMouseMotionEvent(MouseEvent e) {
        super.processMouseMotionEvent(e); //To change body of generated methods, choose Tools | Templates.
        mouse=e.getPoint();
        System.out.println(mouse);
    }*/

    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
