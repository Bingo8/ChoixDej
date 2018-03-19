package com.example.weibinwang.myapplication.RecommendationRestaurant.similarity;

import com.example.weibinwang.myapplication.RecommendationRestaurant.input.IReaderFormat;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * Created by weibinwang on 2018/3/7.
 */
public class Similarity {

    private Map<Long, Set<Long>> userResMap;
    private int[][] sparseMatrix;
    private int nb_user;
    private int nb_res;
    private float[][] tableUserSimilarity;
    private float[][] tableItemSimilarity;

    public Similarity(IReaderFormat iReaderFormat){
        this.nb_res = iReaderFormat.readNomebreRes();
        this.nb_user = iReaderFormat.readNombreUser();
        this.userResMap = iReaderFormat.getMap();
        sparseMatrix = new int[nb_user+1][nb_res+1];

        tableUserSimilarity = new float[nb_user+1][nb_user+1];
        tableItemSimilarity = new float[nb_res+1][nb_res+1];
        initTable();
        for(int i=0; i< nb_user+1; i++ ){
            for(int j=0; j<nb_res+1; j++){
                sparseMatrix[i][j] = 0 ;
            }
        }

        createTableUserItems();
        userSimilarity();
        itemSimilarity();
    }


    private void createTableUserItems(){
        Set<Map.Entry<Long,Set<Long>>> entrySet = userResMap.entrySet();
        Iterator<Entry<Long, Set<Long>>> iterator = entrySet.iterator();

        Set<Long> itemsSet = new HashSet<>();

        while(iterator.hasNext()){
            Entry<Long,Set<Long>> entry = iterator.next();
            long user_id = entry.getKey();
            //System.out.println("user_id"+user_id);
            itemsSet = entry.getValue();

            Iterator<Long> ite_items = itemsSet.iterator();
            while(ite_items.hasNext()){
                sparseMatrix[(int)user_id][ite_items.next().intValue()] +=1;
            }

        }
    }

    private float distanceUser(int user1, int user2){
        float sum = (float) 0.0;
        for(int i=1 ; i < nb_res+1; i++){
            sum += Math.pow(sparseMatrix[user1][i]-sparseMatrix[user2][i], 2);
        }
        return (float) Math.sqrt(sum);
    }

    private float distanceItem(int item1, int item2){
        float sum = (float) 0.0;
        for(int i=1;i < nb_user+1;i++){
            sum += Math.pow(sparseMatrix[i][item1]-sparseMatrix[i][item2], 2);
        }
        return (float)Math.sqrt(sum);
    }

    private void initTable(){
        for(int i=0; i < nb_user+1;i++){
            for(int j =0;j < nb_user+1; j++){
                tableUserSimilarity[i][j] = (float)0.0;
            }
        }

        for(int i=0; i < nb_res+1;i++){
            for(int j=0; j < nb_res+1;j++){
                tableItemSimilarity[i][j] = (float)0.0;
            }
        }
    }

    public float[][] userSimilarity(){

        for(int i=1; i < nb_user+1; i++){
            for(int j=1; j< nb_user+1; j++){
                if(i == j){
                    tableUserSimilarity[i][j] = 1;
                }else{
                    //distance(sparseMatrix[i][],sparseMatrix[j][]
                    float distance = distanceUser(i,j);
                    tableUserSimilarity[i][j] = 1/(1+distance);
                }
            }
        }
        //setTableUserSimilarity(tableUserSimilarity);
        return tableUserSimilarity;
    }

    public float[][] itemSimilarity(){

        for(int i = 1; i < nb_res+1;i++){
            for(int j=1; j < nb_res+1; j++){
                float distance = distanceItem(i,j);
                tableItemSimilarity[i][j] = 1/(1+distance);
            }
        }
        return tableItemSimilarity;
    }

    public String userSimilarityToString(){
        String res = "";
        for(int i=1; i < nb_user+1; i++){
            res += "\n";
            for(int j = 1; j < nb_user+1; j++){
                res += " | "+tableUserSimilarity[i][j];
            }
            res += "|\n";
        }
        return res;
    }

    public String itemSimilarityToString(){
        String res = "";
        for(int i=1; i < nb_res+1; i++){
            res += "\n";
            for(int j = 1; j < nb_res+1; j++){
                res += " | "+ tableItemSimilarity[i][j];
            }
            res += "|\n";
        }
        return res;
    }

    public void setTableItemSimilarity(float[][] tableItemSimilarity){
        this.tableItemSimilarity = tableItemSimilarity;
    }

    public void setTableUserSimilarity(float[][] tableUserSimilarity){
        this.tableUserSimilarity = tableUserSimilarity;
    }

    public int getNombreUser(){
        return this.nb_user;
    }

    public int getNombreRes(){
        return this.nb_res;
    }

    public int[][] getSparseMatrix(){
        return this.sparseMatrix;
    }

    public String tableUserItemsToString(){
        String res = "";
        for(int i=1; i < nb_user+1; i++){
            res += "\n";
            for(int j = 1; j < nb_res+1; j++){
                res += " | "+ sparseMatrix[i][j];
            }
            res += "|\n";
        }
        return res;
    }

}
