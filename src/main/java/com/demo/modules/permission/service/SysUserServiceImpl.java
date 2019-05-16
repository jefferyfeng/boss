package com.demo.modules.permission.service;

import com.demo.core.base.PageBean;
import com.demo.core.constants.Constants;
import com.demo.core.util.CodeUtil;
import com.demo.core.util.MyUtil;
import com.demo.modules.per.exception.UserException;
import com.demo.modules.per.model.UserVo;
import com.demo.modules.permission.entity.SysUser;
import com.demo.modules.permission.dao.SysUserDao;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Date;

/**
 *  SysUserServiceImpl  实现类
 *
 *  @author fdh
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
public class SysUserServiceImpl implements SysUserService{
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 新增SysUser
     * @param sysUser
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void add(SysUser sysUser){
        UserVo userVo = (UserVo) MyUtil.getSession().getAttribute(Constants.SESSIOIN_USER);
        Date date = new Date();
        sysUser.setCreateDate(date);
        sysUser.setUpdateDate(date);
        sysUser.setCreateUser(userVo.getId());
        sysUser.setUpdateUser(userVo.getId());
        sysUser.setIsValid(1);
        sysUserDao.insert(sysUser);
    }

    /**
     * 根据主键 删除SysUser (逻辑删除)
     * @param id
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void remove(java.lang.Long id){
        UserVo userVo = (UserVo) MyUtil.getSession().getAttribute(Constants.SESSIOIN_USER);
        Date date = new Date();
        SysUser sysUser = new SysUser();
        sysUser.setIsValid(0);
        sysUser.setUpdateUser(userVo.getId());
        sysUser.setUpdateDate(date);
        sysUserDao.update(sysUser);
    }

    /**
     * 根据主键 删除SysUser (物理删除)
     * @param id
     */
    @Override
    public void delete(Long id) {
        sysUserDao.delete(id);
    }

    /**
     * 修改SysUser
     * @param sysUser
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void modify(SysUser sysUser){
        UserVo userVo = (UserVo) MyUtil.getSession().getAttribute(Constants.SESSIOIN_USER);
        Date date = new Date();
        sysUser.setUpdateDate(date);
        sysUser.setUpdateUser(userVo.getId());
        sysUserDao.update(sysUser);
    }

    /**
     * 根据主键查询SysUser
     * @param id
     * @return sysUser
     */
    @Override
    public SysUser queryOne(java.lang.Long id){
        return sysUserDao.queryOne(id);
    }

    /**
     * 根据主键查询SysUser
     * @return sysUsers
     */
    @Override
    public List<SysUser> queryAll(){
        return sysUserDao.queryAll();
    }

    /**
     * 根据字段查询（如需分页请setPageBean）
     * @param sysUser
     * @return sysUsers
     */
    @Override
    public List<SysUser> queryByFieldsAndPage(SysUser sysUser){
        return sysUserDao.queryByFieldsAndPage(sysUser);
    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void batchRemove(Long[] ids) {
        UserVo user = (UserVo) MyUtil.getSession().getAttribute("user");
        sysUserDao.batchRemove(ids,0,user.getId(),new Date());
    }

    /**
     * 批量修改状态
     * @param ids
     * @param status
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void batchModifyStatus(Long[] ids, Integer status) {
        UserVo userVo = (UserVo) MyUtil.getSession().getAttribute(Constants.SESSIOIN_USER);
        sysUserDao.batchModifyStatus(ids,status,userVo.getId(),new Date());
    }

    /**
     * 重置密码
     * @param userIds 重置密码的用户ids
     */
    @Override
    public void resetPassword(Long[] userIds) {
        if(MyUtil.isNotEmpty(userIds)){
            UserVo userVo = (UserVo) MyUtil.getSession().getAttribute(Constants.SESSIOIN_USER);
            for (Long id : userIds) {
                SysUser sysUser = new SysUser();
                String salt = CodeUtil.buildCode();//重置盐值
                String password = DigestUtils.md5Hex(Constants.DEFAULT_USER_PWD + salt);
                sysUser.setId(id);
                sysUser.setSalt(salt);
                sysUser.setPassword(password);
                sysUser.setUpdateDate(new Date());
                sysUser.setUpdateUser(userVo.getId());
                sysUserDao.update(sysUser);
            }
        }
    }

    /**
     * 批量删除用户
     * @param userIds 要删除的用户ids
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,readOnly = false)
    public void batchRemoveUsers(Long[] userIds) {
        SysUserService sysUserService = (SysUserService) AopContext.currentProxy();
        //删除用户
        sysUserService.batchRemove(userIds);
        //删除用户角色绑定关系
        sysUserRoleService.batchRemoveUserRoles(userIds, null);
    }

    /**
     * 添加用户
     * @param sysUser
     */
    @Override
    public void addUser(SysUser sysUser) {
        UserVo userVo = (UserVo) MyUtil.getSession().getAttribute(Constants.SESSIOIN_USER);
        Date date = new Date();
        //生成盐值
        String salt = CodeUtil.buildCode();
        sysUser.setSalt(salt);
        //生成MD5加密密码
        String md5Pwd = DigestUtils.md5Hex(sysUser.getPassword() + salt);
        sysUser.setPassword(md5Pwd);

        //维护修改信息
        sysUser.setCreateUser(userVo.getId());
        sysUser.setCreateDate(date);
        sysUser.setUpdateUser(userVo.getId());
        sysUser.setCreateDate(date);
        sysUser.setIsValid(1);
        sysUserDao.insert(sysUser);
    }

    /**
     * 获取用户数据表格 分页
     * @param user 查询条件
     * @param pageBean 分页条件
     * @return
     */
    @Override
    public List<UserVo> listUsersByPage(UserVo user, PageBean pageBean) {
        //预先获取总条数
        pageBean.setAll(sysUserDao.countListUsers(user));
        return sysUserDao.listUsersByPage(user,pageBean);
    }

    /**
     * 修改密码
     * @param userId 用户id
     * @param oldPwd 原始密码
     * @param newPwd 新密码
     */
    @Override
    public void changePwd(Long userId, String oldPwd, String newPwd) {
        UserVo userVo = (UserVo) MyUtil.getSession().getAttribute(Constants.SESSIOIN_USER);

        SysUser userQuery = sysUserDao.queryOne(userId);
        String oldMd5Pwd = DigestUtils.md5Hex(oldPwd + userQuery.getSalt());
        if( !oldMd5Pwd.equals(userQuery.getPassword()) ){
            throw new UserException("旧密码错误！");
        }
        String newSalt = CodeUtil.buildCode(4);

        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
        sysUser.setSalt(newSalt);
        sysUser.setPassword(DigestUtils.md5Hex(newPwd + newSalt));
        sysUser.setUpdateUser(userVo.getId());
        sysUser.setUpdateDate(new Date());
        sysUserDao.update(sysUser);
    }

    /**
     * 查询用户
     * @param userId
     * @return
     */
    @Override
    public UserVo queryUser(Long userId) {
        return sysUserDao.queryUser(userId);
    }
}