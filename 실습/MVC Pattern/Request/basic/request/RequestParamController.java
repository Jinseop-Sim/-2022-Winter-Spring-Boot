package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age = {}", username, age);

        response.getWriter().write("OK");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                               @RequestParam("age") int memberAge) throws IOException{
        log.info("username = {}, age = {}", memberName, memberAge);
        return "OK!";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3") // 요청 파라미터에서 데이터 읽어옴
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) throws IOException{
        log.info("username = {}, age = {}", username, age);
        return "OK!";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4") // 단순타입만.
    public String requestParamV4(String username, int age) throws IOException{
        log.info("username = {}, age = {}", username, age);
        return "OK!";
    }

    @ResponseBody
    @RequestMapping("/request-param-required") // 반드시 들어와야하는 파라미터. 안들어오면 오류!
    public String requestParamRequired(@RequestParam(required = true,defaultValue = "guest") String username,
                                       @RequestParam(required = false, defaultValue = "-1") int age) throws IOException{
        // 여기서 age는 int형인데, 이는 기본형이라 null이 들어갈 수 없다. 그 말인 즉슨 값을 안넣으면 자동으로 null이 들어가는 것.
        // username의 경우 아무것도 안적으면 ""로 인식, null과 다르다!
        // 신기하게도 defaultvalue 옵션의 경우, ""로 넣어도 기본 값이 나온다!
        log.info("username = {}, age = {}", username, age);
        return "OK!";
    }

    @ResponseBody
    @RequestMapping("/request-param-map") // 반드시 들어와야하는 파라미터. 안들어오면 오류!
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) throws IOException{
        log.info("username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));
        return "OK!";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok!";
    }
}
