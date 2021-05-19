package tools;

public class FormatBalance {
	
	/*
	 * @param balance - pass in the current balance to convert to readable balance (i.e. add commas)
	 */
	public static void formatBalance(int balance) {
		String sBalance = String.valueOf(balance);
		for(int i = 0; i < sBalance.length(); i++) {
			
		}
	}

    /*
    @param arr - passed in array
    @param currIndex - current index relative to where we want to shift from
    @param n - how many shifts over to the right
    */
   public static void shiftArrayRight(int[] arr, int currIndex, int n){
       for(int i = arr.length - 1; i > currIndex; i--){
           arr[i] = arr[i - 1];
       }
   }
}
