def is_prime(n):
    if n < 2:
        return False
    if n < 4:
        return True
    if n % 2 == 0:
        return False
    for i in range(3, int(n ** 0.5) + 1, 2):
        if n % i == 0:
            return False
    return True


def find_primes(numbers):
    return [n for n in numbers if is_prime(n)]


if __name__ == "__main__":
    numbers = [1, 2, 3, 4, 5, 10, 11, 15, 17, 20, 23, 29, 50, 97, 100]
    primes = find_primes(numbers)
    print(f"Input:  {numbers}")
    print(f"Primes: {primes}")
