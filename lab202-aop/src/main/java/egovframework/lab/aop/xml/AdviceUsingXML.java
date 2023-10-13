package egovframework.lab.aop.xml;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.lab.aop.common.BizException;

public class AdviceUsingXML {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdviceUsingXML.class);
	
	public void beforeTargetMethod(JoinPoint thisJoinPoint) {
		LOGGER.debug("\nAdviceUsingXML.beforeTargetMethod executed");
		
		@SuppressWarnings("unused")
	Class<? extends Object> clazz = thisJoinPoint.getTarget().getClass();
		String className = thisJoinPoint.getTarget().getClass().getSimpleName();
		String methodName = thisJoinPoint.getSignature().getName();
		
		// 현재 class, method  정보 및 method arguments 로깅
		StringBuffer buf = new StringBuffer();
		buf.append("\n==AdviceUsingXML.beforeTargetMethod : [" + className
				+ "."+ methodName + "()]==");
		Object[] arguments = thisJoinPoint.getArgs();
		int argCount = 0;
		for(Object obj : arguments) {
			buf.append("\n - arg");
			buf.append(argCount++);
			buf.append(" : ");
			// commons-lang 의 ToStringBuilder 를
			// 통해(reflection 을 이용)한 VO 정보 출력
			buf.append(ToStringBuilder.reflectionToString(obj));
		}
		
		// 대상 클래스의 logger 를 사용하여 method arguments 로깅
		LOGGER.debug(buf.toString());
	}
	
	public void afterTargetMethod(JoinPoint thisJoinPoint) {
		LOGGER.debug("AdviceUsingXML.afterTargetMethod executed");
	}
	
	public void afterReturningTargetMethod(JoinPoint thisJoinPoint, Object retVal) {
		LOGGER.debug("AdviceUsingXML.afterReturningTargetMethod executed.");
		
		@SuppressWarnings("unused")
	Class<? extends Object> clazz = thisJoinPoint.getTarget().getClass();
		String className = thisJoinPoint.getTarget().getClass().getSimpleName();
		String methodName = thisJoinPoint.getSignature().getName();
		
		// 현재 class, method 정보 및 method arguments 로깅
		StringBuffer buf = new StringBuffer();
		buf.append("\n==AdviceUsingXML.afterReturningTargetMethod :[" +
		className +"." + methodName + "()]==");
		
		buf.append("\n");
		
		// 결과값이 List이면 size와 전체 List 데이터를 풀어 reflection으로 출력 - 성능상 사용하지 않는 것이 좋음
		if(retVal instanceof List) {
			List<?> resultList = (List<?>) retVal;
			buf.append("resultList size :" + resultList.size() + "\n");
			for(Object oneRow : resultList) {
				buf.append(ToStringBuilder.reflectionToString(oneRow));
				buf.append("\n");
			}
		}else {
			LOGGER.debug(buf.toString());
		}
		}

	public void afterThrwoingTargetMethod(JoinPoint thisJoinPoint,Exception exception) throws Exception {
		LOGGER.debug("AdviceUsingXML.afterThrowingTargetMethod executed.");
		LOGGER.error("에러가 발생했습니다. {}", exception);
		
		//원본 exception 을 wrapping 하고 user-friendly 한메세지를 설정하여 새로운 Exception으로 re-throw
		throw new BizException("에러가 발상했습니다", exception);
		}
	
	public Object aroundTargetMethod(ProceedingJoinPoint thisJoinPoint) throws Throwable{
		LOGGER.debug("AdviceUsingXML.aroundTargetMethod start.");
			long time1 = System.currentTimeMillis();
			
			Object retVal = thisJoinPoint.proceed();
			
			long time2 = System.currentTimeMillis();
			LOGGER.debug("AdviceUsingXML.aroundTargetMethod end. Time{}", (time2 - time1));
			// JetBrans Mono A typeface for developers
			return retVal;
	
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}