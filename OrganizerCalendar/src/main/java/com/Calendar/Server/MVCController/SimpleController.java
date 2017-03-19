package com.Calendar.Server.MVCController;

import com.Calendar.Client.OrderConfirmation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Владимир on 05.02.2017.
 */
@Controller
public class SimpleController {
    @RequestMapping(value = "/session", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody OrderConfirmation handleRequest(HttpServletRequest request,
                                                         HttpServletResponse response) throws ServletException, IOException {

        OrderConfirmation confirmation = new OrderConfirmation();
        confirmation.message = "HELLO MOTHERFACKER";
        confirmation.ready_time = System.currentTimeMillis() + 1000 * 60 * 30;
        return confirmation;
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody OrderConfirmation GetRequest(HttpServletRequest request,
                                                         HttpServletResponse response) throws ServletException, IOException {

        OrderConfirmation confirmation = new OrderConfirmation();
        confirmation.message = "HELLO MOTHERFACKER";
        confirmation.ready_time = System.currentTimeMillis() + 1000 * 60 * 30;
        return confirmation;
    }
}
