package devtalks.springdatarest.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import javax.servlet.http.HttpSession;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

@RestController
@RequestMapping("/admin")
public class ArgumentsInjectionDemoController {

    @GetMapping("/headers")
    public String headers(@RequestHeader Map<String, String> headers)
    {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, String> entry : headers.entrySet()) {

            builder.append(entry.getKey() + ": " + entry.getValue() + "\n");
        }
        return builder.toString();
    }

    @GetMapping("/headers/ct")
    public String headers(@RequestHeader("Content-Type") String contentType)
    {
        return "Content-Type: " + contentType;
    }

    @GetMapping("/cookies")
    public String cookie(@CookieValue("JSESSIONID") String sessionId)
    {
        return "JSESSIONID: " + sessionId;
    }

    @GetMapping("/request")
    public void requestAndSession(WebRequest request,
                                  NativeWebRequest nativeWebRequest,
                                  HttpSession session)
    {
        System.out.println();
    }

    @GetMapping("/locale")
    public String locale(Locale locale)
    {
        return "Your locale: " + locale.toString();
    }

    @GetMapping("/timezone")
    public String timeZone(TimeZone timeZone, ZoneId zoneId)
    {
        StringBuilder builder = new StringBuilder();

        builder.append(timeZone);
        builder.append("</br>");
        builder.append("zoneId: ").append(zoneId);

        return builder.toString();
    }
}
