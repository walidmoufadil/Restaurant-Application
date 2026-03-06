package org.example.mcpclient.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
public class Agent {
    private final ChatClient chatClient;

    private Agent(ChatClient.Builder chatClient, ToolCallbackProvider toolCallbackProvider) {
        this.chatClient = chatClient
                .defaultSystem("Answer the user question using provided tools")
                .defaultToolCallbacks(toolCallbackProvider)
//                .defaultAdvisors(MessageChatMemoryAdvisor
//                        .builder(MessageWindowChatMemory.builder().build()).build())
                .build();
    }

    public String prompt(String question) {
        return chatClient.prompt()
                .user(question).call().content();
    }

}