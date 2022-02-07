package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController // @Controller을 달고 return을 하면 ViewName을 반환하는 것!
public class LogTestController {

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        System.out.println("name = " + name);

        log.trace("trace log={}", name); // Trace 레벨이 가장 낮음
        log.debug("debug log={}", name); // Debug 까지만 보면 Trace가 안나옴. 개발 단계.
        log.info(" info log={}", name); // 운영 서버에서는 보통 Info 레벨까지.
        log.warn(" warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}
