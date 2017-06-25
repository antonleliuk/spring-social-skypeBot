package org.springframework.social.botframework.api.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.social.botframework.api.dict.ActivityType;
import org.springframework.social.botframework.api.dict.AttachmentLayout;
import org.springframework.social.botframework.api.dict.TextFormat;
import org.springframework.social.botframework.api.json.ActivityTypeDeserializer;
import org.springframework.social.botframework.api.json.DictTypeSerializer;
import org.springframework.social.botframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * Basic communication type for BotFramework
 * @author Anton Leliuk
 */
public class Activity extends BaseMessage {

    /**
     * ContactAdded/Removed action
     */
    private String action;

    /**
     * Layout of multiple attachments
    */
    private AttachmentLayout attachmentLayout;

    private List<Attachment> attachments;

    /**
     * Channel specific payload
     */
    private Object channelData;

    /**
     * Conversation
     */
    private ConversationAccount conversation;

    /**
     * Entities Collection of Entity which contain metadata about this activity (each is typed)
     */
    private List<Map<String, Object>> entities;

    /**
     * Sender address
     */
    private ChannelAccount from;

    /**
     * The previous history of the channel was disclosed
     */
    private boolean historyDisclosed;

    /**
     * Value that indicates whether your bot is accepting, expecting, or ignoring user input after the message is
     * delivered to the client. One of these values: acceptingInput, expectingInput, ignoringInput.
     */
    private String inputHint;

    /**
     * Locale of the language that should be used to display text within the message, in the format <language>-<country>.
     * The channel uses this property to indicate the user's language, so that your bot may specify display strings
     * in that language. Default value is en-US.
     */
    private String locale = "en-US";

    /**
     * Addresses of added contacts
     */
    private List<ChannelAccount> membersAdded;

    /**
     * Addresses of removed contacts
     */
    private List<ChannelAccount> membersRemoved;


    private Object properties;

    /**
     * (Outbound to bot only) Bot's address that received the message 
     */
    private ChannelAccount recipient;

    /**
     * A ConversationReference object that defines a particular point in a conversation.
     */
    private ConversationReference relatesTo;

    /**
     * The original id this message is a response to
     */
    private String replyToId;

    /**
     * Text to be spoken by your bot on a speech-enabled channel. To control various characteristics of your bot's
     * speech such as voice, rate, volume, pronunciation, and pitch, specify this property
     * in @see https://msdn.microsoft.com/en-us/library/hh378377(v=office.14).aspx Speech Synthesis Markup Language (SSML) format.
     */
    private String speak;

    /**
     * A SuggestedActions object that defines the options from which the user can choose.
     */
    private SuggestedActions suggestedActions;

    /**
     * Text to display if you can't render cards
     */
    private String summary;

    /**
     * Content for the message
     */
    private String text;

    /**
     * Format of text fields
     */
    private TextFormat textFormat;

    /**
     * Conversations new topic name
     */
    private String topicName;

    /**
     * The type of the activity
     */
    @JsonSerialize(using = DictTypeSerializer.class)
    @JsonDeserialize(using = ActivityTypeDeserializer.class)
    private ActivityType type;

    public Activity createReplay(){
        Activity replay = new Activity();
        replay.setFrom(recipient);
        replay.setRecipient(from);
        replay.setConversation(conversation);
        replay.setServiceUrl(getServiceUrl());
        replay.setChannelId(getChannelId());
        return replay;
    }

    @JsonIgnore
    @Override
    public ActivityType getActivity() {
        return type;
    }

    public <C> Activity addAttachment(Attachment<C> attachment){
        attachments = CollectionUtils.add(attachments, attachment);
        return this;
    }

    public Activity typing(){
        this.type = ActivityType.typing;
        return this;
    }

    public Activity message(){
        this.type = ActivityType.message;
        return this;
    }

    public Activity attachmentLayout(AttachmentLayout layout){
        this.attachmentLayout = layout;
        return this;
    }

    public Activity text(String text){
        this.text = text;
        return this;
    }

    public Activity summary(String summary){
        this.summary = summary;
        return this;
    }

    public Activity textFormat(TextFormat format){
        this.textFormat = format;
        return this;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public AttachmentLayout getAttachmentLayout() {
        return attachmentLayout;
    }

    public void setAttachmentLayout(AttachmentLayout attachmentLayout) {
        this.attachmentLayout = attachmentLayout;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public Object getChannelData() {
        return channelData;
    }

    public void setChannelData(Object channelData) {
        this.channelData = channelData;
    }

    public ConversationAccount getConversation() {
        return conversation;
    }

    public void setConversation(ConversationAccount conversation) {
        this.conversation = conversation;
    }

    public List<Map<String, Object>> getEntities() {
        return entities;
    }

    public void setEntities(List<Map<String, Object>> entities) {
        this.entities = entities;
    }

    public ChannelAccount getFrom() {
        return from;
    }

    public void setFrom(ChannelAccount from) {
        this.from = from;
    }

    public boolean isHistoryDisclosed() {
        return historyDisclosed;
    }

    public void setHistoryDisclosed(boolean historyDisclosed) {
        this.historyDisclosed = historyDisclosed;
    }

    public String getInputHint() {
        return inputHint;
    }

    public void setInputHint(String inputHint) {
        this.inputHint = inputHint;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public List<ChannelAccount> getMembersAdded() {
        return membersAdded;
    }

    public void setMembersAdded(List<ChannelAccount> membersAdded) {
        this.membersAdded = membersAdded;
    }

    public List<ChannelAccount> getMembersRemoved() {
        return membersRemoved;
    }

    public void setMembersRemoved(List<ChannelAccount> membersRemoved) {
        this.membersRemoved = membersRemoved;
    }

    public Object getProperties() {
        return properties;
    }

    public void setProperties(Object properties) {
        this.properties = properties;
    }

    public ChannelAccount getRecipient() {
        return recipient;
    }

    public void setRecipient(ChannelAccount recipient) {
        this.recipient = recipient;
    }

    public ConversationReference getRelatesTo() {
        return relatesTo;
    }

    public void setRelatesTo(ConversationReference relatesTo) {
        this.relatesTo = relatesTo;
    }

    public String getReplyToId() {
        return replyToId;
    }

    public void setReplyToId(String replyToId) {
        this.replyToId = replyToId;
    }

    public String getSpeak() {
        return speak;
    }

    public void setSpeak(String speak) {
        this.speak = speak;
    }

    public SuggestedActions getSuggestedActions() {
        return suggestedActions;
    }

    public void setSuggestedActions(SuggestedActions suggestedActions) {
        this.suggestedActions = suggestedActions;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TextFormat getTextFormat() {
        return textFormat;
    }

    public void setTextFormat(TextFormat textFormat) {
        this.textFormat = textFormat;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }
}
