# Object Oriented Programming
---
## Polymorphism
> 이 세상을 역할(Interface)과 구현(Implements)으로 구분한다!  
- Example : 운전자 - 자동차
  - 자동차 역할 -> 자동차 구현(K3, Avante, Tesla Model 3)
  - 자동차는 바뀌어도, 운전자에는 영향을 미치지 않는다.
  - 즉, 운전자는 자동차 역할의 기능만 알면 되고, 자동차 구현은 몰라도 된다!
  - 새로운 자동차가 나와도, Client들은 새로운 기능을 배울 필요가 없어야 하는 것이 중요!

- Example2 : 로미오와 줄리엣
  - 로미오 역할 : 장동건, 원빈, 최수종, ..
  - 줄리엣 역할 : 김태희, 송혜교, 한지민, ..
  - 로미오와 줄리엣 역은 대체 가능한 배우들이 얼마든지 많이 있다!
    - 이것이 바로 유연하고 변경에 용이한 OOP의 특징이다.
   
- 결론은 Client는 Interface의 기능들의 사용법만 알 것.
- Client는 구현 대상의 내부 구조를 전혀 몰라도 된다.
- Client는 구현 대상의 내부 구조 변경에 영향을 받지 않는다.
- Client는 구현 대상 자체가 바뀌어도 영향을 받지 않는다.
 
- 다형성의 본질은 결국 실행 시간에 유연하게 객체 인스턴스를 변경할 수 있는 것!(Dynamic Binding)
- 객체는 Client와 Server간의 협력이다!

## SOLID
- SRP(단일 책임 원칙)
  - 한 클래스는 하나의 책임만 가져야한다.
  - 어떤 클래스에 변경이 일어났을 때, 파급효과가 적어야 SRP를 잘 따른 것이다.
  - ex) UI 변경, 객체의 생성과 사용을 분리.

- OCP(개방-폐쇄 원칙, 중요!)
  - 소프트웨어 요소는 __확장에는 열려__ 있으나, __변경에는 닫혀__ 있어야 한다.
  - 다형성을 활용하여, 기존 코드를 변경하는 것이 아닌 Interface를 구현한 새로운 클래스를 만들어 새로운 기능을 구현하는 것.
  - 역할과 구현의 분리를 잘 생각해보자.
  - OCP의 문제점
    ```java
      public class MemberService{
        private MemberRepository memberRepository = new MemoryMemberRepository();
      }
      
      public class MemberService{
        //private MemberRepository memberRepository = new MemoryMemberRepository();
        private MemberRepository memberRepository = new JdbcMemberRepository();
    ```
    - 위와 같이 구현 객체를 변경하려면, Client 코드를 변경해야만 한다.
    - 분명히 다형성을 사용했지만, OCP가 깨졌다.(변경에 열려버렸다.)
    - 이 문제는 객체를 생성하고 관계를 맺어주는 별도의 설정자가 필요하다!(Spring Container!)
 
- LSP(리스코프 치환 원칙)
  - 프로그램의 객체는 정확성을 깨뜨리지 않고, 하위 타입의 인스턴스로 바꿀 수 있어야 한다.
  - 컴파일 단계에서의 원칙이 아닌 기능면에서의 원칙이다.
  - ex) 자동차 Interface의 엑셀은 앞으로 가는 기능이다. 뒤로 가도록 구현하면 LSP가 위반된 것!

- ISP(인터페이스 분리 원칙)
  - 특정 Client를 위한 인터페이스 여러 개가 범용 Interface 하나보다 낫다.
  - 자동차 Interface ==> 운전, 정비 Interface로 분리
  - 사용자 Client ==> 운전자, 정비사 Client로 분리
  - 분리된 서로의 Clinet는 영향을 끼치지 않는다.
  - 명확한 Interface의 생성.

- DIP(의존관계 역전 원칙, 중요!)
  - 프로그래머는 __추상화에 의존해야 하며, 구체화에 의존해서는 안된다!__
  - 구현 클래스에 의존하지 말고, Interface에 의존할 것.
  - 즉 역할에 의존해야 하는 것!

## 관심사를 분리시키자!
> 어플리케이션을 하나의 공연이라고 생각을 해보자.  
> 각 Interface는 역할(배우)이다. 그런데 이 때 실제 배역에 맞는 배우는 누가 선택하는가?  
- 우리가 DIP를 위반하는 것은, 각 배역에 맞는 배우를 배우가 직접 고르는 꼴이 되어버린다.
- 따라서 배우를 할당시켜 줄 __공연 기획자가__ 필요한 시점이다.
- 공연 기획자를 만들고, 배우와 공연 기획자의 책임을 확실히 분리시키자!

### APP Configuration의 등장
> App의 전체 동작 방식을 구성하기 위해, __구현 객체를 생성__ 하고 __연결__ 하는 책임을 가지는 별도의 설정 클래스를 만들어보자.  
- Appconfig가 앞서 말했던 __공연 기획자__ 의 역할이다.
- 우리(배우)가 직접 구현체를 선택하는 것이 아니다.
- 우리는 역할(Interface)만 선언을 해주고, __AppConfig__ 가 어떤 구현체를 사용할 것인지를 정해줄 것이다.
