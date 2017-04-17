package org.algos4j.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	/**
	 * Given n stairs, this method counts the number of ways (paths) to reach
	 * the top. A person can take either 1 or 2 or 3 steps at a time.
	 * Time: O(3^n)
	 * 
	 * @param stairs
	 * 		number of steps to climb
	 * 
	 * @return
	 * 		the number of ways
	 */
	public static int countPaths(int stairs) {
		if (stairs < 0)
			return 0;
		if (stairs == 0)
			return 1;
	
		return countPaths(stairs - 1) + countPaths(stairs - 2) + countPaths(stairs - 3);
	}
	
	/**
	 * Given n stairs, this method counts the number of ways (paths) to reach
	 * the top. A person can take either 1 or 2 or 3 steps at a time.
	 * Uses memorization of the computed results. Time: O(n), Space: O(n).
	 * 
	 * @param stairs
	 * 		number of steps to climb
	 * 
	 * @return
	 * 		the number of ways
	 */
	public static int countPathsMemorization(int stairs) {
		int memo[] = new int[stairs];
	
		return countPathsMemorization(stairs, memo);
	}

	/**
	 * Recursively compute the number of ways starting at current stair.
	 * 
	 * @param stairs
	 * 		stairs
	 * @param memo
	 * 		memorized result
	 * 
	 * @return
	 * 		number of ways
	 */
	private static int countPathsMemorization(int stairs, int[] memo) {
		if (stairs < 0)
			return 0;
		if (stairs == 0)
			return 1;

		if (memo[stairs] == 0)
			memo[stairs] = countPathsMemorization(stairs - 1) + countPathsMemorization(stairs - 2) + countPathsMemorization(stairs - 3);

		return memo[stairs];
	}
	
	/**
	 * Given n stairs, this method counts the number of ways (paths) to reach
	 * the top. A person can take either 1 or 2 or 3 steps at a time.
	 * Uses dynamic programming of the computed results. Time: O(n), Space: O(n).
	 * 
	 * @param stairs
	 * 		number of steps to climb
	 * 
	 * @return
	 * 		the number of ways
	 */
	public static int countPathsDynamic(int stairs) {
		if (stairs < 0)
			return 0;
		
		if (stairs <= 1)
			return 1;
	
		int[] paths = new int[stairs];
		paths[0] = 1;
		paths[1] = 1;
		paths[2] = 2;
		for (int i = 3; i <= stairs; i++)
			paths[i] = paths[i - 1] + paths[i - 2] + paths[i - 3];

		return paths[stairs];
	}
	
	/**
	 * Given n stairs, this method counts the number of ways (paths) to reach
	 * the top. A person can take either 1 or 2 or 3 steps at a time.
	 * Uses dynamic programming of the computed results. Time: O(n), Space optimized.
	 * 
	 * @param stairs
	 * 		number of steps to climb
	 * 
	 * @return
	 * 		the number of ways
	 */
	public static int countPathsDynamicOpt(int stairs) {
		if (stairs < 0)
			return 0;

		if (stairs <= 1)
			return 1;

		// We do not need more than last three counts
		int[] paths = { 1, 1, 2 };
		for (int i = 3; i <= stairs; i++) {
			int count = paths[2] + paths[1] + paths[0];
			paths[0] = paths[1];
			paths[1] = paths[2];
			paths[2] = count;
		}

		return paths[2];
	}
	
	/**
	 * Given denominations and the amount to make change, 
	 * this method computes the number of ways the coins can be picked to make the sum.
	 * 
	 * @param denominations
	 * 		denominations (coins)
	 * @param amount
	 * 		the amount to make
	 * 
	 * @return
	 * 		the number of ways to make change
	 */
	public static int findWaysToMakeChange(int[] denominations, int amount) {
		
		return findWaysToMakeChange(denominations, amount, new HashMap<String, Integer>(), 0);
	}

	/**
	 * Helper method which computes the ways to make change.
	 * Memorization. Dynamic programming.
	 * 
	 * @param denominations
	 * 		denominations
	 * @param amount
	 * 		amount
	 * @param memomap
	 * 		memorization map
	 * @param index
	 * 		current index of denomination
	 * 
	 * @return
	 * 		the coins
	 */
	private static int findWaysToMakeChange(int[] denominations, int amount, Map<String, Integer> memomap, int index) {
		if (amount == 0)
			return 1;

		if (index >= denominations.length)
			return 0;

		String key = amount + "-" + index;
		if (memomap.containsKey(key))
			return memomap.get(key);

		int amountWith = 0;
		int ways = 0;
		while (amountWith <= amount) {
			int remaining = amount - amountWith;
			ways += findWaysToMakeChange(denominations, remaining, memomap, index + 1);
			amountWith += denominations[index];
		}
		memomap.put(key, ways);

		return ways;
	}
	
	/**
	 * Given denominations and the amount to make change, 
	 * this method computes the minimum number of coins required to make change.
	 * Time: O(n * amount), Space: O(amount)
	 * 
	 * @param denominations
	 * 		denominations (coins)
	 * @param amount
	 * 		the amount to make
	 * 
	 * @return
	 * 		minimum coins required to make change
	 */
	public static int findMinCoinsToMakeChange(int[] denominations, int amount) {
		int min = 0;
		int[] minCoins = new int[amount + 1];
		Arrays.fill(minCoins, Integer.MAX_VALUE);
		minCoins[0] = 0;
		for (int i = 1; i <= amount; i++) {
			min = minCoins[i];
			for (int j = 0; j < denominations.length; j++) {
				if (i >= denominations[j]) {
					min = Math.min(min, minCoins[i - denominations[j]]);
					if (min != Integer.MAX_VALUE && min + 1 < minCoins[i]) {
						minCoins[i] = min + 1;
					}
				}
			}
		}

		return minCoins[amount];
	}
	
	/**
	 * Given denominations and the amount to make change, 
	 * this method computes the minimum number of coins required to make change.
	 * Time: O(n * amount), Space: O(amount)
	 * 
	 * @param denominations
	 * 		denominations (coins)
	 * @param amount
	 * 		the amount to make
	 * 
	 * @return
	 * 		array of coins required to make change, empty array if can not make change
	 */
	public static int[] makeChange(int[] denominations, int amount) {
		int min = 0;
		int[] minCoins = new int[amount + 1];
		Arrays.fill(minCoins, Integer.MAX_VALUE);
		int[] prevCoin = new int[amount + 1];
		Arrays.fill(prevCoin, -1);
		
		minCoins[0] = 0;
		for (int i = 1; i <= amount; i++) {
			min = minCoins[i];
			for (int j = 0; j < denominations.length; j++) {
				if (i >= denominations[j]) {
					min = Math.min(min, minCoins[i - denominations[j]]);
					if (min != Integer.MAX_VALUE && min + 1 < minCoins[i]) {
						minCoins[i] = min + 1;
						prevCoin[i] = j;
					}
				}
			}
		}

		if (prevCoin[amount] != -1) {
			List<Integer> coins = new ArrayList<>();
			int curAmount = amount;
			while (curAmount != 0) {
				int j = prevCoin[curAmount];
				coins.add(denominations[j]);
				curAmount = curAmount - denominations[j];
			}
			
			return ArrayUtil.toArray(coins);
		}
		
		return new int[0];
	}
}