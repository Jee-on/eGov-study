package egovframework.lab.ioc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.lab.ioc.service.EmpService;
import egovframework.lab.ioc.service.EmpVO;

@Service("annotationEmpService")
public class AnnotationEmpServiceImpl implements EmpService {
	
	@Resource(name= "annotationEmpDAO")
	private AnnotationEmpDAO empDAO;

	@Override
	public void insertEmp(EmpVO empVO) throws Exception {
		empDAO.insertEmp(empVO);
		
	}

	@Override
	public void updateEmp(EmpVO empVO) throws Exception {
		empDAO.updateEmp(empVO);
		
	}

	@Override
	public void deleteEmp(EmpVO empVO) throws Exception {
		empDAO.deleteEmp(empVO);	
	}

	@Override
	public EmpVO selectEmp(EmpVO empVO) throws Exception {
		return empDAO.selectEmp(empVO);
	}

	@Override
	public List<EmpVO> selectEmpList() throws Exception {
		return empDAO.selectEmpList();
	}

    

}
