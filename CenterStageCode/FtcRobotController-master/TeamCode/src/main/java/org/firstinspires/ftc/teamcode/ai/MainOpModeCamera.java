package org.firstinspires.ftc.teamcode.ai;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.os.Handler;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.android.util.Size;
import org.firstinspires.ftc.robotcore.external.function.Consumer;
import org.firstinspires.ftc.robotcore.external.function.Continuation;
import org.firstinspires.ftc.robotcore.external.hardware.camera.Camera;
import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraCaptureRequest;
import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraCaptureSequenceId;
import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraCaptureSession;
import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraCharacteristics;
import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraException;
import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraFrame;
import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraManager;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.internal.collections.EvictingBlockingQueue;
import org.firstinspires.ftc.robotcore.internal.network.CallbackLooper;
import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.firstinspires.ftc.robotcore.internal.system.ContinuationSynchronizer;
import org.firstinspires.ftc.robotcore.internal.system.Deadline;
import org.firstinspires.ftc.teamcode.CameraConfigGreen;
import org.firstinspires.ftc.teamcode.CameraConfigPurple;
import org.firstinspires.ftc.teamcode.CameraConfigWhite;
import org.firstinspires.ftc.teamcode.CameraConfigYellow;
import org.firstinspires.ftc.teamcode.ColorConverter;
import org.firstinspires.ftc.teamcode.MainConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * This OpMode illustrates how to open a webcam and retrieve images from it. It requires a configuration
 * containing a webcam with the default name ("Webcam 1"). When the opmode runs, pressing the 'A' button
 * will cause a frame from the camera to be written to a file on the device, which can then be retrieved
 * by various means (e.g.: Device File Explorer in Android Studio; plugging the device into a PC and
 * using Media Transfer; ADB; etc)
 */
@TeleOp(name="Concept: Webcam", group ="Concept")
public class MainOpModeCamera extends LinearOpMode {

    //----------------------------------------------------------------------------------------------
    // State
    //----------------------------------------------------------------------------------------------
    FtcDashboard dashboard = FtcDashboard.getInstance();
    private static final String TAG = "Webcam Sample";

    /** How long we are to wait to be granted permission to use the camera before giving up. Here,
     * we wait indefinitely */
    private static final int secondsPermissionTimeout = Integer.MAX_VALUE;

    /** State regarding our interaction with the camera */
    private CameraManager cameraManager;
    private WebcamName cameraName;
    private Camera camera;
    private CameraCaptureSession cameraCaptureSession;

    /** The queue into which all frames from the camera are placed as they become available.
     * Frames which are not processed by the OpMode are automatically discarded. */
    private EvictingBlockingQueue<Bitmap> frameQueue;

    /** State regarding where and how to save frames when the 'A' button is pressed. */
    private int captureCounter = 0;
    private File captureDirectory = AppUtil.ROBOT_DATA_DIR;

    /** A utility object that indicates where the asynchronous callbacks from the camera
     * infrastructure are to run. In this OpMode, that's all hidden from you (but see {@link #startCamera}
     * if you're curious): no knowledge of multi-threading is needed here. */
    private Handler callbackHandler;

    //----------------------------------------------------------------------------------------------
    // Main OpMode entry
    //----------------------------------------------------------------------------------------------

    @Override public void runOpMode() {

        callbackHandler = CallbackLooper.getDefault().getHandler();

        cameraManager = ClassFactory.getInstance().getCameraManager();
        cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        initializeFrameQueue(2);
        AppUtil.getInstance().ensureDirectoryExists(captureDirectory);

        try {
            openCamera();
            if (camera == null) return;

            startCamera();
            if (cameraCaptureSession == null) return;

            telemetry.addData(">", "Press Play to start");
            telemetry.update();
            waitForStart();
            telemetry.clear();
            telemetry.addData(">", "Started...Press 'A' to capture frame");

            boolean buttonPressSeen = false;
            boolean captureWhenAvailable = false;
            while (opModeIsActive()) {

                boolean buttonIsPressed = gamepad1.a;//true;//
                if (buttonIsPressed && !buttonPressSeen) {//){
                    captureWhenAvailable = true;
                }
                buttonPressSeen = buttonIsPressed;

                if (captureWhenAvailable) {
                    Bitmap bmp = frameQueue.poll();
                    if (bmp != null) {
                        captureWhenAvailable = false;
                        onNewFrame(bmp);
                    }
                }

                telemetry.update();
            }
        } finally {
            closeCamera();
        }
    }




