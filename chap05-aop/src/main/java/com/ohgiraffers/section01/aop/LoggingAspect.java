package com.ohgiraffers.section01.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Aspect
@Component
public class LoggingAspect {
    
    /*
    * @Aspect
    * - pointcut 과 advice 를 하나의 클래스 단위로 정의하기 위한 어노테이션이다.
    * 
    * 조인 포인트 - aop 에서 특정한 실행 지점을 의미한다.
    * 종류
    *   메소드 호출 = 특정 메소드가 호출될 때
    *   메소드 실행 = 특정 메소드가 실행이 완료된 후
    *   예외 발생 = 특정 메소드 내에서 예외가 발생할 때
    *   필드 접근 = 객체의 필드에 접근할 때
    * 
    * pointcut = 특정 조건에 의해 필터핑된 조인 포인트
    * 수 많은 조인포인트 중에 특정 메소드만 횡단 공통기능을 수행하기 위해 사용한다.
    * 
    * advice = 횡단 관심에 해당하는 공통 기능의 코드 / 독립된 메소드로 작성
    * */
    
    
    /*
    * pointcut = 관심 조인 포인트를 결정하여 어드바이스가 실행되는 시기를 제어한다.
    * execution = 메소드 실행 조인 포인트를 매칭한다.
    * 
    * execution ( * 리턴타입 . 이름(파라미터))
    * *Service.*(..) = 매개변수가 0개 이상인 모든 메소드
    * *Service.*(*) = 매개변수가 1개인 모든 메소드
    * *Service.*(*,*) = 매개변수가 2개인 모든 메소드
    * */

    // pointcut
    @Pointcut("execution(* com.ohgiraffers.section01.aop.*Service.*(..))") // 조인포인트 지정만 - 실행부 x
    public void logPointCut(){
        System.out.println("이거 진짜 실행안되나?"); // 실행안됨
    }

    // advice
    // ex. 성능진단, 예외처리, 보안, 특정 메소드 성능 체크(메소드 실행/종료시간 확인), 사용자가 권한을 갖고 있는지 체크, transaction.. 등..
    @Before("LoggingAspect.logPointCut()")
    public void logBefore(JoinPoint joinPoint){
        // 현재 실행 중인 타깃 객체를 반환
        System.out.println("before joinpoint.getTarget() : " + joinPoint.getTarget());

        // 호출된 메소드의 시그니처 반환
        System.out.println("before joinpoint.getSignature() : " + joinPoint.getSignature());

        if(joinPoint.getArgs().length > 0){
            System.out.println("before joinpoint.getArgs()[] : " + joinPoint.getArgs()[0]);
        }
    }

    @After("logPointCut()")
    public void logAfter(JoinPoint joinPoint){
        System.out.println("after joinpoint.getTarget() : " + joinPoint.getTarget());
        System.out.println("after joinpoint.getSignature() : " + joinPoint.getSignature());
    }

    // aop 가 적용될 메소드가 에러 없이 정상적으로 실행된 이후 실행
    @AfterReturning(pointcut = "logPointCut()", returning = "result") // returning 에 정해진 이름대로 두번째 인자의 이름을 받아야 한다.
    public void logAfterReturning(JoinPoint joinPoint, Object result){

        System.out.println("After Returning result : " + result); // 호출한 메소드의 결과값
        // 꼭 가지고 있어야 하는 정보를 특정 조건(ex.성공 시)에서만 넘겨줄 때 사용
//        if(result != null && result instanceof Map){
//            ((Map<Integer, MemberDTO>)result).put(100, new MemberDTO(100, "가공한 값"));
//        }
    }

    @AfterThrowing(pointcut = "logPointCut()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Exception exception){
        System.out.println("After Throwing exception : " + exception);
        // After Throwing exception : java.lang.RuntimeException: 해당 id를 가진 회원은 존재하지 않습니다.
    }


    /*
     * Around Advice 는 가장 강력한 어드바이스이다.
     * 이 어드바이스는 조인포인트를 완전히 장악한다.
     * 따라서 앞에 살펴 본 어드바이스 모두 Around 어드바이스로 조합할 수 있다.
     * 매개변수는 ProceedingJoinPoint 로 고정되어 있다.
     * JoinPoint 의 하위 인터페이스로 원본 조인포인트의 진행 시점을 제어할 수 있다.
     * - 원본 메소드가 실행될지도 정할 수 있음.
     * - 단점 :
     *      가독성 좋지 않음.
     *      유지보수성 좋지 않음.
     *      원본 메소드에 영향을 줄 수 있음.
     *      잘못된 동작이 일어날 수 있다.
     * */
    // 제일 강력한 advice (모두 조합해서 쓸 수 있음)
    @Around("logPointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        // 메소드 실행 전 로그 출력
        System.out.println("Around Before : " + joinPoint.getSignature().getName());

        // 원본 메소드 실행
        Object result = joinPoint.proceed();
        // Around 에서 원본을 실행하지 않으면 다른 메소드들이 동작하지 않는다.

        if(result != null && result instanceof Map){
            ((Map<Integer, MemberDTO>)result).put(100, new MemberDTO(100, "반환 값 가공"));
        }

        // 메소드 실행 후 로그 출력
        System.out.println("Around after : " + joinPoint.getSignature().getName());

        // 결과 반환
        return result;
    }
}
