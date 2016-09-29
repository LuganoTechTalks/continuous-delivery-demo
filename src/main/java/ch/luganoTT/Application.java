package ch.luganoTT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

@Controller
class GreetingController {

    //private final String bgColor = "#F6CECE"; //red
    private final String bgColor = "#CCFFCC"; //green
    

    @RequestMapping(value = "/", method = RequestMethod.GET)


    @ResponseBody

    public String helloHtml() {

        StringBuilder stringBuilder = new StringBuilder()
                .append("<HTML>")
                .append("<BODY bgcolor=\"").append(bgColor).append("\">")
                .append("<h1>Hello Lugano Tech Talks!!</h1>")
                .append("</BODY>")
                .append("</HTML>");

        return stringBuilder.toString();
    }
}