    /** Do something with the frame */
    private void onNewFrame(Bitmap frame) {

        int red = 0;
        int green = 0;
        int blue = 0;
        float cyan=0, yellow=0, magenta=0, key=0;
        int alpha = 0;
        int posX=0;
        int posY=0;
        float a=0;
        float b=0;
        float c=0;
        int jkldfghjkl=0;
        a= ColorConverter.rgbToHsv(frame.getPixel(frame.getWidth()/2, frame.getHeight()/2))[0];
        b=ColorConverter.rgbToHsv(frame.getPixel(frame.getWidth()/2, frame.getHeight()/2))[1];
        c=ColorConverter.rgbToHsv(frame.getPixel(frame.getWidth()/2, frame.getHeight()/2))[0];

        int ApixelColor = frame.getPixel(frame.getWidth()/2, frame.getHeight()/2);
        red = Color.red(ApixelColor);
        green = Color.green(ApixelColor);
        blue = Color.blue(ApixelColor);
        alpha = Color.alpha(ApixelColor);
        float[] cmyk=ColorConverter.rgbToCmyk(red, green, blue);
        cyan=cmyk[0];
        magenta=cmyk[1];
        yellow=cmyk[2];
        key=cmyk[3];
        telemetry.addData("Cyan", cyan);
        telemetry.addData("Magenta", magenta);
        telemetry.addData("Yellow", yellow);
        telemetry.addData("Key", key);

        for(int i1=0; i1<frame.getWidth(); i1=i1+10){
            for(int i2=0; i2<frame.getHeight(); i2=i2+10){
                int pixelColor = frame.getPixel(i1, i2);
                red = Color.red(pixelColor);
                green = Color.green(pixelColor);
                blue = Color.blue(pixelColor);
                alpha = Color.alpha(pixelColor);
                float[] color=ColorConverter.rgbToHsv(frame.getPixel(i1, i2));
                float[] cmykA=ColorConverter.rgbToCmyk(red, green, blue);
                cyan=cmykA[0];
                magenta=cmykA[1];
                yellow=cmykA[2];
                key=cmykA[3];

                float hue=color[0];
                float saturation=color[1];
                float value=color[2];
                if(green + CameraConfigGreen.minGreen >red&&green+ CameraConfigGreen.minGreen>blue){//(red<100&&green>100&&blue<100)||(red<150&&green>150&&blue<150)){
                    posX= posX==0 ? i1:(i1+posX)/2;
                    posY= posY==0 ? i2:(i2+posY)/2;
                    frame.setPixel(Math.max(0,i1), Math.max(0, i2), Color.GREEN);
                    jkldfghjkl++;
                }
                else if(yellow + CameraConfigYellow.minYellow >=Math.max(magenta, cyan)&&value>.5&&saturation>.4){//(red<100&&green>100&&blue<100)||(red<150&&green>150&&blue<150)){.
                    posX= posX==0 ? i1:(i1+posX)/2;
                    posY= posY==0 ? i2:(i2+posY)/2;
                    frame.setPixel(Math.max(0,i1), Math.max(0, i2), Color.YELLOW);
                    jkldfghjkl++;
                }
                else if(magenta+ CameraConfigPurple.minMagenta >=Math.max(yellow, cyan)&&saturation>CameraConfigPurple.minSaturation){//(red<100&&green>100&&blue<100)||(red<150&&green>150&&blue<150)){.
                    posX= posX==0 ? i1:(i1+posX)/2;
                    posY= posY==0 ? i2:(i2+posY)/2;
                    frame.setPixel(Math.max(0,i1), Math.max(0, i2), Color.MAGENTA);
                    jkldfghjkl++;
                }
                else if(saturation<CameraConfigWhite.maxSaturation &&value>.8){
                    posX= posX==0 ? i1:(i1+posX)/2;
                    posY= posY==0 ? i2:(i2+posY)/2;
                    frame.setPixel(Math.max(0,i1), Math.max(0, i2), Color.WHITE);
                    jkldfghjkl++;
                }

//                if((hue>=230 || hue<=10)&&saturation>.1f&&value<.76f*256){//purple
//                    posX= posX==0 ? i1:(i1+posX)/2;
//                    posY= posY==0 ? i2:(i2+posY)/2;
//                    frame.setPixel(Math.max(0,i1), Math.max(0, i2), Color.CYAN);++
//                    jkldfghjkl++;
//                }
//                else if((hue>=20 && hue<=70)/*&&saturation>.1f*/&&value>.3f*256){//GREEN?
//                    posX= posX==0 ? i1:(i1+posX)/2;
//                    posY= posY==0 ? i2:(i2+posY)/2;
//                    frame.setPixel(Math.max(0,i1), Math.max(0, i2), Color.GREEN);
//                    jkldfghjkl++;
//                }
//                else if((saturation<.9f&&value<.9f*256)){//purple?
//                    posX= posX==0 ? i1:(i1+posX)/2;
//                    posY= posY==0 ? i2:(i2+posY)/2;
//                    frame.setPixel(Math.max(0,i1), Math.max(0, i2), Color.RED);
//                    jkldfghjkl++;
//                }

            }
        }

        telemetry.addData("Hue", a);
        telemetry.addData("Saturation", b);
        telemetry.addData("Value", c);

        telemetry.addData("POSITION", "("+posX+", "+posY+")");
        //telemetry.addData("jkndrhkl", jkldfghjkl);
        frame.setPixel(posX, posY, Color.BLUE);
        if( MainConfig.DoWeSave) saveBitmap(frame);
        frame.recycle(); // not strictly necessary, but helpful
        TelemetryPacket packet = new TelemetryPacket();
        packet.put("x", 3.7);
        packet.put("Position X", posX);
        packet.put("Position Y", posY);
        packet.fieldOverlay()
                .setFill("blue")
                .fillRect(-20, -20, 40, 40);
        packet.addLine(String.format(Locale.getDefault(), "Took %d images;", captureCounter+1));
        dashboard.sendTelemetryPacket(packet);
    }

