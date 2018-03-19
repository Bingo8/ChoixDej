package com.example.weibinwang.myapplication.Model.DatabaseOutils;

import com.example.weibinwang.myapplication.Bean.Group;

/**
 * Created by Eliass on 28/02/2018.
 */

public interface GroupOpe {

    long addGroup(Group group);

    void updateGroup(Group group);

    void deleteGroupByName(String name);

    Group queryGroupByName(String name);

    boolean isExisted(String name);
}