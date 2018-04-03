package com.xiaoyu.lingdian.controller;

import java.util.List;
import java.util.ArrayList;
import com.xiaoyu.lingdian.tool.FileUtil;
import com.xiaoyu.lingdian.tool.RandomUtil;
import com.xiaoyu.lingdian.tool.StringUtil;
import javax.servlet.http.HttpServletResponse;
import com.xiaoyu.lingdian.core.mybatis.page.Page;
import org.springframework.stereotype.Controller;
import com.xiaoyu.lingdian.tool.init.ConfigIni;
import com.xiaoyu.lingdian.tool.out.ResultMessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import com.xiaoyu.lingdian.controller.BaseController;
import com.xiaoyu.lingdian.entity.CoreDirMovie;
import com.xiaoyu.lingdian.service.CoreDirMovieService;
import com.xiaoyu.lingdian.vo.CoreDirMovieVO;

@Controller
@RequestMapping(value="/coreDirMovie")
public class CoreDirMovieController extends BaseController {

	/**
	* 视频目录表
	*/
	@Autowired
	private CoreDirMovieService coreDirMovieService;
	
	/**
	* 添加
	*
	* @param crdirName 目录名称
	* @return
	*/
	@RequestMapping(value="/add/coreDirMovie", method=RequestMethod.POST)
	public void addCoreDirMovie (String crdirName, HttpServletResponse response) {
		logger.info("[CoreDirMovieController]:begin addCoreDirMovie");

		if (StringUtil.isEmpty(crdirName)) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[目录名称]不能为空!"), response);
			return;
		}
		
		List<CoreDirMovie> list = coreDirMovieService.getCoreDirMovieByName(crdirName);
		if (null != list && list.size() > 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "该目录已存在!"), response);
			return;
		}
		
		String uuid = RandomUtil.generateString(16);
		coreDirMovie.setCrdirUuid(uuid);
		coreDirMovie.setCrdirName(crdirName);

		coreDirMovieService.insertCoreDirMovie(coreDirMovie);

		String moviePath = ConfigIni.getIniStrValue("MOVIE_DIR", "path") + crdirName;
		if (!FileUtil.isExist(moviePath)) {
			FileUtil.createFolder(moviePath);
		}
		
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "新增成功!"),response);
		logger.info("[CoreDirMovieController]:end addCoreDirMovie");

	}

	/**
	* 修改
	*
	* @param crdirUuid 标识UUID
	* @param crdirName 目录名称
	* @return
	*/
	@RequestMapping(value="/update/coreDirMovie", method=RequestMethod.POST)
	public void updateCoreDirMovie (String crdirUuid, String crdirName, HttpServletResponse response) {
		logger.info("[CoreDirMovieController]:begin updateCoreDirMovie");

			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}

		coreDirMovie.setCrdirUuid(crdirUuid);
		coreDirMovie.setCrdirName(crdirName);

		coreDirMovieService.updateCoreDirMovie(coreDirMovie);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "修改成功!"),response);
		logger.info("[CoreDirMovieController]:end updateCoreDirMovie");

	}

	/**
	* 删除
	*
	* @param crdirUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/delete/one", method=RequestMethod.POST)
	public void deleteCoreDirMovie (String crdirUuid, HttpServletResponse response) {
		logger.info("[CoreDirMovieController]:begin deleteCoreDirMovie");

			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}

		coreDirMovie.setCrdirUuid(crdirUuid);

		coreDirMovieService.deleteCoreDirMovie(coreDirMovie);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "删除成功!"),response);
		logger.info("[CoreDirMovieController]:end deleteCoreDirMovie");

	}

	/**
	* 批量删除
	*
	* @param crdirUuids UUID集合
	* @return
	*/
	@RequestMapping(value="/delete/batch", method=RequestMethod.POST)
	public void deleteBatchCoreDirMovie (String crdirUuids, HttpServletResponse response) {
		logger.info("[CoreDirMovieController]:begin deleteBatchCoreDirMovie");

			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[UUID集合]不能为空!"), response);
			return;
		}

		List<String> list = new ArrayList<String>();
		for (int i = 0; i < uuids.length; i++) {
			list.add(uuids[i]);
		}
		if (list.size() == 0) {
			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "请选择批量删除对象！"), response);
			return;
		}
		coreDirMovieService.deleteBatchByIds(list);

		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "批量删除成功!"),response);
		logger.info("[CoreDirMovieController]:end deleteBatchCoreDirMovie");

	}

	/**
	* 获取单个
	*
	* @param crdirUuid 标识UUID
	* @return
	*/
	@RequestMapping(value="/views", method=RequestMethod.POST)
	public void viewsCoreDirMovie (String crdirUuid, HttpServletResponse response) {
		logger.info("[CoreDirMovieController]:begin viewsCoreDirMovie");

			writeAjaxJSONResponse(ResultMessageBuilder.build(false, -1, "[标识UUID]不能为空!"), response);
			return;
		}

		coreDirMovie.setCrdirUuid(crdirUuid);

		coreDirMovie = coreDirMovieService.getCoreDirMovie(coreDirMovie);

		CoreDirMovieVO coreDirMovieVO = new CoreDirMovieVO();
		coreDirMovieVO.convertPOToVO(coreDirMovie);

		logger.info("[CoreDirMovieController]:end viewsCoreDirMovie");

	}

	/**
	* 获取列表<List>
	* 
	* @return
	*/
	@RequestMapping(value="/find/all", method=RequestMethod.POST)
	public void findCoreDirMovieList (HttpServletResponse response) {
		logger.info("[CoreDirMovieController]:begin findCoreDirMovieList");

		List<CoreDirMovieVO> vos = new ArrayList<CoreDirMovieVO>();
		CoreDirMovieVO vo;
		for (CoreDirMovie coreDirMovie : lists) {
			vo = new CoreDirMovieVO();


		}
		writeAjaxJSONResponse(ResultMessageBuilder.build(true, 1, "list列表获取成功!", vos),response);
		logger.info("[CoreDirMovieController]:end findCoreDirMovieList");

	}

	/**
	* 获取列表<Page>
	* 
	* @param crdirName 目录名
	* @param pageNum 页码
	* @param pageSize 页数
	* @return
	*/
	@RequestMapping(value="/find/by/cnd", method=RequestMethod.POST)
	public void findCoreDirMoviePage (String crdirName, Integer pageNum, Integer pageSize, HttpServletResponse response) {
		logger.info("[CoreDirMovieController]:begin findCoreDirMoviePage");

			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 20;
		}
		Page<CoreDirMovie> page = coreDirMovieService.findCoreDirMoviePage(crdirName, pageNum, pageSize);
		Page<CoreDirMovieVO> pageVO = new Page<CoreDirMovieVO>(page.getPageNumber(), page.getPageSize(), page.getTotalCount());
		List<CoreDirMovieVO> vos = new ArrayList<CoreDirMovieVO>();
		CoreDirMovieVO vo;
		for (CoreDirMovie coreDir : page.getResult()) {
			vo = new CoreDirMovieVO();


		}
		pageVO.setResult(vos);

		logger.info("[CoreDirMovieController]:end findCoreDirMoviePage");

	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}