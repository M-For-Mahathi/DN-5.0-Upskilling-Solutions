import java.util.*;
import java.util.stream.*;
import java.io.*;
import java.net.*;
import java.net.http.*;
import java.lang.reflect.*;
import java.util.concurrent.*;

public class CoreJavaExercises {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Exercise 1
        helloWorld();

        // Exercise 2
        calculator(sc);

        // Exercise 3
        evenOrOdd(sc);

        // Exercise 4
        leapYear(sc);

        // Exercise 5
        multiplicationTable(sc);

        // Exercise 6
        dataTypes();

        // Exercise 7
        typeCasting();

        // Exercise 8
        operatorPrecedence();

        // Exercise 9
        gradeCalculator(sc);

        // Exercise 10
        numberGuessingGame(sc);

        // Exercise 11
        factorial(sc);

        // Exercise 12
        methodOverloading();

        // Exercise 13
        fibonacciDemo(sc);

        // Exercise 14
        arraySumAverage(sc);

        // Exercise 15
        stringReversal(sc);

        // Exercise 16
        palindromeChecker(sc);

        // Exercise 17
        carDemo();

        // Exercise 18
        inheritanceDemo();

        // Exercise 19
        interfaceDemo();

        // Exercise 20
        tryCatchDemo(sc);

        // Exercise 21
        customExceptionDemo(sc);

        // Exercise 22
        fileWriting(sc);

        // Exercise 23
        fileReading();

        // Exercise 24
        arrayListDemo(sc);

        // Exercise 25
        hashMapDemo(sc);

        // Exercise 26
        threadDemo();

        // Exercise 27
        lambdaSortDemo();

        // Exercise 28
        streamFilterDemo();

        // Exercise 29
        recordsDemo();

        // Exercise 30
        patternMatchingSwitch();

        // Exercise 36
        httpClientDemo();

        // Exercise 37
        bytecodeInspection();

        // Exercise 38
        decompilerDemo();

        // Exercise 39
        reflectionDemo();

        // Exercise 40
        virtualThreadsDemo();

        // Exercise 41
        executorCallableDemo();

