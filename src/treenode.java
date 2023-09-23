package poroje3;

public class treenode {
	Character ch ;
	Integer freq ;
	treenode left = null  , right = null ;
	treenode(char ch , Integer freq){
		this.ch = ch ;
		this.freq = freq ;
	}
	 public treenode(Character ch, Integer freq, treenode left, treenode right)
	    {
	        this.ch = ch;
	        this.freq = freq;
	        this.left = left;
	        this.right = right;
	    }

}
