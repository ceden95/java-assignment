
public class Ex14
{
    //Question 1.b.1
    /**
     * Check if the number Val is in the metrix.
     * 
     * @param m merix of ints 
     * @param val the int to check if it is in the metrix.
     * 
     * @return true if int Val is in Matrix which returns true to 'what' method
     * 
     */
    public static boolean findValWhat (int [][] m, int val){
        int n= m.length;
        int i = 0;
        int j = n-1;//first step is on the top right

        while(i<n &&  j >= 0){ 
            if (m[i][j] == val){
                return true;
            }
            if (m[i][j] > val){ // if value of m[i][j] is bigger than val
                j--; //go one step left to a smaller value
            }else{ // if val is bigger than value of m[i][j] 
                i++; // go one step down for a bigger row of numbers
            }
        }
        return false;
    }

    //Question 1.b.2
    /**
     * returns true if the number Val is in the metrix.
     * 
     * Time Complexity: O(n)- worst case is checking 4n times,(2n for index i and 2n for scanning 2 adjacent rows). O(n) is in the same growth rates.
     * Place complexity:O(1)- int n as a loop boundary indicator, place usage doesnt change according to the parameters storage space
     * 
     * @return true if int Val is in Matrix which returns True to 'test' method
     * @param m merix of ints 
     * @param val the int to check if it is in the metrix.
     * 
     */
    public static boolean findValTest (int [][] m, int val){
        int n = m.length;
        if (val <= m[0][0] || m[n-1][0]<=val){//if val might be in first or last row  
            for (int j=0; j<n;j++){ //scan first and last line of merix
                if (val == m[0][j]) 
                    return true;
                if (val == m[n-1][j])
                    return true;
            }
            return false;//if val not in those 2 lines, no need to check the rest 
        }
        
        for (int i=0; i<n-1; i++){
            if (m[i][0]<=val && val<=m[i+1][0]){//if val is between range of 2 adjacent rows
                for (int j=0; j<n;j++){//check 2 adjacent rows if val exist.
                    if (m[i][j] == val){
                        return true;
                    }
                    if (m[i+1][j] == val){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    //Question 2
    /**
     * returns the number of the subsequence which in the given arrey.
     * minimum of subsequence is two numbers adjacent to each other.
     * 
     * Time Complexity: O(n) loop for a.length-1 times
     * Place complexity: O(1)- occupancy is permanent, not changing according to the parameter arrey given.
     * (only 4 updated variables)
     * 
     * @return the number of the subsubsequences in arrey of numbers bigger the previous.
     * @param a the arrey to check the max number of subsubsequences
     * 
     */
    public static int strictlyIncreasing (int[] a){
        int count = 0;
        int len = a.length;
        int start=0;
        int subArrey;
        for (int i=0; i<len-1; i++){
            if (a[i]>=a[i+1]){//if a[i] is the last number of subsequence
                subArrey = i-start;//first number of the next subsequence
                count += countSub(subArrey); // add number of all subsequence from start to a[i]
                start = i+1;//Initializing start for the next subsequence

            }
            if (i+2 == len && a[i]<a[i+1] ){ // case for 2 last index
                subArrey = len-start-1;
                count += countSub(subArrey);
            }
        }

        return count;

    }

    /**
     * 
     * return the number of all different subsequence in a sequence 
     * 
     * Time Complexity: O(1)-adds i from 0 to num(can't be larger than max int)
     * Place complexity: O(1)- only for updated int count
     * 
     * @param num is number of ints in a sequence.
     */
    private static int countSub(int num){
        
        int count = 0;
        for (int i=1; i<num+1; i++){
            count += i;
        }
        return count;

    }

    //Question 3
    /**
     * checks the longest flat sequence in arrey arr.
     *
     * @param arr the arrey to check the number of the longest flat sequence
     * 
     * @return the longest flat sequence in arrey arr.
     */
    public static int longestFlatSequence (int[] arr){
        if (arr.length == 1){
            return 1;
        }else if (arr.length == 0){
            return 0;
        }else{
            return maxFlatSequenceLength (arr, 0); 
        }
        
    }
    
    /**
     * return the number of longest flat arrey from index i  
     */
    private static int maxFlatSequenceLength (int [] arr, int i){
        int maxLength = 0;
        //stop condition
        if(i == arr.length-1){
            return 1;
        }else if (i > arr.length-1){
            return 0;
        }
        
        int a = arr[i];
        int b = arr[i+1];
        int length = 0;
        
        
        if (a-b == 1 || a-b == -1){ //if a,b are consecutive numbers
            length = 2+flatSequenceLength(arr, i+2, a, b); 
        }
        else if (a == b){ 
            length = 1+maxFlatSequenceLength (arr, i+1);
        }
        else{
            length = maxFlatSequenceLength(arr, i+1);
        }
        
        maxLength = Math.max(maxFlatSequenceLength(arr, i+1), length);
        return maxLength;
    }
    
    /**
     * checks the length of flat Sequence
     */
    private static int flatSequenceLength(int [] arr, int index, int a, int b){
        if (index > arr.length-1){//if index out of limits 
            return 0;
        }
        if (arr[index] != a && arr[index] != b ){ 
            return 0;
        }
        return 1+flatSequenceLength(arr, index+1, a, b);
     }

    //Question 4
    /**
     * returns the max points a routes of values == 1 || 0 can accumulate
     * 1=1 point, 0=zero points.
     * 
     * @return the max points of all possible route in metrix.
     * @param mat the metrix to check the max points.
     */
    public static int findMaximum(int[][] mat){
        if (mat[0][0] == -1){
            return -1;
        }
        return findMaximum(mat, 0, 0); // uses findMaximum private method from start point 0,0 
    }

    /**
     * returns the max number of points all possible steps from index i,j in metrix
     */
    private static int findMaximum(int[][] mat, int i, int j){

        int ret1 = 0;
        int ret2 = 0;
        //stop condition
        if (isNotOk(mat, i, j)){ //if index out of limits or -1
            return 0;
        }
        //try right
        if (i%2 == 0){
            ret1 = findMaximum(mat,i,j+1);    
        }
        //try left
        if (i%2 == 1){
            ret1 = findMaximum(mat,i,j-1);
        }
        //try down
        ret2 = findMaximum(mat,i+1,j);

        //return current value with the biggest sum accumulated before
        return mat[i][j]+(ret1>ret2 ? ret1 : ret2);

    }

    /**
     * returns true if the value of given index is -1 or index is out of matrix limits.
     * 
     */
    private static boolean isNotOk(int [][] mat, int i, int j){
        int len = mat.length;
        int rowLen =mat[0].length;
        if (i >= len || j<0 || j >= rowLen ){
            return true; 
        }else if (mat[i][j] == -1){
            return true;
        }
        else{
            return false;
        }
    }

}
