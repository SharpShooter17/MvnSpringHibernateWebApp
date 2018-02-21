package web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;

@Controller
public class Home {

    @RequestMapping("/")
    public String index(Model model) {
		System.out.println("index");
		DatabaseSingleton db = DatabaseSingleton.instance();
		db.refreshRanking();
		model.addAttribute("Rank", db.getRankingList());
        return "main";
    }
}