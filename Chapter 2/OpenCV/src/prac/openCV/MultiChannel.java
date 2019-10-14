package prac.openCV;

import org.opencv.core.*;
import org.opencv.imgcodecs.*;
import org.opencv.highgui.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import  javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.nio.Buffer;
import java.nio.file.Paths;
import java.awt.image.BufferedImage;
import java.nio.file.Paths;
import java.util.Arrays;

public class MultiChannel {
    public static void main(String[] args){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String filepath = "C:\\Users\\djhoo\\Videos\\Captures\\TEKKEN 7  2019-08-12 22-25-31.mp4";
        if(!Paths.get(filepath).toFile().exists()){
            System.out.println("File" +filepath+ "does not exists");
            return;
        }
        VideoCapture camera = new VideoCapture(filepath);

        if (!camera.isOpened()){
            System.out.println("Error! Camaera cant be opened");
            return;
        }
        Mat frame = new Mat();

        while (true) {
            if(camera.read(frame)){
                System.out.println(("Frame obtained"));
                System.out.println("Captured Frame Width" + frame.width() + "Height" + frame.height());
                Imgcodecs.imwrite("camera.jpg",frame);
                System.out.println("ok");
                break;
            }
        }
    // Entering
        BufferedImage bufferedImage = matToBufferedImage(frame);
        showWindow(bufferedImage);
        camera.release();
        System.out.println(frame);
       // System.out.println(Arrays.toString(frame));
    }
    private  static  BufferedImage matToBufferedImage(Mat frame){
        int type = 0;
        if(frame.channels()==1){
            type = BufferedImage.TYPE_BYTE_GRAY;
        } else if (frame.channels() == 3) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        BufferedImage image = new BufferedImage(frame.width(), frame.height(), type);
        WritableRaster raster = image.getRaster();
        DataBufferByte dataBufferByte = (DataBufferByte) raster.getDataBuffer();
        byte[] data = dataBufferByte.getData();
        frame.get(0,0,data);

        return image;
    }

    private static void  showWindow(BufferedImage img){
        JFrame frame = new JFrame();
        frame.getContentPane().add(new JLabel((new ImageIcon(img))));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(img.getWidth(), img.getHeight() + 30);
        frame.setTitle("Image captured");
        frame.setVisible(true);
    }

}
