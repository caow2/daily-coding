public class BinarySearch {
  public static void main(String[] args) {
    int[] arr = new int[] {1,2,3,4,5,6};
    System.out.println(bSearch(arr, 1));
    System.out.println(bSearch(arr, 3));
    System.out.println(bSearch(arr, 6));
    System.out.println(bSearch(arr, 10));
  }

  public static int bSearch(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    while(left <= right) {
      int mid = left + (right - left) / 2;
      if(arr[mid] == target)
        return mid;
      else if(arr[mid] > target)
        right = mid - 1;
      else
        left = mid + 1;
    }
    return -1;
  }
}
