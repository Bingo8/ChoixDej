package com.example.weibinwang.myapplication.RecommendationRestaurant.recommand;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.weibinwang.myapplication.RecommendationRestaurant.similarity.Similarity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by weibinwang on 2018/3/19.
 */

public class Recommandation {

    private Similarity similarity;
    private long user_id;
    private int[][] tableUserItem;
    private int nb_user;
    private int nb_res;

    public Recommandation(long id, Similarity sim){
        this.user_id = id;
        this.similarity = sim;
        this.tableUserItem = sim.getSparseMatrix();
        this.nb_user = similarity.getNombreUser();
        this.nb_res = similarity.getNombreRes();
    }

    public List<Long> recommendeRestaurantByUserSimilarity(){
        float[][] tableUserSim = similarity.userSimilarity();

        float max = (float)0.0;
        long id = (long)5;
        List<Long> lstItems = new ArrayList<>();

        for(int i=0; i < nb_user+1; i++){
            if(max < tableUserSim[(int) user_id][i] && i != user_id){
                max = tableUserSim[(int) user_id][i];
                id = i;
            }
        }

        //System.out.println("user similary"+id);

        for(int i=0; i < nb_res+1; i++){
            if((tableUserItem[(int) id][i] - tableUserItem[(int) user_id][i]) == 1)
                lstItems.add((long) i);
        }

        return lstItems;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Long> recommendeRestaurantByItemSimilarity(){
        List<Long> lstItems = new ArrayList<>();

        float[][] tableItemSim = similarity.itemSimilarity();
        float[] sum = new float[nb_res];
        float max = (float)0.0;
        //long id = (long)5;
        Map<Integer,Float> m = new HashMap<>();

        for(int i=0; i < nb_res; i++)
            sum[i] = (float)0.0;
        for(int i=0; i < nb_res; i++){
            for(int j=0; j < nb_res; j++){
                sum[i] += tableItemSim[i][j]*tableUserItem[(int)user_id][j];
            }
            m.put(i,sum[i]);
        }

        m = sortByValue(m);

        Iterator<Entry<Integer,Float>> iterator = m.entrySet().iterator();

        for(int i=0; i < 5;i++){
            if(iterator.hasNext())
                lstItems.add((long)iterator.next().getKey());
        }

        return lstItems;
    }

    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> unsortMap) {

        List<Map.Entry<K, V>> list =
                new LinkedList<Map.Entry<K, V>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return -(o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;

    }
}
