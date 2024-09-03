package kr.or.ddit.board.dao;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.mybatis.config.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BoardDaoImpl implements IBoardDao {

    public BoardDaoImpl() {
    }

    //싱글톤
    private static IBoardDao dao;

    public static IBoardDao getInstance() {
        if (dao == null) {
            dao = new BoardDaoImpl();
        }
        return dao;
    }


    @Override
    public List<BoardVO> selectBoardList(Map<String, Object> map) {
        //1. 선언
        List<BoardVO> list = null;
        SqlSession sql = null;
        //2. 실행
        try {
            sql = MybatisUtil.getSqlSession();
            list = sql.selectList("board.selectBoardList", map);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sql.close();
        }
        return list;
    }

    @Override
    public int countBoard(Map<String, Object> map) {

        int cnt = 0;
        SqlSession sql = null;
        try {
            sql = MybatisUtil.getSqlSession();
            cnt = sql.selectOne("board.countBoard", map);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sql.close();
        }

        return cnt;
    }
}
