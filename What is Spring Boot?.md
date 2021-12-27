# What is Spring Boot?
---
> 먼저, Spring은 자바 기반의 __Web Framework__ 이다.  
> 다른 언어의 Framework에는 Python의 Django, Flask, Javascript의 Node.js 등이 있다.  
> Spring Boot는 자바의 Spring을 더 쉽게 이용하기 위한 도구라고 볼 수 있다.  

## Spring의 특징
- TOMCAT과 같은 WAS(Web Application Server)가 내장이 되어있어, 자바 웹 어플리케이션 구동이 가능하다.
- Spring은 일종의 Container로, 자바 객체를 Spring 내에서 직접 관리한다.
- Spring 가장 중요한 특징은 __IOC, DI__ 이다.
  - IOC(Inversion Of Control)
    - 보통 자바 프로그래밍은 사용자가 모든 작업을 제어하도록 되어있다.
    - 하지만 Spring의 경우는, 객체를 Spring이 직접 관리하도록 하고 사용자는 메소드 호출만 하도록 되어있다.
    - 즉, 제어 권한을 Spring에게 위임하는 것이다.
  - DI(Dependancy Injection)
    - 객체를 Spring에서 생성해서 사용자가 쓰려는 객체에 의존성을 주입시킨다.
