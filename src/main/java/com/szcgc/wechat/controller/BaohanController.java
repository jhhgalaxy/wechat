package com.szcgc.wechat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
* @ClassName: BaohanWebController 
* @Description: TODO
* @author lanqi@szcgc.com 
* @date 2020年4月26日 上午9:55:21 
*
 */
@Controller
@RequestMapping("baohan")
public class BaohanController {
	
//	private static final Logger logger=LoggerFactory.getLogger(BaohanController.class);
//	
//	@Autowired
//	private IProjectService projectservice;
//	
//	@Autowired
//	private IPersonService personservice;
//	
//	@Autowired
//	private ICorporationService corporationservice;
//	
//	@RequestMapping(value="apply",method=RequestMethod.GET)
//	public String viewapply(){
//		
//		return "baohan/addBusiness";
//	}
//	
//	@RequestMapping(value="apply",method=RequestMethod.POST)
//	public String postapply(ProjectInfo projectInfo,CorporationInfo corporationInfo,PersonInfo personInfo,RedirectAttributes redir){
//		
//		logger.info("baohan apply: {},{}",projectInfo.getProjectname(),projectInfo.getProjectno());
//		corporationInfo=corporationservice.insert(corporationInfo);
//		personInfo.setCorporationid(corporationInfo.getId());
//		personservice.insert(personInfo);
//		projectInfo.setCorporationid(corporationInfo.getId());
//		projectInfo.setPersonid(personInfo.getId());
//		projectInfo.setProjectno(getprojectNo());
//		projectInfo.setSigntype(SignTypeEnum.ESIGN);
//		projectservice.insert(projectInfo);
//		return "redirect:/baohan/apply";
//	}
//
//	
//	/**
//	 * 自动生成项目编号
//	 * @return
//	 */
//	public  String getprojectNo(){
//			
//		LocalDate localDate=LocalDate.now();
//		String year=String.valueOf(localDate.getYear());
//		Integer maxId=projectservice.getMaxId()+1;
//		String result=String.valueOf(maxId);
//		int id=maxId;
//		int i=0;
//		while(id!=0){
//			id=id/10;
//			i++;
//		}
//		int num=9-i;
//		for (int j = 0; j < num; j++) {
//			result="0"+result;
//		}
//		return "深担（"+year+"）年保函字（"+result+"）号";
//		}
	
}
