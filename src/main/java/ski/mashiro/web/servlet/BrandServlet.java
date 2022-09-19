package ski.mashiro.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ski.mashiro.pojo.Brand;
import ski.mashiro.service.BrandService;
import ski.mashiro.util.DeserializeJsonUtils;

import java.io.IOException;
import java.util.List;

/**
 * @author FeczIne
 */
@WebServlet(name = "BrandServlet", value = "/brand/*")
public class BrandServlet extends BaseServlet {
    private static final String POST = "POST";
    BrandService brandService = new BrandService();
    ObjectMapper objectMapper = new ObjectMapper();

    public void saveBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (POST.equals(req.getMethod())) {
            String newBrandJson = DeserializeJsonUtils.deserializeJson(req);
            Brand brand = objectMapper.readValue(newBrandJson, Brand.class);
            boolean rs = brandService.saveBrand(brand);
            resp.setContentType("text/json;charset:utf-8");
            resp.getWriter().write("{\"brandName\":\"" + brand.getBrandName() + "\",\"result\":" + rs + "}");
        }
    }
    public void deleteBrandById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (POST.equals(req.getMethod())) {
            String reqId = DeserializeJsonUtils.deserializeJson(req);
            String id = reqId.substring(reqId.lastIndexOf(":") + 2, reqId.lastIndexOf("}") - 1);
            resp.setContentType("text/json;charset:utf-8");
            try {
                boolean rs = brandService.deleteBrandById(Integer.parseInt(id));
                resp.getWriter().write("{\"result\":" + rs + "}");
            } catch (Exception e) {
                resp.getWriter().write("{\"result\":" + false + "}");
            }
        }
    }
    public void listAllBrands(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (POST.equals(req.getMethod())) {
            List<Brand> brands = brandService.listAllBrands();
            resp.setContentType("text/json;charset:utf-8");
            resp.getWriter().write(objectMapper.writeValueAsString(brands));
        }
    }
    public void getBrandById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (POST.equals(req.getMethod())) {
            String reqId = DeserializeJsonUtils.deserializeJson(req);
            String id = reqId.substring(reqId.lastIndexOf(":") + 2, reqId.lastIndexOf("}") - 1);
            Brand brand = null;
            resp.setContentType("text/json;charset:utf-8");
            try {
                brand = brandService.getBrandById(Integer.parseInt(id));
                if (brand.getId() != null) {
                    resp.getWriter().write("[" + objectMapper.writeValueAsString(brand) + "]");
                } else {
                    resp.getWriter().write("{\"result\":" + false + "}");
                }
            } catch (Exception e) {
                resp.getWriter().write("{\"result\":" + false + "}");
            }
        }
    }
    public void listBrandsByBrandName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (POST.equals(req.getMethod())) {
            String reqName = DeserializeJsonUtils.deserializeJson(req);
            String name = reqName.substring(reqName.indexOf(":") + 2, reqName.lastIndexOf("\""));
            List<Brand> brands = brandService.listBrandsByBrandName(name);
            resp.setContentType("text/json;charset=UTF-8");
            if (brands.size() > 0) {
                resp.getWriter().write(objectMapper.writeValueAsString(brands));
            } else {
                resp.getWriter().write("{\"result\":" + false + "}");
            }
        }
    }
    public void returnSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (POST.equals(req.getMethod())) {
            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write("{\"username\":\"" + req.getSession().getAttribute("username") + "\"}");
        }
    }
}
