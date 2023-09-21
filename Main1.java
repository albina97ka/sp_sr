import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Main1 {
    public static void main(String[] args){
        int[] vectorSizes = {10, 100, 1000, 10000};
        int[] vectorCounts = {2, 3, 4, 5, 10};
        System.out.println("Mul\tN\tm\tTime (ms)");

        for (int N : vectorSizes) {
            for (int M : vectorCounts){
                int[] vector = generateRandomVector(N);
                boolean Mul = true;

                long startTime = System.currentTimeMillis();
                processVectorInParallel(vector, M);
                long endTime = System.currentTimeMillis();

                System.out.println(Mul + "\t" + N + "\t" + M + "\t" + (endTime - startTime));

                Mul = false;

                startTime = System.currentTimeMillis();
                processVectorNotInParallel(vector);
                endTime = System.currentTimeMillis();

                System.out.println(Mul + "\t" + N + "\t" + M + "\t" + (endTime - startTime));
            }
        }
    }
    private static int[] generateRandomVector(int N) {
        Random random = new Random();
        int[] vector = random.ints(N, 0, 100).toArray();
        return vector;
    }
    private static void processVectorInParallel(int[] vector, int threadCount) {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        int vectorLength = vector.length;
        int batchSize = vectorLength / threadCount;
        int startIndex = 0;

        for (int i = 0; i < threadCount; i++) {
            int endIndex = startIndex + batchSize;
            int[] subVector = new int[batchSize];
            System.arraycopy(vector, startIndex, subVector, 0, batchSize);

            executorService.execute(() -> {
            });
            startIndex = endIndex;
        }
        executorService.shutdown();
    }
    private static void processVectorNotInParallel(int[] vector) {
    }
}