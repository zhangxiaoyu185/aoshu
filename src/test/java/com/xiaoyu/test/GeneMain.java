package com.xiaoyu.test;

import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.xiaoyu.lingdian.vo.ContentList;
import com.xiaoyu.lingdian.vo.Problem;

public class GeneMain {

	public static void main(String[] args) throws Exception {	
		
		ContentList contentList = new ContentList();
		List<Problem> list = new ArrayList<Problem>();
		Problem problem = new Problem();
		problem.setCrqtsClass("1");
		problem.setCrcasName("一年级");
		problem.setCrqtsCategory("1");
		problem.setCrceyName("计算");
		problem.setCrqtsKnowledge("001");
		problem.setCrkleName("速算与巧算");
		problem.setCrqtsAnalysisFont("啦啦啦");
		problem.setCrqtsAnalysisType(0);
		problem.setCrqtsQuesFont("12.9 分米=（ ）米=（ ）厘米；9 厘米=（ ）千米");
		problem.setCrqtsQuesType(0);	
		problem.setCrqtsProblem("上面的答案分别是#、#、#.");
		problem.setCrqtsAnswer("1.29,129,0.00009");
		problem.setCrqtsUuid("0519220021439XqD");
		problem.setCrqtsCode("a91001");
		problem.setCrqtsColor("#ffffff");
		problem.setCrqtsDir("一年级模拟卷");	
		problem.setCrqtsLevel(1);
		list.add(problem);
		
		problem = new Problem();
		problem.setCrqtsClass("1");
		problem.setCrcasName("一年级");
		problem.setCrqtsCategory("1");
		problem.setCrceyName("计算");
		problem.setCrqtsKnowledge("001");
		problem.setCrkleName("速算与巧算");
		problem.setCrqtsAnalysisUrl("0519220021642e78");
		problem.setCrqtsAnalysisType(1);
		problem.setCrqtsQuesUrl("0519220021631G0F");
		problem.setCrqtsQuesType(1);	
		problem.setCrqtsProblem("上面的答案分别是#、#、#.");
		problem.setCrqtsAnswer("1.29,129,0.00009");
		problem.setCrqtsUuid("0519220021623lfw");
		problem.setCrqtsCode("a91002");
		problem.setCrqtsColor("#333333");
		problem.setCrqtsDir("一年级模拟卷");	
		problem.setCrqtsLevel(1);
		list.add(problem);
		
		problem = new Problem();
		problem.setCrqtsClass("1");
		problem.setCrcasName("一年级");
		problem.setCrqtsCategory("1");
		problem.setCrceyName("计算");
		problem.setCrqtsKnowledge("001");
		problem.setCrkleName("速算与巧算");
		problem.setCrqtsAnalysisFont("快来分析一下");
		problem.setCrqtsAnalysisType(0);
		problem.setCrqtsQuesFont("12分米=（ ）米=（ ）厘米");
		problem.setCrqtsQuesType(0);	
		problem.setCrqtsProblem("上面的答案分别是#、#.");
		problem.setCrqtsAnswer("1.2,129");
		problem.setCrqtsUuid("0519220021671po7");
		problem.setCrqtsCode("a91003");
		problem.setCrqtsDir("一年级模拟卷");	
		problem.setCrqtsLevel(1);
		list.add(problem);
		
		problem = new Problem();
		problem.setCrqtsClass("1");
		problem.setCrcasName("一年级");
		problem.setCrqtsCategory("1");
		problem.setCrceyName("计算");
		problem.setCrqtsKnowledge("001");
		problem.setCrkleName("速算与巧算");
		problem.setCrqtsAnalysisUrl("05192200217360ku");
		problem.setCrqtsAnalysisType(1);
		problem.setCrqtsQuesUrl("0519220021721OgX");
		problem.setCrqtsQuesType(1);	
		problem.setCrqtsProblem("上面的答案分别是#、#.");
		problem.setCrqtsAnswer("1.29,129");
		problem.setCrqtsUuid("05192200217129Dg");
		problem.setCrqtsCode("a91004");
		problem.setCrqtsDir("一年级模拟卷");	
		problem.setCrqtsLevel(1);
		list.add(problem);
		
		contentList.setProblem(list);
		String strJson = JSON.toJSONString(contentList);
		System.out.println(strJson);
	}
	
}
