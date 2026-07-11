import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Primes {

    public static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n < 4) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; (long) i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static List<Integer> findPrimes(int[] numbers) {
        List<Integer> primes = new ArrayList<>();
        for (int n : numbers) {
            if (isPrime(n)) primes.add(n);
        }
        return primes;
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 10, 11, 15, 17, 20, 23, 29, 50, 97, 100};
        List<Integer> primes = findPrimes(numbers);
        System.out.println("Input:  " + Arrays.toString(numbers));
        System.out.println("Primes: " + primes);
    }
}
