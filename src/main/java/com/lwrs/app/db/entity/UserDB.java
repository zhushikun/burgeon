package com.lwrs.app.db.entity;

import com.lwrs.app.domain.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
public class UserDB extends BaseDto{
    private Integer id;
    private String alias;
    private String pwd;
    private String name;
    private String gender;
    private String phone;
    private String birthday;
    private String address;
    private Integer avatarId;
    private Date createAt;
    private Date updateAt;


    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static final class UserBuilder {
        private Integer id;
        private String alias;
        private String pwd;
        private String name;
        private String gender;
        private String phone;
        private String birthday;
        private String address;
        private Integer avatarId;
        private Date createAt;
        private Date updateAt;

        private UserBuilder() {
        }

        public UserBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public UserBuilder alias(String alias) {
            this.alias = alias;
            return this;
        }

        public UserBuilder pwd(String pwd) {
            this.pwd = pwd;
            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder birthday(String birthday) {
            this.birthday = birthday;
            return this;
        }

        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        public UserBuilder avatarId(Integer avatarId) {
            this.avatarId = avatarId;
            return this;
        }

        public UserBuilder createAt(Date createAt) {
            this.createAt = createAt;
            return this;
        }

        public UserBuilder updateAt(Date updateAt) {
            this.updateAt = updateAt;
            return this;
        }

        public UserDB build() {
            UserDB userDB = new UserDB();
            userDB.setId(id);
            userDB.setAlias(alias);
            userDB.setPwd(pwd);
            userDB.setName(name);
            userDB.setGender(gender);
            userDB.setPhone(phone);
            userDB.setBirthday(birthday);
            userDB.setAddress(address);
            userDB.setAvatarId(avatarId);
            userDB.setCreateAt(createAt);
            userDB.setUpdateAt(updateAt);
            return userDB;
        }
    }
}


 
 
 
 
 
 
 
 
 
 
