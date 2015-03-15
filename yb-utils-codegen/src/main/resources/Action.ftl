package ${packageName};

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ModelDriven;
import ${packageName}.BaseAction;

import ${pModelPath}.${pModelName};

@Service("${pModelName?substring(1)}Action")
@Transactional
public class ${pModelName?substring(1)?cap_first}Action extends BaseAction  implements ModelDriven<${pModelName}> {
	
	@Autowired
	private ${pModelName?substring(1)?cap_first}ServiceI ${pModelName?substring(1)}ServiceI;
	private ${pModelName} ${pModelName?uncap_first} =new ${pModelName}();

    
    @Override
	public ${pModelName} getModel() {
		// TODO Auto-generated method stub
		return ${pModelName?uncap_first};
	}
}
