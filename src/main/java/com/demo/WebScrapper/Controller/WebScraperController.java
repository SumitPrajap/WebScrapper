package com.demo.WebScrapper.Controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class WebScraperController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/scrape")
    public String scrape(@RequestParam String url, @RequestParam(required = false) String[] tags, Model model) {
        Map<String, String> scrapedData = new HashMap<>();
        try {
            Document doc = Jsoup.connect(url).get();
            if (tags != null) {
                for (String tag : tags) {
                    Elements elements = doc.select(tag);
                    scrapedData.put(tag, elements.text());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("url", url);
        model.addAttribute("scrapedData", scrapedData);
        return "result";
    }
}
