/*
 * @lc app=leetcode.cn id=605 lang=java
 *
 * [605] 种花问题
 */

// @lc code=start
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {

        int[] newBed = new int[flowerbed.length+2];

        newBed[0] = 0;
        newBed[flowerbed.length+1] = 0;

        for(int i = 0;i<flowerbed.length;i++){
            newBed[i+1] = flowerbed[i];
        }
        
        int i = 0;
        while(i<flowerbed.length){
            if(newBed[i+1]==0){
                if(newBed[i]==0&&newBed[i+2]==0){
                    newBed[i+1]=1;
                }else{
                    if(newBed[i]==1){
                        i=i+2;
                    }
                    if(newBed[i+1]==1){
                        i=i+3;
                    }
                }
            }
        }

        return n==0;
    }
}
// @lc code=end

