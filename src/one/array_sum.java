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

public class array_sum {
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
	 * 18. 4Sum
	 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
	 * Find all unique quadruplets in the array which gives the sum of target.
	 * Note: The solution set must not contain duplicate quadruplets.
	 * just similar as three sum.
	 */
	public List<List<Integer>> fourSum(int[] nums, int target) {

		/*110ms
		Arrays.sort(nums);
		List<List<Integer>> res=new ArrayList<List<Integer>>();
		for(int i=0;i<nums.length-3;i++){
			if(i==0 || nums[i-1]!=nums[i]){
				for(int j=i+1;j<nums.length-2;j++){
					if(j==i+1 || nums[j-1]!=nums[j]){
						int lo=j+1,hi=nums.length-1;
						while(lo<hi){
							int sum=nums[i]+nums[j]+nums[lo]+nums[hi];
							if(sum<target){
								while(lo<hi&&nums[lo+1]==nums[lo]) lo++;
								lo++;
							}else if(sum>target){
								while(lo<hi&&nums[hi-1]==nums[hi]) hi--;
								hi--;
							}else{
								res.add(Arrays.asList(nums[i],nums[j],nums[lo],nums[hi]));
								while (lo < hi && nums[lo] == nums[lo+1]) lo++;
		                        while (lo < hi && nums[hi] == nums[hi-1]) hi--;
		                        lo++; hi--;
							}
						}
					}				
				}
			}
		}
		return res;
		 */
		
		/*130ms
		 * 用set去除重复但效率低
		Arrays.sort(nums);//升序
    	List<List<Integer>> result=new ArrayList<List<Integer>>();
    	Set<List<Integer>> mid=new HashSet<List<Integer>>();
    	for(int i=0;i<nums.length;i++){
    		for(int j=i+1;j<nums.length;j++){
    			int j1=j+1;
            	int j2=nums.length-1;
            	while(j1<j2){
            		int count=nums[i]+nums[j]+nums[j1]+nums[j2];
            		if(count==target){
            			List<Integer> tmp=new ArrayList<Integer>();
            		    mid.add(Arrays.asList(nums[i],nums[j],nums[j1],nums[j2]));
            		    j1++;
            		}
            		else if(count<target) j1++;
            		else j2--;
            	}
    		}
        }
    	//去除重复元素 TLE
    	//HashSet h=new HashSet(result);     
        //result.clear();     
        //result.addAll(h); 
    	result.addAll(mid);
    	return result;
		 */
		
		ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
		int len = nums.length;
		if (nums == null || len < 4)
			return res;

		Arrays.sort(nums);

		int max = nums[len - 1];
		if (4 * nums[0] > target || 4 * max < target)
			return res;

		int i, z;
		for (i = 0; i < len; i++) {
			z = nums[i];
			if (i > 0 && z == nums[i - 1])// avoid duplicate
				continue;
			if (z + 3 * max < target) // z is too small
				continue;
			if (4 * z > target) // z is too large
				break;
			if (4 * z == target) { // z is the boundary
				if (i + 3 < len && nums[i + 3] == z)
					res.add(Arrays.asList(z, z, z, z));
				break;
			}

			threeSumForFourSum(nums, target - z, i + 1, len - 1, res, z);
		}

		return res;
	}
	/*
	 * Find all possible distinguished three numbers adding up to the target
	 * in sorted array nums[] between indices low and high. If there are,
	 * add all of them into the ArrayList fourSumList, using
	 * fourSumList.add(Arrays.asList(z1, the three numbers))
	 */
	private void threeSumForFourSum(int[] nums, int target, int low, int high, ArrayList<List<Integer>> fourSumList,
			int z1) {
		if (low + 1 >= high)
			return;

		int max = nums[high];
		if (3 * nums[low] > target || 3 * max < target)
			return;

		int i, z;
		for (i = low; i < high - 1; i++) {
			z = nums[i];
			if (i > low && z == nums[i - 1]) // avoid duplicate
				continue;
			if (z + 2 * max < target) // z is too small
				continue;

			if (3 * z > target) // z is too large
				break;

			if (3 * z == target) { // z is the boundary
				if (i + 1 < high && nums[i + 2] == z)
					fourSumList.add(Arrays.asList(z1, z, z, z));
				break;
			}

			twoSumForFourSum(nums, target - z, i + 1, high, fourSumList, z1, z);
		}

	}
	/*
	 * Find all possible distinguished two numbers adding up to the target
	 * in sorted array nums[] between indices low and high. If there are,
	 * add all of them into the ArrayList fourSumList, using
	 * fourSumList.add(Arrays.asList(z1, z2, the two numbers))
	 */
	private void twoSumForFourSum(int[] nums, int target, int low, int high, ArrayList<List<Integer>> fourSumList,
			int z1, int z2) {

		if (low >= high)
			return;

		if (2 * nums[low] > target || 2 * nums[high] < target)
			return;

		int i = low, j = high, sum, x;
		while (i < j) {
			sum = nums[i] + nums[j];
			if (sum == target) {
				fourSumList.add(Arrays.asList(z1, z2, nums[i], nums[j]));

				x = nums[i];
				while (++i < j && x == nums[i]) // avoid duplicate
					;
				x = nums[j];
				while (i < --j && x == nums[j]) // avoid duplicate
					;
			}
			if (sum < target)
				i++;
			if (sum > target)
				j--;
		}
		return;
		
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
    
    /* 454. 4Sum II
     * Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.
     * To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500.
     * All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.
     * 
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D){
    	//空间换时间,hashMap
    	Map<Integer,Integer> AB=new HashMap<Integer,Integer>();
    	for(int a:A){
    		for(int b:B){
    		    AB.put(a+b,AB.getOrDefault(a+b, 0)+1);
    		}
    	}
    	int res=0;
    	for(int c:C){
    		for(int d:D){
    			int part2=(c+d)*-1;
    			res+=AB.getOrDefault(part2, 0);
    		}
    		
    	}
    	return res;
    }
}
