import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okio.BufferedSource
import org.json.JSONObject
import java.io.IOException
import kotlin.math.log

class OllamaClient {
    private val client = OkHttpClient()
    private val baseUrl = "http://localhost:11434/api/generate"
    private var lastResponse: String? = null

    fun streamResponse(prompt: String, onResponse: (String) -> Unit, onComplete: () -> Unit, onError: (Exception) -> Unit) {
        val requestBody = JSONObject()
            .put("model", "llama3.2")
            .put("prompt", prompt)
            .put("stream", true)
            .toString()
            .toRequestBody("application/json".toMediaType())

        val request = Request.Builder()
            .url(baseUrl)
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onError(e)
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    onError(IOException("Unexpected code $response"))
                    return
                }

                response.body?.use { responseBody ->
                    val source: BufferedSource = responseBody.source()
                    while (!source.exhausted()) {
                        val line = source.readUtf8Line()
                        if (line != null) {
                            val jsonResponse = JSONObject(line)
                            if (jsonResponse.has("response")) {
                                val responseFragment = jsonResponse.getString("response")
                                if (responseFragment != lastResponse) {
                                    onResponse(responseFragment)
                                    lastResponse = responseFragment
                                }
                            }
                        }
                    }
                    onComplete()
                }
            }
        })
    }
}
