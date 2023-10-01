package ptithcm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Transactional
@Controller
@RequestMapping("/imageSearch")
public class imageSearchController {
	@RequestMapping("/test")
	public String imageSearch(HttpServletRequest request, ModelMap model) {
		return "shop/imageSearch";
	}
}
