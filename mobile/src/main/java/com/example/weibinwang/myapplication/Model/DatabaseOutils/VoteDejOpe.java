package com.example.weibinwang.myapplication.Model.DatabaseOutils;

import com.example.weibinwang.myapplication.Bean.VoteDej;

import java.util.List;

/**
 * Created by weibinwang on 2018/2/28.
 */

public interface VoteDejOpe  {
    long addVoteDej(VoteDej votedej);

    void updateVoteDej(VoteDej votedej);

    void deleteVoteDej(long id);

    VoteDej queryVoteDejById(long id);

    List<VoteDej> queryVoteDejByPurpose(String restaurantName);


}
