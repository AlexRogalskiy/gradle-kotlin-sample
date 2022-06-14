package io.nullables.api.playground.commons.asyncapi;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * This is the root document object for the API specification. It combines resource listing and API
 * declaration together into one document.
 *
 * @author Pavel Bodiachevskii
 * @version 2.0.0
 * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0/#A2SObject">AsyncAPI</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AsyncAPI {

    /**
     * Required.
     * <p>
     * Specifies the AsyncAPI Specification version being used. It can be used by tooling
     * Specifications and clients to interpret the version. The structure shall be
     * major.minor.patch, where patch versions must be compatible with the existing major.minor
     * tooling.
     * <p>
     * Typically patch versions will be introduced to address errors in the documentation, and
     * tooling should typically be compatible with the corresponding major.minor (1.0.*). Patch
     * versions will correspond to patches of this document.
     */
    @NonNull
    private final String asyncapi = "2.0.0";

    /**
     * Identifier of the application the AsyncAPI document is defining.
     * <p>
     * This field represents a unique universal identifier of the application the AsyncAPI document
     * is defining. It must conform to the URI format, according to RFC3986.
     * <p>
     * It is RECOMMENDED to use a URN to globally and uniquely identify the application during long
     * periods of time, even after it becomes unavailable or ceases to exist.
     */
    private String id;

    /**
     * A string representing the default content type to use when encoding/decoding a message's
     * payload. The value MUST be a specific media type (e.g. application/json). This value MUST be
     * used by schema parsers when the contentType property is omitted.
     * <p>
     * In case a message can't be encoded/decoded using this value, schema parsers MUST use their
     * default content type.
     */
    private String defaultContentType;

    /**
     * Required.
     * <p>
     * The available channels and messages for the API.
     * <p>
     * Holds the relative paths to the individual channel and their operations. Channel paths are
     * relative to servers.
     * <p>
     * Channels are also known as "topics", "routing keys", "event types" or "paths".
     */
    @NonNull
    private Map<String, ChannelItem> channels;
}
