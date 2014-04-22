package worms.util;

import java.util.Random;

public class Util {
	public static final double DEFAULT_EPSILON = 1e-4;

	public static boolean fuzzyEquals(double x, double y) {
		return fuzzyEquals(x, y, DEFAULT_EPSILON);
	}

	public static boolean fuzzyEquals(double x, double y, double eps) {
		if (Double.isNaN(x) || Double.isNaN(y))
			return false;
		return Math.abs(x - y) <= eps
				|| Double.valueOf(x).equals(Double.valueOf(y));
	}

	public static boolean fuzzyLessThanOrEqualTo(double x, double y) {
		return fuzzyLessThanOrEqualTo(x, y, DEFAULT_EPSILON);
	}

	public static boolean fuzzyLessThanOrEqualTo(double x, double y, double eps) {
		if (fuzzyEquals(x, y, eps)) {
			return true;
		} else {
			return Double.compare(x, y) < 0;
		}
	}
	
	public static boolean fuzzyLessThan(double x, double y) {
		return fuzzyLessThan(x, y, DEFAULT_EPSILON);
	}
	
	public static boolean fuzzyLessThan(double x, double y, double eps) {
		if (fuzzyEquals(x, y, eps)) {
			return false;
		} else {
			return Double.compare(x, y) < 0;
		}
	}

	public static boolean fuzzyGreaterThanOrEqualTo(double x, double y) {
		return fuzzyGreaterThanOrEqualTo(x, y, DEFAULT_EPSILON);
	}


	public static boolean fuzzyGreaterThanOrEqualTo(double x, double y,
			double eps) {
		if (fuzzyEquals(x, y, eps)) {
			return true;
		} else {
			return Double.compare(x, y) > 0;
		}
	}
	
	public static boolean fuzzyGreaterThan(double x, double y) {
		return fuzzyGreaterThan(x, y, DEFAULT_EPSILON);
	}
	
	public static boolean fuzzyGreaterThan(double x, double y,
			double eps) {
		if (fuzzyEquals(x, y, eps)) {
			return false;
		} else {
			return Double.compare(x, y) > 0;
		}
	}
	
	public static double absoluteError(double expected, double actual) {
		return Math.abs(expected - actual);
	}

	public static double relativeError(double expected, double actual) {
		return absoluteError(expected, actual) / Math.abs(expected);
	}
	
	public static int randomInt(int min, int max) {
		Random random = new Random();
		int randomNumber = random.nextInt((max - min) + 1) + min;
		return randomNumber;
	}
	
	public static double randomDouble(double min, double max) {
		Random random = new Random();
		double randomDouble = random.nextDouble() * (max - min) + min;
		return randomDouble;
	}

}