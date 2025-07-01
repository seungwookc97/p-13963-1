package com.back.global.initData;

import com.back.domain.wiseSaying.wiseSaying.service.WiseSayingService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {
    private final WiseSayingService wiseSayingService;

    @Bean
    ApplicationRunner baseInitDataApplicationRunner() {
        return args -> {
            if (wiseSayingService.count() > 0) return;

            if (wiseSayingService.findAll().isEmpty()) {
                wiseSayingService.write("""
                        - 행복은 습관이다. 그것을 몸에 지니라
                        - 운동을 열심히 해라
                        """.stripIndent(), "허버트 조지 웰스");
                wiseSayingService.write("신념이 있는 곳에 길이 있다.", "마하트마 간디");
                wiseSayingService.write("행복은 결코 우연히 오지 않는다. 그것은 당신의 행동에 의해 만들어진다.", "엘리노어 루즈벨트");
                wiseSayingService.write("성공은 최종 목적지가 아니라 여정이다.", "아서 애쉬");
                wiseSayingService.write("당신이 할 수 있다고 믿든, 할 수 없다고 믿든, 당신은 옳다.", "헨리 포드");
                wiseSayingService.write("인생은 10%의 사건과 90%의 반응으로 이루어져 있다.", "찰스 R. 스윈돌");
            }
        };
    }


}
