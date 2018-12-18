package com.alexvasilkov.foldablelayout.sample.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRecommand {


    public static List<Long> recommandUser(User user,int top_n) {

        List<Map.Entry<Long, Double>> commonFriends = getNeighbourScores(user);
        List<Map.Entry<Long, Double>> commonTags = getTagScores(user);

        List<Long>ids = new ArrayList<>();
        for(int i=0;i<top_n;i++){
            ids.add(commonFriends.get(i).getKey());
        }

        for(int i=0;i<top_n;i++){
            ids.add(commonTags.get(i).getKey());
        }
        return ids;
    }

    private static List<Map.Entry<Long, Double>> getNeighbourScores(User user) {

        HashMap<Long, Double> scores = new HashMap<Long, Double>();
        for (Card card : user.cards) {
            double score = 1.0 / Math.log(card.getSharedto().size());
            for (long id : card.getSharedto()) {
                scores.put(id, scores.get(id) + score);
            }
        }
        List<Map.Entry<Long, Double>> list = new ArrayList<>(scores.entrySet());
        Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        return list;
    }


    private static List<Map.Entry<Long, Double>> getTagScores(User user) {
        HashMap<Long, Double> scores = new HashMap<Long, Double>();
        double totalCnt = 0;
        for (Tag tag : user.tags)
            totalCnt += tag.count;

        for (Tag tag : user.tags) {
            double score = ((double) tag.count) / (totalCnt * Math.log(tag.getTaggedto().size()));
            for (long id : tag.getTaggedto()) {
                scores.put(id, scores.get(id) + score);
            }
        }

        List<Map.Entry<Long, Double>> list = new ArrayList<>(scores.entrySet());
        Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        return list;
    }
}