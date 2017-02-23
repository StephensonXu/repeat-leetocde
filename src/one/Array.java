package one;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.Set;

public class Array {
	/*
	 * 1.two sum
	 Given an array of integers, return indices of the two numbers such that they add up to a specific target.
     You may assume that each input would have exactly one solution, and you may not use the same element twice.
	 *1.nums sort, one in head, one in tail: add>tar, tail-1; else head+1; but when sort the indices is change is 0(nlg(n)).
	 *  so later is to find the num in nums indice is 0(n)
	 *2.遍历nums数组，如果map含有(taget-nums[i]),return i和map.get(target-nums[i]);否则添加(nums[i],i)
	 *  map.containsKey复杂度——hash-0(1)
	 */
	public int[] twoSum1(int[] nums, int target){
		Map<Integer, Integer> map=new HashMap<Integer,Integer>();
		for(int i=0;i<nums.length;map.put(nums[i], i++)){
			if(map.containsKey(target-nums[i])) return new int[]{map.get(target-nums[i]),i};
		}
		return new int[2];
		//for loop -when loop all is done then do i++ or in this map.put
	}
	/*
	 * 15. 3Sum
	 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
	 * Find all unique triplets in the array which gives the sum of zero.
	 * Note: The solution set must not contain duplicate triplets.//三元组不能重复(-1,1,0)=(1,-1,0)
     * 1.sort nums, and do as twosum //nearly TLE
     * 2.sort nums, and find from head and tail
	 */
	public List<List<Integer>> threeSum(int[] nums){
		/*
		 * nearly TLE 470ms
		Arrays.sort(nums);
		List<List<Integer>> res=new ArrayList<List<Integer>>();
		Set<Integer> set=new HashSet<Integer>();
		for(int i=0;i<nums.length;){
			for(int j=i+1;j<nums.length;){
				if(set.contains(-nums[i]-nums[j])){
					res.add(Arrays.asList(nums[i],nums[j],-nums[i]-nums[j]));
					while(++j<nums.length&&nums[j]==nums[j-1]);
				}else set.add(nums[j++]);
			}
			while(i<nums.length-1&&nums[++i]==nums[i-1]);
		}
		return res;
		 */
		//90 ms--暂时找到更一个质的提升到30ms的
		Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>(); 
        for (int i = 0; i < nums.length-2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {
                int lo = i+1, hi = nums.length-1, sum = 0 - nums[i];
                while (lo < hi) {
                    if (nums[lo] + nums[hi] == sum) {
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo+1]) lo++;
                        while (lo < hi && nums[hi] == nums[hi-1]) hi--;
                        lo++; hi--;
                    } else if (nums[lo] + nums[hi] < sum){
                    	// improve: skip duplicates
                        while (lo < hi && nums[lo] == nums[lo+1]) lo++;
                        lo++;
                    }
                    else {
                    	// improve: skip duplicates
                        while (lo < hi && nums[hi] == nums[hi-1]) hi--;
                        hi--;
                    }
                }  
            }
        }
        return res;
	}
	/*
	 * 16. 3Sum Closest
	 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. 
	 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
	 * For example, given array S = {-1 2 1 -4}, and target = 1.
	 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
	 * Arrays.sort-升序, 事先一个d=Integer.MAX/2->还是nums[0]+nums[1]+nums[nums.length-1]更好  and i+head+tail,>则tail--,=则输出target,<则head++;
	 */
	public int threeSumClosest(int[] nums, int target){
		 //I think if the length < 3,return plus of nums
	    if(nums.length<3){
			int res=0;
			for(int i:nums) res+=i;
			return res;
		}
	    Arrays.sort(nums);
	    int res=nums[0]+nums[1]+nums[nums.length-1],count;
		for(int i=0;i<nums.length-2;i++){
			int head=i+1,tail=nums.length-1;
			while(head<tail){
				count=nums[i]+nums[head]+nums[tail];	
				if(count<target) head++;
				else if(count>target) tail--;
				else return target;
				res= Math.abs(target-count)>Math.abs(target-res)?res:count;
			}
		}
		return res;
	}
	/*
	 * 167. Two Sum II - Input array is sorted
	 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
	 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
	 * You may assume that each input would have exactly one solution and you may not use the same element twice.
	 * Input: numbers={2, 7, 11, 15}, target=9
	 * Output: index1=1, index2=2
	 * 1.head+tail and find
	 * 2.map and find
	 * 3.binary search for each 0(nlg(n))
	 */
    public int[] twoSum2(int[] numbers, int target) {
    	int head=0,tail=numbers.length-1;
    	while(head<tail){
    		int count=numbers[head]+numbers[tail];
    		if(count==target) return new int[]{head+1,tail+1};
    		else if(count<target) head++;
    		else tail--;
    	}
    	return new int[2];
    }
}
