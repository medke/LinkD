
public class jaroWinkler {
	public double jaroWinkler(String s1, String s2)
	{
		return similarity (s1,s2);
	}
	  public static double similarity(String s1, String s2) {
		    if (s1.equals(s2))
		      return 1.0;

		    if (s1.length() > s2.length()) {
		      String tmp = s2;
		      s2 = s1;
		      s1 = tmp;
		    }

		    int maxdist = s2.length() / 2;
		    int c = 0; // count of common characters
		    int t = 0; // count of transpositions
		    int prevpos = -1;
		    for (int ix = 0; ix < s1.length(); ix++) {
		      char ch = s1.charAt(ix);

		      // now try to find it in s2
		      for (int ix2 = Math.max(0, ix - maxdist);
		           ix2 < Math.min(s2.length(), ix + maxdist);
		           ix2++) {
		        if (ch == s2.charAt(ix2)) {
		          c++; // we found a common character
		          if (prevpos != -1 && ix2 < prevpos)
		            t++; // moved back before earlier 
		          prevpos = ix2;
		          break;
		        }
		      }
		    }

		 
		    if (c == 0)
		      return 0.0;

		    // first compute the score
		    double score = ((c / (double) s1.length()) +
		                    (c / (double) s2.length()) +
		                    ((c - t) / (double) c)) / 3.0;

		    // (2) common prefix modification
		    int p = 0; // length of prefix
		    int last = Math.min(4, s1.length());
		    for (; p < last && s1.charAt(p) == s2.charAt(p); p++)
		      ;

		    score = score + ((p * (1 - score)) / 10);

		    return score;
		  }

}
