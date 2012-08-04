package au.id.paulmurray.project.battlemat;

import java.io.*;
import java.util.Random;

public class Main {
	// 0 33.11 46.81
	// 1 23.39 33.11
	// 2 16.54 23.39
	// 3 11.69 16.54
	// 4 8.27 11.69

	
	static final int W = 32;
	static final int H = 22;
	static Random rnd = new Random();


	static final double ZZ = .01;

	static double z0(double a) {
		return ZZ + rnd.nextDouble() * (rnd.nextInt(10)==0?ZZ:ZZ*3);
	}

	static double z1(double a) {
		return .5 - ZZ + rnd.nextDouble() *  (rnd.nextInt(10)==0?ZZ:ZZ*3) * 2;
	}

	static double z2(double a) {
		return 1 - ZZ - rnd.nextDouble() *  (rnd.nextInt(10)==0?ZZ:ZZ*3);
	}

	public static void main(String[] args) throws Exception {

		PrintWriter w = new PrintWriter(new FileOutputStream(new File(System
				.getProperty("user.dir"), "a1Grid.svg")));

		w
				.println("<?xml version='1.0' encoding='UTF-8' ?><!DOCTYPE svg PUBLIC '-//W3C//DTD SVG 1.1//EN' 'http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd'>");

		w.println("<svg");
		w.println("   xmlns:svg='http://www.w3.org/2000/svg'");
		w.println("   xmlns='http://www.w3.org/2000/svg'");
		w.println("   xmlns:xlink='http://www.w3.org/1999/xlink'");
		w.println("   version='1.1'");
		w.println("   width='" + (W+.1) + "in'");
		w.println("   height='" + (H+.1) + "in'");
		w.println("  viewBox='-.05 -.05 " + (W+.1) + " " + (H+.1) + "'");
		w.println("  >	");

//		 w.println("<defs>");
//		
//		 for (int i = 0; i < 10; i++) {
//		
//		 w.println("<radialGradient id='MyGradient" + i
//		 + "' gradientUnits='userSpaceOnUse'");
//		 w.println("cx='" + (.45 + rnd.nextDouble() * .1) + "' cy='"
//		 + (.45 + rnd.nextDouble() * .1) + "' r='.75' ");
//		 w.println("fx='" + (.33 + rnd.nextDouble() * .4 - .2) + "' fy='"
//		 + (.33 + rnd.nextDouble() * .4 - .2)
//		 + "' spreadMethod='repeat'>");
//		 w.println("<stop offset='0%' stop-color='white' />");
//		 w.println("<stop offset='33%' stop-color='white' />");
//		 w.println("<stop offset='50%' stop-color='#f4f4f4' />");
//		 w.println("<stop offset='100%' stop-color='#c0c0c0' />");
//		 w.println("</radialGradient>");
//		 }
//		
//		 w.println("</defs>");

//		 w.println("<rect x='0' y='0' width='"+W+"' height='"+H+"' fill='#e8e8e8'/>");

		w.println("<g stroke='#808080' stroke-width='.007'>");
		for (int x = 0; x < W; x++)
			for (int y = 0; y < H; y++) {
				w.print("<svg x='" + x + "' y='" + y + "'>");

				int xx2 = 1 + x % 2 * 2;
				int xx1 = 3 - x % 2 * 2;
				int yy2 = 1 + y % 2 * 2;
				int yy1 = 3 - y % 2 * 2;

				double x1 = z0(xx1);
				double y1 = z1(1);
				w.print("<path" //
					// + " fill='url(#MyGradient" + rnd.nextInt(10) + ")'" //
						+ " fill='none'" //
						+ " d='M " + x1 + " " + y1//
						+ " C " //
						+ z0(xx1) + " " + z2(yy2) + " " //
						+ z0(xx1) + " " + z2(yy2) + " " //
						+ z1(1) + " " + z2(yy2)//
						+ " S " //
						+ z2(xx2) + " " + z2(yy2) + " " //
						+ z2(xx2) + " " + z1(1)//
						+ " S " //
						+ z2(xx2) + " " + z0(yy1) + " " //
						+ z1(1) + " " + z0(yy1) //
						+ " S " //
						+ z0(xx1) + " " + z0(yy1) + " " //
						+ x1 + " " + y1//
						+ " z'/>");
				w.println("</svg>");

			}
		w.println("</g>");
		w.println("<g stroke='black' stroke-width='.014'>");
		for (int x = 0; x <= W; x += 2)
			for (int y = 0; y <= H; y += 2) {
				w.println("<line x1='" + (x - .05) + "' y1='" + (y) + "' x2='"
						+ (x + .05) + "' y2='" + (y) + "' />");
				w.println("<line x1='" + (x) + "' y1='" + (y - .05) + "' x2='"
						+ (x) + "' y2='" + (y + .05) + "' />");

			}
		w.println("</g>");
		w.println("</svg>");
		w.close();
	}

}
