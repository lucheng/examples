package org.cheng.reference;
public class ReferenceTest {   
    /** Creates a new instance of ReferenceTest */
    public ReferenceTest() {
    }   
    
    // 消耗大量内存
    public static void drainMemory() {
        String[] array = new String[10240 * 10];
        for(int i = 0; i < 10240 * 10; i++) {
            for(int j = 'a'; j <= 'z'; j++) {
                array[i] += (char)j;
            }           
        }
    }
}