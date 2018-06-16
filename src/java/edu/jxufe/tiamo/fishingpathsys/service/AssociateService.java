package edu.jxufe.tiamo.fishingpathsys.service;

import edu.jxufe.tiamo.fishingpathsys.domain.DynamicState;
import edu.jxufe.tiamo.fishingpathsys.domain.DynamicStateComment;
import edu.jxufe.tiamo.fishingpathsys.domain.DynamicStateLike;

public interface AssociateService {

    // 发表动态
    DynamicState publishDynamicState(Short userId,String content);

    // 发表评论
    DynamicStateComment publishDynamicStateComment(Short userId,String content,Short dynamicStateId);

    // 点赞
    DynamicStateLike publishDynamicStateLike(Short userId, Short dynamicStateId);
}
