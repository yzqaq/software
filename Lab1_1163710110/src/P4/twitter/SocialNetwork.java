/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package src.P4.twitter;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even
 * exist as a key in the map; this is true even if A is followed by other people
 * in the network. Twitter usernames are not case sensitive, so "ernie" is the
 * same as "ERNie". A username should appear at most once as a key in the map or
 * in any given map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

    /**
     * x Guess who might follow whom, from evidence found in tweets.
     * 
     * @param tweets
     *            a list of tweets providing the evidence, not modified by this
     *            method.
     * @return a social network (as defined above) in which Ernie follows Bert if
     *         and only if there is evidence for it in the given list of tweets. One
     *         kind of evidence that Ernie follows Bert is if Ernie
     * @-mentions Bert in a tweet. This must be implemented. Other kinds of evidence
     *            may be used at the implementor's discretion. All the Twitter
     *            usernames in the returned social network must be either authors
     *            or @-mentions in the list of tweets.
     */
    public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
        // throw new RuntimeException("not implemented");
        Map<String, Set<String>> follows = new HashMap<String, Set<String>>();
        /*
         * 重建tweets，并将username 和text改为全小写;
         */
        List<Tweet> tweetsNoSentive = new ArrayList<Tweet>();
        for (int i = 0; i < tweets.size(); i++) {
            Tweet tweet = new Tweet(tweets.get(i).getId(), tweets.get(i).getAuthor().toLowerCase(),
                    tweets.get(i).getText().toLowerCase(), tweets.get(i).getTimestamp());
            tweetsNoSentive.add(tweet);
        }
        for (int j = 0; j < tweetsNoSentive.size(); j++) {
            String name = tweetsNoSentive.get(j).getAuthor();
            if (!follows.containsKey(name)) {
                follows.put(name, Extract.getMentionedUsers(Filter.writtenBy(tweetsNoSentive, name)));
                /*
                 * 不能follw自己
                 */
                if (follows.get(name).contains(name)) {
                    follows.get(name).remove(name);
                }
            }
        }
        return follows;
    }

    /**
     * Find the people in a social network who have the greatest influence, in the
     * sense that they have the most followers.
     * 
     * @param followsGraph
     *            a social network (as defined above)
     * @return a list of all distinct Twitter usernames in followsGraph, in
     *         descending order of follower count.
     */
    public static List<String> influencers(Map<String, Set<String>> followsGraph) {
        // throw new RuntimeException("not implemented");
        List<String> influencersList = new ArrayList<String>();
        Map<String, Integer> map = new TreeMap<String, Integer>();
        /*
         * 初始化map
         */
        for (String key : map.keySet()) {
            map.put(key, 0);

        }
        for (Set<String> value : followsGraph.values()) {
            for (String str : value) {
                if (!map.containsKey(str))
                    map.put(str, 0);
                else
                    map.put(str, map.get(str) + 1);
            }
        }
        // 升序比较器
        Comparator<Map.Entry<String, Integer>> valueComparator = new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                // TODO Auto-generated method stub
                return o2.getValue() - o1.getValue();
            }
        };
        // map转换成list进行排序
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        // 排序
        Collections.sort(list, valueComparator);
        // 默认情况下，TreeMap对key进行升序排序
        for (Map.Entry<String, Integer> entry : list) {
            influencersList.add(entry.getKey());
        }

        return influencersList;
    }

}
