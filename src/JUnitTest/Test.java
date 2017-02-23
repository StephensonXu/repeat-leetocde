package JUnitTest;

import one.Array;
import junit.framework.TestCase;

public class Test extends TestCase {
	public void testtwoSum()
    {
        Array calculator = new Array();
        int[] result = calculator.twoSum1(new int[]{1,2,4,8}, 8);
        // 判断方法的返回结果
        org.junit.Assert.assertArrayEquals(new int[]{0,0}, result);// 第一个参数是期望值，第二个参数是要验证的值
    }

}
