package cn.willvi.dao.appTeacher;


public interface AppTeacherDao {
	/*
	 * 根据t_id查询教师
	 */
	public Object findByTIdPwd(String statement, Object obj) throws Exception;
}
