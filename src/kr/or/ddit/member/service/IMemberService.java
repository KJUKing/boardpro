package kr.or.ddit.member.service;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.ZipVO;

import java.util.List;

public interface IMemberService {
	
	//멤버리스트갖고오기
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
