/**
 * O(n)
 * 572 ms
 * 
 * String
 *      equals(), substring() 
 *      indexOF()//return -1 if doesn't exit.
 *      Integer.valueOf()//string to integer
 *      String.valueOf()/integer to string
 */
public class Solution {
    public int compareVersion(String version1, String version2) {
        if(version1.equals(version2)) return 0;
        int val_v1, val_v2;
        String sub_v1, sub_v2;
        
        if(version1.indexOf('.') != -1) {//if version1 has dot inside.
            int dotIndex1 = version1.indexOf('.');
            val_v1 = Integer.valueOf(version1.substring(0, dotIndex1));
            sub_v1 = version1.substring(dotIndex1 + 1, version1.length());
        }
        else {
            val_v1 = Integer.valueOf(version1.substring(0, version1.length()));
            sub_v1 = "0";
        }
        
        if(version2.indexOf('.') != -1) {//if version1 has dot inside.
            int dotIndex2 = version2.indexOf('.');
            val_v2 = Integer.valueOf(version2.substring(0, dotIndex2));
            sub_v2 = version2.substring(dotIndex2 + 1, version2.length());
        }
        else {
            val_v2 = Integer.valueOf(version2.substring(0, version2.length()));
            sub_v2 = "0";
        }
        
        if(val_v1 > val_v2) return 1;
        if(val_v1 < val_v2) return -1;
        return compareVersion(sub_v1, sub_v2);
        
        
//      int dotIndex1, dotIndex2, v1head, v1tail, v2head, v2tail;
//      
//      while(true) {
//          dotIndex1 = version1.indexOf('.') != -1 ? version1.indexOf('.') : version1.length();
//          dotIndex2 = version2.indexOf('.') != -1 ? version2.indexOf('.') : version2.length();
//          
//          v1head = Integer.valueOf(version1.substring(0, dotIndex1));
////            v1tail = version1.indexOf('.') != -1 ? Integer.valueOf(version1.substring(dotIndex1 + 1, version1.length())) : 0;
//          
//          v2head = Integer.valueOf(version2.substring(0, dotIndex2));
////            v2tail = version2.indexOf('.') != -1 ? Integer.valueOf(version2.substring(dotIndex2 + 1, version2.length())) : 0;
//  
//          if(v1head > v2head) return 1;
//          if(v1head < v2head) return -1;
////            if(v1head == v2head){
////                if(v1tail > v2tail) return 1;
////                if(v1tail < v2tail) return -1;
////            }
////            return 0;
//          
//          /*
//           * version1 and version2 are equal.
//           */
//          //version2 has more dots
//          if(version1.indexOf('.') == -1 && version2.indexOf('.') != -1) {
//              version2 = version2.substring(dotIndex2 + 1, version2.length());
//              dotIndex2 = version2.indexOf('.') != -1 ? version2.indexOf('.') : version2.length();
//              //return 0 if all the digits of version2 are 0
//              //return -1 if any non-0 found after the dots of version2.
//              while(true) {
//                  if(Integer.valueOf(version2.substring() ) != 0 ) return -1;
//                  version2 = version2.substring(dotIndex2 + 1, version2.length());
//                  
//              }
//              
//              return -1;
//          }
//          //version1 has more dots
//          if(version1.indexOf('.') != -1 && version2.indexOf('.') == -1) return 1;
//          //both of them do not have dots any more.
//          if(version1.indexOf('.') == -1 && version2.indexOf('.') == -1) return 0;
//          
//          /**
//           * both of them have more dots.
//           */
//          version1 = version1.substring(dotIndex1 + 1, version1.length());
//          version2 = version2.substring(dotIndex2 + 1, version2.length());
//      }//while ture

    }
}