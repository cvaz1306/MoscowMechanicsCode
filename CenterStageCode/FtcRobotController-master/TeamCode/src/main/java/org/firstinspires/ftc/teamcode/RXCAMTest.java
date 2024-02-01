package org.firstinspires.ftc.teamcode;

import android.graphics.Bitmap;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.ftccommon.FtcEventLoopIdle;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.teamcode.ai.RobotXWithCamera;
import org.opencv.android.Utils;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;
@Config
@TeleOp(name = "RXCAMTest")
public class RXCAMTest extends RobotXWithCamera {

public static double PosMultiplier=25;
    @Override
    public void initialiseX() {

    }

    @Override
    public void startX() {

    }

    @Override
    public void LoopX(Bitmap x) {
        List<Point> circlePositions = new ArrayList<>();

        Mat mat = new Mat();
        Utils.bitmapToMat(x,mat);

        Mat grayImage = new Mat();
        Imgproc.cvtColor(mat, grayImage, Imgproc.COLOR_RGBA2GRAY);

        Mat edges = new Mat();
        Imgproc.Canny(grayImage, edges, 50, 150);

        List<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(edges, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        for (MatOfPoint contour : contours) {
            // Approximate the contour to a polygon
            MatOfPoint2f approxCurve = new MatOfPoint2f();
            Imgproc.approxPolyDP(new MatOfPoint2f(contour.toArray()), approxCurve, Imgproc.arcLength(new MatOfPoint2f(contour.toArray()), true) * 0.02, true);

            // Match the contour with a hexagon
            double matchScore = Imgproc.matchShapes(new MatOfPoint2f(getHexagon()), approxCurve, Imgproc.CONTOURS_MATCH_I1, 0.0);

            // You can adjust the threshold based on your specific requirements
            if (matchScore < 0.1) {
                // This contour is similar to a hexagon
                Rect boundingRect = Imgproc.boundingRect(new MatOfPoint2f(contour.toArray()));
                Point center = new Point(boundingRect.x + boundingRect.width / 2, boundingRect.y + boundingRect.height / 2);

                // Save the circle position
                circlePositions.add(center);

                // Draw a circle on the original image
                Imgproc.circle(mat, center, (int) Math.max(boundingRect.width, boundingRect.height) / 2, new Scalar(0, 255, 0), 2);
            }
            Utils.matToBitmap(mat,x);
            saveBitmap(x,"recent.jpg");
        }
        TelemetryPacket packet=new TelemetryPacket();

        if(!circlePositions.isEmpty()){
            double posx=circlePositions.get(0).x;
            double posy=circlePositions.get(0).y;
            packet.put("X", posx);
            packet.put("Y", posy);
            packet.fieldOverlay()
                    .fillCircle(posx*PosMultiplier,posy*PosMultiplier,25);
            RobotLog.a("Pixel Detected: ("+posx+", "+posy+")");
        }

        dashboard.sendTelemetryPacket(packet);
    }

    private Point[] getHexagon() {
        // Define the coordinates of a regular hexagon
        Point[] hexagon = new Point[6];
        for (int i = 0; i < 6; i++) {
            double angle = 2.0 * Math.PI / 6 * i;
            double x = 100.0 * Math.cos(angle); // Adjust the size as needed
            double y = 100.0 * Math.sin(angle);
            hexagon[i] = new Point(x, y);
        }
        return hexagon;
    }
}
