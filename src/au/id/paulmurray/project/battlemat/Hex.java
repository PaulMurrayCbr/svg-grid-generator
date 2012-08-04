package au.id.paulmurray.project.battlemat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Hex {
	// 0 33.11 46.81
	// 1 23.39 33.11
	// 2 16.54 23.39
	// 3 11.69 16.54
	// 4 8.27 11.69

//	static final int W = 32;
//	static final int H = 22;
	static final int W = 8;
	static final int H = 11;
	static final double SQ = Math.sqrt(3) / 2.0;

	static PrintWriter w;

	public static void main(String[] av) throws Exception {

		w = new PrintWriter(new FileOutputStream(new File(System.getProperty("user.dir"), "a4Hex.svg")));

		w.println("<?xml version='1.0' encoding='UTF-8' ?><!DOCTYPE svg PUBLIC '-//W3C//DTD SVG 1.1//EN' 'http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd'>");

		w.println("<svg");
		w.println("   xmlns:svg='http://www.w3.org/2000/svg'");
		w.println("   xmlns='http://www.w3.org/2000/svg'");
		w.println("   xmlns:xlink='http://www.w3.org/1999/xlink'");
		w.println("   version='1.1'");
		w.println("   width='" + (W) + "in'");
		w.println("   height='" + (H) + "in'");
		w.println("  viewBox='0 0 " + W + " " + H + "'");
		w.println("  >	");

		for (int yi = -1; SQ * 2 * yi <= H + 1; yi++) {
			double y = SQ * 2 * (yi - .5);
			for (double x = -1; x <= W + 1; x += 1) {
				hex(x, y);
				hex(x + .5, y + SQ);
			}
		}

		w.println("</svg>");
		w.close();

	}

	static final double rad = 1 / Math.sqrt(3);


	static void hex(double cx, double cy) {

		// w.println("<circle cx='" + cx + "' cy='" + cy +
		// "' r='.5' fill='none' stroke='black' stroke-width='"
		// + (1.0 / 72.0) + "'/>");

		if (cx < rad || cx > (W - rad) || cy < rad || cy > (H - rad))
			return;

		w.print("<polygon fill='none' stroke='black' stroke-width='" + (1.0 / 72.0) + "' points='");

		for (int th = 1; th < 12; th += 2) {
			double t = th * Math.PI * 2 / 12;
			w.print((cx + rad * Math.cos(t)) + "," + (cy + rad * Math.sin(t)) + " ");
		}
		w.println("'/>");
	}

}
