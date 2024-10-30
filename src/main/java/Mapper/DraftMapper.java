package Mapper;

import org.apache.ibatis.annotations.Param;

public interface DraftMapper {

    public String getContent();
    public void insertDraft(@Param("content") String content);
    public void updateDraft(@Param("content") String content);
}
