package ${packageName};

import com.yb.db.dao.BaseDaoI;

import ${entityPath}.${modelName};

public interface ${modelName?substring(1)?cap_first}DaoI extends BaseDaoImpl<${modelName}>  {

}