    //----------------------------------------------------------------------------------------------
    // Camera operations
    //----------------------------------------------------------------------------------------------

    private void initializeFrameQueue(int capacity) {
        /** The frame queue will automatically throw away bitmap frames if they are not processed
         * quickly by the OpMode. This avoids a buildup of frames in memory */
        frameQueue = new EvictingBlockingQueue<Bitmap>(new ArrayBlockingQueue<Bitmap>(capacity));
        frameQueue.setEvictAction(new Consumer<Bitmap>() {
            @Override public void accept(Bitmap frame) {
                // RobotLog.ii(TAG, "frame recycled w/o processing");

                frame.recycle(); // not strictly necessary, but helpful
            }
        });
    }

    private void openCamera() {
        if (camera != null) return; // be idempotent

        Deadline deadline = new Deadline(secondsPermissionTimeout, TimeUnit.SECONDS);
        camera = cameraManager.requestPermissionAndOpenCamera(deadline, cameraName, null);
        if (camera == null) {
            error("camera not found or permission to use not granted: %s", cameraName);
        }
    }

    private void startCamera() {
        if (cameraCaptureSession != null) return; // be idempotent

        /** YUY2 is supported by all Webcams, per the USB Webcam standard: See "USB Device Class Definition
         * for Video Devices: Uncompressed Payload, Table 2-1". Further, often this is the *only*
         * image format supported by a camera */
        final int imageFormat = ImageFormat.YUY2;

        /** Verify that the image is supported, and fetch size and desired frame rate if so */
        CameraCharacteristics cameraCharacteristics = cameraName.getCameraCharacteristics();
        if (!contains(cameraCharacteristics.getAndroidFormats(), imageFormat)) {
            error("image format not supported");
            return;
        }
        final Size size = cameraCharacteristics.getDefaultSize(imageFormat);
        final int fps = cameraCharacteristics.getMaxFramesPerSecond(imageFormat, size);

        /** Some of the logic below runs asynchronously on other threads. Use of the synchronizer
         * here allows us to wait in this method until all that asynchrony completes before returning. */
        final ContinuationSynchronizer<CameraCaptureSession> synchronizer = new ContinuationSynchronizer<>();
        try {
            /** Create a session in which requests to capture frames can be made */
            camera.createCaptureSession(Continuation.create(callbackHandler, new CameraCaptureSession.StateCallbackDefault() {
                @Override public void onConfigured(@NonNull CameraCaptureSession session) {
                    try {
                        /** The session is ready to go. Start requesting frames */
                        final CameraCaptureRequest captureRequest = camera.createCaptureRequest(imageFormat, size, fps);
                        session.startCapture(captureRequest,
                                new CameraCaptureSession.CaptureCallback() {
                                    @Override public void onNewFrame(@NonNull CameraCaptureSession session, @NonNull CameraCaptureRequest request, @NonNull CameraFrame cameraFrame) {
                                        /** A new frame is available. The frame data has <em>not</em> been copied for us, and we can only access it
                                         * for the duration of the callback. So we copy here manually. */
                                        Bitmap bmp = captureRequest.createEmptyBitmap();
                                        cameraFrame.copyToBitmap(bmp);
                                        frameQueue.offer(bmp);
                                    }
                                },
                                Continuation.create(callbackHandler, new CameraCaptureSession.StatusCallback() {
                                    @Override public void onCaptureSequenceCompleted(@NonNull CameraCaptureSession session, CameraCaptureSequenceId cameraCaptureSequenceId, long lastFrameNumber) {
                                        RobotLog.ii(TAG, "capture sequence %s reports completed: lastFrame=%d", cameraCaptureSequenceId, lastFrameNumber);
                                    }
                                })
                        );
                        synchronizer.finish(session);
                    } catch (CameraException|RuntimeException e) {
                        RobotLog.ee(TAG, e, "exception starting capture");
                        error("exception starting capture");
                        session.close();
                        synchronizer.finish(null);
                    }
                }
            }));
        } catch (CameraException|RuntimeException e) {
            RobotLog.ee(TAG, e, "exception starting camera");
            error("exception starting camera");
            synchronizer.finish(null);
        }

        /** Wait fd to complete */
        try {
            synchronizer.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        /** Retrieve the created session. This will be null on error. */
        cameraCaptureSession = synchronizer.getValue();
    }

    private void stopCamera() {
        if (cameraCaptureSession != null) {
            cameraCaptureSession.stopCapture();
            cameraCaptureSession.close();
            cameraCaptureSession = null;
        }
    }

    private void closeCamera() {
        stopCamera();
        if (camera != null) {
            camera.close();
            camera = null;
        }
    }

    //----------------------------------------------------------------------------------------------
    // Utilities
    //----------------------------------------------------------------------------------------------

    private void error(String msg) {
        telemetry.log().add(msg);
        telemetry.update();
    }
    private void error(String format, Object...args) {
        telemetry.log().add(format, args);
        telemetry.update();
    }

    private boolean contains(int[] array, int value) {
        for (int i : array) {
            if (i == value) return true;
        }
        return false;
    }

    private void saveBitmap(Bitmap bitmap) {
        File file = new File(captureDirectory, String.format(Locale.getDefault(), "webcam-frame-%d.jpg", captureCounter++));
        try {
            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                telemetry.log().add("captured %s", file.getName());
            }
        } catch (IOException e) {
            RobotLog.ee(TAG, e, "exception in saveBitmap()");
            error("exception saving %s", file.getName());
        }
    }
}