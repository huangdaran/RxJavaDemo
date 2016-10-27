package com.huangdaran.rxjavademo.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/10/25.
 */

public class User {
    public String status;
    public Data data;
    public List<Promotion> promo;
    public class Data implements Serializable {
        public String id;
        public String uname;
        public String username;
        public String head;
        public String gender;
        public String byear;
        public String bmonth;
        public String bday;
        public String daren;
        public String close;

    }
    public class Promotion implements Serializable{
        public String pid;
        public String sid;
        public String type;
        public String belong_id;
        public String shop_name;
    }
}
