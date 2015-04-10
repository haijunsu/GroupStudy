/**
 * O(n)
 * 234 ms
 * 
 * Greedy
 */
public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        /**
         * brute force
         * The most important idea is
         *      If car starts at A and can not reach B (B is the first station that A can not reach),
         *          Then Any station between A and B can not reach B.
         *      Proof: use contradiction, suppose position A+1 could reach to B, then since
         *              A could reach to A+1, then A could reach to B. It's contradicted with the
         *              assumption. So A+1 can not reach to B.
         */ 
        //#1 trial
        // if(gas.length == 0) return -1;
        
        // int haveGas = 0, stationNum, traveled;
        
        // for(int i = 0; i < gas.length; i++){
        //     traveled = 0;
        //     stationNum = i;
        //     //if(gas[i] < cost[i])
        //     while(true){
        //         haveGas = haveGas + gas[stationNum];
        //         //if gas is enough!
        //         if(haveGas >= cost[stationNum]){
        //             haveGas -= cost[stationNum++];
        //             if(stationNum >= gas.length)
        //                 stationNum = stationNum % gas.length;
        //             traveled++;
        //             if(traveled >= gas.length) return i;
        //         }
        //         else if(haveGas < cost[stationNum] ) {
        //             i = (stationNum + 1)  % gas.length;
        //             // traveled = 0;
        //             haveGas = 0;
        //             break;
        //         }
                
        //     }//while
        // }
        // return -1;
        
        //#2 trail
        int sumCost = 0, sumGas = 0, haveGasNow = 0, start = 0;
        for(int i = 0; i < gas.length; i++){
            sumGas += gas[i];
            sumCost += cost[i];
            haveGasNow += gas[i] - cost[i];
            if(haveGasNow < 0){
                start = i + 1;
                haveGasNow = 0;
            } 
        }
        if(sumGas < sumCost) return -1;
        else return start;
        
    }//method
}