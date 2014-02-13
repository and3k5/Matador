
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package matador;

//~--- JDK imports ------------------------------------------------------------

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 *
 * @author and3k5
 */
public class EasterEggSound {
    private boolean isPlaying = false;
    private Thread thread = null;
    private int bitNumber = 0;
    public byte compose8bit(int t) {
        byte rtn = (byte)0;
        switch (bitNumber) {
            case 0:
                rtn = (byte)(((t >> 5 & t) - (t >> 5) + (t >> 5 & t)) + (t * ((t >> 14) & 14))); 
                break;
            case 1:
                rtn = (byte)((t>>5)|(t<<4)|((t&1023)^1981)|((t-67)>>4));
                break;
            case 2:
                rtn = (byte)(((t*(t>>8|t>>9)&46&t>>8))^(t&t>>13|t>>6));
                break;
            case 3:
                rtn = (byte)((t>>(t&7))|(t<<(t&42))|(t>>7)|(t<<5));
                break;
            case 4:
                rtn = (byte)((t>>6|t<<1)+(t>>5|t<<3|t>>3)|t>>2|t<<1);
                break;
            case 5:
                rtn = (byte)((t>>6|t|t>>(t>>16))*10+((t>>11)&7));
                break;
            case 6:
                rtn = (byte)((t/8)>>(t>>9)*t/((t>>14&3)+4));
                break;
            case 7:
                rtn = (byte)(t*(42&t>>10));
                break;
            case 8:
                rtn = (byte)((t*t*t/t)%123);
                break;
            default:
                rtn = (byte)((t*t*t*t)%123);
                break;
        }
        
        return rtn;
    }
    public void startSound() {
        if (!isPlaying) {
            isPlaying = true;
            bitNumber=new Random().nextInt(9);
            thread    = new Thread() {
                AudioFormat af;
                SourceDataLine sdl;
                @Override
                public void run() {
                    super.run();    // To change body of generated methods, choose Tools | Templates.

                    try {

                        // TODO code application logic here
                        // byte[] buf = new byte[ 1 ];
                        af  = new AudioFormat((float) 8000, 8, 1, true, false);
                        sdl = AudioSystem.getSourceDataLine(af);

                        sdl = AudioSystem.getSourceDataLine(af);
                        System.out.print("1");
                        sdl.open(af);
                        System.out.print("2");
                        sdl.start();
                        System.out.print("3");

                        byte[] buffer = new byte[1];
                        int t = 0;
                        while (isPlaying) {
                            

                            for (int i = 0; i < 100 * (float) 44100 / 1000; i++) {

                                // buffer[ 0 ] = (byte )(new Random().nextInt(255));
                                t=(t+1)%Integer.MAX_VALUE;
                                buffer[0] = compose8bit(t);
                                sdl.write(buffer, 0, 1);
                                if (!isPlaying) break;
                            }
                        }

                        System.out.print("4");
                        sdl.drain();
                        System.out.print("5");
                        sdl.stop();
                        System.out.print("6");
                        sdl.close();
                        System.out.print("7");
                    } catch (LineUnavailableException ex) {
                        Logger.getLogger(GameBoard.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                @Override
                public void interrupt() {
                    super.interrupt();    // To change body of generated methods, choose Tools | Templates.
                }
            };
            thread.start();
        }
    }
    public void stopSound() {
        if (isPlaying) {
            isPlaying=false;
            if (thread.isAlive()) {
                thread.interrupt();
                
            }
            
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
