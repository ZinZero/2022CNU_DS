package Quiz2;

public class MainP {
    public static void main(String[] args) {
        int[] a = {70, 40, 20, 15, 50,90, 30, 10, 80, 60, 99999};

//        int mid = partition(a, 0, 9);
        int mid = findKth(a, 0, 9, 7);
        System.out.println(mid);
    }

    static int partition(int[] elements, int left, int right) {
        int toRight = left;
        int toLeft = right + 1;
        int pivotValue = elements[left];
        int temp;
        do {
            do {
                toRight++;
            } while (elements[toRight] < pivotValue);
            do {
                toLeft--;
            } while (elements[toLeft] > pivotValue);
            if (toRight < toLeft) {
                swap(elements, toRight, toLeft);
            }
        } while (toRight < toLeft);
        swap(elements, left, toLeft);
        return toLeft;
    }

    static void swap(int[] elements, int p, int q) {
        int temp = elements[p];
        elements[p] = elements[q];
        elements[q] = temp;
        for (int i = 0; i < elements.length; i++) {
            System.out.printf("%d \t", elements[i]);
        }
        System.out.println();
    }

    static int findKth(int[] a, int left, int right, int k_th){
        int mid = partition(a, left, right);
        if (mid == k_th){
            return a[mid];
        }
        else if (mid > k_th){
            findKth(a, left, mid - 1, k_th);
        }
        else if (mid < k_th){
            findKth(a, mid + 1, right, k_th);
        }
        return a[mid];
    }
}

