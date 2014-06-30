/**
LRU Cache 

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: 
get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. 
When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
*/

/*
cevsekp 
The key to solve this problem is using a double linked list which enables us to quickly move nodes.
*/
/*
这题首先想到需要用HashMap<Key, node>，然后需要使用一个数据结构来表示这个node，采用DoubleLinkedList是因为双向链表的插入和删除操作比较简单。
这样get的时候根据提供的key,从HashMap中得到相应的node，然后从这个DoubleLinkedList中得到相应的值。
而DoubleLinkedList中让插入删除简单的方法是加入FakeHead和FakeTail。
有几种情况：
1，empty map
2，map contains X: set
3. map doesnot contain X
   1) map.size == capacity
   2) map.size < capacity


LinkedList插入删除比较快，分散空间
Array查找比较快。连续空间

*/

import java.util.HashMap;

public class LRUCache {
	private HashMap<Integer, DoubleLinkedListNode> map = new HashMap<Integer, DoubleLinkedListNode>();
	private DoubleLinkedListNode head;
	private DoubleLinkedListNode end;
	private int capacity;
	private int len;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        len = 0;
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
        	DoubleLinkedListNode latest = map.get(key);
        	removeNode(latest); //why remove and set head?删除这个节点在DoubledLinkedList里面原始的位置，
        	setHead(latest);//把这个节点放到DoubleLinkedList的头。因为LinkedList查找比较麻烦，要遍历，所以把最常用的直接放到头上就是LRU了
        	return latest.val;
        } else{
        	return -1;
        }
    }

    public void removeNode(DoubleLinkedListNode node){
    	DoubleLinkedListNode cur = node;
    	DoubleLinkedListNode pre = cur.pre;
    	DoubleLinkedListNode post = cur.next;

        //要改双向
    	if(pre != null){
    		pre.next = post;
    	}else{
    		head = post;
    	}

    	if(post != null){
    		post.pre = pre;
    	}else{
    		end = pre;
    	}
    }

    public void setHead(DoubleLinkedListNode node){
    	node.next = head;//insert node before head, here assign next
    	node.pre = null;
    	if(head != null){
    		head.pre = node; // here assign pre
    	}

    	head = node; //here assign node to head
    	if(end == null){ //if end is null, the node not only is head, but also acts as end
    		end = node;
    	}

    }
    
    public void set(int key, int value) {
        //map中间包含了这个key,此时需要找到这个key，然后改变value的值
        if(map.containsKey(key)){
        	DoubleLinkedListNode oldNode = map.get(key);//得到key对应的node
        	oldNode.val = value;//改变node的value值
        	removeNode(oldNode);//移除这个node
        	setHead(oldNode);//把这个node因为是最新使用了，所以把它设为头
        } else{//map中没有，就要插入。插入又分两种情况，一个是没满，一个是满了
        	DoubleLinkedListNode newNode = new DoubleLinkedListNode(key, value);
            //没满时直接设为头，然后map.put,len++
        	if(len < capacity){
        		setHead(newNode); // set the new node to head, why?
        		map.put(key, newNode);
        		len++;
        	} else{
                //满了的时候，就要先移除尾巴上用的最少的key，然后把尾巴前移，
        		map.remove(end.key); //remove the end
        		end = end.pre; //move the end to previous node
        		if(end != null){
        			end.next = null;// set the new end.next to null
        		}

                //把新的node加到头上，然后put
        		setHead(newNode);
        		map.put(key, newNode);
        	}
        }
    }
}

class DoubleLinkedListNode{
	public int val;
	public int key;
	public DoubleLinkedListNode pre;
	public DoubleLinkedListNode next;

	public DoubleLinkedListNode(int key, int value){
		val = value;
		this.key = key;
	}
}