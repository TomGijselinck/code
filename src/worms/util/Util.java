package worms.util;

import java.util.Random;

public class Util {
	public static final double DEFAULT_EPSILON = 1e-4;

	/**
	 * Returns true if x == y (with precision DEFAULT_EPSILON)
	 */
	public static boolean fuzzyEquals(double x, double y) {
		return fuzzyEquals(x, y, DEFAULT_EPSILON);
	}

	/**
	 * Returns true if x == y (with precision eps)
	 */
	public static boolean fuzzyEquals(double x, double y, double eps) {
		if (Double.isNaN(x) || Double.isNaN(y))
			return false;
		return Math.abs(x - y) <= eps
				|| Double.valueOf(x).equals(Double.valueOf(y));
	}

	/**
	 * Returns true if x <= y (with precision DEFAULT_EPSILON)
	 */
	public static boolean fuzzyLessThanOrEqualTo(double x, double y) {
		return fuzzyLessThanOrEqualTo(x, y, DEFAULT_EPSILON);
	}
	
	/**
	 * Returns true if a <= x <= b (with precision DEFAULT_EPSILON)
	 */
	public static boolean fuzzyBetween(double a, double b, double x) {
		return fuzzyBetween(a, b, x, DEFAULT_EPSILON);
	}
	
	/**
	 * Returns true if a <= x <= b (with precision eps)
	 */
	public static boolean fuzzyBetween(double a, double b, double x, double eps) {
		return fuzzyLessThanOrEqualTo(a, x, eps) && fuzzyLessThanOrEqualTo(x, b, eps);
	}

	/**
	 * Returns true if x <= y (with precision eps)
	 */
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

	/**
	 * Returns true if x >= y (with precision DEFAULT_EPSILON)
	 */
	public static boolean fuzzyGreaterThanOrEqualTo(double x, double y) {
		return fuzzyGreaterThanOrEqualTo(x, y, DEFAULT_EPSILON);
	}

	/**
	 * Returns true if x >= y (with precision eps)
	 */
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

	/**
	 * Returns the absolute error |expected - actual|
	 */
	public static double absoluteError(double expected, double actual) {
		return Math.abs(expected - actual);
	}

	/**
	 * Returns the relative error |expected - actual|/|expected|
	 */
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