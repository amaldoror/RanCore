package org.rancore.ad.ad_3_1;

import edu.princeton.cs.algs4.StdIn;

public class FrequencyCounter {
    public static void main(String[] args) {
        int type = Integer.parseInt(args[0]);
        int minlen = Integer.parseInt(args[1]);
        STInterface<String,Integer> st= STFactory.getSTInstance(type);
        while(!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() < minlen) continue;
            if (!st.contains(word)) st.put(word,1);
            else st.put(word, st.get(word) +1);
        }
        String max = ""; st.put(max,0);
        int total=0;
        for (String word: st.keys()){
            int numWord = st.get(word);
            if (numWord> st.get(max)) max = word;
            total+=numWord;
        }
        System.out.printf("total:%d diff:%d\n%s %d",total-1,st.size()-1, max,st.get(max));
    }
}
