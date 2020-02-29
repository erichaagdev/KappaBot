package com.gorlah.kappabot.function.command;

/**
 * Used to bind a specific command prefix to a specific message source to facilitate integration with message sources
 * which may have certain requirements or limitations related to the message format, and which may be available only
 * under certain conditions.
 *
 * @see CommandFunction
 */
public interface CommandAdapter {

    /**
     * Returns the command prefix that messages from the source must begin with. The command prefix a source-specific
     * sequence of characters at the start of a message used to indicate the message is a command.
     *
     * @return the command prefix
     */
    String getCommandPrefix();

    /**
     * Returns the source that this command adapter is associated with. Only messages originating at the specified
     * source will be matched by this command adapter.
     *
     * @return the source identifier
     */
    String getSource();

    /**
     * Gets the output content type that this adapter supports.
     *
     * @return the supported output content type
     */
    String getContentType();

    /**
     * Returns the enabled status of this command adapter. When the command adapter is enabled, it may be used to
     * handle incoming requests from a certain source with a certain command prefix.
     *
     * @return true if this command adapter is enabled
     */
    boolean isEnabled();
}
