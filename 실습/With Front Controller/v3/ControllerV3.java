package hello.servlet.Web.frontcontroller.v3;
import hello.servlet.Web.frontcontroller.ModelView;
import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);
}
