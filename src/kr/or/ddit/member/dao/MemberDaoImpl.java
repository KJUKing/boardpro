package kr.or.ddit.member.dao;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.ZipVO;
import kr.or.ddit.mybatis.config.MybatisUtil;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class MemberDaoImpl implements IMemberDao {
	
	//mapper수행을위한
	private SqlSession sql;

	//생성자
	private MemberDaoImpl() {
		
	}
	
    //싱글톤
    private static IMemberDao dao;

    public static IMemberDao getInstance() {
        if (dao == null) {
            dao = new MemberDaoImpl();
        }
        return dao;
    }

    @Override
    public List<MemberVO> selectAllMember(){

    	//결과를 얻어서 리턴할 변수
    	List<MemberVO> list = null;
        try {
            sql = MybatisUtil.getSqlSession();
            //mapper수행
            list = sql.selectList("member.selectAllMember");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sql.commit();
            sql.close();
        }

    	//결과값을 service로 리턴
    	return list;
    }

	@Override
	public String idCheck(String id) {
		
		//리턴변수선언
		String userId = null;
		
		try {
			//실행
			sql = MybatisUtil.getSqlSession();
			userId = sql.selectOne("member.idCheck", id);

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			sql.commit();
			sql.close();
		}
		//리턴
		return userId;
	}

	@Override
	public List<ZipVO> selectByDong(String dong) {

		//리턴변수선언
		List<ZipVO> list = null;

		try {
			//실행
			sql = MybatisUtil.getSqlSession();
			list = sql.selectList("member.selectByDong", dong);

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			sql.commit();
			sql.close();
		}
		//리턴
		return list;
	}

	@Override
	public int insertMember(MemberVO vo) {

		//리턴변수선언
		int cnt= 0;

		try {
			//실행
			sql = MybatisUtil.getSqlSession();
			cnt = sql.insert("member.insertMember", vo);

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			sql.commit();
			sql.close();
		}
		//리턴
		return cnt;
	}

	@Override
	public MemberVO loginSelect(MemberVO vo) {
		//리턴변수선언
		MemberVO returnVo=  null;

		try {
			//실행
			sql = MybatisUtil.getSqlSession();
			returnVo = sql.selectOne("member.loginSelect", vo);

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			sql.commit();
			sql.close();
		}
		//리턴
		return returnVo;
	}
}
