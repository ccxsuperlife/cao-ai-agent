package com.cao.caoaiagent.demo.invoke;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 使用 HttpUtil 封装的 DashScope API 调用示例
 */
@Slf4j
public class HttpAiInvoke {

    private static final String DASHSCOPE_API_URL = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation";
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String BEARER_PREFIX = "Bearer ";

    private static final String KEY_MODEL = "model";
    private static final String KEY_INPUT = "input";
    private static final String KEY_MESSAGES = "messages";
    private static final String KEY_ROLE = "role";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_PARAMETERS = "parameters";
    private static final String KEY_RESULT_FORMAT = "result_format";

    private static final String ROLE_SYSTEM = "system";
    private static final String ROLE_USER = "user";

    private static final String RESULT_FORMAT_MESSAGE = "message";

    /**
     * 调用 DashScope API
     *
     * @param apiKey       API Key
     * @param model        模型名称
     * @param systemPrompt 系统提示
     * @param userPrompt   用户提示
     * @return 解析后的 JSON 响应对象，如果请求失败则返回 null
     */
    public static JSONObject callDashScopeApi(String apiKey, String model, String systemPrompt, String userPrompt) {
        // 1. 构建请求体 JSON
        JSONObject requestBody = buildRequestBody(model, systemPrompt, userPrompt);

        try {
            // 2. 发送 HTTP POST 请求
            HttpResponse response = HttpRequest.post(DASHSCOPE_API_URL)
                    .header(HEADER_AUTHORIZATION, BEARER_PREFIX + apiKey)
                    .header(HEADER_CONTENT_TYPE, CONTENT_TYPE_JSON)
                    .body(requestBody.toString()) // Hutool 会自动处理 JSON 序列化
                    .timeout(60000) // 设置超时时间，例如 20 秒
                    .execute();

            // 3. 处理响应
            if (response.isOk()) { // isOk() 检查状态码是否为 2xx
                String responseBody = response.body();
                log.debug("DashScope API Response: {}", responseBody);
                return JSONUtil.parseObj(responseBody);
            } else {
                log.error("DashScope API request failed! Status: {}, Body: {}", response.getStatus(), response.body());
                return null;
            }
        } catch (Exception e) {
            log.error("Error calling DashScope API", e);
            return null;
        }
    }

    private static JSONObject buildRequestBody(String model, String systemPrompt, String userPrompt) {
        JSONObject systemMessage = new JSONObject()
                .set(KEY_ROLE, ROLE_SYSTEM)
                .set(KEY_CONTENT, systemPrompt);

        JSONObject userMessage = new JSONObject()
                .set(KEY_ROLE, ROLE_USER)
                .set(KEY_CONTENT, userPrompt);

        JSONArray messagesArray = new JSONArray(new Object[]{systemMessage, userMessage});

        JSONObject input = new JSONObject().set(KEY_MESSAGES, messagesArray);
        JSONObject parameters = new JSONObject().set(KEY_RESULT_FORMAT, RESULT_FORMAT_MESSAGE);

        return new JSONObject()
                .set(KEY_MODEL, model)
                .set(KEY_INPUT, input)
                .set(KEY_PARAMETERS, parameters);
    }

    // 可以在这里添加一个 main 方法用于快速测试
    public static void main(String[] args) {
        // 从环境变量或其他安全配置中获取 API Key
        String apiKey = TestApiKey.API_KEY;// 示例：从环境变量获取
        if (apiKey == null || apiKey.isEmpty()) {
            log.error("API Key not found. Please set the DASHSCOPE_API_KEY environment variable.");
             // 注意：在实际项目中，不应将 API Key 硬编码在代码中
             apiKey = "sk-daea9aac729e4b0cacf797d407be5774"; // 临时用于测试
             if (apiKey == null || apiKey.isEmpty()) return;
        }

        String model = "qwen-plus";
        String systemPrompt = "You are a helpful assistant.";
        String userPrompt = "介绍一下避孕套？";

        JSONObject responseJson = callDashScopeApi(apiKey, model, systemPrompt, userPrompt);

        if (responseJson != null) {
            log.info("API Call Successful:");
            // 可以根据实际返回的 JSON 结构提取需要的信息
            // 例如：String outputText = responseJson.getByPath("output.choices[0].message.content", String.class);
            log.info(JSONUtil.toJsonPrettyStr(responseJson));
        } else {
            log.error("API Call Failed.");
        }
    }
} 