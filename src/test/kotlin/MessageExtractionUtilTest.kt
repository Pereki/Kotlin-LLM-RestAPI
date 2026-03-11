import com.anthropic.models.messages.CacheCreation
import com.anthropic.models.messages.CitationCharLocation
import org.junit.Test
import com.anthropic.models.messages.Message
import com.anthropic.models.messages.Model
import com.anthropic.models.messages.StopReason
import com.anthropic.models.messages.TextBlock
import com.anthropic.models.messages.TextCitation
import com.anthropic.models.messages.Usage
import com.example.util.extractMessage
import java.net.CacheRequest
import kotlin.test.assertEquals

class MessageExtractionUtilTest {

    @Test
    fun `test if text gets extracted correctly`() {
        val extractedString = extractMessage(
            Message.builder().addContent(TextBlock.builder()
                .text("Hello World")
                .addCitation(
                    CitationCharLocation.builder()
                        .citedText("")
                        .documentIndex(0)
                        .documentTitle("")
                        .startCharIndex(0)
                        .endCharIndex(0)
                        .fileId("")
                        .build()
                )
                .build())
                .id("")
                .model(Model.CLAUDE_SONNET_4_6)
                .stopReason(StopReason.STOP_SEQUENCE)
                .stopSequence("")
                .usage(
                    Usage.builder()
                        .cacheCreation(CacheCreation.builder()
                            .ephemeral1hInputTokens(0)
                            .ephemeral5mInputTokens(0)
                            .build()
                        )
                        .cacheCreationInputTokens(0)
                        .cacheReadInputTokens(0)
                        .inferenceGeo("")
                        .inputTokens(0).outputTokens(0)
                        .serverToolUse(null)
                        .serviceTier(null)
                        .build()
                    )
                .build()
        );

        assertEquals(extractedString, "Hello World");
    }
}