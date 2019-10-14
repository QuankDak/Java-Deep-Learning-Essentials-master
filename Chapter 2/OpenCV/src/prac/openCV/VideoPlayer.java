package prac.openCV;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

public class VideoPlayer {
    public VideoPlayer(){ }
        public static void main(String[] args){

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String filePath = "D:\\Movie\\Leon\\Leon.mp4";
        VideoCapture camera = new VideoCapture(filePath);
        // Video to Load
            JFrame jframe = new JFrame(("OpenCV TEST"));
            jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JLabel vidpanel = new JLabel();
            jframe.setContentPane(vidpanel);
            jframe.setVisible(true);
            jframe.doLayout();
            jframe.setBounds(0,0,600,480);

            Mat frame = new Mat();
            try{
                while(true){
                    if(camera.read(frame)){
                        ImageIcon image = new ImageIcon(Mat2BufferedImage(frame));
                        vidpanel.setIcon(image);
                        vidpanel.repaint();
                    }
                }
            }catch(Exception e ){
                e.printStackTrace();
            }


        }

        private static BufferedImage Mat2BufferedImage(Mat matrix)throws Exception{
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(".jpg",matrix,mob);
        byte ba[] = mob.toArray();

        BufferedImage bi = ImageIO.read(new ByteArrayInputStream(ba));
        return bi;
        }
}
