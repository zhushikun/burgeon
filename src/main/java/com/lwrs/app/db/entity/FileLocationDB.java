package com.lwrs.app.db.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class FileLocationDB {

    private Integer id;
    private String subPath;
    private Date createAt;
    private Date updateAt;


    public static FileLocationBuilder builder() {
        return new FileLocationBuilder();
    }


    public static final class FileLocationBuilder {
        private Integer id;
        private String subPath;
        private Date createAt;
        private Date updateAt;

        private FileLocationBuilder() {
        }

        public FileLocationBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public FileLocationBuilder subPath(String subPath) {
            this.subPath = subPath;
            return this;
        }

        public FileLocationBuilder createAt(Date createAt) {
            this.createAt = createAt;
            return this;
        }

        public FileLocationBuilder updateAt(Date updateAt) {
            this.updateAt = updateAt;
            return this;
        }

        public FileLocationDB build() {
            FileLocationDB fileLocationDB = new FileLocationDB();
            fileLocationDB.setId(id);
            fileLocationDB.setSubPath(subPath);
            fileLocationDB.setCreateAt(createAt);
            fileLocationDB.setUpdateAt(updateAt);
            return fileLocationDB;
        }
    }
}
