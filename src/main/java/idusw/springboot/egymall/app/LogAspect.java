package idusw.springboot.egymall.app;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Aspect
@Component
class LogAspect { // @AspectJ 지원 AOP, 횡단 관심 - 공통 기능 (로그, .... ),
    // 1. module dependency 해결 - builde.gradle (spring-aop...)
    // 2. Aspect -> Advice, Pointcut 정의
    // 3. 시점 결정
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(* idusw.springboot.egymall.serivce.*ServiceImpl.loginById*(..))")
    public void getLogging() {} // 포인트 컷 서명

    @Before("getLogging()")
    public void loggerBefore(JoinPoint joinPoint) { // Advice

        for(int i = 1; i < 10; i++)
            System.out.println("횡단관심,공통코드 : 보안, 트랜잭션, 로깅 .. ");

//        String message = "AOP - 로깅 시작";
//        logger.info(message); // vs. System.out.println() : 표준 출력으로 다름

    }
    @AfterReturning("execution(* idusw.springboot.egymall.serivce.MemberServiceImpl.*(..))")
    public void loggerAfterReturning(JoinPoint joinPoint) { // Advice
        String message = "트랜잭션 처리";
        logger.info(message);
    }
}