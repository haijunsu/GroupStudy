import java.util.*;
/**
 * 369 ms
 * This is the best way to solve.
 * http://www.acmerblog.com/leetcode-lru-cache-lru-5745.html
 */
// public class LRUCache extends LinkedHashMap<Integer, Integer> {
// 	private int capacity;

// 	public LRUCache(int capacity){
// 		super(1, 0.75f, true);
// 		this.capacity = capacity;
// 	}

// 	public Integer get(Object key){
// 		Integer v = super.get(key);
// 		return (v != null)? v : -1;
// 	}

// 	public boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest){
// 		return this.size() > capacity;
// 	}

// 	public void set(int key, int value){
// 		super.put(key, value);
// 	}
// }


/**
 * 364 ms
 * This is the manual way to implement the LRUCache.
 * http://www.programcreek.com/2013/03/leetcode-lru-cache-java/
 * http://www.cnblogs.com/springfor/p/3869393.html
 */

public class LRUCache{
	private HashMap<Integer, DoubleLLNode> map
			= new HashMap<Integer, DoubleLLNode>();
	private DoubleLLNode head;
	private DoubleLLNode end;
	private int capacity;
	private int len;

	public LRUCache(int capacity){
		this.capacity = capacity;
		len = 0;
	}

	public void removeNode(DoubleLLNode n){
		DoubleLLNode pre = n.pre;
		DoubleLLNode post = n.next;

		if(pre != null){
			pre.next = post;
		}else{//n is head
			head = post;
		}

		if(post != null){
			post.pre = pre;
		}else{//n is end;
			end = pre;
		}
	}

	public void setHead(DoubleLLNode n){
		n.next = head;
		n.pre = null;
		if(head != null){
			head.pre = n;
		}
		head = n;
		if(end == null){//before this method call, the LL is empty.
			end = n;
		}
	}

	public int get(int key){
		if(map.containsKey(key)){
			DoubleLLNode latest = map.get(key);
			removeNode(latest);
			setHead(latest);
			return latest.value;
		}else{
			return -1;
		}
	}

	public void set(int key, int value){
		if(map.containsKey(key)){
			DoubleLLNode oldNode = map.get(key);
			oldNode.value = value;
			removeNode(oldNode);
			setHead(oldNode);
		}else{//No key found
			DoubleLLNode newNode = new DoubleLLNode(key, value);
			if(len < capacity){
				map.put(key, newNode);
				setHead(newNode);
				len++;
			}else{//full
				map.remove(end.key);
				end = end.pre;
				if(end != null)
				    end.next = null;
				map.put(key, newNode);
				setHead(newNode);
			}
		}
	}
}





class DoubleLLNode{
	public int key;
	public int value;
	public DoubleLLNode pre;
	public DoubleLLNode next;

	public DoubleLLNode(int key, int value){
		this.value = value;
		this.key = key;
	}
}







/**
 * 1st trail FAIL.
 */
//public class LRUCache {
    
    
//     private int max, elements = 0;
//     private Hashtable<Integer, Integer> ht;
//     private DoubleLLNode head, tail;
    
//     // private int lruKey;
    
//     public LRUCache(int capacity) {
//         max = capacity;
//         ht = new Hashtable<Integer, Integer>();
//     }
    
//     public int get(int key) {
//         if(!ht.containsKey(key)) return -1;
        
//         lruKey = key;
        
//         int val = ht.get(key);
        
//         ht.remove(key);
//         elements--;
        
//         return val;
//     }
//     /**
//      * Set or insert the value if the key is not already present. 
//      * When the cache reached its capacity, 
//      *      it should invalidate the least recently used item before inserting a new item.
//      */
//     public void set(int key, int value) {
//         if(ht.containsKey(key)){
//             //No need to consider the capacity of the LL.
//             ht.put(key, value);//overwrite the key value pair.
//             removeNode(key);
//             addOnHead(key);
            
//         }else{//no this key found. Needs to add.
//             DoubleLLNode newNode = new DoubleLLNode(key);//wrap the key into DoubleLLNode.
//             if(elements < max){
//                 //add this newNode to the head of the doubleLL.
//                 addOnHead(newNode);
//                 elements++;
//                 ht.put(key, value);
//             }else{//full
//                 ht.remove(end.key);
//                 ht.put(key, value);
                
//                 //remove the end node of DoubleLL
//                 end = end.pre;
//                 if(end != null)
//                     end.next = null;
//                 addOnHead(key, value);
//             }
//         }
        
        
        
//         // if(! ht.containsKey(key)){
//         //     if(elements < max){//not full
//         //         lruKey = key;
//         //         ht.put(key, value);
//         //         elements++;
//         //     }else{//full
//         //         ht.remove(lruKey);
//         //         ht.put(key, value);
//         //         lruKey = key;
//         //     }
//         // }else{//has this key
//         //     if(elements < max){//not full
//         //         lruKey = key;
//         //         ht.put(key, value);
//         //         elements++;
//         //     }else{//full
//         //         ht.remove(lruKey);
//         //         ht.put(key, value);
//         //         lruKey = key;
//         //     }
//         // }
        
//     }
//     public void addOnHead(DoubleLLNode n){
//         n.next = head;
//         if(head != null)
//             head.pre = n;
//         head = n;
//         if(end == null)
//             end = n;
//     }
//     public void removeNode(DoubleLLNode n){
//         DoubleLLNode preNode = n.pre;
//         // DoubleLLNode cur = n;
//         DoubleLLNode nextNode = n.next;
        
//         //deal with the preNode;
//         if(preNode != null){
//             preNode.next = nextNode;
//             //nextNode.pre = preNode;
//         }else{
//             head = nextNode;
//         }
        
//         //deal with the nextNode;
//         if(nextNode != null){
//             nextNode.pre = preNode;
//         }else{//n is the end
//             end = preNode;
//         }
//     }
// }

// public class DoubleLLNode{
//     public int key;
//     public DoubleLLNode pre;
//     public DoubleLLNode next;
    
//     public DoubleLLNode(int key){
//         this.key = key;
//         pre = null;
//         next = null;
//     }
//}