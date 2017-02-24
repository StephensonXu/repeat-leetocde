package one;

public class array_merge {
	/*
	 * 4. Median of Two Sorted Arrays ***
	 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
	 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
	 * 找sorted数列中位数-x为长度(nums[(x-1)/2]+nums[x/2])/2，所以前后(x-1)/2个数
     * 1.如下-略
     * 2.
		
	 */
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		
		/*
		 * TLE
		int N1 = nums1.length;
        int N2 = nums2.length;
        if (N1 < N2) return findMedianSortedArrays(nums2, nums1);	// Make sure A2 is the shorter one.
        
        if (N2 == 0) return ((double)nums1[(N1-1)/2] + (double)nums1[N1/2])/2;  // If A2 is empty
        
        int lo = 0, hi = N2 * 2;
        while (lo <= hi) {
            int mid2 = (lo + hi) / 2;   // Try Cut 2 
            int mid1 = N1 + N2 - mid2;  // Calculate Cut 1 accordingly
            
            double L1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[(mid1-1)/2];	// Get L1, R1, L2, R2 respectively
            double L2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[(mid2-1)/2];
            double R1 = (mid1 == N1 * 2) ? Integer.MAX_VALUE : nums1[(mid1)/2];
            double R2 = (mid2 == N2 * 2) ? Integer.MAX_VALUE : nums2[(mid2)/2];
            
            if (L1 > R2) lo = mid2 + 1;		// A1's lower half is too big; need to move C1 left (C2 right)
            else if (L2 > R1) hi = mid2 - 1;	// A2's lower half too big; need to move C2 left.
            else return (Math.max(L1,L2) + Math.min(R1, R2)) / 2;	// Otherwise, that's the right cut.
        }
        return -1;
        */
		
	        int m = nums1.length, n = nums2.length;
		    int l = (m + n + 1) / 2;
		    int r = (m + n + 2) / 2;
		    return (getkth(nums1, 0, nums2, 0, l) + getkth(nums1, 0, nums2, 0, r)) / 2.0;
		}
		public double getkth(int[] nums1, int aStart, int[] nums2, int bStart, int k) {
		    if (aStart > nums1.length - 1) return nums2[bStart + k - 1];            
		    if (bStart > nums2.length - 1) return nums1[aStart + k - 1];                
		    if (k == 1) return Math.min(nums1[aStart], nums2[bStart]);
		
		    int aMid = Integer.MAX_VALUE, bMid = Integer.MAX_VALUE;
		    if (aStart + k/2 - 1 < nums1.length) aMid = nums1[aStart + k/2 - 1]; 
		    if (bStart + k/2 - 1 < nums2.length) bMid = nums2[bStart + k/2 - 1];        
		
		    if (aMid < bMid) 
		        return getkth(nums1, aStart + k/2, nums2, bStart,       k - k/2);// Check: aRight + bLeft 
	    	else 
		        return getkth(nums1, aStart,       nums2, bStart + k/2, k - k/2);// Check: bRight + aLeft
	    }
	

}
