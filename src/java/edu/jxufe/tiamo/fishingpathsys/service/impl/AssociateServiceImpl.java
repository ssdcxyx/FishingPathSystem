package edu.jxufe.tiamo.fishingpathsys.service.impl;

import edu.jxufe.tiamo.fishingpathsys.dao.DynamicStateCommentDao;
import edu.jxufe.tiamo.fishingpathsys.dao.DynamicStateDao;
import edu.jxufe.tiamo.fishingpathsys.dao.DynamicStateLikeDao;
import edu.jxufe.tiamo.fishingpathsys.dao.UserDao;
import edu.jxufe.tiamo.fishingpathsys.domain.DynamicState;
import edu.jxufe.tiamo.fishingpathsys.domain.DynamicStateComment;
import edu.jxufe.tiamo.fishingpathsys.domain.DynamicStateLike;
import edu.jxufe.tiamo.fishingpathsys.domain.User;
import edu.jxufe.tiamo.fishingpathsys.exception.CustomException;
import edu.jxufe.tiamo.fishingpathsys.service.AssociateService;
import edu.jxufe.tiamo.util.Logger;
import edu.jxufe.tiamo.util.OtherAPIRequestUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class AssociateServiceImpl implements AssociateService {

    private UserDao userDao;
    private DynamicStateDao dynamicStateDao;
    private DynamicStateCommentDao dynamicStateCommentDao;
    private DynamicStateLikeDao dynamicStateLikeDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setDynamicStateDao(DynamicStateDao dynamicStateDao) {
        this.dynamicStateDao = dynamicStateDao;
    }

    public void setDynamicStateCommentDao(DynamicStateCommentDao dynamicStateCommentDao) {
        this.dynamicStateCommentDao = dynamicStateCommentDao;
    }

    public void setDynamicStateLikeDao(DynamicStateLikeDao dynamicStateLikeDao) {
        this.dynamicStateLikeDao = dynamicStateLikeDao;
    }

    @Override
    public DynamicState publishDynamicState(Short userId, String content) {
        try{
            DynamicState dynamicState = new DynamicState();
            dynamicState.setUser(userDao.get(User.class,userId));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dynamicState.setTime(simpleDateFormat.format(new Date()));
            dynamicState.setContent(content);
            return dynamicStateDao.get(DynamicState.class,dynamicStateDao.save(dynamicState));
        }catch (Exception ex){
            Logger.Log.error("publishDynamicState:"+ex);
            ex.printStackTrace();
            throw new CustomException("发表动态时出现异常，请通知管理员！");
        }
    }

    @Override
    public DynamicStateComment publishDynamicStateComment(Short userId, String content, Short dynamicStateId) {
        try{
            DynamicStateComment dynamicStateComment = new DynamicStateComment();
            dynamicStateComment.setUser(userDao.get(User.class,userId));
            dynamicStateComment.setDynamicState(dynamicStateDao.get(DynamicState.class,dynamicStateId));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dynamicStateComment.setTime(simpleDateFormat.format(new Date()));
            dynamicStateComment.setContent(content);
            return dynamicStateCommentDao.get(DynamicStateComment.class,dynamicStateCommentDao.save(dynamicStateComment));
        }catch (Exception ex){
            Logger.Log.error("publishDynamicStateComment:"+ex);
            ex.printStackTrace();
            throw new CustomException("发表评论时出现异常，请通知管理员！");
        }
    }

    @Override
    public DynamicStateLike publishDynamicStateLike(Short userId, Short dynamicStateId) {
        try{
            List<DynamicStateLike> dynamicStateLikeList = dynamicStateLikeDao.findDynamicStateLikeByUserIdAndDynamicStateId(userId,dynamicStateId);
            if(dynamicStateLikeList!=null&&dynamicStateLikeList.size()>0){
                DynamicStateLike dynamicStateLike = dynamicStateLikeList.get(0);
                dynamicStateLikeDao.delete(dynamicStateLike);
                return null;
            }else{
                DynamicStateLike dynamicStateLike = new DynamicStateLike();
                dynamicStateLike.setUser(userDao.get(User.class,userId));
                dynamicStateLike.setDynamicState(dynamicStateDao.get(DynamicState.class,dynamicStateId));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                dynamicStateLike.setTime(simpleDateFormat.format(new Date()));
                return dynamicStateLikeDao.get(DynamicStateLike.class,dynamicStateLikeDao.save(dynamicStateLike));
            }
        }catch (Exception ex){
            Logger.Log.error("publishDynamicStateLike:"+ex);
            ex.printStackTrace();
            throw new CustomException("点赞时出现异常，请通知管理员！");
        }
    }
}
