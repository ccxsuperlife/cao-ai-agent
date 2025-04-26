package com.cao.caoaiagent.demo.invoke;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 本地安装大模型，Spring AI调用 OllamaAiInvoke 示例
 */

@Component
public class OllamaAiInvoke implements CommandLineRunner {

    @Resource
    private ChatModel ollamaChatModel;


    @Override
    public void run(String... args) throws Exception {

        String text = ollamaChatModel.call(new Prompt("你好啊"))
                .getResult()
                .getOutput()
                .getText();

        System.out.println(text);
    }
}
