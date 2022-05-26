package com.cxp.openadmin.configuration;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Properties;

@Configuration
public class KaptchaConfiguration {

    @Bean
    public DefaultKaptcha producer() {
        Properties properties = new Properties();//properties
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.border.color", "red");
        properties.put("kaptcha.textproducer.font.color", "black");
        properties.put("kaptcha.textproducer.char.space", "1");
        properties.put("kaptcha.textproducer.char.length", "4");//보안 문자 텍스트 길이(문자)  默认为5
        properties.put("kaptcha.image.width", "100");
        properties.put("kaptcha.image.height", "40");
        properties.put("kaptcha.textproducer.font.size", "36");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

//	kaptcha.border  테두리가 있는지 여부, 기본값은 true입니다. yes, no를 설정할 수 있습니다.
//	kaptcha.border.color   테두리 색상은 기본적으로 Color.BLACK으로 설정되어 있습니다.
//	kaptcha.border.thickness  테두리 두께는 기본적으로 1로 설정됩니다.
//	kaptcha.producer.impl   보안 문자 생성기는 기본적으로 DefaultKaptcha로 설정됩니다.
//	kaptcha.textproducer.impl   보안 문자 생성기 기본값은 DefaultTextCreator입니다.
//	kaptcha.textproducer.char.string   보안 문자 텍스트 문자 내용 범위 기본값은 abcde2345678gfynmnpwx입니다.
//	kaptcha.textproducer.char.length   인증 코드 텍스트 문자의 길이는 기본적으로 5입니다.
//	kaptcha.textproducer.font.names    보안 문자 텍스트 글꼴 스타일 기본값은 new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)입니다.
//	kaptcha.textproducer.font.size   보안 문자 텍스트 문자 크기 기본값은 40입니다.
//	kaptcha.textproducer.font.color  Captcha 텍스트 문자 색상 기본값은 Color.BLACK입니다.
//	kaptcha.textproducer.char.space  Captcha 텍스트 문자 간격 기본값은 2입니다.
//	kaptcha.noise.impl    확인 코드 노이즈 생성 개체의 기본값은 DefaultNoise입니다.
//	kaptcha.noise.color   보안 문자 노이즈 색상 기본값은 Color.BLACK입니다.
//	kaptcha.obscurificator.impl   보안 문자 스타일 엔진 기본값은 WaterRipple입니다.
//	kaptcha.word.impl   보안 문자 텍스트 문자 렌더링 DefaultWordRenderer
//	kaptcha.background.impl   Captcha 백그라운드 생성기 기본값은 DefaultBackground입니다.
//	kaptcha.background.clear.from   인증 코드의 배경색은 프로그레시브이며 기본값은 Color.LIGHT_GRAY입니다.
//	kaptcha.background.clear.to   인증 코드의 배경색은 프로그레시브이며 기본값은 Color.WHITE입니다.
//	kaptcha.image.width   보안 문자 이미지 너비는 기본적으로 200으로 설정됩니다.
//	kaptcha.image.height  보안 문자 이미지의 높이는 기본적으로 50입니다.

}
