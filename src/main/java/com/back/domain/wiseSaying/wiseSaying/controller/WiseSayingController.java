package com.back.domain.wiseSaying.wiseSaying.controller;

import com.back.domain.wiseSaying.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.wiseSaying.service.WiseSayingService;
import com.back.standard.util.service.MarkdownService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class WiseSayingController {
    private final WiseSayingService wiseSayingService;
    private final MarkdownService markdownService;

    @GetMapping("/wiseSayings/write")
    @ResponseBody
    public String write(String content, String author){

        if(content.isBlank()){
            throw new IllegalArgumentException("명언의 내용을 입력해주세요.");
        }
        if(author.isBlank()){
            throw new IllegalArgumentException("명언의 작성자를 입력해주세요.");
        }
        WiseSaying wiseSaying = wiseSayingService.write(content, author);

        return "%d번 명언 생성".formatted(wiseSaying.getId());
    }

    @GetMapping("/wiseSayings/{id}")
    @ResponseBody
    public String detail(@PathVariable int id) {
        WiseSaying wiseSaying = wiseSayingService.findById(id).get();

        String html = markdownService.toHtml(wiseSaying.getContent());

        return """
                <h1>%d번 명언</h1>
                <p>작가 : %s</p>
                <p>%s</p>
                """.formatted(wiseSaying.getId(),wiseSaying.getAuthor(), html);
    }
    @GetMapping("/wiseSayings")
    @ResponseBody
    public String list(){
        return "<ul>" +
                wiseSayingService.findAll()
                        .stream()
                        .map(wiseSaying -> "<li>%d : %s - %s</li>".formatted(wiseSaying.getId(), wiseSaying.getContent(), wiseSaying.getAuthor()))
                        .collect(Collectors.joining()) +
                "</ul>";


    }

    @GetMapping("/wiseSayings/{id}/delete")
    @ResponseBody
    public String delete(@PathVariable int id){
        WiseSaying wiseSaying = wiseSayingService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("%d번 명언은 존재하지 않습니다.".formatted(id)));

        wiseSayingService.delete(wiseSaying);

        return "%d번 명언 삭제".formatted(id);
    }



    @GetMapping("/wiseSayings/{id}/modify")
    @ResponseBody
    public String modify(
            @PathVariable int id,
            @RequestParam(defaultValue = "")String content,
            @RequestParam(defaultValue = "")String author) {

        if(content.isBlank()){
            throw new IllegalArgumentException("명언의 내용을 입력해주세요.");
        }
        if(author.isBlank()){
            throw new IllegalArgumentException("명언의 작성자를 입력해주세요.");
        }

        WiseSaying wiseSaying = wiseSayingService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("%d번 명언은 존재하지 않습니다.".formatted(id)));

        wiseSayingService.modify(wiseSaying, content, author);

        return "%d번 명언 수정".formatted(id);
    }
}
