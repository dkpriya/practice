package prac.kp.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @author dkpriya
 *<p>
 * <b> Problem statement </b>
 * <p>
 *Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 *You may assume that each input would have exactly one solution.
 	*<p>Example:
  	*<BR>Given nums = [2, 7, 11, 15], target = 9,
 	*<BR>Because nums[0] + nums[1] = 2 + 7 = 9,
	*<BR>return [0, 1].
	*
	*<p>
 *<b> Further requirement clarifications : </b> 
 * <ul>
 * <li> how long can the array be  ?
 * <li> Is the array sorted ?
 * <li> can the target be a long or will fit into an int?
 * <li> Can the array have duplicates ?
 * <li> Do we have to return all the possible combinations ?
 * <li> what should the response be when the solution is not found, null or exception ?
 * <li> will we need to call this multiple times ? in which case we will precalculate and store the possible targets
 * </ul>
 * <p>
 * <b>Implementation strategies:</b>
 * <ul>
 * <li> Bruteforce (ignored):  for each element in the array, iterate over the other and find out which one adds up to the targets. [ O(n^2) ]
 * <li> optimized : Build a map of all elements and their indexes, then iterate over the map to find the corresponding part .[ O(n) | O(2n) ]
 * <li> final : Loop through the array and store it in a map with its index as we traverse until its counterpart is found.[ O(n) ]
 * </ul>
 */
public class TwoSum {

	public static void main(String[] args) {
		
		int[] givenArray = new int[]{5,2,7,11,1,15,2,0};
		int target = 6;
 		
		int[] indexes = getIndexForTargetSumComponents2(givenArray, target);
		
	}


	/**
	 * <p> Final optimized implementation: loop through the array to find corresponding element to reach target sum, 
	 * while storing the visited elements in map for subsequent lookups.
	 * @param nums   array of integers from which the components of target sum needs to be listed
	 * @param target target sum of the 2 components
	 * @return array of two indexes 
	 * 
	 *<p> Final efficient impl for the current problem statement.
	 *<p> NOTE: however if the requirement is to get the first possible combination, in rare cases it may not be correct
	 * 	<BR>	int[] givenArray = new int[]{2,7,11,15,2,0};
	 *	<BR>	int target = 2;
	 *  <BR>output : [4,5] instead of [0,5]  
	 */
	public static int[] getIndexForTargetSumComponents(int[] nums, int target) {

		Map<Integer, Integer> ints = new HashMap<Integer,Integer>(nums.length);
		
		for(int i=0;i<nums.length;i++){
			
			if( ints.get( target-nums[i]) != null){
				System.out.println( "["+ints.get( target-nums[i])+" ,"+ i+"]");
				return new int[]{ints.get( target-nums[i]), i};
			}
			ints.put(nums[i], i);
		}
		return null;
  	}
 	
	// Run time is 3ms.. can  be more performant. Also loses on the first possible indexes.
	//NOTE : must store array of indexes if there can be duplicate values
	//Example input : [0,4,3,0], 0 expected output:[0,3]
	private static int[] getIndexForTargetSumComponents2(int[] nums, int target) {
  		Map<Integer, List<Integer>> ints = new TreeMap<Integer, List<Integer>>();
		for(int i=0;i<nums.length;i++){
			List<Integer> existing = ints.get(nums[i]);
			if( existing != null)
				existing.add(i);
			else{
				existing = new ArrayList<Integer>();
				existing.add(i);
				ints.put( nums[i], existing );
			}
		}
		
		for(Integer num1 : ints.keySet()){
			int num2 = target - num1;
			List<Integer> index2 = ints.get(num2);
			
 			if(index2 != null){
				// Assumption :since there will only be one solution
  				if(num2 == num1 && index2.size()> 1){
 					System.out.println("["+index2.get(0)+","+index2.get(1)+"]");
  					return new int[]{index2.get(0), index2.get(1)};
 				}else{
 					System.out.println("["+ints.get(num1).get(0)+","+index2.get(0)+"]");
 					if( ints.get(num1).get(0) <= index2.get(0))
 						return new int[]{ints.get(num1).get(0), index2.get(0)};
 					else
 						return new int[]{index2.get(0), ints.get(num1).get(0)};
 				}
 			}
		}
		//return indexes.stream().mapToInt(Integer::intValue).toArray();
		return null;
  	}
   
}
