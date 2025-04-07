package org.project.backend.SecurityService.Service;

import org.apache.ibatis.annotations.Mapper;
import org.project.backend.SecurityService.Model.RefreshEntity;


@Mapper
public interface RefreshMapper {
    public void insertByRefresh(RefreshEntity refreshEntity);
    public Boolean existsByRefresh(String refresh);
    public void deleteByRefresh(String refresh);
}