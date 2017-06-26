package service;

import java.util.List;

import entity.MemberEntity;
import vo.MemberVO;

public class MemberService {
	private MemberEntity e;

	public MemberService(MemberVO vo) {
		e = new MemberEntity(vo);
	}
	public MemberService() {}
	public void insertMember() {
		e.insertMember();
	}	
	public void idCheck(MemberVO vo) {
		e.idCheck(vo);
	}	
    public void loginMember() {
		e.loginMember();
	}
    public void selectMember() {
    	e.selectMember();	
    }
    public void updateMember() {
    	e.updateMember();
    }
	public void deleteMember() {
		e.deleteMember();
	}
	public List selectAllMember(List<MemberVO> list) {
		System.out.println("service");
		e.selectAllMember(list);
		return list;
	}
}