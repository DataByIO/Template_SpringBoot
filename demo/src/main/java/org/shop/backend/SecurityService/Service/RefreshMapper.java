package org.shop.backend.SecurityService.Service;

import org.apache.ibatis.annotations.Mapper;
import org.shop.backend.SecurityService.Model.RefreshEntity;

import java.util.HashMap;


@Mapper
public interface RefreshMapper {
    public void insertByRefresh(RefreshEntity refreshEntity);
    public Boolean existsByRefresh(String refresh);
    public void deleteByRefresh(String refresh);
}
