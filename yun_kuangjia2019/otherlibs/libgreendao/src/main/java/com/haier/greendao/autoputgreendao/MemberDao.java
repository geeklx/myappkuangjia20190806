package com.haier.greendao.autoputgreendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.haier.greendao.demogreendao.bean.Member;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MEMBER".
*/
public class MemberDao extends AbstractDao<Member, String> {

    public static final String TABLENAME = "MEMBER";

    /**
     * Properties of entity Member.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, String.class, "id", true, "ID");
        public final static Property Nickname = new Property(1, String.class, "nickname", false, "NICKNAME");
        public final static Property HeadImgUrl = new Property(2, String.class, "headImgUrl", false, "HEAD_IMG_URL");
        public final static Property Sex = new Property(3, String.class, "sex", false, "SEX");
        public final static Property Weight = new Property(4, double.class, "weight", false, "WEIGHT");
        public final static Property Height = new Property(5, String.class, "height", false, "HEIGHT");
        public final static Property Age = new Property(6, int.class, "age", false, "AGE");
        public final static Property Num = new Property(7, String.class, "num", false, "NUM");
        public final static Property DeviceNum = new Property(8, String.class, "deviceNum", false, "DEVICE_NUM");
        public final static Property CourseId = new Property(9, String.class, "courseId", false, "COURSE_ID");
        public final static Property CourseType = new Property(10, String.class, "courseType", false, "COURSE_TYPE");
        public final static Property Hr = new Property(11, int.class, "hr", false, "HR");
        public final static Property Bmi = new Property(12, double.class, "bmi", false, "BMI");
        public final static Property Tizhi = new Property(13, double.class, "tizhi", false, "TIZHI");
        public final static Property Cal = new Property(14, double.class, "cal", false, "CAL");
        public final static Property FirstTimestamp = new Property(15, long.class, "firstTimestamp", false, "FIRST_TIMESTAMP");
        public final static Property LastTimestamp = new Property(16, long.class, "lastTimestamp", false, "LAST_TIMESTAMP");
        public final static Property CurrentTimestamp = new Property(17, long.class, "currentTimestamp", false, "CURRENT_TIMESTAMP");
        public final static Property Rank = new Property(18, int.class, "rank", false, "RANK");
        public final static Property Maxhr = new Property(19, int.class, "maxhr", false, "MAXHR");
        public final static Property OldIndex = new Property(20, int.class, "oldIndex", false, "OLD_INDEX");
    }


    public MemberDao(DaoConfig config) {
        super(config);
    }
    
    public MemberDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MEMBER\" (" + //
                "\"ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: id
                "\"NICKNAME\" TEXT," + // 1: nickname
                "\"HEAD_IMG_URL\" TEXT," + // 2: headImgUrl
                "\"SEX\" TEXT," + // 3: sex
                "\"WEIGHT\" REAL NOT NULL ," + // 4: weight
                "\"HEIGHT\" TEXT," + // 5: height
                "\"AGE\" INTEGER NOT NULL ," + // 6: age
                "\"NUM\" TEXT," + // 7: num
                "\"DEVICE_NUM\" TEXT," + // 8: deviceNum
                "\"COURSE_ID\" TEXT," + // 9: courseId
                "\"COURSE_TYPE\" TEXT," + // 10: courseType
                "\"HR\" INTEGER NOT NULL ," + // 11: hr
                "\"BMI\" REAL NOT NULL ," + // 12: bmi
                "\"TIZHI\" REAL NOT NULL ," + // 13: tizhi
                "\"CAL\" REAL NOT NULL ," + // 14: cal
                "\"FIRST_TIMESTAMP\" INTEGER NOT NULL ," + // 15: firstTimestamp
                "\"LAST_TIMESTAMP\" INTEGER NOT NULL ," + // 16: lastTimestamp
                "\"CURRENT_TIMESTAMP\" INTEGER NOT NULL ," + // 17: currentTimestamp
                "\"RANK\" INTEGER NOT NULL ," + // 18: rank
                "\"MAXHR\" INTEGER NOT NULL ," + // 19: maxhr
                "\"OLD_INDEX\" INTEGER NOT NULL );"); // 20: oldIndex
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MEMBER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Member entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String nickname = entity.getNickname();
        if (nickname != null) {
            stmt.bindString(2, nickname);
        }
 
        String headImgUrl = entity.getHeadImgUrl();
        if (headImgUrl != null) {
            stmt.bindString(3, headImgUrl);
        }
 
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(4, sex);
        }
        stmt.bindDouble(5, entity.getWeight());
 
        String height = entity.getHeight();
        if (height != null) {
            stmt.bindString(6, height);
        }
        stmt.bindLong(7, entity.getAge());
 
        String num = entity.getNum();
        if (num != null) {
            stmt.bindString(8, num);
        }
 
        String deviceNum = entity.getDeviceNum();
        if (deviceNum != null) {
            stmt.bindString(9, deviceNum);
        }
 
        String courseId = entity.getCourseId();
        if (courseId != null) {
            stmt.bindString(10, courseId);
        }
 
        String courseType = entity.getCourseType();
        if (courseType != null) {
            stmt.bindString(11, courseType);
        }
        stmt.bindLong(12, entity.getHr());
        stmt.bindDouble(13, entity.getBmi());
        stmt.bindDouble(14, entity.getTizhi());
        stmt.bindDouble(15, entity.getCal());
        stmt.bindLong(16, entity.getFirstTimestamp());
        stmt.bindLong(17, entity.getLastTimestamp());
        stmt.bindLong(18, entity.getCurrentTimestamp());
        stmt.bindLong(19, entity.getRank());
        stmt.bindLong(20, entity.getMaxhr());
        stmt.bindLong(21, entity.getOldIndex());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Member entity) {
        stmt.clearBindings();
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(1, id);
        }
 
        String nickname = entity.getNickname();
        if (nickname != null) {
            stmt.bindString(2, nickname);
        }
 
        String headImgUrl = entity.getHeadImgUrl();
        if (headImgUrl != null) {
            stmt.bindString(3, headImgUrl);
        }
 
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(4, sex);
        }
        stmt.bindDouble(5, entity.getWeight());
 
        String height = entity.getHeight();
        if (height != null) {
            stmt.bindString(6, height);
        }
        stmt.bindLong(7, entity.getAge());
 
        String num = entity.getNum();
        if (num != null) {
            stmt.bindString(8, num);
        }
 
        String deviceNum = entity.getDeviceNum();
        if (deviceNum != null) {
            stmt.bindString(9, deviceNum);
        }
 
        String courseId = entity.getCourseId();
        if (courseId != null) {
            stmt.bindString(10, courseId);
        }
 
        String courseType = entity.getCourseType();
        if (courseType != null) {
            stmt.bindString(11, courseType);
        }
        stmt.bindLong(12, entity.getHr());
        stmt.bindDouble(13, entity.getBmi());
        stmt.bindDouble(14, entity.getTizhi());
        stmt.bindDouble(15, entity.getCal());
        stmt.bindLong(16, entity.getFirstTimestamp());
        stmt.bindLong(17, entity.getLastTimestamp());
        stmt.bindLong(18, entity.getCurrentTimestamp());
        stmt.bindLong(19, entity.getRank());
        stmt.bindLong(20, entity.getMaxhr());
        stmt.bindLong(21, entity.getOldIndex());
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public Member readEntity(Cursor cursor, int offset) {
        Member entity = new Member( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // nickname
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // headImgUrl
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // sex
            cursor.getDouble(offset + 4), // weight
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // height
            cursor.getInt(offset + 6), // age
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // num
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // deviceNum
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // courseId
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // courseType
            cursor.getInt(offset + 11), // hr
            cursor.getDouble(offset + 12), // bmi
            cursor.getDouble(offset + 13), // tizhi
            cursor.getDouble(offset + 14), // cal
            cursor.getLong(offset + 15), // firstTimestamp
            cursor.getLong(offset + 16), // lastTimestamp
            cursor.getLong(offset + 17), // currentTimestamp
            cursor.getInt(offset + 18), // rank
            cursor.getInt(offset + 19), // maxhr
            cursor.getInt(offset + 20) // oldIndex
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Member entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setNickname(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setHeadImgUrl(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setSex(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setWeight(cursor.getDouble(offset + 4));
        entity.setHeight(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setAge(cursor.getInt(offset + 6));
        entity.setNum(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setDeviceNum(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setCourseId(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setCourseType(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setHr(cursor.getInt(offset + 11));
        entity.setBmi(cursor.getDouble(offset + 12));
        entity.setTizhi(cursor.getDouble(offset + 13));
        entity.setCal(cursor.getDouble(offset + 14));
        entity.setFirstTimestamp(cursor.getLong(offset + 15));
        entity.setLastTimestamp(cursor.getLong(offset + 16));
        entity.setCurrentTimestamp(cursor.getLong(offset + 17));
        entity.setRank(cursor.getInt(offset + 18));
        entity.setMaxhr(cursor.getInt(offset + 19));
        entity.setOldIndex(cursor.getInt(offset + 20));
     }
    
    @Override
    protected final String updateKeyAfterInsert(Member entity, long rowId) {
        return entity.getId();
    }
    
    @Override
    public String getKey(Member entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Member entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
