package cn.ecust.service.Impl;

import cn.ecust.dao.EscoreMapper;
import cn.ecust.entity.Escore;
import cn.ecust.service.EscoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Chen Kang
 * @Date 2022/5/17
 * @Description
 */
@Service
public class EscoreServiceImpl implements EscoreService {
    @Autowired
    private EscoreMapper escoreMapper;
    
    @Override
    public int addEscore(Escore escore) {
        escoreMapper.addEscore(escore);
        return escore.getSid();
    }

    @Override
    public int deleteEscore(int sid) {
        return escoreMapper.deleteEscore(sid);
    }

    @Override
    public int updateScore(int sid,int score) {
        return escoreMapper.updateScore(sid,score);
    }

    @Override
    public int scoreTemp(String tempName) {
        return 0;
    }

    @Override
    public List<Escore> selectAll() {
        return escoreMapper.selectAll();
    }
}
