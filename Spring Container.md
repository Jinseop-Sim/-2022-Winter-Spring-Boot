# Spring Container
---
## Spring Container??
> 우리는 앞서 AppConfig(공연 기획자)에 대해 이미 배웠다.  
> AppConfig도 Spring Container의 일종이다. (Annotation 기반의 자바 설정 클래스)  
```java
ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
```
- 이 때, ```ApplicationContext```가 Spring Container가 된다.

1. Create Spring Container  
![image](https://user-images.githubusercontent.com/71700079/148956338-2f69a52d-06cb-4731-b4bd-70066ae3492e.png)  

2. Spring Bean Regist  
![image](https://user-images.githubusercontent.com/71700079/148956445-bd33064e-86db-450c-9c92-be0bb79b2ec1.png)  
- 여기서 Bean의 이름이 겹쳐버리면, 오류가 발생할 수가 있다.
- 따라서 반드시 Bean의 이름들은 다르게 부여할 것!

3. Dependancy Injection in Bean  
![image](https://user-images.githubusercontent.com/71700079/148956710-27782462-bb2b-4ef8-838e-82c24364e06d.png)  
- 우리가 미리 설계 해놓은 대로 의존 관계(Dependancy)를 주입한다.
