package by.poshelyuk.blog.dao;

import by.poshelyuk.blog.entity.Tag;

import java.util.List;

public interface TagDAO {
     Tag findByName(String s);

     List<Tag> findAll();
}
