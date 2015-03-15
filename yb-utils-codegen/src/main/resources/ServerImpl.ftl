package ${packageName};

import java.io.Serializable;
import com.yb.local.BeanUtils;
import org.apache.log4j.Logger;

import ${entityPath}.${modelName};
import ${pModelPath}.${pModelName};

@Service("${modelName?substring(1)}Service")
@Transactional
public class ${modelName?substring(1)?cap_first}ServiceImpl  implements ${modelName?substring(1)?cap_first}ServiceI {
	private static final Logger logger = Logger.getLogger(${modelName?substring(1)?cap_first}ServiceImpl.class);
	@Autowired
	private ${modelName?substring(1)?cap_first}DaoI ${modelName?substring(1)}Dao;
	/**
     * 增加
     */
	public Puser add${pModelName}(${pModelName} puser){
		${modelName} t=new ${modelName}();
		BeanUtils.copyProperties(puser, t);
		${modelName?substring(1)}Dao.save(t);
		BeanUtils.copyProperties(t, puser);
		return puser;
	}
  /**
     * 主键删除
     */
	public void remove${pModelName}(String id){
	  ${modelName} t= ${modelName?substring(1)}Dao.get(${modelName}.class ,id);
	  ${modelName?substring(1)}Dao.delete(t);
	}
	  /**
     * 集合删除
     */
	public void remove${pModelName}s(String[] ids){
	   for(String id :ids){
	     this.remove${pModelName}(String id);
	   }
	}

   /**
     * 编辑
     */
	public ${pModelName} edit${pModelName}(${pModelName} puser){
	    ${modelName} t=new ${modelName}();
		BeanUtils.copyProperties(puser, t);
		${modelName?substring(1)}Dao.saveOrUpdate(t);
		BeanUtils.copyProperties(t, puser);
		return puser;
	}
	
	
    /**
     * 查询列表
     */
    public List<${pModelName}> get${pModelName}s(){
    
    }
    
    /**
     * 条件查询列表
     */
    public List<${pModelName}> get${pModelName}s(String hql){
    }
    /**
     * 根据主键查询
     */
    public ${pModelName} get${pModelName}(String id){
      ${modelName} t= ${modelName?substring(1)}Dao.get(${modelName}.class ,id);
      ${pModelName} pt= new ${pModelName}();
      BeanUtils.copyProperties(t, pt);
      return pt;
    }
    
  
}
