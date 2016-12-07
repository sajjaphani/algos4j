package org.algos4j.util;

import java.util.Arrays;

/**
 * Some math utility methods.
 * 
 * @author psajja
 * 
 */
public class MathUtil {

	/**
	 * Non-instantiable
	 */
	private MathUtil() {
	}

	/**
	 * Computes a^n. It takes O(log n) multiplications.
	 * 
	 * @param a
	 * 		given a
	 * @param n
	 * 		given n
	 * 
	 * @return
	 * 		computed result
	 */
	public static int power(int a, int n) {
		if (n == 0)
			return 1;

		int x = power(a, (int) Math.floor(n / 2));
	
		if (n % 2 == 0)
			return x * x;

		return a * x * x;
	}
	
	/**
	 * Computes the greatest common divisor of given numbers.
	 * 
	 * @param a
	 * 		first number
	 * @param b
	 * 		second number
	 * 
	 * @return
	 * 		GCD of the given numbers
	 */
	public static int gcd(int a, int b) {
		if (b == 0)
			return a;
		
		return gcd(b, a % b);
	}

	/**
	 * Computes the least common mean of the given numbers.
	 * 
	 * <p>
	 * 	lcm(a,b) * gcd(a,b) = a*b
	 * </p>
	 * 
	 * @param a
	 * 		first number
	 * @param b
	 * 		second number
	 * 
	 * @return
	 * 		LCM of given numbers
	 */
	public static int lcm(int a, int b) {
		return (a * b) / gcd(a, b);
	}
	
	/**
	 * This method will check whether the given number is prime or not.
	 * 
	 * @see <a href="https://primes.utm.edu/notes/faq/negative_primes.html">Negative primes</a> for more details.
	 * 
	 * @param number
	 * 		given number
	 * 
	 * @return
	 * 		true if it is prime, false otherwise
	 */
	public static boolean isPrime(int number) {
	
		int sqrt = (int) Math.sqrt(number);

		for (int i = 2; i <= sqrt; i++) {
			if (number % i == 0) 
				return false;
		}
		
		return true;
	}
	
	/**
	 * Given an integer, prints all the prime numbers up to that number.
	 * Time O(n log log n), Space O(n)
	 * 
	 * @see <a href="https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes">Sieve of Eratosthenes</a> for more details.
	 * 
	 * @param n
	 * 		given number
	 */
	public static void sieveOfEratosthenes(int n) {
		boolean[] prime = new boolean[n + 1];
	
		Arrays.fill(prime, true);
		
		int sqrt = (int) Math.sqrt(n);

		for (int p = 2; p * p <= sqrt; p++) {
			if (prime[p] == true) {
				for (int i = p * 2; i <= n; i += p)
					prime[i] = false;
			}
		}

		for (int p = 2; p <= n; p++)
			if (prime[p])
				System.out.print(p + " ");
	}
	
	/**
	 * Computes the nth fibonacci number.
	 * 
	 * @param n
	 * 		the nth
	 * 
	 * @return
	 * 		nth fibonacci number
	 */
	public static int fibonacci(int n) {
		if (n <= 0)
			return 0;
		else if (n == 1)
			return 1;
		
		return fibonacci(n - 1) + fibonacci(n - 2);
	}
	
	/**
	 * Given a number, prints fibonacci numbers form 0 to n. 
	 * It memorizes already computed numbers. 
	 * Time: O(n), Space: O(n)
	 * 
	 * @param n
	 * 		number of fibonacci numbers to print
	 */
	public static void printFibonacci(int n) {

		int[] cache = new int[n + 1];
		for (int i = 0; i < n; i++) {
			System.out.println(i + 1 + ": " + fibonacci(i, cache));
		}
	}

	/**
	 * Compute the nth fibonacci number.
	 * 
	 * @param n
	 * 		the n
	 * @param cache
	 * 		the cached fib array
	 * 
	 * @return
	 * 		the nth fibonacci number
	 */
	private static int fibonacci(int n, int[] cache) {
		if (n <= 0)
			return 0;
		else if (n == 1)
			return 1;
		else if (cache[n] > 0)
			return cache[n];

		cache[n] = fibonacci(n - 1, cache) + fibonacci(n - 2, cache);
		return cache[n];
	}

	/**
	 * Given an integer, print all the factors of that number.
	 * 
	 * @param num
	 *    	given number
	 */
	public static void printFactors(int num) {

		// A prime factor does not go beyond square root of it
		int sqrt = (int) Math.sqrt(num);
	
		for (int i = 1; i <= sqrt; i++) {
			if (num % i == 0 && i * i != num)
				System.out.print(i + " " + num / i + " ");
			if (num % i == 0 && i * i == num)
				System.out.print(i + " ");
		}
	}
	
	/**
	 * Given an integer, print all the prime factors of that number. The prime
	 * factors of a positive integer are the prime numbers that divide that
	 * integer exactly.
	 * 
	 * @param n
	 * 		given number
	 */
	public static void printPrimeFactors(int n) {
		int sqrt = (int) Math.sqrt(n);
		int count = 0;
		for (int i = 2; i <= sqrt; i++) {
			if (n % i == 0) {
				count = 0;
				while (n % i == 0) {
					count += 1;
					n = n / i;
				}
				System.out.println(i + " -> " + count);
			}
		}

		if (n != 1)
			System.out.println(n + " -> " + 1);
	}
}