package top.liyf.dao;

import java.util.List;
import java.util.Map;

import top.liyf.domain.Notice;


/**   
 * @Description: NoticeMapper接口
 * @version V1.0   
 */
public interface NoticeDao {

	// 动态查询
	 
	List<Notice> selectByPage(Map<String, Object> params);
	
 	Integer count(Map<String, Object> params);
		
 	Notice selectById(int id);
	
	// 根据id删除公告
 	void deleteById(Integer id);
		
	// 动态插入公告
 	void save(Notice notice);
		
	// 动态修改公告
 	void update(Notice notice);
	
}
