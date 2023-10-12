package egovframework.example.sample.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.example.sample.service.SampleVO;
import egovframework.example.sample.service.SampleDefaultVO;

/**
 * @Class Name : SampleDAO.java
 * @Description : Sample DAO Class
 * @Modification Information
 *
 * @author 전성호
 * @since 2023/10/12
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("sampleDAO")
public class SampleDAO extends EgovAbstractMapper {

	/**
	 * SAMPLE을 등록한다.
	 * @param vo - 등록할 정보가 담긴 SampleVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public void insertSample(SampleVO vo) throws Exception {
        insert("sampleDAO.insertSample", vo);
    }

    /**
	 * SAMPLE을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateSample(SampleVO vo) throws Exception {
        update("sampleDAO.updateSample", vo);
    }

    /**
	 * SAMPLE을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SampleVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteSample(SampleVO vo) throws Exception {
        delete("sampleDAO.deleteSample", vo);
    }

    /**
	 * SAMPLE을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SampleVO
	 * @return 조회한 SAMPLE
	 * @exception Exception
	 */
    public SampleVO selectSample(SampleVO vo) throws Exception {
        return (SampleVO) selectOne("sampleDAO.selectSample", vo);
    }

    /**
	 * SAMPLE 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 List<SampleDefaultVO>
	 * @return SAMPLE 목록
	 * @exception Exception
	 */
    public List<SampleDefaultVO> selectSampleList(SampleDefaultVO searchVO) throws Exception {
        return selectList("sampleDAO.selectSampleList", searchVO);
    }

    /**
	 * SAMPLE 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return SAMPLE 총 갯수
	 * @exception
	 */
    public int selectSampleListTotCnt(SampleDefaultVO searchVO) {
        return (Integer)selectOne("sampleDAO.selectSampleListTotCnt", searchVO);
    }

}
