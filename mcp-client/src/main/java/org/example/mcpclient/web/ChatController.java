package org.example.mcpclient.web;

import lombok.Data;
import org.example.mcpclient.service.Agent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@CrossOrigin("*")
public class ChatController {
    private final Agent agent;

    @Data
    public static class ChatRequest {
        private String message;
    }

    @Data
    public static class ChatResponse {
        private String content;
        private String type;  // peut être "text", "table", "list", etc.
        private String format; // "markdown", "html", etc.
    }

    public ChatController(Agent agent) {
        this.agent = agent;
    }

    @PostMapping("/send")
    public ResponseEntity<ChatResponse> sendMessage(@RequestBody ChatRequest request) {
        String rawResponse = agent.prompt(request.getMessage());

        ChatResponse response = new ChatResponse();
        response.setContent(rawResponse);
        response.setType(detectResponseType(rawResponse));
        response.setFormat("markdown");

        return ResponseEntity.ok(response);
    }

    private String detectResponseType(String response) {
        if (response.contains("|") && response.contains("\n")) {
            return "table";
        } else if (response.contains("- ")) {
            return "list";
        }
        return "text";
    }
}
