package generator.mapper;

import generator.entity.User;

/**
* @author user
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-04-12 20:09:28
* @Entity generator.entity.User
*/
public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}
