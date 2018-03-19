package com.example.weibinwang.myapplication.Model.DatabaseOutils;

import com.example.weibinwang.myapplication.Bean.VoteNote;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by weibinwang on 2018/3/11.
 */

public interface VoteNoteOpe {

    long addVoteNote(VoteNote vn);

    void deleteVoteNote(long id);

    void updateVoteNote(VoteNote vn);

    List<VoteNote> queryListVoteNoteByOwner(long user_id);

    List<VoteNote> queryListVoteNoteByPurpose(long restaurant_id);

    boolean isExisted(long id);

    Map<Long, Set<Long>> queryTableUser_Res();

}
