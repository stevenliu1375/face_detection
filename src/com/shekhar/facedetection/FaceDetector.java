package com.shekhar.facedetection;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

public class FaceDetector {

	public static void main(String[] args) {

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);   // 引用dll库的路径设置(system.loadLibrary()调用Dll路径问题)
		System.out.println("\nRunning FaceDetector");

		CascadeClassifier faceDetector = new CascadeClassifier(
				FaceDetector.class.getResource("haarcascade_frontalface_alt.xml").getPath());
		Mat image = Highgui.imread(FaceDetector.class.getResource("DSC02552.JPG").getPath());

		MatOfRect faceDetections = new MatOfRect();
		faceDetector.detectMultiScale(image, faceDetections);

		System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

		for (Rect rect : faceDetections.toArray()) {
			Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
					new Scalar(0, 255, 0));
		}

		String filename = "ouput.png";
		System.out.println(String.format("Writing %s", filename));
		Highgui.imwrite(filename, image);
	}
}