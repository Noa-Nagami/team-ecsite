package jp.co.internous.ocean.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.internous.ocean.model.domain.MstUser;
import jp.co.internous.ocean.model.mapper.MstUserMapper;
import jp.co.internous.ocean.model.session.LoginSession;

@Controller
@RequestMapping("/ocean/mypage")
public class MyPageController {
	
	//@Autowiredアノテーションにより、LoginSessionをインスタンス化
	@Autowired
	LoginSession loginSession;
	
	@Autowired
	MstUserMapper mstUserMapper;
	
	@RequestMapping("/")
	public String index(Model m) {
		
		//ログインユーザーの情報をSessionで受け取ってDB情報を取得
        int userId = loginSession.getUserId();
		
        //MstUserからユーザー情報全てを取ってくる
		List<MstUser> mstUser = mstUserMapper.findById(userId);
		
		//ビューに表示を返す
		m.addAttribute("loginSession", loginSession);
		m.addAttribute("mstUser", mstUser);
		
		return "my_page";
	}
}