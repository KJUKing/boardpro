package kr.or.ddit.member.service;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.ZipVO;

import java.util.List;

public class MemberServiceImpl implements IMemberService{

	//dao메소드를 호출하기 위한 객체
	private IMemberDao dao;

	private MemberServiceImpl() {
		dao = MemberDaoImpl.getInstance();
	}

	//싱글톤
	private static IMemberService service;
	
	public static IMemberService getInstance() {
		if(service == null) service = new MemberServiceImpl();
		
		return service;
	}

	@Override
	public List<MemberVO> selectAllMember() {
		//Dao 메소드 호출
		/*
		  List<MemberVO> list = null; 
		  list = dao.selectAllMember();
		  return list;
		 */
		
		return dao.selectAllMember();
	}

	@Override
	public String idCheck(String id) {
		// TODO Auto-generated method stub
		return dao.idCheck(id);
	}

	@Override
	public List<ZipVO> selectByDong(String dong) {
		// TODO Auto-generated method stub
		return dao.selectByDong(dong);
	}

	@Override
	public int insertMember(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.insertMember(vo);
	}

	@Override
	public MemberVO loginSelect(MemberVO vo) {
		return dao.loginSelect(vo);
	}

}
