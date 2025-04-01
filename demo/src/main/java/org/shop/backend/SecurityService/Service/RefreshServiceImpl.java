package org.shop.backend.SecurityService.Service;

import org.shop.backend.SecurityService.Model.RefreshEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service("RefreshService")
public class RefreshServiceImpl implements RefreshService {

    @Autowired
    private RefreshMapper refreshMapper;

    @Override
    public Boolean existsByRefresh(String refresh) {
        return refreshMapper.existsByRefresh(refresh);
    }

    @Override
    public void insertByRefresh(RefreshEntity refreshEntity){
        refreshMapper.insertByRefresh(refreshEntity);
    }

    public void deleteByRefresh(String refresh) {
        refreshMapper.deleteByRefresh(refresh);
    }
}
