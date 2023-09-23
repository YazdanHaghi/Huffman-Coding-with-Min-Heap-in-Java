package poroje3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class huffman {
	static String s ;
	static String s1  ;
	Scanner scanner = new Scanner(System.in) ;
	public static void encode(treenode root, String str,
            Map<Character, String> huffmanCode){
if (root == null) {
return;
}
if (root.left == null && root.right == null) {
huffmanCode.put(root.ch, str.length() > 0 ? str : "1");
}

encode(root.left, str + '0', huffmanCode);
encode(root.right, str + '1', huffmanCode);
}
	public static int decode(treenode root, int index, String sb){
        if (root == null) {
            return index;
        }
        if (root.left == null && root.right == null)
        {
            System.out.print(root.ch);
            s1 += Character.toString(root.ch) ;
            return index;
        }
 
        index++;
 
        root = (sb.charAt(index) == '0') ? root.left : root.right;
        index = decode(root, index, sb);
        return index;
    }
	
	public static void dbuildtree(int x , String sb) {
		Scanner scanner = new Scanner(System.in) ;
		Map<Character, Integer> freq = new HashMap<>();
		System.out.println("ENTER WORD");
		String yz = scanner.nextLine() ;
        	for (int i = 0 ; i < x ; i ++) {
        		freq.put( yz.charAt(i) , scanner.nextInt() );
        	}
        PriorityQueue<treenode> pq;
        pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.freq));
        for (var entry: freq.entrySet()) {
            pq.add(new treenode(entry.getKey(), entry.getValue()));
        	}
        while (pq.size() != 1) {
        	treenode left = pq.poll();
            treenode right = pq.poll();
            int sum = left.freq + right.freq;
            pq.add(new treenode(null, sum, left, right));
        }
        treenode root = pq.peek();
        if (root.left == null && root.right == null)
        {

            while (root.freq-- > 0) {
                 System.out.print(root.ch);
                
            }
        }
        else {            
            int index = -1;
            while (index < sb.length() - 1) {
                index = decode(root, index, sb);
            }
        }
        
	}
	public static void ebuildtree(String text) {
		if(text == null || text.length() == 0) {
			return ;
		}
		Map<Character, Integer> freq = new HashMap<>();
        	for (char c: text.toCharArray()) {
        		freq.put(c, freq.getOrDefault(c, 0) + 1);
        	}
        PriorityQueue<treenode> pq;
        pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.freq));
        for (var entry: freq.entrySet()) {
            pq.add(new treenode(entry.getKey(), entry.getValue()));
        	}
        while (pq.size() != 1) {
        	treenode left = pq.poll();
            treenode right = pq.poll();
            int sum = left.freq + right.freq;
            pq.add(new treenode(null, sum, left, right));
        }
        treenode root = pq.peek();
        Map<Character, String> huffmanCode = new HashMap<>();
        encode(root, "", huffmanCode);
        StringBuilder sb = new StringBuilder();
        for (char c: text.toCharArray()) {
            sb.append(huffmanCode.get(c));
            s = sb.toString() ;
        }
        Scanner scanner = new Scanner(System.in) ;
        System.out.println("ENTER \n1) IF YOU WANT TO SEE HUFFMAN CODE \n2) IF YOU WANT TO SEE ENCODED STRING \n3) IF YOU WAN TO SEE BOTH ");
        int x = scanner.nextInt() ;
        switch(x) {
        case 1 :
        	System.out.println(huffmanCode);
        	System.out.println("NUMBERS OF THE BITS AFTER ENCODE " + sb.length());
        	break ;
        case 2 :
        	System.out.println(sb);
        	System.out.println("NUMBERS OF THE BITS AFTER ENCODE " + sb.length());
        	break ;
        case 3 :
        	System.out.println(huffmanCode + "\n" + sb);
        	System.out.println("NUMBERS OF THE BITS AFTER ENCODE " + sb.length());
        }
      
	}
	 public static String readFileAsString(String fileName)throws Exception
	  {
	    String data = "";
	    data = new String(Files.readAllBytes(Paths.get(fileName)));
	    return data;
	  }
	public static void main (String [] args) throws Exception {
		Scanner scanner = new Scanner(System.in) ;
		System.out.println("ENTER\n1) IF YOU WANT TO ENCODE THE TEXT OF MAIN.TXT\n2)ENTER 2 IF YOU WANT TO DECODE");
		int x = scanner.nextInt() ;
		switch(x) {
		case 1 :
			String data = readFileAsString("C:\\Users\\Asus\\eclipse-workspace\\poroje3\\src\\poroje3\\main.txt");
		    ebuildtree(data);
		    int tmp = data.length() ;
		    System.out.println("NUMBERS OF THE BITS BEFOR ENCODE " +tmp*8);
		    FileWriter fw=new FileWriter("C:\\Users\\Asus\\eclipse-workspace\\poroje3\\src\\poroje3\\encode.txt");		     
	        for (int i = 0; i < s.length(); i++)
	            fw.write(s.charAt(i));
	        fw.close();
		    break ;
		case 2 :
			System.out.println("PLEASE ENTER the WORDS AND THEY FREQEUNCY WHITOUT SPACE AND CODE");
			s1 = "" ;
			int t = scanner.nextInt() ;
			String temp = scanner.next() ;
			 dbuildtree(t, temp);
			FileWriter fw1=new FileWriter("C:\\Users\\Asus\\eclipse-workspace\\poroje3\\src\\poroje3\\decode.txt");		     
	        for (int i = 0; i < s1.length(); i++)
	            fw1.write(s1.charAt(i));
	        fw1.close();
			
		}
		
		
	}
}

