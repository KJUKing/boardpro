package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.ZipVO;

public interface IMemberDao {

	public List<MemberVO>  selectAllMember();

	//id중복검사
	public String idCheck(String id);
	
	//우편번호찾기
	public List<ZipVO> selectByDong(String dong);
	
	//저장가입하기
	public int insertMember(MemberVO vo);

	//로그인하기
	public MemberVO loginSelect(MemberVO vo);

}
