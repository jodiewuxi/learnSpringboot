package com.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.example.entities.Todo;

@org.apache.ibatis.annotations.Mapper
public interface TodoMapper {
    @Insert("INSERT INTO todo (title, details, finished) VALUES (#{title}, #{details}, #{finished})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Todo todo);

    @Select("SELECT id, title, details, finished FROM todo WHERE id = #{id}")
    Todo select(int id);
}
