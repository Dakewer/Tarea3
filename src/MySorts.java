public class MySorts {

    public static int[] createArray(int N, int min, int max) {
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = min + (int) ((max - min) * Math.random());
        }
        return array;
    }

    public static int[] createOrderedArray(int N, int min) {
        int[] array = new int[N];

        array[0] = min;
        for (int i = 1; i < N; i++) {
            array[i] = array[i - 1] + (int) (6 * Math.random());
        }
        return array;
    }

    public static int[] createInvertedArray(int N, int max) {
        int[] array = new int[N];

        array[0] = max;
        for (int i = 1; i < N; i++) {
            array[i] = array[i - 1] - (int) (6 * Math.random()) - 1;
        }
        return array;
    }

    public static int[] createWorstCaseArray(int N, int min) {
        int[] array = createOrderedArray(N, min);
        array[0] = array[N - 1] + 1;
        return array;
    }

    static long comps = 0, moves = 0;

    static boolean greaterThan(int a, int b) {
        comps ++;
        return a > b;
    }

    private static void insertion(int[] array, int delta, int start) {
        moves = 0;
        comps = 0;
        for(int p = delta + start; p < array.length; p += delta) {
            int pivot = array[p];
            moves ++;
            int i = p - delta;
            while (i >= start && greaterThan(array[i], pivot)) {
                array[i + 1] = array[i];
                moves ++;
                i --;
            }
            array[i + 1] = pivot;
            moves ++;
        }
        //System.out.printf("%d\t%d\t%d\n", array.length, moves, comps);
    }

    public static void selection(int[] array) {
        int n = array.length;
        comps = 0;
        moves = 0;
        for(int i = 0; i < n - 1; i ++) {
            int min = i;
            for(int j = i + 1; j < n; j ++) {
                comps++;
                if(array[min] > array[j]) min = j;
            }
            if(min != i) {
                int temp = array[i];
                array[i] = array[min];
                array[min] = temp;
                moves += 3;
            }
        }
    }

    public static boolean isSorted(int[] array) {
        for (int i=0 ; i < array.length-1 ; i++) {
            if(array[i] > array[i+1]) return false;
        }
        return true;
    }

    public static void shell(int[] array) {
        int deltaMax = 1;
        while(deltaMax < array.length){
            deltaMax = deltaMax * 3 + 1;

        }
        deltaMax /= 3;
        System.out.println(deltaMax);
        for(int delta = deltaMax; delta >= 1; delta /= 3){
            for(int start = 0; start <= delta - 1; start ++){
                insertion(array, delta, start);
            }
        }
    }

    public static void heapSort(int[] array){

    }

    public static void main(String[] args) {
    /*int N = 100_000;
    int[] array = createArray(N, -N, N); // { 3, 1, 5, 2, 8, 4, 6, 9, 7 };
//		System.out.println(Arrays.toString(array));
    selection(array);
    System.out.println(isSorted(array));
    */

        for(int N = 1000; N <= 5000; N += 100) {
            double avgMoves = 0, avgComps = 0;
            int runs = N / 100;
            for(int r = 0; r < runs; r ++) {
                int[] array1 = createArray(N, -4 * N, 4 * N); //createWorstCaseArray(N, 0);
                selection(array1);
                avgMoves += moves;
                avgComps += comps;
            }
            avgMoves /= runs;
            avgComps /= runs;
            System.out.printf("%d\t%.2f\t%.2f\n", N, avgMoves, avgComps);
        }

//		long start = System.currentTimeMillis();
//		insertion(array1);
//		long end = System.currentTimeMillis();
//		System.out.println(end - start);
//		System.out.println(isSorted(array1));
    }

}