        sc.close();
    }

    //Exercise 1
    static void helloWorld() {
        System.out.println("Hello, World!");
    }

    //Exercise 2
    static void calculator(Scanner sc) {
        System.out.print("Enter first number: ");
        double a = sc.nextDouble();
        System.out.print("Enter second number: ");
        double b = sc.nextDouble();
        System.out.print("Choose operation (+, -, *, /): ");
        char op = sc.next().charAt(0);

        switch (op) {
            case '+' -> System.out.println("Result: " + (a + b));
            case '-' -> System.out.println("Result: " + (a - b));
            case '*' -> System.out.println("Result: " + (a * b));
            case '/' -> {
                if (b == 0) System.out.println("Cannot divide by zero.");
                else System.out.println("Result: " + (a / b));
            }
            default -> System.out.println("Invalid operator.");
        }
    }

    //Exercise 3
    static void evenOrOdd(Scanner sc) {
        System.out.print("Enter an integer: ");
        int n = sc.nextInt();
        System.out.println(n + " is " + (n % 2 == 0 ? "Even" : "Odd"));
    }

    //Exercise 4
    static void leapYear(Scanner sc) {
        System.out.print("Enter a year: ");
        int year = sc.nextInt();
        boolean leap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        System.out.println(year + " is " + (leap ? "a" : "not a") + " leap year.");
    }

    //Exercise 5
    static void multiplicationTable(Scanner sc) {
        System.out.print("Enter a number: ");
        int num = sc.nextInt();
        for (int i = 1; i <= 10; i++)
            System.out.println(num + " x " + i + " = " + (num * i));
    }

    //Exercise 6
    static void dataTypes() {
        int i = 42;
        float f = 3.14f;
        double d = 2.718281828;
        char c = 'J';
        boolean b = true;

        System.out.println("int: " + i);
        System.out.println("float: " + f);
        System.out.println("double: " + d);
        System.out.println("char: " + c);
        System.out.println("boolean: " + b);
    }

    //Exercise 7
    static void typeCasting() {
        double d = 9.99;
        int fromDouble = (int) d;
        System.out.println("double to int: " + d + " -> " + fromDouble);

        int x = 7;
        double fromInt = (double) x;
        System.out.println("int to double: " + x + " -> " + fromInt);
    }

    //Exercise 8
    static void operatorPrecedence() {
        int r1 = 10 + 5 * 2; // multiplication before addition
        int r2 = (10 + 5) * 2; // parentheses first
        int r3 = 100 / 5 + 3 * 2; // division and multiplication before addition

        System.out.println("10 + 5 * 2 = " + r1 + "  (multiplication before addition)");
        System.out.println("(10 + 5) * 2 = " + r2 + "  (parentheses first)");
        System.out.println("100 / 5 + 3 * 2 = " + r3 + "  (division and multiplication before addition)");
    }

    //Exercise 9
    static void gradeCalculator(Scanner sc) {
        System.out.print("Enter marks (0-100): ");
        int marks = sc.nextInt();
        String grade;
        if (marks >= 90) grade = "A";
        else if (marks >= 80) grade = "B";
        else if (marks >= 70) grade = "C";
        else if (marks >= 60) grade = "D";
        else grade = "F";
        System.out.println("Grade: " + grade);
    }

    //Exercise 10 
    static void numberGuessingGame(Scanner sc) {
        int target = new Random().nextInt(100) + 1;
        System.out.println("Guess a number between 1 and 100:");
        int guess;
        do {
            guess = sc.nextInt();
            if (guess < target) System.out.println("Too low! Try again:");
            else if (guess > target) System.out.println("Too high! Try again:");
            else System.out.println("Correct! The number was " + target);
        } while (guess != target);
    }

    //Exercise 11 
    static void factorial(Scanner sc) {
        System.out.print("Enter a non-negative integer: ");
        int n = sc.nextInt();
        long result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        System.out.println(n + "! = " + result);
    }

    //Exercise 12 
    static int add(int a, int b) { return a + b; }
    static double add(double a, double b) { return a + b; }
    static int add(int a, int b, int c) { return a + b + c; }

    static void methodOverloading() {
        System.out.println("add(3, 4) = " + add(3, 4));
        System.out.println("add(1.5, 2.5) = " + add(1.5, 2.5));
        System.out.println("add(1, 2, 3) = " + add(1, 2, 3));
    }

    //Exercise 13 
    static long fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    static void fibonacciDemo(Scanner sc) {
        System.out.print("Enter n for Fibonacci: ");
        int n = sc.nextInt();
        System.out.println("Fibonacci(" + n + ") = " + fibonacci(n));
    }

    //Exercise 14 
    static void arraySumAverage(Scanner sc) {
        System.out.print("How many elements? ");
        int size = sc.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            arr[i] = sc.nextInt();
        }
        int sum = 0;
        for (int v : arr) sum += v;
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + (double) sum / size);
    }

    //Exercise 15 
    static void stringReversal(Scanner sc) {
        System.out.print("Enter a string: ");
        String s = sc.next();
        System.out.println("Reversed: " + new StringBuilder(s).reverse());
    }

    //Exercise 16 
    static void palindromeChecker(Scanner sc) {
        System.out.print("Enter a string: ");
        String raw = sc.next().replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String reversed = new StringBuilder(raw).reverse().toString();
        System.out.println(raw.equals(reversed) ? "\"" + raw + "\" is a Palindrome" : "\"" + raw + "\" is Not a Palindrome");
    }

    //Exercise 17 
    static class Car {
        String make, model;
        int year;

        Car(String make, String model, int year) {
            this.make = make;
            this.model = model;
            this.year = year;
        }

        void displayDetails() {
            System.out.println("Car: " + year + " " + make + " " + model);
        }
    }

    static void carDemo() {
        Car c1 = new Car("Toyota", "Corolla", 2022);
        Car c2 = new Car("Honda", "Civic", 2023);
        c1.displayDetails();
        c2.displayDetails();
    }

    //Exercise 18 
    static class Animal {
        void makeSound() { System.out.println("Animal makes a sound"); }
    }

    static class Dog extends Animal {
        @Override
        void makeSound() { System.out.println("Dog says: Bark"); }
    }

    static void inheritanceDemo() {
        Animal a = new Animal();
        Animal d = new Dog();
        a.makeSound();
        d.makeSound();
    }

    //Exercise 19 
    interface Playable {
        void play();
    }

    static class Guitar implements Playable {
        public void play() { System.out.println("Guitar is playing"); }
    }

    static class Piano implements Playable {
        public void play() { System.out.println("Piano is playing"); }
    }

    static void interfaceDemo() {
        Playable g = new Guitar();
        Playable p = new Piano();
        g.play();
        p.play();
    }

    //Exercise 20 
    static void tryCatchDemo(Scanner sc) {
        System.out.print("Enter dividend: ");
        int a = sc.nextInt();
        System.out.print("Enter divisor: ");
        int b = sc.nextInt();
        try {
            System.out.println("Result: " + (a / b));
        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero.");
        }
    }

    //Exercise 21 
    static class InvalidAgeException extends Exception {
        InvalidAgeException(String msg) { super(msg); }
    }

    static void validateAge(int age) throws InvalidAgeException {
        if (age < 18)
            throw new InvalidAgeException("Age " + age + " is invalid. Must be 18 or older.");
    }

    static void customExceptionDemo(Scanner sc) {
        System.out.print("Enter your age: ");
        int age = sc.nextInt();
        try {
            validateAge(age);
            System.out.println("Access granted.");
        } catch (InvalidAgeException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }

    //Exercise 22 
    static void fileWriting(Scanner sc) {
        System.out.print("Enter text to write to file: ");
        String text = sc.next();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            bw.write(text);
            System.out.println("Data written to output.txt");
        } catch (IOException e) {
            System.out.println("File write error: " + e.getMessage());
        }
    }

    //Exercise 23 
    static void fileReading() {
        try (BufferedReader br = new BufferedReader(new FileReader("output.txt"))) {
            String line;
            System.out.println("Contents of output.txt:");
            while ((line = br.readLine()) != null)
                System.out.println(line);
        } catch (IOException e) {
            System.out.println("File read error: " + e.getMessage());
        }
    }

    //Exercise 24 
    static void arrayListDemo(Scanner sc) {
        List<String> names = new ArrayList<>();
        System.out.print("How many names to add? ");
        int count = sc.nextInt();
        for (int i = 0; i < count; i++) {
            System.out.print("Name " + (i + 1) + ": ");
            names.add(sc.next());
        }
        System.out.println("Names in list: " + names);
    }

    //Exercise 25 
    static void hashMapDemo(Scanner sc) {
        Map<Integer, String> students = new HashMap<>();
        System.out.print("How many entries? ");
        int count = sc.nextInt();
        for (int i = 0; i < count; i++) {
            System.out.print("Student ID: ");
            int id = sc.nextInt();
            System.out.print("Student Name: ");
            students.put(id, sc.next());
        }
        System.out.print("Enter ID to lookup: ");
        int lookup = sc.nextInt();
        System.out.println("Name: " + students.getOrDefault(lookup, "Not found"));
    }

    //Exercise 26 
    static class PrintTask implements Runnable {
        private final String message;
        PrintTask(String message) { this.message = message; }

        public void run() {
            for (int i = 0; i < 5; i++)
                System.out.println(Thread.currentThread().getName() + ": " + message);
        }
    }

    static void threadDemo() {
        Thread t1 = new Thread(new PrintTask("Hello from Thread 1"));
        Thread t2 = new Thread(new PrintTask("Hello from Thread 2"));
        t1.start();
        t2.start();
        try { t1.join(); t2.join(); } catch (InterruptedException ignored) {}
    }

    //Exercise 27 
    static void lambdaSortDemo() {
        List<String> words = new ArrayList<>(List.of("banana", "apple", "cherry", "date", "mango"));
        Collections.sort(words, (x, y) -> x.compareTo(y));
        System.out.println("Sorted list: " + words);
    }

    //Exercise 28 
    static void streamFilterDemo() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evens = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Even numbers: " + evens);
    }

    //Exercise 29 
    record Person(String name, int age) {}

    static void recordsDemo() {
        List<Person> people = List.of(
            new Person("Alice", 30), new Person("Bob", 17), new Person("Charlie", 25), new Person("Diana", 15)
        );

        System.out.println("All people:");
        people.forEach(System.out::println);

        System.out.println("Adults (age >= 18):");
        people.stream()
              .filter(p -> p.age() >= 18)
              .forEach(System.out::println);
    }

    //Exercise 30 
    static String describeType(Object obj) {
        return switch (obj) {
            case Integer i -> "Integer: " + i;
            case String s -> "String: " + s;
            case Double d -> "Double: " + d;
            case Boolean b -> "Boolean: " + b;
            default -> "Unknown type: " + obj.getClass().getSimpleName();
        };
    }

    static void patternMatchingSwitch() {
        System.out.println(describeType(42));
        System.out.println(describeType("hello"));
        System.out.println(describeType(3.14));
        System.out.println(describeType(true));
        System.out.println(describeType(9.99f));
    }

    //Exercise 36 
    static void httpClientDemo() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.github.com"))
                    .header("Accept", "application/json")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("HTTP Status: " + response.statusCode());
            System.out.println("Response (truncated): " +
                response.body().substring(0, Math.min(300, response.body().length())));
        } catch (Exception e) {
            System.out.println("HTTP request failed: " + e.getMessage());
        }
    }

    //Exercise 37 
    static void bytecodeInspection() {
        System.out.println("--- Exercise 37: Bytecode Inspection ---");
        System.out.println("Steps to inspect bytecode:");
        System.out.println("  1. javac Demo.java");
        System.out.println("  2. javap -c Demo");
        System.out.println("The javap tool disassembles the .class file and shows JVM instructions");
        System.out.println("such as iload, imul, ireturn for a method like int square(int x).");
    }

    //Exercise 38 
    static void decompilerDemo() {
        System.out.println("--- Exercise 38: Decompiling a Class File ---");
        System.out.println("Steps to decompile:");
        System.out.println("  1. javac Demo.java");
        System.out.println("  2. java -jar cfr.jar Demo.class");
        System.out.println("CFR reconstructs Java source from bytecode.");
        System.out.println("JD-GUI is a visual alternative: drag and drop the .class file.");
    }

    //Exercise 39 
    static void reflectionDemo() {
        try {
            Class<?> clazz = Class.forName("CoreJavaExercises$Car");

            System.out.println("Class name: " + clazz.getName());
            System.out.println("Declared methods:");
            for (Method m : clazz.getDeclaredMethods())
                System.out.println("  " + m.getName() + "(" + Arrays.toString(m.getParameterTypes()) + ")");

            Object car = clazz.getDeclaredConstructors()[0].newInstance("Ford", "Mustang", 2024);
            Method display = clazz.getMethod("displayDetails");
            display.invoke(car);

        } catch (Exception e) {
            System.out.println("Reflection error: " + e.getMessage());
        }
    }

    //Exercise 40 
    static void virtualThreadsDemo() {
        System.out.println("Launching 100,000 virtual threads...");
        long start = System.currentTimeMillis();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100_000; i++) {
            Thread t = Thread.ofVirtual().start(() -> {
                long x = 1;
                for (int j = 0; j < 100; j++) x += j;
            });
            threads.add(t);
        }
        threads.forEach(t -> {
            try { t.join(); } catch (InterruptedException ignored) {}
        });

        System.out.println("100,000 virtual threads completed in " +
            (System.currentTimeMillis() - start) + "ms");
    }

    //Exercise 41 
    static void executorCallableDemo() {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        List<Callable<Integer>> tasks = List.of(
            () -> 1 + 1, () -> 2 * 3, () -> 10 - 4, () -> 100 / 5
        );
        try {
            List<Future<Integer>> futures = pool.invokeAll(tasks);
            System.out.print("Callable results: ");
            for (Future<Integer> f : futures)
                System.out.print(f.get() + " ");
            System.out.println();
        } catch (Exception e) {
            System.out.println("Executor error: " + e.getMessage());
        } finally {
            pool.shutdown();
        }
    }
}