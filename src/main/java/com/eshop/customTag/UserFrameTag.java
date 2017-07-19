package com.eshop.customTag;

import com.eshop.dao.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class UserFrameTag extends SimpleTagSupport {

    private static final Logger log = Logger.getLogger(UserFrameTag.class);

    public void doTag() throws JspException{

        PageContext pageContext = (PageContext) getJspContext();
        HttpSession session = pageContext.getSession();
        User user = (User) session.getAttribute("user");
        JspWriter out = pageContext.getOut();
        StringBuffer sb = new StringBuffer();
        sb.append("<div align=\"right\">");

        if (user.isAdmin()){
            sb.append("<a href=\"/?command=admin\">");
            sb.append(user.getFirstName());
            sb.append("</a>\n");
        }
        else {
            sb.append("Hello ").append(user.getFirstName()).append("<br/>");
            sb.append("Cash <br/>").append(user.getCash()).append("<br/>");
            sb.append("<form method=\"post\" action=\"/\">");
            sb.append("<p><input name=\"amount\" type=\"number\" min=\"1\"" +
                    " max=\"20000\" size=\"3\" required> Add money</p>");
            sb.append("<input type=\"submit\" value=\"add money\">");
            sb.append("<input type=\"hidden\" name=\"command\" value=\"addMoney\">");
            sb.append("</form>");
            sb.append("<a href=\"/?command=bucket\">bucket</a> <br/>");
            sb.append("<br/>");
        }

        sb.append("<form method=\"post\" action=\"/\">");
        sb.append("<input name=\"command\" type=\"submit\" value=\"logout\">");
        sb.append("<input type=\"hidden\" name = \"toBucket\" value=\"toBucket\">");
        sb.append("</form>");

        sb.append("</div>");

        try {
            out.println(sb.toString());
        } catch (IOException e) {
            log.info("IOException in UserFrameTag");
        }
    }
}
