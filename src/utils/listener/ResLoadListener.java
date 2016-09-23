package utils.listener;


import com.opensymphony.xwork2.ActionContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.*;
import java.util.List;

/**
 * Created by RZ on 7/1/16.
 */
public class ResLoadListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/erp?useUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=false","root","root");
            Statement st = conn.createStatement();
            String sql  = "select url from tbl_res";
            ResultSet rs = st.executeQuery(sql);
            StringBuilder sb = new StringBuilder();
            while(rs.next()) {
                sb.append(rs.getString("url"));
                sb.append(",");
            }
            sce.getServletContext().setAttribute("allRes",sb.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
