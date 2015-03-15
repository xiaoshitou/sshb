package ${packageName};

import java.io.Serializable;


import ${pModelPath}.${pModelName};

public interface ${pModelName?substring(1)?cap_first}ServiceI extends BaseServiceI<${pModelName}>{
  /**
     * 增加
     */
	public Puser add${pModelName}(${pModelName} puser);
  /**
     * 主键删除
     */
	public void remove${pModelName}(String id);
	  /**
     * 集合删除
     */
	public void remove${pModelName}s(String[] ids);

   /**
     * 编辑
     */
	public ${pModelName} edit${pModelName}(${pModelName} puser);
	
	
    /**
     * 查询列表
     */
    public List<${pModelName}> get${pModelName}s();
    
    /**
     * 条件查询列表
     */
    public List<${pModelName}> get${pModelName}s(String hql);
    /**
     * 根据主键查询
     */
    public ${pModelName} get${pModelName}(String id) ;
}
