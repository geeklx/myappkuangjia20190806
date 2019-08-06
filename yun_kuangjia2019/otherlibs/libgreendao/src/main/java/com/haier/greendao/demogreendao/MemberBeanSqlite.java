package com.haier.greendao.demogreendao;

import android.content.Context;
import android.widget.Toast;

import com.haier.greendao.autoputgreendao.MemberDao;
import com.haier.greendao.demogreendao.bean.Member;
import com.haier.greendao.greendaoutil.DbManager;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;


public class MemberBeanSqlite {

    /**
     * 判断是否存在这条记录
     *
     * @param context
     * @param address
     * @return
     */
    public static boolean is_cunzai(Context context, Member address) {
        if (address == null) {
            Toast.makeText(context, "用户不存在", Toast.LENGTH_SHORT).show();
            return false;
        }
        Member user = DbManager.getDaoSession(context).getMemberDao().queryBuilder().where(MemberDao.Properties.Id.eq(address.getId() + "")).build().unique();
        if (user == null) {
            Toast.makeText(context, "用户不存在", Toast.LENGTH_SHORT).show();
        } else {
            return true;
        }
        return false;
    }

    /**
     * 判断是否存在这条记录
     *
     * @param context
     * @param id
     * @return
     */
    public static boolean is_cunzai2(Context context, String id) {
        Member user = DbManager.getDaoSession(context).getMemberDao().queryBuilder().where(MemberDao.Properties.Id.eq(id + "")).build().unique();
        if (user == null) {
            Toast.makeText(context, "用户不存在", Toast.LENGTH_SHORT).show();
        } else {
            return true;
        }
        return false;
    }


    /**
     * 查询所有数据
     *
     * @param context
     * @return
     */
    public static List<Member> queryAll(Context context) {
        QueryBuilder<Member> builder = DbManager.getDaoSession(context).getMemberDao().queryBuilder();
        return builder.list();
    }

    /**
     * 添加数据至数据库
     *
     * @param context
     * @param stu
     */
    public static void insertData(Context context, Member stu) {
        DbManager.getDaoSession(context).getMemberDao().insert(stu);
        DbManager.getDaoSession(context).clear();
    }


    /**
     * 将数据实体通过事务添加至数据库
     *
     * @param context
     * @param list
     */
    public static void insertData(Context context, List<Member> list) {
        if (null == list || list.size() <= 0) {
            return;
        }
        DbManager.getDaoSession(context).getMemberDao().insertInTx(list);
    }


    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
     *
     * @param context
     * @param address
     */
    public static void saveData(Context context, Member address) {
        DbManager.getDaoSession(context).getMemberDao().save(address);
    }

    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
     *
     * @param context
     */
    public static void saveData(Context context, List<Member> list) {
        DbManager.getDaoSession(context).getMemberDao().saveInTx(list);
    }

    /**
     * 删除数据至数据库
     *
     * @param context
     * @param address 删除具体内容
     */
    public static void deleteData(Context context, Member address) {
        if (!is_cunzai(context, address)) {
            return;
        }
        DbManager.getDaoSession(context).getMemberDao().delete(address);
    }

    /**
     * 根据id删除数据至数据库
     *
     * @param context
     * @param id      删除具体内容
     */
    public static void deleteByKeyData(Context context, String id) {
        if (!is_cunzai2(context, id)) {
            return;
        }
        DbManager.getDaoSession(context).getMemberDao().deleteByKey(id);
    }

    /**
     * 删除全部数据
     *
     * @param context
     */
    public static void deleteAllData(Context context) {
        DbManager.getDaoSession(context).getMemberDao().deleteAll();
    }

    /**
     * 修改后增加单个数据
     *
     * @param context
     */
    public static void changeData(Context context, Member address) {
        if (!is_cunzai(context, address)) {
            return;
        }
        DbManager.getDaoSession(context).getMemberDao().insertOrReplace(address);
    }

    /**
     * 修改后增加单个数据2
     *
     * @param context
     */
    public static void changeData2(Context context, Member address) {
        DbManager.getDaoSession(context).getMemberDao().insertOrReplace(address);
    }

    /**
     * 更新数据库
     *
     * @param context
     * @param address
     */
    public static void updateData(Context context, Member address) {
        if (!is_cunzai(context, address)) {
            return;
        }
        DbManager.getDaoSession(context).getMemberDao().update(address);
    }

    /**
     * 根据id，其他的字段类似 查询
     *
     * @param context
     * @param id
     * @return
     */
    public static List<Member> queryListForId(Context context, long id) {
        QueryBuilder<Member> builder = DbManager.getDaoSession(context).getMemberDao().queryBuilder();
        /**
         * 返回当前id的数据集合,当然where(这里面可以有多组，做为条件);
         * 这里build.list()；与where(addressDao.Properties.Id.eq(id)).list()结果是一样的；
         * 在QueryBuilder类中list()方法return build().list();
         *
         */
        // Query<address> build = builder.where(addressDao.Properties.Id.eq(id)).build();
        // List<address> list = build.list();
        return builder.where(MemberDao.Properties.Id.eq(id)).list();
    }

    /**
     * 判断是否存在这条记录
     *
     * @param context
     * @param id
     * @return
     */
    public static Member queryBeanForId(Context context, long id) {
        Member user = DbManager.getDaoSession(context).getMemberDao().queryBuilder().
                where(MemberDao.Properties.Id.eq(id + "")).build().unique();
        if (user == null) {
            Toast.makeText(context, "用户不存在", Toast.LENGTH_SHORT).show();
            return null;
        }
        return user;
    }

}
