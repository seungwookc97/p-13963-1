package com.back.standard.util.service;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Service;

@Service
public class MarkdownService {
    private final HtmlRenderer htmlRenderer;
    private final Parser parser;

    public MarkdownService() {
        htmlRenderer = HtmlRenderer.builder().build();
        parser = Parser.builder().build();
    }

    public String toHtml(String markdown) {
        Node document = parser.parse(markdown);
        return htmlRenderer.render(document);
    }
}
