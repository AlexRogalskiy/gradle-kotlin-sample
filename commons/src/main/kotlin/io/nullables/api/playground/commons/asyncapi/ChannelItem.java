package io.nullables.api.playground.commons.asyncapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Describes the operations available on a single channel.
 *
 * @author Pavel Bodiachevskii
 * @version 2.0.0
 * @see <a href="https://www.asyncapi.com/docs/specifications/2.0.0/#channelItemObject">ChannelItem</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelItem {

    /**
     * Allows for an external definition of this channel item. The referenced structure MUST be in
     * the format of a Channel Item Object. If there are conflicts between the referenced definition
     * and this Channel Item's definition, the behavior is undefined.
     */
    private String $ref;

    /**
     * An optional description of this channel item. CommonMark syntax can be used for rich text
     * representation.
     */
    private String description;
}
