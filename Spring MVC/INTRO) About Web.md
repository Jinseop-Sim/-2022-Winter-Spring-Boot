# Web
---
## HTTP
> 전 세계적인 통신 규약으로, 모든 것이 HTTP로 통신한다.  
> 지금은 HTTP의 시대!  

## Web server
> HTTP 기반으로 동작하는 서버로, 기본적으로 Static Resource(메인 HTML)을 제공하며,  
> 기타 부가기능들을 제공한다.  
- ex) NGINX, APACHE..

### WAS(Web Application Server)
> HTTP 기반으로 웹서버와 같이 동작한다.  
> 하지만 Web Server와는 다르게 추가적으로 동적인 HTML, JSON 등 기능을 제공한다.  
> 서블릿, JSP, 스프링 MVC 등등..  
- ex) TOMCAT, Jetty, Undertow..

- 하지만 사실, 둘의 경계는 매우 애매모호하다.
- 웹 서버도 프로그램을 실행하기도 하며, WAS도 웹 서버의 기능을 제공하기 때문이다.
- 그래서 Java에서는 보통, 서블릿 컨테이너를 제공하면 WAS라고 생각한다.
  - WAS는 Application Code를 실행하는데에 특화!

## Consist of Web System
> Web System은 기본적으로 WAS, DB 만으로 구성이 가능하긴 하다.  
> WAS가 정적 리소스, App logic 모두 제공을 하기 때문이다.  

- 하지만 그렇게 구성해버리면, WAS가 너무 많은 역할을 담당하기 때문에, 서버 과부하의 우려가 있다.
- WAS에 장애가 발생해버리면 오류 페이지 조차 출력이 불가능 해진다.  
![image](https://user-images.githubusercontent.com/71700079/150331116-6b4a966a-99fd-4e4d-a182-f9e70174df25.png)  

- 따라서 일반적으로는 WEB, WAS, DB로 구성이 된다.
  - Static Resource는 WEB server가 처리한다.
  - WAS가 App logic등의 동적인 요소들을 처리한다.  
  - WAS, DB 장애시 WEB server가 정적 페이지로 오류화면을 제공한다.
![image](https://user-images.githubusercontent.com/71700079/150331154-739fde5c-8508-4d7e-882b-8afeaf8b45e4.png)  

## Servelet
> HTTP의 요청 및 응답 정보를 굉장히 편리하게 다룰 수 있게 해주는 툴이다.  
> 따라서 개발자는 HTTP 스펙을 매우 편하게 다룰 수 있다.  
- HTTP Request가 들어오면,
  - WAS는 Request, Response 객체를 새로 만들어서 Servelet 객체를 호출한다.
  - 개발자는 Request 객체에서 HTTP 요청 정보를 편하게 꺼내서 사용.
  - 마찬가지로 Response 객체에서 HTTP 응답 정보를 편하게 꺼내서 사용.

### Servelet Container
- TOMCAT과 같이 Servelet을 지원하는 WAS를 Servelet Container이라고 한다.
- 기본적으로 Servelet 객체 생성, 초기화, 호출, 소멸하는 생명주기를 가진다.
- Servelet 객체는 Singleton으로 관리된다.
  - 고객의 요청마다 새로운 객체를 생성하는 것은 비효율적이다.
  - 최초 로딩 시점에 객체를 미리 만들어두고 재활용한다.
  - 따라서 모든 고객의 요청은 동일한 객체에 대한 접근.
  - __공유 변수__ 사용에 있어서 주의할 것!

## Thread

### 요청마다 쓰레드를 생성한다면?
- 장점
  - 동시 요청을 처리할 수 있다.
  - 리소스(CPU, MEMORY)가 허용하는 한 모두 처리 가능.
  - 하나의 Thread가 지연되어도, 나머지는 정상동작
- 단점
  - Thread 생성 비용은 매우 비싸다.
    - 응답 속도가 매우 느려질 것!
  - Thread는 Context Switching 비용이 발생한다.
  - Thread는 생성에 제한이 없어, 임계점을 넘어 서버가 죽을 수도 있다.

### Solve : Thread Pool
> 요청이 Thread Pool의 크기 이상으로 들어왔을 때, 요청을 대기 및 거절시킬 수 있다.  
- 사용
  - Thread가 필요하면, 이미 생성된 풀에서 꺼내서 사용한다.
  - 사용을 종료하면 Pool에 반납한다. (종료가 아님!)
- 장점
  - Thread를 미리 생성해놓는 것이므로, Thread의 생성 종료 비용이 절약된다.
  - 생성 가능한 Thread의 최대치가 정해져 있어서, 임계점을 넘는 요청이 들어와도 기존 요청은 안전하다.
- 실무에서의 TIP!
  - WAS의 주요 튜닝 포인트는 Max Thread의 수이다.
  - 이 값을 너무 낮게 설정하면?
    - 동시 요청이 너무 많으면, 서버 리소스는 여유롭지만 Clinet가 받아야 할 응답이 지연된다.
  - 이 값을 너무 높게 설정하면?
    - 리소스 임계점 초과로 서버가 다운될 위험이 증가한다.
  - 장애 발생시?
    - CLOUD면 일단 서버부터 늘리고, 이후에 튜닝한다.
    - 아니면 열심히 튜닝!
 - 적정 숫자를 어떻게 찾을 것인가?
  - Application Logic의 Complexity, CPU Memory, IO Resource 상황 모든 걸 고려해야한다.
  - 성능 테스트 TOOL
    - Apache ab
    - Jmeter
    - nGrinder
