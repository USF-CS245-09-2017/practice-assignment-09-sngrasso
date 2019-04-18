
import java.util.Arrays;

public class BinaryHeap{
    protected int arr_size = 10;
    protected int[] array;
    protected int size;

    /*Constructs a new BinaryHeap.*/
    public BinaryHeap () {

        array = new int[arr_size];
        size = 0;
    }

    /*Add value */
    public void add(int value) {
        // grow array if needed
        if (size >= array.length - 1) {
            array = this.resize();
        }

        // place element in heap at the bottom
        size++;
        int index = size;
        array[index] = value;

        bubbleUp();
    }


    /*Check and return true if empty*/
    public boolean isEmpty() {
        return size == 0;
    }

    /*swap function*/
    protected void swap(int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }


    /*Returns the minimum element in the heap.*/
    public int peek() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }

        return array[1];
    }


    /*Removes and returns the minimum element in the heap.*/
    public int remove() {
        int result = peek();

        // get rid of the last leaf/decrement
        array[1] = array[size];
        array[size] = 0;
        size--;

        bubbleDown();

        return result;
    }


    /**
     * Performs the "bubble down" operation to place the element that is at the 
     * root of the heap in its correct place so that the heap maintains the
     * min-heap order property.
     */
    protected void bubbleDown() {
        int index = 1;

        // bubble down
        while (hasLeftChild(index)) {
            // find the smaller child
            int smallerChild = leftIndex(index);

            // bubble with the smaller child, if there's a smaller child
            if (hasRightChild(index)
                    && array[leftIndex(index)] > array[rightIndex(index)]) {
                smallerChild = rightIndex(index);
            }

            if (array[index] > array[smallerChild]) {
                swap(index, smallerChild);
            } else {
                break;
            }

            index = smallerChild;
        }
    }


    /*Performs the "bubble up" operation to place a newly inserted element
     in its correct place so that the heap is still in order*/
    protected void bubbleUp() {
        int index = size;

        while (hasParent(index) && (parent(index) > array[index])) {
            // swap the parent and the child
            swap(index, parentIndex(index));
            index = parentIndex(index);
        }
    }


    protected boolean hasParent(int i) {
        return i > 1;
    }


    protected int leftIndex(int i) {
        return i * 2;
    }


    protected int rightIndex(int i) {
        return i * 2 + 1;
    }

    //returns true if child is on left
    protected boolean hasLeftChild(int i) {
        return leftIndex(i) <= size;
    }

    //returns true if child is on right
    protected boolean hasRightChild(int i) {
        return rightIndex(i) <= size;
    }

    //returns parent value
    protected int parent(int i) {
        return array[parentIndex(i)];
    }


    //returns parent index
    protected int parentIndex(int i) {
        return i / 2;
    }


    //fuction to resize the array as needed
    protected int[] resize() {
        return Arrays.copyOf(array, array.length * 2);
    }



}