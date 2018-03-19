package com.example.weibinwang.myapplication.RecommendationRestaurant.input;

import java.util.Map;
import java.util.Set;

/**
 * Created by weibinwang on 2018/3/7.
 */

public interface IReaderFormat {

    int readNombreUser();

    int readNomebreRes();

    Map<Long, Set<Long>> getMap();

    Set<Long> getSetObjectID(long id);

}
