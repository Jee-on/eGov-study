package egovframework.lab.ioc.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.lang.reflect.Field;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath*:META-INF/spring/context-common.xml",
    "classpath*:META-INF/spring/context-emp.xml"
    //,"classpath*:META-INF/spring/context-postprocessor.xml"   // 이 주석을 풀고 테스트 시 annotationEmpService 에 대해서는 delete 메서드에 @Debug 를 설정하였으므로 trace 로그가 출력될 것임.
    })
public class EmpServiceTest {

    // TODO [Step 1-6, 2-6] EmpServiceTest 작성
    @Resource(name="xmlEmpService")
    EmpService empService;

    // annotation 형식 테스트
//     @Resource(name="annotationEmpService")
//     EmpService empService;

    public EmpVO makeVO(){
        return makeVO(101);
    }

    public EmpVO makeVO(int empNo){
        EmpVO vo = new EmpVO();
        vo.setEmpNo(empNo);
        vo.setEmpName("홍길동" + empNo);
        vo.setJob("개발자");
        return vo;
    }

    public void checkResult(EmpVO vo, EmpVO resultVO){
        assertNotNull(resultVO);
        assertEquals(vo.getEmpNo(),resultVO.getEmpNo());
        assertEquals(vo.getEmpName(), resultVO.getEmpName());
        assertEquals(vo.getJob(), resultVO.getJob());
    }

    @Test
    public void testInsertEmp() throws Exception {
        EmpVO vo = makeVO();

        // insert
        empService.insertEmp(vo);
        // select
        EmpVO resultVO = empService.selectEmp(vo);
        // check
        checkResult(vo, resultVO);
        for(Field field : EmpVO.class.getDeclaredFields()) {
        	field.setAccessible(true);
        	System.out.println(field.getName() + ": " + field.get(resultVO));
        }
    }

    @Test
    public void testUpdateEmp() throws Exception {
        EmpVO vo = makeVO(102);

        // insert
        empService.insertEmp(vo);
        // data change
        vo.setEmpName("홍길순");
        vo.setJob("설계자");

        // update
        empService.updateEmp(vo);
        //select
        EmpVO resultVO = empService.selectEmp(vo);
        // check
        checkResult(vo, resultVO);

    }

    @Test
    public void testDeleteEmp() throws Exception {
        EmpVO vo = makeVO(103);

        // insert
        empService.insertEmp(vo);

        //delete
        empService.deleteEmp(vo);
        // select
        EmpVO resultVO = empService.selectEmp(vo);
        // null 이어야 함
        assertNull(resultVO);

    }

    @Test
    public void testSelectEmpList() throws Exception {

        // select list
        List<EmpVO> resultList = empService.selectEmpList();
        // check
        int firstListSize = resultList.size();
        assertEquals(1, resultList.get(0).getEmpNo());

        // delete first data
        EmpVO empVO = new EmpVO();
        empVO.setEmpNo(1);

        empService.deleteEmp(empVO);

        // select List again
        resultList = empService.selectEmpList();

        assertEquals(firstListSize-1, resultList.size());
        assertEquals(2, resultList.get(0).getEmpNo());
        assertEquals("EmpName2", resultList.get(0).getEmpName());
        assertEquals("SALESMAN", resultList.get(0).getJob());
    }
}
