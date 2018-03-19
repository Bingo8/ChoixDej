package com.example.weibinwang.myapplication.RecommendationRestaurant.input;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.weibinwang.myapplication.Model.DatabaseOutils.VoteNoteOpe;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by weibinwang on 2018/3/7.
 * Firstly we need read the User data from database like user_id, user_name,...
 * Secondly the Restaurant data from database should be saved.
 * Finally, we create the format like |user_id|restaurant_id1|restaurant_id2|...
 */

public class ReaderFormatForUser implements IReaderFormat {

    private VoteNoteOpe voteNoteService;
    private Map<Long,Set<Long>> userItemsMap;

    public ReaderFormatForUser(VoteNoteOpe vns){
        this.voteNoteService = vns;
        this.userItemsMap = vns.queryTableUser_Res();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Long> readUsers() {

        return userItemsMap.keySet().stream().collect(Collectors.toList());
    }


    @Override
    public int readNombreUser() {

        return userItemsMap.keySet().size();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public int readNomebreRes() {
        int sum = 0;
        Iterator<Map.Entry<Long, Set<Long>>> iterator = userItemsMap.entrySet().iterator();
        while(iterator.hasNext()){
            int tmp = iterator.next().getValue().size();
            if(tmp > sum){
                sum = tmp;
            }
        }
        return sum;
    }

    @Override
    public Map<Long, Set<Long>> getMap() {

        return this.userItemsMap;
    }

    @Override
    public Set<Long> getSetObjectID(long id) {

        return userItemsMap.get(id);
    }


}
