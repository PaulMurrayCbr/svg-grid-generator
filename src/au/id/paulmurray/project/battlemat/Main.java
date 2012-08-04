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
//	static final int W = 8;
//	static final int H = 10;
	static Random rnd = new Random();

	static final double ZZ = .01;

	static double z0(double a) {
		return ZZ + rnd.nextDouble() * (rnd.nextInt(10) == 0 ? ZZ : ZZ * 3);
	}

	static double z1(double a) {
		return .5 - ZZ + rnd.nextDouble() * (rnd.nextInt(10) == 0 ? ZZ : ZZ * 3) * 2;
	}

	static double z2(double a) {
		return 1 - ZZ - rnd.nextDouble() * (rnd.nextInt(10) == 0 ? ZZ : ZZ * 3);
	}

	public static void main(String[] args) throws Exception {

		PrintWriter w = new PrintWriter(new FileOutputStream(new File(System.getProperty("user.dir"), "a1Grid.svg")));

		w.println("<?xml version='1.0' encoding='UTF-8' ?><!DOCTYPE svg PUBLIC '-//W3C//DTD SVG 1.1//EN' 'http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd'>");

		w.println("<svg");
		w.println("   xmlns:svg='http://www.w3.org/2000/svg'");
		w.println("   xmlns='http://www.w3.org/2000/svg'");
		w.println("   xmlns:xlink='http://www.w3.org/1999/xlink'");
		w.println("   version='1.1'");
		w.println("   width='" + (W * 72 + 10) + "pt'");
		w.println("   height='" + (H * 72 + 10) + "pt'");
		w.println("  viewBox='-5 -5 " + (W * 72 + 10) + " " + (H * 72 + 10) + "'");
		w.println("  >	");

		// w.println("<defs>");
		//
		// for (int i = 0; i < 10; i++) {
		//
		// w.println("<radialGradient id='MyGradient" + i
		// + "' gradientUnits='userSpaceOnUse'");
		// w.println("cx='" + (.45 + rnd.nextDouble() * .1) + "' cy='"
		// + (.45 + rnd.nextDouble() * .1) + "' r='.75' ");
		// w.println("fx='" + (.33 + rnd.nextDouble() * .4 - .2) + "' fy='"
		// + (.33 + rnd.nextDouble() * .4 - .2)
		// + "' spreadMethod='repeat'>");
		// w.println("<stop offset='0%' stop-color='white' />");
		// w.println("<stop offset='33%' stop-color='white' />");
		// w.println("<stop offset='50%' stop-color='#f4f4f4' />");
		// w.println("<stop offset='100%' stop-color='#c0c0c0' />");
		// w.println("</radialGradient>");
		// }
		//
		// w.println("</defs>");

		// w.println("<rect x='0' y='0' width='"+W+"' height='"+H+"' fill='#e8e8e8'/>");

		w.println("<g stroke='#808080' stroke-width='.5pt'>");
		for (int x = 0; x < W; x++)
			for (int y = 0; y < H; y++) {
				w.print("<svg x='" + pp(x) + "' y='" + pp(y) + "'>");

				int xx2 = 1 + x % 2 * 2;
				int xx1 = 3 - x % 2 * 2;
				int yy2 = 1 + y % 2 * 2;
				int yy1 = 3 - y % 2 * 2;

				double x1 = z0(xx1);
				double y1 = z1(1);
				w.print("<path" //
						// + " fill='url(#MyGradient" + rnd.nextInt(10) + ")'"
						// //
						+ " fill='none'" //
						+ " d='M " + pp(x1) + " " + pp(y1)//
						+ " C " //
						+ pp(z0(xx1)) + " " + pp(z2(yy2)) + " " //
						+ pp(z0(xx1)) + " " + pp(z2(yy2)) + " " //
						+ pp(z1(1)) + " " + pp(z2(yy2))//
						+ " S " //
						+ pp(z2(xx2)) + " " + pp(z2(yy2)) + " " //
						+ pp(z2(xx2)) + " " + pp(z1(1))//
						+ " S " //
						+ pp(z2(xx2)) + " " + pp(z0(yy1)) + " " //
						+ pp(z1(1)) + " " + pp(z0(yy1)) //
						+ " S " //
						+ pp(z0(xx1)) + " " + pp(z0(yy1)) + " " //
						+ pp(x1) + " " + pp(y1)//
						+ " z'/>");
				w.println("</svg>");

			}
		w.println("</g>");
		w.println("<g stroke='black' stroke-width='1pt'>");
		for (int x = 0; x <= W; x += 2)
			for (int y = 0; y <= H; y += 2) {
				w.println("<line x1='" + (pp(x)-5) + "' y1='" + pp(y) + "' x2='" + (pp(x)+5) + "' y2='" + pp(y) + "' />");
				w.println("<line x1='" + pp(x) + "' y1='" + (pp(y)-5) + "' x2='" + pp(x) + "' y2='" + (pp(y)+5) + "' />");

			}
		w.println("</g>");
		w.println("</svg>");
		w.close();
	}

	private static int pp(double z0) {
		return (int) (z0 * 72 + .49);
	}

}
