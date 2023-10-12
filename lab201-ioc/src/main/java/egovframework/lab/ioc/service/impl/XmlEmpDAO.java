package egovframework.lab.ioc.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import egovframework.lab.ioc.service.EmpVO;

public class XmlEmpDAO {

   static List<EmpVO> list;
   
   static {
	   list = new ArrayList<EmpVO>();
	   EmpVO empVO;
	   for(int i=1; i<=100; i++) {
		   empVO = new EmpVO();
		   empVO.setEmpNo(i);
		   empVO.setEmpName("EmpName" + i);
		   empVO.setJob("SALESMAN");
		   list.add(empVO);
	   }
   }
   
   public void insertEmp(EmpVO empVO) throws Exception{
	   list.add(empVO);
	   Collections.sort(list);
   }

}
